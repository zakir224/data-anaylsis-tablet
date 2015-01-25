package com.nhf.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nhf.adapter.CustomSpinnerAdapter;
import com.nhf.icu.R;
import com.nhf.parameters.BaseParameter;
import com.nhf.reports.PatientReport;
import com.nhf.task.GetReport;
import com.nhf.task.IcuParameters;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Zakir on 8/16/2014.
 */
public class PodTimeSelection extends DialogFragment{

    public static final String PATIENT_ID = "patient_id";
    public static final String POD = "pod";
    Patient patient;
    private TextView patientId;
    private TextView patientName;
    private TextView patientAge;
    private Spinner spPod;
    private String[] timePick;
    private Spinner spTime;
    private ProgressDialog progDL;
    private String[] poDay;
    private Button confirmButton;
    Switch aSwitch;
    PatientRecord patientRecord = PatientRecord.getInstance();

    public PodTimeSelection() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_selection_dialog, null);
        patientId = (TextView) view.findViewById(R.id.select_patient_id);
        patientName = (TextView)view.findViewById(R.id.select_patient_name);
        patientAge = (TextView)view.findViewById(R.id.patientAge);
        confirmButton = (Button)view.findViewById(R.id.confirm);
        aSwitch = (Switch)view.findViewById(R.id.reportSwitch);
        spPod = (Spinner)view.findViewById(R.id.spPod);
        spTime = (Spinner)view.findViewById(R.id.spTime);
        timePick = getResources().getStringArray(R.array.timePick);
        CustomSpinnerAdapter aTime = new CustomSpinnerAdapter(getActivity(),android.R.layout.simple_list_item_single_choice, timePick);
        spTime.setAdapter(aTime);
        poDay = getResources().getStringArray(R.array.day);
        CustomSpinnerAdapter aDay = new CustomSpinnerAdapter(getActivity(),android.R.layout.simple_list_item_single_choice, poDay);
        spPod.setAdapter(aDay);
        patientId.setText(patientRecord.getPid());
        patientName.setText(patientRecord.getPatientName());
        patientAge.setText(String.valueOf(patientRecord.getAge()));

        addListeners();

        getDialog().setTitle("Time");

        return view;
    }

    private void addListeners() {
        // TODO Auto-generated method stub
        Log.d("icu_load","Initializing Patient List Listeners......");
        spTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                patientRecord.setTime_picked(arg0.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spPod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                patientRecord.setPod(arg0.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(aSwitch.isChecked()){

                    new GetReport(getActivity(),patientRecord.getPatient_id(),Integer.valueOf(patientRecord.getPod())).execute();
//                    Intent intent = new Intent(getActivity(), PatientReport.class);
//                    intent.putExtra(PATIENT_ID,String.valueOf(patientRecord.getPatient_id()));
//                    intent.putExtra(POD,patientRecord.getPod());
//                    startActivity(intent);
                } else {
                    Log.d("loading","loading");
                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                    postParameters.add(new BasicNameValuePair("time_picked", patientRecord.getTime_picked()));
                    postParameters.add(new BasicNameValuePair("patient_id", String.valueOf(patientRecord.getPatient_id())));
                    postParameters.add(new BasicNameValuePair("pod", patientRecord.getPod()));
                    postParameters.add(new BasicNameValuePair("time_id",
                            String.valueOf(IcuParameters.getTimeId(patientRecord.getTime_picked(),getResources().getStringArray(R.array.timePick) ))));

                    new CheckParameters().execute(postParameters, postParameters, postParameters);

                    Log.d("loading completed!!","loading completed");
                }
            }

        });
        Log.d("icu_load","Initializing Patient List listener Completed......");


       aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   spTime.setEnabled(false);
                   spTime.setBackgroundColor(getResources().getColor(R.color.even));

               }else {
                   spTime.setEnabled(true);
                   spTime.setBackgroundColor(getResources().getColor(android.R.color.white));
               }

           }
       });
    }



    public class CheckParameters extends AsyncTask< ArrayList<NameValuePair>, String, String> {
        String result;
        private String response;
        private boolean error;
        DataManipulationDialog dataManipulationDialog;

        @Override
        protected void onPreExecute(){
            dataManipulationDialog = new DataManipulationDialog();
            Bundle bundle = new Bundle();
            bundle.putCharSequence("title","Loading");
            bundle.putCharSequence("message","Loading Parameters");
            dataManipulationDialog.setArguments(bundle);
            dataManipulationDialog.show(getFragmentManager(),"parameter");
        }

        @Override
        protected String doInBackground(ArrayList<NameValuePair>... params) {
            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();

            try {
                Log.d("icu_load", "Checking Parameters......");
                response = CustomHttpClient.executeHttpPost(IcuParameters.GET_URL+"getMonitoringParams", params[0]);
                result = response.toString();
                Log.d("json", response.toString() + " " + response.length());

                if (response.length() == 3) {
                    Log.d("icu_load","No previous record found.creating new record......\n");
                    response = CustomHttpClient.executeHttpPost(IcuParameters.GET_URL+"insert_monitor", params[0]);
                    Log.d("icu_load","Loading new record......");
                    response = CustomHttpClient.executeHttpPost(IcuParameters.GET_URL+"getMonitoringParams",params[0]);
                    Log.d("result",response.toString());
                    if (response.length() == 3) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Internet connection error")
                                .setMessage("Connect to the internet and try again")
                                .setNeutralButton("Close",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dlg,
                                                                int sumthin) {
                                                Log.d("icu_load","Crashed closing the app......");
                                                Intent login = new Intent(
                                                        getActivity(), Login.class);
                                                login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(login);
                                            }
                                        }).show();
                    }
                    Log.d("icu_load","Loading new record done......\n");
                    Log.d("result",response.toString());
                    result = response.toString();
                }



            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
                dataManipulationDialog.dismiss();
                try{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Internet connection error")
                            .setMessage("Connect to the internet and try again")
                            .setNeutralButton("Close",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dlg,
                                                            int sumthin) {
                                            Log.d("icu_load","Crashed closing the app......");
                                            Intent login = new Intent(
                                                    getActivity(), Login.class);
                                            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(login);
                                        }
                                    }).show();
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            return result;
        }

        protected void onPostExecute(String result) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                Log.d("icu_load","Converting from JSON data......");
                IcuParameters.initializeParams(jsonArray);
                Log.d("icu_load","Converting from JSON data completed......");
                dataManipulationDialog.dismiss();
                Intent intent = new Intent(getActivity(),BaseParameter.class);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
                dataManipulationDialog.dismiss();
                new AlertDialog.Builder(getActivity())
                        .setTitle("Error Parsing data")
                        .setMessage("Please try again")
                        .setNeutralButton("Close",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dlg,
                                                        int sumthin) {
                                        Log.d("icu_load","Crashed closing the app......");
                                        Intent login = new Intent(
                                                getActivity(), Login.class);
                                        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(login);
                                    }
                                }).show();
            }
        }
    }
}

package com.nhf.app;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

import com.nhf.adapter.PatientListAdapter;
import com.nhf.icu.R;
import com.nhf.parameters.BaseParameter;
import com.nhf.task.IcuParameters;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends ListActivity implements DataManipulationDialog.DataManipulationDialogListener, SearchView.OnQueryTextListener {

	public JSONArray jsonArray;
	protected ArrayList<String> list;
	protected ArrayList<Integer> agel;
	protected ArrayList<String> name;
	protected EditText patientName;
	protected Spinner spPatient;
	protected Spinner spPod;
	protected ArrayAdapter<String> patientListAdapter;
	protected Button confirm;
	protected String result;
	private String[] timePick;
	private Spinner spTime;
	private ProgressDialog progDL;
	private String[] poDay;
    private ShareActionProvider shareActionProvider;
    EditText patientAge;
    PatientRecord patientRecord = PatientRecord.getInstance();
    GetPatientList getPatientList;
    ArrayList<Patient> patients = new ArrayList<Patient>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_list_custom);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                patientRecord.setDPid(patients.get(position).getPatientId());
                try {
                    patientRecord.setPatient_id(jsonArray.getJSONObject(position).getInt("patient_id"));
                    patientRecord.setWeight(jsonArray.getJSONObject(position).getDouble("weight"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                patientRecord.setPatientName(patients.get(position).getPatientName());
                patientRecord.setAge(patients.get(position).getPatientAge());
                PodTimeSelection editNameDialog = new PodTimeSelection();
                editNameDialog.show(fm, "fragment_edit_name");
            }
        });

		getPatientList = new GetPatientList();
        getPatientList.execute("","","");
//		addListeners();
	}

//	public void initializeUI() {
//        Log.d("icu_load","Initializing Patient List UI......");
//		patientName = (EditText) findViewById(R.id.patientName);
//        patientAge = (EditText) findViewById(R.id.patientAge);
//		confirm = (Button) findViewById(R.id.confirm);
//		spPatient = (Spinner) findViewById(R.id.patientList);
//		spTime = (Spinner) findViewById(R.id.spTime);
//		timePick = getResources().getStringArray(R.array.timePick);
//		ArrayAdapter<String> aTime = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_single_choice, timePick);
//		spTime.setAdapter(aTime);
//		patientName.setEnabled(false);
//		poDay = getResources().getStringArray(R.array.day);
//		spPod = (Spinner) findViewById(R.id.spPod);
//		ArrayAdapter<String> aDay = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_single_choice, poDay);
//		spPod.setAdapter(aDay);
//        Log.d("icu_load","Initializing Patient List UI completed......");
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.patient_list, menu);
        SearchManager searchManager = (SearchManager) getSystemService( Context.SEARCH_SERVICE );
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
	}

    public void onCrash(){
        Log.d("icu_load","Crashed closing the app......");
        Intent login = new Intent(
                PatientListActivity.this, Login.class);
        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
    }


	private void addListeners() {
		// TODO Auto-generated method stub
        Log.d("icu_load","Initializing Patient List Listeners......");
		spTime.setOnItemSelectedListener(new OnItemSelectedListener() {

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

		spPatient.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				try {
					Log.d("tested", "filled!");
					int index = arg0.getSelectedItemPosition();
					Toast.makeText(
							getBaseContext(),
							"Selected patient ID : "
									+ list.get(index).toString(),
							Toast.LENGTH_SHORT).show();
					Log.d("tested", "done!");
                    patientRecord.setPatientName(jsonArray.getJSONObject(index).getString("name"));
                    patientRecord.setPatient_id(jsonArray.getJSONObject(index).getInt("patient_id"));
                    patientRecord.setDPid(jsonArray.getJSONObject(index).getString("id"));
                    patientRecord.setAge(jsonArray.getJSONObject(index).getInt("age"));
                    patientRecord.setWeight(jsonArray.getJSONObject(index).getDouble("weight"));

//					selectedPatient = new Patient(jsonArray
//							.getJSONObject(index).getString("name"), jsonArray
//							.getJSONObject(index).getString("id"), jsonArray
//							.getJSONObject(index).getInt("patient_id"),
//							jsonArray.getJSONObject(index).getInt("age"));

					int age = jsonArray.getJSONObject(index).getInt("age");
					patientName.setText(patientRecord.getPatientName());
					patientAge.setText(String.valueOf(patientRecord.getAge()));



				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		spPod.setOnItemSelectedListener(new OnItemSelectedListener() {

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

		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
                Log.d("loading","loading");
                ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("time_picked", patientRecord.getTime_picked()));
                postParameters.add(new BasicNameValuePair("patient_id", String.valueOf(patientRecord.getPatient_id())));
                postParameters.add(new BasicNameValuePair("pod", patientRecord.getPod()));
                postParameters.add(new BasicNameValuePair("time_id",
                        String.valueOf(IcuParameters.getTimeId(patientRecord.getTime_picked(),getResources().getStringArray(R.array.timePick) ))));

				new CheckParameters().execute(postParameters, postParameters, postParameters);

                Log.d("loading completed!!","loading completed");
                //startActivity(intent);
			}

		});
        Log.d("icu_load","Initializing Patient List listener Completed......");
	}

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        getListView().setFilterText(query.toString());
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // this is your adapter that will be filtered
        if (TextUtils.isEmpty(newText))
        {
            getListView().clearTextFilter();
        }
        else
        {
            getListView().setFilterText(newText);
        }

        return true;
    }


    public class CheckParameters extends AsyncTask< ArrayList<NameValuePair>, String, String> {
        String result;
        private String response;
        private boolean error;
        DataManipulationDialog dataManipulationDialog;

        @Override
        protected void onPreExecute(){
//            progDL = ProgressDialog.show(PatientListActivity.this, "Loading", "Loading Parameters");
            //dataManipulationDialog = new DataManipulationDialog("Loading","Loading Parameters");
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
                Log.d("icu_load","Checking Parameters......");
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
                        new AlertDialog.Builder(PatientListActivity.this)
                                .setTitle("Internet connection error")
                                .setMessage("Connect to the internet and try again")
                                .setNeutralButton("Close",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dlg,
                                                                int sumthin) {
                                                Log.d("icu_load","Crashed closing the app......");
                                                Intent login = new Intent(
                                                        PatientListActivity.this, Login.class);
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
                    new AlertDialog.Builder(PatientListActivity.this)
                            .setTitle("Internet connection error")
                            .setMessage("Connect to the internet and try again")
                            .setNeutralButton("Close",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dlg,
                                                            int sumthin) {
                                            Log.d("icu_load","Crashed closing the app......");
                                            Intent login = new Intent(
                                                    PatientListActivity.this, Login.class);
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
                Intent intent = new Intent(PatientListActivity.this,BaseParameter.class);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
                dataManipulationDialog.dismiss();
                new AlertDialog.Builder(PatientListActivity.this)
                        .setTitle("Error Parsing data")
                        .setMessage("Please try again")
                        .setNeutralButton("Close",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dlg,
                                                        int sumthin) {
                                        Log.d("icu_load","Crashed closing the app......");
                                        Intent login = new Intent(
                                                PatientListActivity.this, Login.class);
                                        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(login);
                                    }
                                }).show();
            }
        }

    }


    public class GetPatientList extends AsyncTask<String, String, String> {

        private String response;
        private boolean error;
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        DataManipulationDialog dataManipulationDialog;
        @Override
        protected void onPreExecute(){
            dataManipulationDialog = new DataManipulationDialog();
            Bundle bundle = new Bundle();
            bundle.putCharSequence("title","Loading......");
            bundle.putCharSequence("message","Loading Patient List");
            dataManipulationDialog.setArguments(bundle);
            dataManipulationDialog.show(getFragmentManager(),"patient");
        }

        protected String doInBackground(String... urls) {


            postParameters.add(new BasicNameValuePair("user", "admin"));
			/* call executeHttpPost method passing necessary parameters */
            try {
                response = CustomHttpClient.executeHttpPost(
                        IcuParameters.GET_URL+"getPatientList",
                        postParameters);

                if(isCancelled())
                    cancel(true);
                result = response.toString();

            } catch (Exception e) {
                Log.e("HTTP_ERROR", "Connection timeout!!" + e.toString());
                error = true;
            }

            return result;
        }

        protected void onPostExecute(String result) {

            try {
                if (!error) {
                    try {
                        list = new ArrayList<String>();
                        Log.d("patients", result.toString());
                        jsonArray = new JSONArray(result);
                        Log.d("patients", "Parsing patient list!");
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                               // list.add(jsonArray.getJSONObject(i).getString("id"));
                                patients.add(new Patient(jsonArray.getJSONObject(i).getString("id"),
                                        jsonArray.getJSONObject(i).getString("name"),
                                        jsonArray.getJSONObject(i).getInt("age")));
                            }
                        }
                    } catch (Exception e) {
                        Log.e("log_tag", "Error parsing data " + e.toString());
                    } finally {
//                        patientListAdapter = new ArrayAdapter<String>(getBaseContext(),
//                                android.R.layout.simple_list_item_single_choice, list);
//                        spPatient.setAdapter(patientListAdapter);
                        dataManipulationDialog.dismiss();
                        setListAdapter(new PatientListAdapter(getBaseContext(), android.R.layout.simple_list_item_1, patients));
                       // listView.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,list));

                    }


                } else {
                    dataManipulationDialog.dismiss();
                    new AlertDialog.Builder(PatientListActivity.this)
                            .setTitle("HTTP error")
                            .setMessage("Connect to the internet and try again")
                            .setNeutralButton("Close",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dlg,
                                                            int sumthin) {
                                            Log.d("icu_load","Crashed closing the app......");
                                            Intent login = new Intent(
                                                    PatientListActivity.this, Login.class);
                                            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(login);
                                        }
                                    }).show();
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("HTTP_ERROR", "Error in http connection!!" + e.toString());
                dataManipulationDialog.dismiss();
            }

        }

    }

    @Override
    public void onNegativeClick(DialogFragment dialogFragment) {
        getPatientList.cancel(true);
        Intent login = new Intent(PatientListActivity.this, Login.class);
        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
        Log.d("patient","Cancelled");
    }

}

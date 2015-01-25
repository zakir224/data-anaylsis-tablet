package com.nhf.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.RecoverySystem;
import android.util.Log;
import android.widget.Toast;

import com.nhf.app.CustomHttpClient;
import com.nhf.parameters.BaseForm;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by ZAKIR_224 on 2/21/14.
 */
public class UpdateParameters extends AsyncTask<Context,Integer,String> implements ProgressDialog.OnCancelListener{
    private ProgressDialog progDL;
    private Context context;
    BaseForm baseForm;
    int success = 0;
    public UpdateParameters(Context context,BaseForm baseForm){
        super();
        this.context = context;
        this.baseForm = baseForm;
    }

    protected void onPreExecute(){

        progDL = ProgressDialog.show(context,"Updating", "Updating Parameters");
        progDL.setCancelable(true);
        progDL.setOnCancelListener(this);
    }

    @Override
    protected String doInBackground(Context... arrayLists) {
        ArrayList<NameValuePair> postParameters;
        String response = "";
//        MonitoringParams.getInstance().grabValues();
//        BloodGas.getInstance().grabValues();
//        Ventilator.getInstance().grabValues();
//        Output.getInstance().grabValues();
//        IvParams.getInstance().grabValues();
        baseForm.grabValues();
        postParameters = IcuParameters.getParameters(context);
        //setParameters();
        try {
            Log.d("update","Commit update");
            Log.d("value",postParameters.toString());
            response  = CustomHttpClient.executeHttpPost(
                    IcuParameters.GET_URL+"update_monitor",
                    postParameters);
            Log.d("update","update status"+response.toString());
            if(response.toString().contains("0"))
              success = 1;

            response = CustomHttpClient.executeHttpPost(
                    IcuParameters.GET_URL+"getMonitoringParams",
                    postParameters);
            Log.d("update","data loaded");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String result){
        Log.d("RESULT",result.toString()+" success: "+ success);
        //JSONArray jsonArray = null;
        try {
            if(success==1) {
                Toast.makeText(baseForm.getActivity(),"Update successful",Toast.LENGTH_LONG).show();
            JSONArray jsonArray = new JSONArray(result);
            IcuParameters.initializeParams(jsonArray);
            baseForm.populateViews();
            } else
                Toast.makeText(baseForm.getActivity(),"Update failed",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        progDL.dismiss();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        progDL.dismiss();
        cancel(true);
    }
}

//    protected void onPostExecute(String result) {
//        if (!error) {
//            if (result.equals("")) {
//                Toast.makeText(getActivity(),
//                        "Data inserted successfully", Toast.LENGTH_LONG)
//                        .show();
//            } else {
//                Toast.makeText(getActivity(), "Data insertion failed",
//                        Toast.LENGTH_LONG).show();
//            }
//            Log.d("result", result);
//            progDL.dismiss();
//        } else {
//            progDL.dismiss();
//            new AlertDialog.Builder(getActivity())
//                    .setTitle("Internet connection error")
//                    .setMessage(
//                            "Updating data failed!\nConnect to the internet and try again")
//                    .setNeutralButton("Close",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dlg,
//                                                    int sumthin) {
//                                    // do nothing it will close on its own
//
//                                    Intent login = new Intent(
//                                            getActivity(), Login.class);
//                                    login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(login);
//                                }
//                            }).show();
//        }
//        patient.setPo2(Double.valueOf(po2_etxt.getText().toString()));
//    }
//

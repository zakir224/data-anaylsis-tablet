package com.nhf.task;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.nhf.app.CustomHttpClient;
import com.nhf.app.DataManipulationDialog;
import com.nhf.app.PatientRecord;
import com.nhf.reports.PatientReport;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Zakir on 8/17/2014.
 */
public class GetReport extends AsyncTask<Void, Void, Void> implements DataManipulationDialog.DataManipulationDialogListener{

    private boolean error;
    private String response;
    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    DataManipulationDialog dataManipulationDialog;
    Context context;
    //private final int patientId;
    private int patient_id;
    private int pod;

    public GetReport(Activity context,int patientId,int pod) {
        this.context = context;
        this.patient_id = patientId;
        this.pod = pod;
    }

    @Override
    protected void onPreExecute(){
        dataManipulationDialog = new DataManipulationDialog();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("title","Loading......");
        bundle.putCharSequence("message","Loading Patient List");
        dataManipulationDialog.setArguments(bundle);
        dataManipulationDialog.show(((Activity)context).getFragmentManager(),"patient");
    }

    protected Void doInBackground(Void... params){


        postParameters.add(new BasicNameValuePair("patient_id", String.valueOf(patient_id)));
        postParameters.add(new BasicNameValuePair("pod", String.valueOf(pod)));
			/* call executeHttpPost method passing necessary parameters */
        try {
            response = CustomHttpClient.executeHttpPost(
                    IcuParameters.GET_URL + "getReport",
                    postParameters);

                if(isCancelled())
                    cancel(true);

        } catch (Exception e) {
            Log.e("HTTP_ERROR", "Connection timeout!!" + e.toString());
            error = true;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            PatientRecord.getInstance().setJsonArray(jsonArray);
            dataManipulationDialog.dismiss();

            context.startActivity(new Intent(context, PatientReport.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onNegativeClick(DialogFragment dialogFragment) {

    }



}


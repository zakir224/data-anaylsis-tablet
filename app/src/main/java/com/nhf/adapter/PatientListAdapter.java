package com.nhf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhf.app.Patient;
import com.nhf.app.PatientListActivity;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zakir on 7/14/2014.
 */
public class PatientListAdapter extends ArrayAdapter<Patient> {

    List<Patient> patients;
    Context context;

    public PatientListAdapter(Context context, int resource, List<Patient> objects) {
        super(context, resource, objects);
        this.context=context;
        this.patients=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_tem,parent,false);
        TextView patientId = (TextView)row.findViewById(R.id.patient_id);
        TextView patientName = (TextView)row.findViewById(R.id.patient_name);
        //TextView patientAge = (TextView)row.findViewById(R.id.patient_age);
        patientId.setText(((Patient)patients.get(position)).getPatientId());
        patientName.setText(((Patient)patients.get(position)).getPatientName());
        //patientAge.setText(IOUtil.getString(((Patient) patients.get(position)).getPatientAge()));

//        if(position%2==0){
//            row.setBackgroundColor(context.getResources().getColor(R.color.background));
//        }
//        else
//            row.setBackgroundColor(context.getResources().getColor(R.color.even));
        //patientId.setTextColor(context.getResources().getColor(android.R.color.white));
        //patientName.setTextColor(context.getResources().getColor(android.R.color.white));
        //patientAge.setTextColor(context.getResources().getColor(android.R.color.white));
        patientId.setTextSize(16.0f);
        return row;
    }
}

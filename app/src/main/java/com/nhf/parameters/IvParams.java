package com.nhf.parameters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.http.NameValuePair;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nhf.app.Parameters;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;

public class IvParams extends BaseForm implements Parameters{

    public static final String ARG_SECTION_NUMBER = "section_number";

    EditText transfus_etxt;
    EditText inffus_etxt;
    EditText bs_etxt;
    EditText ns_etxt;
    EditText albumin_etxt;
    EditText total_etxt;
    EditText dname_etxt;
    private static IvParams ivParams;

    public IvParams(){

    }

    public static BaseForm getInstance(){
        if(ivParams==null)
            ivParams = new IvParams();
        return ivParams;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.iv, container,false);

        grabUiReferenes();
        populateViews();
        grabValues();
        return rootView;
    }

    @Override
    public void populateViews() {

        //getActivity().setTitle("[Iv] Patient:" + patientRecord.getPatientName() + " Day: " + patientRecord.getPod() + " Time: " + patientRecord.getTime_picked() + " Patient ID: " + patientRecord.getPatient_id());

        transfus_etxt.setText(String.valueOf(patientRecord.getTransfus()));
        inffus_etxt.setText(String.valueOf(patientRecord.getInfuss()));
        bs_etxt.setText(String.valueOf(patientRecord.getB_s()));
        ns_etxt.setText(String.valueOf(patientRecord.getN_s()));
        albumin_etxt.setText(String.valueOf(patientRecord.getAlbumin()));
        total_etxt.setText(String.valueOf(patientRecord.getTotal4()));
        dname_etxt.setText(String.valueOf(patientRecord.getDoctor_name3()));


    }

    @Override
    public void grabValues() {

        Log.d("update", "graveValues of iv parameter");

        patientRecord.setTransfus(IOUtil.getInt(transfus_etxt.getText().toString()));
        patientRecord.setInfuss(IOUtil.getInt(inffus_etxt.getText().toString()));
        patientRecord.setB_s(IOUtil.getInt(bs_etxt.getText().toString()));
        patientRecord.setN_s(IOUtil.getInt(ns_etxt.getText().toString()));
        patientRecord.setAlbumin(IOUtil.getDouble(albumin_etxt.getText().toString()));
        patientRecord.setTotal4(IOUtil.getDouble(total_etxt.getText().toString()));
        patientRecord.setDoctor_name3(dname_etxt.getText().toString());
    }

    @Override
    public void grabUiReferenes() {

        transfus_etxt = (EditText) rootView.findViewById(R.id.transfus_etxt);
        inffus_etxt = (EditText) rootView.findViewById(R.id.inffus_etxt);
        bs_etxt = (EditText) rootView.findViewById(R.id.bs_etxt);
        ns_etxt = (EditText) rootView.findViewById(R.id.ns_etxt);
        albumin_etxt = (EditText) rootView.findViewById(R.id.albumin_etxt);
        total_etxt = (EditText) rootView.findViewById(R.id.total_etxt);
        dname_etxt = (EditText) rootView.findViewById(R.id.dname_etxt);

    }

    @Override
    public void prepareListeners() {

    }
}


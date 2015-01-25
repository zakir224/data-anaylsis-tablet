package com.nhf.parameters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nhf.app.CustomHttpClient;
import com.nhf.app.FragmentLifecycle;
import com.nhf.app.Login;
import com.nhf.app.Parameters;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Ventilator extends BaseForm implements FragmentLifecycle{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    EditText mode_et;
    EditText rate_et;
    EditText rsbi_et;
    EditText pip_et;
    EditText tv_et;
    EditText peep_et;
    EditText map_et;
    EditText ti_et;
    EditText psupp_et;
    EditText fio2_et;
    EditText ie1_et;
    EditText ie2_et;
    EditText vent_comment;
    ProgressDialog progDL;
    private Calendar today = Calendar.getInstance();
    private ArrayList<NameValuePair> postParameters;
    HashMap<String, Object> ventilatorMap;

    private static Ventilator ventilator;

    public Ventilator() {
        // TODO Auto-generated constructor stub
    }

    public static BaseForm getInstance(){
        if(ventilator==null)
            ventilator = new Ventilator();
        return ventilator;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(
                R.layout.ventilator, container, false);

        grabUiReferenes();
        populateViews();
        grabValues();
        prepareListeners();

        return rootView;
    }

    @Override
    public void populateViews() {
        //getActivity().setTitle("[Ventilator] Patient:" + patientRecord.getPatientName() + " Day: " + patientRecord.getPod() + " Time: " + patientRecord.getTime_picked() + " Patient ID: " + patientRecord.getPatient_id());
        // getMode() or getMode_text() ???
        mode_et.setText(String.valueOf(patientRecord.getMode()));
        rate_et.setText(String.valueOf(patientRecord.getRate()));
        rsbi_et.setText(String.valueOf(patientRecord.getRsbi()));
         pip_et.setText(String.valueOf(patientRecord.getPip()));
          tv_et.setText(String.valueOf(patientRecord.getTv()));
        peep_et.setText(String.valueOf(patientRecord.getPeep()));
         map_et.setText(String.valueOf(patientRecord.getMap()));
          ti_et.setText(String.valueOf(patientRecord.getTi()));
       psupp_et.setText(String.valueOf(patientRecord.getPsupp()));
        fio2_et.setText(String.valueOf(patientRecord.getFio2()));
         ie1_et.setText(String.valueOf(patientRecord.getI()));
         ie2_et.setText(String.valueOf(patientRecord.getE()));
   vent_comment.setText(String.valueOf(patientRecord.getVent_comment()));
    }

    @Override
    public void grabValues() {

        Log.d("update","graveValues of ventilator");

        //EditText mode_et;//setMode_text() or setMode()

        patientRecord.setMode(mode_et.getText().toString());
        patientRecord.setRate(IOUtil.getInt(rate_et.getText().toString()));
        patientRecord.setRsbi(IOUtil.getInt(rsbi_et.getText().toString()));
        patientRecord.setPip(IOUtil.getInt(pip_et.getText().toString()));
        patientRecord.setTv(IOUtil.getInt(tv_et.getText().toString()));
        patientRecord.setPeep(IOUtil.getInt(peep_et.getText().toString()));
        patientRecord.setMap(IOUtil.getInt(map_et.getText().toString()));
        patientRecord.setTi(IOUtil.getDouble(ti_et.getText().toString()));
        patientRecord.setPsupp(IOUtil.getInt(psupp_et.getText().toString()));
        patientRecord.setFio2(IOUtil.getDouble(fio2_et.getText().toString()));
        patientRecord.setI(IOUtil.getInt(ie1_et.getText().toString()));
        patientRecord.setE(IOUtil.getDouble(ie2_et.getText().toString()));
        patientRecord.setVent_comment(String.valueOf(vent_comment.getText().toString()));
    }

    @Override
    public void grabUiReferenes() {
        mode_et =       (EditText) rootView.findViewById(R.id.mode_etxt);
        rate_et =       (EditText) rootView.findViewById(R.id.rate_etxt);
        rsbi_et =       (EditText) rootView.findViewById(R.id.RSBI_etxt);
        pip_et =        (EditText) rootView.findViewById(R.id.PIP_etxt);
        tv_et =         (EditText) rootView.findViewById(R.id.TV_etxt);
        peep_et =       (EditText) rootView.findViewById(R.id.PEEP_etxt);
        map_et =        (EditText) rootView.findViewById(R.id.MAP_etxt);
        ti_et =         (EditText) rootView.findViewById(R.id.TI_etxt);
        psupp_et =      (EditText) rootView.findViewById(R.id.PSUPP_etxt);
        fio2_et =       (EditText) rootView.findViewById(R.id.FiO2_etxt);
        ie1_et =        (EditText) rootView.findViewById(R.id.IE1_etxt);
        ie2_et =        (EditText) rootView.findViewById(R.id.IE2_etxt);
        vent_comment =  (EditText) rootView.findViewById(R.id.vent_comment);

    }

    @Override
    public void prepareListeners() {
           fio2_et.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

               }

               @Override
               public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    patientRecord.setFio2(IOUtil.getDouble(fio2_et.getText().toString()));
               }

               @Override
               public void afterTextChanged(Editable editable) {

               }
           });
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {

    }
}
package com.nhf.parameters;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;

import com.nhf.adapter.CustomSpinnerAdapter;
import com.nhf.app.FragmentLifecycle;
import com.nhf.app.Parameters;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;
import com.nhf.task.UpdateParameters;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class BloodGas extends BaseForm implements FragmentLifecycle, TextWatcher {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */ 
	public static final String ARG_SECTION_NUMBER = "section_number";
	Spinner type_spnr;
	private EditText ph_etxt;
	private EditText pco2_etxt;
	private EditText po2_etxt;
	private EditText hco3_etxt;
	private EditText sat_etxt;
	private EditText be_etxt;
	private EditText hb_etxt;
	private EditText na_etxt;
	private EditText k_etxt;
	private EditText cl_etxt;
	private EditText anion_etxt;
	private EditText aado2_etxt;
	private EditText lact_etxt;
	private EditText ca_mg_etxt;
	private EditText glu_etxt;
	ProgressDialog progDL;
	private Calendar today = Calendar.getInstance();
    Button saveBtn;
    View rootView;
    private static BloodGas bloodGas;

    public BloodGas(){

    }

	public static BaseForm getInstance(){
        if(bloodGas==null)
            bloodGas = new BloodGas();

        return bloodGas;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        rootView = inflater.inflate(
				R.layout.blood_gas_bio, container, false);
        saveBtn = (Button)getActivity().findViewById(R.id.saveBtn);

        grabUiReferenes();
        populateViews();
        prepareListeners();

		return rootView;
	}

    @Override
    public void populateViews() {
        //getActivity().setTitle("[Blood Gas] Patient:" + patientRecord.getPatientName() + " Day: " + patientRecord.getPod() + " Time: " + patientRecord.getTime_picked() + " Patient ID: " + patientRecord.getPatient_id());
        type_spnr.setSelection(patientRecord.getType());
        ph_etxt.setText(String.valueOf(patientRecord.getPh()));
        pco2_etxt.setText(String.valueOf(patientRecord.getPco2()));
        po2_etxt.setText(String.valueOf(patientRecord.getPo2()));
        hco3_etxt.setText(String.valueOf(patientRecord.getHco3()));
        sat_etxt.setText(String.valueOf(patientRecord.getSat()));
        be_etxt.setText(String.valueOf(patientRecord.getBe()));
        hb_etxt.setText(String.valueOf(patientRecord.getHb()));
        na_etxt.setText(String.valueOf(patientRecord.getNa()));
        k_etxt.setText(String.valueOf(patientRecord.getK()));
        cl_etxt.setText(String.valueOf(patientRecord.getCl()));
        anion_etxt.setText(String.valueOf(patientRecord.getAnion_gp()));
        aado2_etxt.setText(String.valueOf(patientRecord.getAado2()));
        lact_etxt.setText(String.valueOf(patientRecord.getLact()));
        ca_mg_etxt.setText(String.valueOf(patientRecord.getCa()));
        glu_etxt.setText(String.valueOf(patientRecord.getGlu()));
    }

    @Override
    public void grabValues() {
        Log.d("update", "Bloodgas GrabValues()");
        patientRecord.setType(type_spnr.getSelectedItemPosition());
        patientRecord.setPh(IOUtil.getDouble(ph_etxt.getText().toString()));
        patientRecord.setPco2(IOUtil.getDouble(pco2_etxt.getText().toString()));
        patientRecord.setPo2(IOUtil.getDouble(po2_etxt.getText().toString()));
        patientRecord.setHco3(IOUtil.getDouble(hco3_etxt.getText().toString()));
        patientRecord.setSat(IOUtil.getDouble(sat_etxt.getText().toString()));
        patientRecord.setBe(IOUtil.getDouble(be_etxt.getText().toString()));
        patientRecord.setHb(IOUtil.getDouble(hb_etxt.getText().toString()));
        patientRecord.setNa(IOUtil.getDouble(na_etxt.getText().toString()));
        patientRecord.setK(IOUtil.getDouble(k_etxt.getText().toString()));
        patientRecord.setCl(IOUtil.getDouble(cl_etxt.getText().toString()));
        patientRecord.setAnion_gp(IOUtil.getDouble(anion_etxt.getText().toString()));
        patientRecord.setAado2(IOUtil.getDouble(aado2_etxt.getText().toString()));
        patientRecord.setLact(IOUtil.getDouble(lact_etxt.getText().toString()));
        patientRecord.setCa(IOUtil.getDouble(ca_mg_etxt.getText().toString()));
        patientRecord.setGlu(IOUtil.getDouble(glu_etxt.getText().toString()));
        Log.d("update aa class", IOUtil.getString(patientRecord.getAado2()));
    }

    @Override
    public void grabUiReferenes() {
        type_spnr =(Spinner)rootView.findViewById(R.id.type_blood_gas_spnr);
		type_spnr.setAdapter(new CustomSpinnerAdapter(getActivity(),android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.blood_type)));
		ph_etxt = (EditText) rootView.findViewById(R.id.ph_e_txt);
		pco2_etxt = (EditText) rootView.findViewById(R.id.pco2_e_txt);
		po2_etxt = (EditText) rootView.findViewById(R.id.po2_e_txt);
		hco3_etxt = (EditText) rootView.findViewById(R.id.hco3_e_txt);
		sat_etxt = (EditText) rootView.findViewById(R.id.sat_e_txt);
		be_etxt = (EditText) rootView.findViewById(R.id.be_e_txt);
		hb_etxt = (EditText) rootView.findViewById(R.id.hb_e_txt);
		na_etxt = (EditText) rootView.findViewById(R.id.na_e_txt);
		k_etxt = (EditText) rootView.findViewById(R.id.k_e_txt);
		cl_etxt = (EditText) rootView.findViewById(R.id.cl_e_txt);
		anion_etxt = (EditText) rootView.findViewById(R.id.anion_e_txt);
		aado2_etxt = (EditText) rootView.findViewById(R.id.aado2_e_txt);
		lact_etxt = (EditText) rootView.findViewById(R.id.lact_e_txt);
		ca_mg_etxt = (EditText) rootView.findViewById(R.id.ca_mg_e_txt);
		glu_etxt = (EditText) rootView.findViewById(R.id.glu_e_txt);
    }

    @Override
    public void prepareListeners() {
        pco2_etxt.addTextChangedListener(this);
        po2_etxt.addTextChangedListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
       //populateViews();
    }

    @Override
    public void onPauseFragment() {
        Log.i("update", "BloodGas onPauseFragment()");
        //Toast.makeText(getActivity(), "onPauseFragment():" + "update", Toast.LENGTH_SHORT).show();
        this.onPause();
    }

    @Override
    public void onResumeFragment() {
        Log.i("update", "BloodGas onResumeFragment()");
        //this.onResume();
        grabUiReferenes();
        populateViews();
//        Toast.makeText(getActivity(), "onResumeFragment():" + "update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
          setAado2();
    }

    private void setAado2() {
        double fio2 = patientRecord.getFio2();
        double pco2 = IOUtil.getDouble(pco2_etxt.getText().toString());
        double po2 = IOUtil.getDouble(po2_etxt.getText().toString());

        double aado2 = (fio2*(713-(pco2/.8)))-po2;

        aado2_etxt.setText(IOUtil.getString(aado2%.2f));
    }

    @Override
    public void afterTextChanged(Editable editable) {
        patientRecord.setAado2(IOUtil.getDouble(aado2_etxt.getText().toString()));
    }
}
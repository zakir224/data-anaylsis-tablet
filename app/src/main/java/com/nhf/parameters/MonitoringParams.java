package com.nhf.parameters;

import java.util.Calendar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.nhf.adapter.CustomSpinnerAdapter;
import com.nhf.app.FragmentLifecycle;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;

public class MonitoringParams extends BaseForm implements FragmentLifecycle{
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
    Spinner rhy;
    Spinner peri;
    Spinner ppl;
    Spinner ppr;


    String sp02 = "";
    String etc02 = "";
    String Remarks;

    private EditText spo2_et;
    private EditText etco2_et;
    private EditText hrmt_et;
    private EditText sabp_et;
    private EditText dabp_et;
    private EditText mabp_et;
    private EditText snibp_et;
    private EditText pvr_et;
    private EditText dnibp_et;
    private EditText mnibp_et;
    private EditText ptemp_et;
    private EditText stemp_et;
    private EditText ctemp_et;
    private EditText cvp1_et;
    private EditText cvp2_et;
    private EditText svr_et;
    private EditText la_et;
    private EditText pap_et;
    private EditText rr_et;
    private EditText co_et;
    private EditText ci_et;
    private EditText event;
    private EditText comments;
	private Calendar today = Calendar.getInstance();
    boolean lock;
	ProgressDialog progDL;
    View rootView;

    private static MonitoringParams monitoringParams;


    public static BaseForm getInstance(){
        if(monitoringParams==null){
            monitoringParams = new MonitoringParams();
        }
        return monitoringParams;
    }

	public MonitoringParams() { 
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	     rootView = inflater.inflate(R.layout.monitoring, container,
				false);


        grabUiReferenes();
        populateViews();
        grabValues();
        prepareListeners();

		return rootView;
	}

	private void setMabp() {
		if (!TextUtils.isEmpty(sabp_et.getText().toString())
				&& !TextUtils.isEmpty(dabp_et.getText().toString())) {
			int s = Integer.valueOf(sabp_et.getText().toString());
			int d = Integer.valueOf(dabp_et.getText().toString());
			double mabp = ((s - d) / 3) + d;
			mabp_et.setText(String.valueOf(mabp));
		} else if (!TextUtils.isEmpty(sabp_et.getText().toString())
				|| !TextUtils.isEmpty(dabp_et.getText().toString())) {

			mabp_et.setText("");
		}
	}

    @Override
    public void grabValues() {
        Log.d("update","Monitor GrabValues()");
        patientRecord.setSpo2(IOUtil.getInt(spo2_et.getText().toString()));
        patientRecord.setEtco2(IOUtil.getDouble(etco2_et.getText().toString()));
        patientRecord.setHr_mt(IOUtil.getInt(hrmt_et.getText().toString()));
        patientRecord.setRhytm(rhy.getSelectedItemPosition());
        patientRecord.setPeri(peri.getSelectedItemPosition());
        patientRecord.setPpr(ppr.getSelectedItemPosition());
        patientRecord.setPpl(ppl.getSelectedItemPosition());
        patientRecord.setS_abp(IOUtil.getInt(sabp_et.getText().toString()));
        patientRecord.setD_abp(IOUtil.getInt(dabp_et.getText().toString()));
        patientRecord.setM_abp(IOUtil.getDouble(mabp_et.getText().toString()));
        patientRecord.setS_nibp(IOUtil.getInt(snibp_et.getText().toString()));
        patientRecord.setD_nibp(IOUtil.getInt(dnibp_et.getText().toString()));
        patientRecord.setM_nibp(IOUtil.getInt(mnibp_et.getText().toString()));
        patientRecord.setP_temp(IOUtil.getDouble(ptemp_et.getText().toString()));
        patientRecord.setS_temp(IOUtil.getDouble(stemp_et.getText().toString()));
        patientRecord.setC_temp(IOUtil.getDouble(ctemp_et.getText().toString()));
        patientRecord.setCvp(IOUtil.getInt(cvp1_et.getText().toString()));
        patientRecord.setSvr(svr_et.getText().toString());
        patientRecord.setPvr(pvr_et.getText().toString());
        patientRecord.setLa(IOUtil.getInt(la_et.getText().toString()));
        patientRecord.setPap(IOUtil.getInt(pap_et.getText().toString()));
        patientRecord.setRr(IOUtil.getDouble(rr_et.getText().toString()));
        patientRecord.setCo(IOUtil.getDouble(co_et.getText().toString()));
        patientRecord.setCi(IOUtil.getDouble(ci_et.getText().toString()));
        patientRecord.setEvents(String.valueOf(event.getText().toString()));
        patientRecord.setComments(String.valueOf(comments.getText().toString()));
    }

    @Override
    public void populateViews() {

        if(isAdded()){
            //        getActivity().setTitle("[Monitoring] Patient:" + patientRecord.getPatientName() + " Day: " + patientRecord.getPod() + " Time: " + patientRecord.getTime_picked() + " Patient ID: " + patientRecord.getPatient_id());
            rhy.setAdapter(new CustomSpinnerAdapter(getActivity(), android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.rhythm)));
            peri.setAdapter(new CustomSpinnerAdapter(getActivity(),android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.peri)));
            ppl.setAdapter(new CustomSpinnerAdapter(getActivity(),android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.pprl)));
            ppr.setAdapter(new CustomSpinnerAdapter(getActivity(),android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.pprl)));
            rhy.setSelection(patientRecord.getRhytm());
            peri.setSelection(patientRecord.getPeri());
            ppr.setSelection(patientRecord.getPpr());
            ppl.setSelection(patientRecord.getPpl());

            spo2_et.setText(String.valueOf(patientRecord.getSpo2()));                   /*put the values of monitoring params into the UI*/
            etco2_et.setText(String.valueOf(patientRecord.getEtco2()));
            hrmt_et.setText(String.valueOf(patientRecord.getHr_mt()));
            etco2_et.setText(String.valueOf(patientRecord.getEtco2()));
            sabp_et.setText(String.valueOf(patientRecord.getS_abp()));
            dabp_et.setText(String.valueOf(patientRecord.getD_abp()));
            mabp_et.setText(String.valueOf(patientRecord.getM_abp()));
            snibp_et.setText(String.valueOf(patientRecord.getS_nibp()));                   /*put the values of monitoring params into the UI*/
            event.setText(String.valueOf(patientRecord.getEvents()));
            pvr_et.setText(String.valueOf(patientRecord.getPvr()));
            dnibp_et.setText(String.valueOf(patientRecord.getD_nibp()));
            mnibp_et.setText(String.valueOf(patientRecord.getM_nibp()));
            ptemp_et.setText(String.valueOf(patientRecord.getP_temp()));
            stemp_et.setText(String.valueOf(patientRecord.getS_temp()));
            ctemp_et.setText(String.valueOf(patientRecord.getC_temp()));                   /*put the values of monitoring params into the UI*/
            cvp1_et.setText(String.valueOf(patientRecord.getCvp()));
            svr_et.setText(String.valueOf(patientRecord.getSvr()));
            la_et.setText(String.valueOf(patientRecord.getLa()));
            pap_et.setText(String.valueOf(patientRecord.getPap()));
            rr_et.setText(String.valueOf(patientRecord.getRr()));
            co_et.setText(String.valueOf(patientRecord.getCo()));
            ci_et.setText(String.valueOf(patientRecord.getCi()));
            comments.setText(String.valueOf(patientRecord.getComments()));
        }

    }


    @Override
    public void grabUiReferenes() {


        rhy = (Spinner) rootView.findViewById(R.id.Rhythm_spnr);
        peri = (Spinner) rootView.findViewById(R.id.Peri_spnr);
        ppl = (Spinner) rootView.findViewById(R.id.PPL_spnr);
        ppr = (Spinner) rootView.findViewById(R.id.PPR_spnr);
        spo2_et = (EditText) rootView.findViewById(R.id.spo2_etxt);
        etco2_et = (EditText) rootView.findViewById(R.id.etco2_etxt);
        hrmt_et = (EditText) rootView.findViewById(R.id.HRmt_etxt);
        sabp_et = (EditText) rootView.findViewById(R.id.SABP_e_txt);
        dabp_et = (EditText) rootView.findViewById(R.id.DABP_e_txt);
        mabp_et = (EditText) rootView.findViewById(R.id.MABP_e_txt);
        mabp_et.setEnabled(false);
        mabp_et.setTextColor(getResources().getColor(android.R.color.black));
        snibp_et = (EditText) rootView.findViewById(R.id.SNIBP_e_txt);
        pvr_et = (EditText) rootView.findViewById(R.id.PVR_e_txt);
        dnibp_et = (EditText) rootView.findViewById(R.id.DNIBP_e_txt);
        mnibp_et = (EditText) rootView.findViewById(R.id.MNIBP_e_txt);
        ptemp_et = (EditText) rootView.findViewById(R.id.PTEMP_e_txt);
        stemp_et = (EditText) rootView.findViewById(R.id.STEMP_e_txt);
        ctemp_et = (EditText) rootView.findViewById(R.id.CTEMP_e_txt);
        cvp1_et = (EditText) rootView.findViewById(R.id.CVP_e_txt1);
        svr_et = (EditText) rootView.findViewById(R.id.SVR_e_txt);
        la_et = (EditText) rootView.findViewById(R.id.LA_e_txt);
        pap_et = (EditText) rootView.findViewById(R.id.PAP_e_txt);
        rr_et = (EditText) rootView.findViewById(R.id.RR_e_txt);
        co_et = (EditText) rootView.findViewById(R.id.CO_e_txt);
        ci_et = (EditText) rootView.findViewById(R.id.CI_e_txt);
        event = (EditText) rootView.findViewById(R.id.Events_e_txt);
        comments = (EditText) rootView.findViewById(R.id.comments);
    }

    @Override
    public void prepareListeners() {

        sabp_et.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                setMabp();
            }



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        dabp_et.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                setMabp();
            }



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub 
            }
        });
    }

    @Override
    public void onPauseFragment() {
        Log.i("update", "Monitor onPauseFragment()");
//        Toast.makeText(getActivity(), "onPauseFragment():" + "update", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResumeFragment() {
        Log.i("update", "Monitoring onResumeFragment()");
       // Toast.makeText(getActivity(), "onResumeFragment():" + "update", Toast.LENGTH_SHORT).show();
        //grabValues();
    }
}




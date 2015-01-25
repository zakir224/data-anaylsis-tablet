package com.nhf.parameters;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nhf.app.FragmentLifecycle;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;

public class Output extends BaseForm implements FragmentLifecycle {
    public static final String ARG_SECTION_NUMBER = "section_number";


    private EditText amtUrine;

    public EditText getTotalUrine() {
        return totalUrine;
    }

    public void setTotalUrine(EditText totalUrine) {
        this.totalUrine = totalUrine;
    }

    private EditText totalUrine;
    private EditText rt_pl_etxt;
    private EditText lt_pl_etxt;
    private EditText med_etxt;
    private EditText peric_etxt;
    private EditText hr_t_etxt;
    private EditText g_t_etxt;
    private EditText amt_gastric_etxt;
    private EditText totalGastric;
    private EditText amt_lab_etxt;
    private EditText totalLab;
    private EditText total_out_etxt;
    private EditText op_comment;

    private static Output output;

    public Output() {

    }

    public static BaseForm getInstance(){
        if(output==null)
            output = new Output();
        return output;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.output, container,false);

        grabUiReferenes();
        populateViews();
        //grabValues();
        prepareListeners();

        return  rootView;
    }

    @Override
    public void populateViews() {

        //getActivity().setTitle("[Output] Patient:" + patientRecord.getPatientName() + " Day: " + patientRecord.getPod() + " Time: " + patientRecord.getTime_picked() + " Patient ID: " + patientRecord.getPatient_id());

          amtUrine.setText(String.valueOf(patientRecord.getAmtUrine()));
        totalUrine.setText(String.valueOf(patientRecord.getTotalUrine()));
        rt_pl_etxt.setText(String.valueOf(patientRecord.getRt_pl()));
        lt_pl_etxt.setText(String.valueOf(patientRecord.getLt_pl()));
          med_etxt.setText(String.valueOf(patientRecord.getMed()));
        peric_etxt.setText(String.valueOf(patientRecord.getPeric()));
         hr_t_etxt.setText(String.valueOf(patientRecord.getHr_t()));
          g_t_etxt.setText(String.valueOf(patientRecord.getG_t()));
  amt_gastric_etxt.setText(String.valueOf(patientRecord.getAmt_gastric()));
      totalGastric.setText(String.valueOf(patientRecord.getTotalGastric()));
      amt_lab_etxt.setText(String.valueOf(patientRecord.getAmt_lab()));
          totalLab.setText(String.valueOf(patientRecord.getTotalLab()));
    total_out_etxt.setText(String.valueOf(patientRecord.getTotal_out()));
        op_comment.setText(patientRecord.getOutput_comment());
    }

    @Override
    public void grabValues() {

        Log.d("update", "graveValues of output");

        //EditText mode_et;//setMode_text() or setMode()
        patientRecord.setAmtUrine(IOUtil.getInt(amtUrine.getText().toString()));
        //patientRecord.setTotalUrine(jsonArray.getJSONObject(0).getInt("total_urine"));
        Log.d("update", "value of urine"+patientRecord.getTotalUrine());
        patientRecord.setRt_pl(IOUtil.getInt(rt_pl_etxt.getText().toString()));
        patientRecord.setLt_pl(IOUtil.getInt(lt_pl_etxt.getText().toString()));
        patientRecord.setMed(IOUtil.getInt(med_etxt.getText().toString()));
        patientRecord.setPeric(IOUtil.getInt(peric_etxt.getText().toString()));
        patientRecord.setHr_t(IOUtil.getInt(hr_t_etxt.getText().toString()));
        patientRecord.setG_t(IOUtil.getInt(g_t_etxt.getText().toString()));
        patientRecord.setAmt_gastric(IOUtil.getInt(amt_gastric_etxt.getText().toString()));
        patientRecord.setTotalGastric(IOUtil.getInt(totalGastric.getText().toString()));
        patientRecord.setAmt_lab(IOUtil.getInt(amt_lab_etxt.getText().toString()));
        patientRecord.setTotalLab(IOUtil.getInt(totalLab.getText().toString()));
        patientRecord.setTotal_out(IOUtil.getInt(total_out_etxt.getText().toString()));
        patientRecord.setOutput_comment(String.valueOf(op_comment.getText().toString()));

    }

    @Override
    public void grabUiReferenes() {

        amtUrine = (EditText) rootView.findViewById(R.id.amt_etxt);
        totalUrine = (EditText) rootView.findViewById(R.id.amt_total_etxt);
        rt_pl_etxt = (EditText) rootView.findViewById(R.id.rt_pl_etxt);
        lt_pl_etxt = (EditText) rootView.findViewById(R.id.lt_pl_etxt);
        med_etxt = (EditText) rootView.findViewById(R.id.med_etxt);
        peric_etxt = (EditText) rootView.findViewById(R.id.peric_etxt);
        hr_t_etxt = (EditText) rootView.findViewById(R.id.hr_t_etxt);
        g_t_etxt = (EditText) rootView.findViewById(R.id.g_t_etxt);
        amt_gastric_etxt = (EditText) rootView.findViewById(R.id.amt_gastric_etxt);
        totalGastric = (EditText) rootView.findViewById(R.id.total_etxt);
        amt_lab_etxt = (EditText) rootView.findViewById(R.id.amt_lab_etxt);
        totalLab = (EditText) rootView.findViewById(R.id.total2_etxt);
        total_out_etxt = (EditText) rootView.findViewById(R.id.total_out_etxt);
        op_comment = (EditText) rootView.findViewById(R.id.op_comment);
    }

    @Override
    public void prepareListeners() {

        rt_pl_etxt.addTextChangedListener(new OnOutputTextChangedListener());
        peric_etxt.addTextChangedListener(new OnOutputTextChangedListener());
        lt_pl_etxt.addTextChangedListener(new OnOutputTextChangedListener());
        med_etxt.addTextChangedListener(new OnOutputTextChangedListener());


    }

    @Override
    public void onResumeFragment() {

    }

    public class OnOutputTextChangedListener implements TextWatcher {

        private EditText currentEditText;
        int total;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            int hrTotal = IOUtil.getInt(rt_pl_etxt.getText().toString()) +
                          IOUtil.getInt(peric_etxt.getText().toString()) +
                          IOUtil.getInt(lt_pl_etxt.getText().toString()) +
                          IOUtil.getInt(med_etxt.getText().toString());
            hr_t_etxt.setText(String.valueOf(hrTotal));
            patientRecord.setHr_t(hrTotal);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}

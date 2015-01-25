package com.nhf.parameters;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nhf.adapter.CustomSpinnerAdapter;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;
import com.nhf.task.IvInfusionCalculatorFragment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zakir on 5/23/14.
 */
public class DialDose extends BaseForm implements IvInfusionCalculatorFragment.IvInfusionCalculatorFragmentResultListener
        {


    private EditText weight;
    private static DialDose dialDose;
    LinkedHashMap<Integer, EditText> doses;
    LinkedHashMap<Integer, EditText> dials;
    LinkedHashMap<Integer, EditText> drugAmount;
    LinkedHashMap<Integer, EditText> saline;
    LinkedHashMap<Integer, Spinner> spinnerUnit;


    public DialDose() {

    }

    public static BaseForm getInstance() {
        if(dialDose==null)
            dialDose = new DialDose();

        return dialDose;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dial_dose,container,false);

        grabUiReferenes();
         prepareListeners();
        populateViews();

        return rootView;
    }

    @Override
    public void populateViews() {
        dials.get(R.id.dial_et_1).setText(IOUtil.getString(patientRecord.getDial1()));
        dials.get(R.id.dial_et_2).setText(IOUtil.getString(patientRecord.getDial2()));
        dials.get(R.id.dial_et_3).setText(IOUtil.getString(patientRecord.getDial3()));
        dials.get(R.id.dial_et_4).setText(IOUtil.getString(patientRecord.getDial4()));
        dials.get(R.id.dial_et_5).setText(IOUtil.getString(patientRecord.getDial5()));
        dials.get(R.id.dial_et_6).setText(IOUtil.getString(patientRecord.getDial6()));

        doses.get(R.id.dose_et_1).setText(IOUtil.getString(patientRecord.getDose1()));
        doses.get(R.id.dose_et_2).setText(IOUtil.getString(patientRecord.getDose2()));
        doses.get(R.id.dose_et_3).setText(IOUtil.getString(patientRecord.getDose3()));
        doses.get(R.id.dose_et_4).setText(IOUtil.getString(patientRecord.getDose4()));
        doses.get(R.id.dose_et_5).setText(IOUtil.getString(patientRecord.getDose5()));
        doses.get(R.id.dose_et_6).setText(IOUtil.getString(patientRecord.getDose6()));

        int index = 0;
        double[] drugAmounts = {patientRecord.getDrugAmount1(),patientRecord.getDrugAmount2(),patientRecord.getDrugAmount3(),
        patientRecord.getDrugAmount4(),patientRecord.getDrugAmount5(),patientRecord.getDrugAmount6()};

        for(Map.Entry<Integer,EditText> entry : drugAmount.entrySet()){
            entry.getValue().setText(IOUtil.getString(drugAmounts[index++]));
        }

        double[] salines = {patientRecord.getSaline1(),patientRecord.getSaline2(),patientRecord.getSaline3(),
        patientRecord.getSaline4(),patientRecord.getSaline5(),patientRecord.getSaline6()};

        index =0;
        for(Map.Entry<Integer,EditText> entry : saline.entrySet()){
            entry.getValue().setText(IOUtil.getString(drugAmounts[index++]));
        }

        int[] spinnerUnits = {patientRecord.getDialUnit1(),patientRecord.getDialUnit2(),patientRecord.getDialUnit3(),
        patientRecord.getDialUnit4(),patientRecord.getDialUnit5(),patientRecord.getDialUnit6()};

        index = 0;
        for(Map.Entry<Integer,Spinner> spinnerEntry : spinnerUnit.entrySet()) {
            spinnerEntry.getValue().setSelection(spinnerUnits[index++]);
        }

        weight.setText(IOUtil.getString(patientRecord.getWeight()));
    }

    @Override
    public void grabValues() {
        patientRecord.setDose1(IOUtil.getDouble(doses.get(R.id.dose_et_1).getText().toString()));
        patientRecord.setDose2(IOUtil.getDouble(doses.get(R.id.dose_et_2).getText().toString()));
        patientRecord.setDose3(IOUtil.getDouble(doses.get(R.id.dose_et_3).getText().toString()));
        patientRecord.setDose4(IOUtil.getDouble(doses.get(R.id.dose_et_4).getText().toString()));
        patientRecord.setDose5(IOUtil.getDouble(doses.get(R.id.dose_et_5).getText().toString()));
        patientRecord.setDose6(IOUtil.getDouble(doses.get(R.id.dose_et_6).getText().toString()));

        patientRecord.setDial1(IOUtil.getDouble(dials.get(R.id.dial_et_1).getText().toString()));
        patientRecord.setDial2(IOUtil.getDouble(dials.get(R.id.dial_et_2).getText().toString()));
        patientRecord.setDial3(IOUtil.getDouble(dials.get(R.id.dial_et_3).getText().toString()));
        patientRecord.setDial4(IOUtil.getDouble(dials.get(R.id.dial_et_4).getText().toString()));
        patientRecord.setDial5(IOUtil.getDouble(dials.get(R.id.dial_et_5).getText().toString()));
        patientRecord.setDial6(IOUtil.getDouble(dials.get(R.id.dial_et_6).getText().toString()));

        patientRecord.setDrugAmount1(IOUtil.getDouble(drugAmount.get(R.id.drug_amount_1_et).getText().toString()));
        patientRecord.setDrugAmount2(IOUtil.getDouble(drugAmount.get(R.id.drug_amount_2_et).getText().toString()));
        patientRecord.setDrugAmount3(IOUtil.getDouble(drugAmount.get(R.id.drug_amount_3_et).getText().toString()));
        patientRecord.setDrugAmount4(IOUtil.getDouble(drugAmount.get(R.id.drug_amount_4_et).getText().toString()));
        patientRecord.setDrugAmount5(IOUtil.getDouble(drugAmount.get(R.id.drug_amount_5_et).getText().toString()));
        patientRecord.setDrugAmount6(IOUtil.getDouble(drugAmount.get(R.id.drug_amount_6_et).getText().toString()));

        patientRecord.setSaline1(IOUtil.getDouble(saline.get(R.id.saline_1_et).getText().toString()));
        patientRecord.setSaline2(IOUtil.getDouble(saline.get(R.id.saline_2_et).getText().toString()));
        patientRecord.setSaline3(IOUtil.getDouble(saline.get(R.id.saline_3_et).getText().toString()));
        patientRecord.setSaline4(IOUtil.getDouble(saline.get(R.id.saline_4_et).getText().toString()));
        patientRecord.setSaline5(IOUtil.getDouble(saline.get(R.id.saline_5_et).getText().toString()));
        patientRecord.setSaline6(IOUtil.getDouble(saline.get(R.id.saline_6_et).getText().toString()));

        patientRecord.setDialUnit1(spinnerUnit.get(R.id.unit_spinner_1).getSelectedItemPosition());
        patientRecord.setDialUnit2(spinnerUnit.get(R.id.unit_spinner_2).getSelectedItemPosition());
        patientRecord.setDialUnit3(spinnerUnit.get(R.id.unit_spinner_3).getSelectedItemPosition());
        patientRecord.setDialUnit4(spinnerUnit.get(R.id.unit_spinner_4).getSelectedItemPosition());
        patientRecord.setDialUnit5(spinnerUnit.get(R.id.unit_spinner_5).getSelectedItemPosition());
        patientRecord.setDialUnit6(spinnerUnit.get(R.id.unit_spinner_6).getSelectedItemPosition());

    }

    @Override
    public void grabUiReferenes() {
        saline = new LinkedHashMap<Integer, EditText>();
        drugAmount = new LinkedHashMap<Integer, EditText>();
        dials = new LinkedHashMap<Integer, EditText>();
        doses = new LinkedHashMap<Integer, EditText>();
        spinnerUnit = new LinkedHashMap<Integer, Spinner>();
        weight = (EditText)rootView.findViewById(R.id.weight_et);

        doses.put(R.id.dose_et_1, (EditText)rootView.findViewById(R.id.dose_et_1));
        doses.put(R.id.dose_et_2, (EditText)rootView.findViewById(R.id.dose_et_2));
        doses.put(R.id.dose_et_3, (EditText)rootView.findViewById(R.id.dose_et_3));
        doses.put(R.id.dose_et_4, (EditText)rootView.findViewById(R.id.dose_et_4));
        doses.put(R.id.dose_et_5, (EditText)rootView.findViewById(R.id.dose_et_5));
        doses.put(R.id.dose_et_6, (EditText)rootView.findViewById(R.id.dose_et_6));

        drugAmount.put(R.id.drug_amount_1_et,(EditText)rootView.findViewById(R.id.drug_amount_1_et));
        drugAmount.put(R.id.drug_amount_2_et,(EditText)rootView.findViewById(R.id.drug_amount_2_et));
        drugAmount.put(R.id.drug_amount_3_et,(EditText)rootView.findViewById(R.id.drug_amount_3_et));
        drugAmount.put(R.id.drug_amount_4_et,(EditText)rootView.findViewById(R.id.drug_amount_4_et));
        drugAmount.put(R.id.drug_amount_5_et,(EditText)rootView.findViewById(R.id.drug_amount_5_et));
        drugAmount.put(R.id.drug_amount_6_et,(EditText)rootView.findViewById(R.id.drug_amount_6_et));

        saline.put(R.id.saline_1_et,(EditText)rootView.findViewById(R.id.saline_1_et));
        saline.put(R.id.saline_2_et,(EditText)rootView.findViewById(R.id.saline_2_et));
        saline.put(R.id.saline_3_et,(EditText)rootView.findViewById(R.id.saline_3_et));
        saline.put(R.id.saline_4_et,(EditText)rootView.findViewById(R.id.saline_4_et));
        saline.put(R.id.saline_5_et,(EditText)rootView.findViewById(R.id.saline_5_et));
        saline.put(R.id.saline_6_et,(EditText)rootView.findViewById(R.id.saline_6_et));

        dials.put(R.id.dial_et_1,(EditText)rootView.findViewById(R.id.dial_et_1));
        dials.put(R.id.dial_et_2,(EditText)rootView.findViewById(R.id.dial_et_2));
        dials.put(R.id.dial_et_3,(EditText)rootView.findViewById(R.id.dial_et_3));
        dials.put(R.id.dial_et_4,(EditText)rootView.findViewById(R.id.dial_et_4));
        dials.put(R.id.dial_et_5,(EditText)rootView.findViewById(R.id.dial_et_5));
        dials.put(R.id.dial_et_6,(EditText)rootView.findViewById(R.id.dial_et_6));

        spinnerUnit.put(R.id.unit_spinner_1,(Spinner) rootView.findViewById(R.id.unit_spinner_1));
        spinnerUnit.put(R.id.unit_spinner_2,(Spinner) rootView.findViewById(R.id.unit_spinner_2));
        spinnerUnit.put(R.id.unit_spinner_3,(Spinner) rootView.findViewById(R.id.unit_spinner_3));
        spinnerUnit.put(R.id.unit_spinner_4,(Spinner) rootView.findViewById(R.id.unit_spinner_4));
        spinnerUnit.put(R.id.unit_spinner_5,(Spinner) rootView.findViewById(R.id.unit_spinner_5));
        spinnerUnit.put(R.id.unit_spinner_6,(Spinner) rootView.findViewById(R.id.unit_spinner_6));

        for(Map.Entry<Integer,Spinner> spinnerEntry : spinnerUnit.entrySet()){
            ((Spinner)spinnerEntry.getValue()).setAdapter(new CustomSpinnerAdapter(getActivity(),android.R.layout.simple_list_item_single_choice,getResources().getStringArray(R.array.dial_dose_unit)));
        }
        //spinnerUnit.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.dial_dose_unit)));

    }

    @Override
    public void prepareListeners() {
        int row =1;
        for(Map.Entry<Integer,EditText> doseEntry : doses.entrySet()){
            ((EditText)doseEntry.getValue()).addTextChangedListener(new DialDoseTextListener(row++));
        }
        row =1;
        for(Map.Entry<Integer,EditText> drugEntry : drugAmount.entrySet()){
            ((EditText)drugEntry.getValue()).addTextChangedListener(new DialDoseTextListener(row++));
        }
        row =1;
        for(Map.Entry<Integer,EditText> salineEntry : saline.entrySet()){
            ((EditText)salineEntry.getValue()).addTextChangedListener(new DialDoseTextListener(row++));
        }
    }

    public void onFinishedListener(DialogFragment dialogFragment, double v) {
        Toast.makeText(getActivity(),"Result received: "+v,Toast.LENGTH_LONG).show();
    }


    private class DialDoseTextListener implements TextWatcher{

        Integer id;
        double dial;
        private DialDoseTextListener(Integer id) {
            this.id= id;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            switch (id){
                case 1:
                    dial = getDial(IOUtil.getDouble(((EditText)doses.get(R.id.dose_et_1)).getText().toString()),
                            IOUtil.getDouble(((EditText)drugAmount.get(R.id.drug_amount_1_et)).getText().toString()),
                            IOUtil.getDouble(((EditText)saline.get(R.id.saline_1_et)).getText().toString())
                    ,((Spinner)spinnerUnit.get(R.id.unit_spinner_1)).getSelectedItemPosition());
                    int i =0;
                    ((EditText)dials.get(R.id.dial_et_1)).setText(IOUtil.getString(dial));
                    break;
                case 2:
                    dial = getDial(IOUtil.getDouble(((EditText)doses.get(R.id.dose_et_2)).getText().toString()),
                            IOUtil.getDouble(((EditText)drugAmount.get(R.id.drug_amount_2_et)).getText().toString()),
                            IOUtil.getDouble(((EditText)saline.get(R.id.saline_2_et)).getText().toString())
                            ,((Spinner)spinnerUnit.get(R.id.unit_spinner_2)).getSelectedItemPosition());
                    ((EditText)dials.get(R.id.dial_et_2)).setText(IOUtil.getString(dial));
                    break;
                case 3:
                    dial = getDial(IOUtil.getDouble(((EditText)doses.get(R.id.dose_et_3)).getText().toString()),
                            IOUtil.getDouble(((EditText)drugAmount.get(R.id.drug_amount_3_et)).getText().toString()),
                            IOUtil.getDouble(((EditText)saline.get(R.id.saline_3_et)).getText().toString())
                            ,((Spinner)spinnerUnit.get(R.id.unit_spinner_3)).getSelectedItemPosition());
                    ((EditText)dials.get(R.id.dial_et_3)).setText(IOUtil.getString(dial));
                    break;
                case 4:
                    dial = getDial(IOUtil.getDouble(((EditText)doses.get(R.id.dose_et_4)).getText().toString()),
                            IOUtil.getDouble(((EditText)drugAmount.get(R.id.drug_amount_4_et)).getText().toString()),
                            IOUtil.getDouble(((EditText)saline.get(R.id.saline_4_et)).getText().toString())
                            ,((Spinner)spinnerUnit.get(R.id.unit_spinner_4)).getSelectedItemPosition());
                    ((EditText)dials.get(R.id.dial_et_4)).setText(IOUtil.getString(dial));
                    break;
                case 5:
                    dial = getDial(IOUtil.getDouble(((EditText)doses.get(R.id.dose_et_5)).getText().toString()),
                            IOUtil.getDouble(((EditText)drugAmount.get(R.id.drug_amount_5_et)).getText().toString()),
                            IOUtil.getDouble(((EditText)saline.get(R.id.saline_5_et)).getText().toString())
                            ,((Spinner)spinnerUnit.get(R.id.unit_spinner_5)).getSelectedItemPosition());
                    ((EditText)dials.get(R.id.dial_et_5)).setText(IOUtil.getString(dial));
                    break;
                case 6:
                    dial = getDial(IOUtil.getDouble(((EditText)doses.get(R.id.dose_et_6)).getText().toString()),
                            IOUtil.getDouble(((EditText)drugAmount.get(R.id.drug_amount_6_et)).getText().toString()),
                            IOUtil.getDouble(((EditText)saline.get(R.id.saline_6_et)).getText().toString())
                            ,((Spinner)spinnerUnit.get(R.id.unit_spinner_6)).getSelectedItemPosition());
                    ((EditText)dials.get(R.id.dial_et_6)).setText(IOUtil.getString(dial));
                    break;
                default:
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        private double getDial(double dose,double drugAmount,double saline,int unit){
            double dial;
            double wt = patientRecord.getWeight();
            double concentration = drugAmount/saline;


            switch (unit){
                case 0:
                       dial = (dose*wt*60)/(1000*concentration);
                    break;
                case 2:
                    dial = (dose * wt) / (concentration*100);
                    break;
                case 3:
                    dial = dose * wt / concentration;
                    break;
                default:
                    dial = 0.0;
                    break;
            }

            return dial;
        }
    }


}

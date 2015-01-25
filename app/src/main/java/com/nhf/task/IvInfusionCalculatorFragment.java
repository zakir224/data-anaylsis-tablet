package com.nhf.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nhf.icu.R;
import com.nhf.io.IOUtil;
import com.nhf.parameters.BaseParameter;

/**
 * Created by Zakir on 5/22/14.
 */
public class IvInfusionCalculatorFragment extends DialogFragment {

    public interface IvInfusionCalculatorFragmentResultListener {
        public abstract void onFinishedListener(DialogFragment dialogFragment,double v);
    }

    IvInfusionCalculatorFragmentResultListener ivInfusionCalculatorFragmentResultListener;

    public IvInfusionCalculatorFragment(IvInfusionCalculatorFragmentResultListener ivInfusionCalculatorFragmentResultListener) {
        this.ivInfusionCalculatorFragmentResultListener = ivInfusionCalculatorFragmentResultListener;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.iv_calc, null);
        final EditText dose = (EditText)v.findViewById(R.id.doseEt);
        builder.setView(v).setMessage("Iv Infusion calculator");
        builder.setPositiveButton("Done",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ivInfusionCalculatorFragmentResultListener.onFinishedListener(IvInfusionCalculatorFragment.this,
                        IOUtil.getDouble(dose.getText().toString()));
            }
        });




        return builder.create();
    }
}

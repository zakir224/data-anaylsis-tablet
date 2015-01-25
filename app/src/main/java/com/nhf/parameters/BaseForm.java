package com.nhf.parameters;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.nhf.app.Parameters;
import com.nhf.app.PatientRecord;

import android.os.Handler;

/**
 * Created by ZAKIR_224 on 2/24/14.
 */
public abstract class BaseForm extends Fragment{

    protected View rootView;
    protected PatientRecord patientRecord = PatientRecord.getInstance();

    public abstract void populateViews();
    public abstract void grabValues();
    public abstract void grabUiReferenes();
    public abstract void prepareListeners();
    public void onPauseFragment(){};

}

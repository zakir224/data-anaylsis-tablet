package com.nhf.reports;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableRow;
import android.widget.TextView;

import com.nhf.adapter.IcuParameterPagerAdapter;
import com.nhf.adapter.IcuReportPagerAdapter;
import com.nhf.adapter.PatientListAdapter;
import com.nhf.app.CustomHttpClient;
import com.nhf.app.DataManipulationDialog;
import com.nhf.app.Login;
import com.nhf.app.Patient;
import com.nhf.app.PatientRecord;
import com.nhf.icu.R;
import com.nhf.task.IcuParameters;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Zakir on 8/17/2014.
 */
public class PatientReport extends FragmentActivity {


    IcuReportPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;

    JSONArray jsonArray;

    BaseReportFragment baseReportFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_report);
//        baseReportFragment = BaseReportFragment.getInstance();
        mSectionsPagerAdapter = new IcuReportPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.reportPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


}
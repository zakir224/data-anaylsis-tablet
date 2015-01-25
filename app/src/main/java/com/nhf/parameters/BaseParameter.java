package com.nhf.parameters;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.nhf.adapter.IcuParameterPagerAdapter;
import com.nhf.app.PatientRecord;
import com.nhf.icu.R;
import com.nhf.task.IvInfusionCalculatorFragment;
import com.nhf.task.UpdateParameters;

public class BaseParameter extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener,
        IvInfusionCalculatorFragment.IvInfusionCalculatorFragmentResultListener{

	IcuParameterPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;
    Button saveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("[Monitoring] Patient:" + PatientRecord.getInstance().getPatientName() + " Day: " + PatientRecord.getInstance().getPod() + " Time: " + PatientRecord.getInstance().getTime_picked() + " Patient ID: " + PatientRecord.getInstance().getPatient_id());

        setContentView(R.layout.parameters);
        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		mSectionsPagerAdapter = new IcuParameterPagerAdapter(getSupportFragmentManager());
        saveBtn = (Button)findViewById(R.id.saveBtn);
		mViewPager = (ViewPager) findViewById(R.id.pager);
        //mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        saveBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parameters, menu);
		return true;
	}


    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    int currentPosition = 0;

    @Override
    public void onPageSelected(int currentPosition) {

        BaseForm fragmentToShow = (BaseForm)mSectionsPagerAdapter.getItem(currentPosition);
        fragmentToShow.populateViews();
        this.currentPosition = currentPosition;

//        FragmentLifecycle fragmentToShow = (Bas)mSectionsPagerAdapter.getItem(newPosition);
//        fragmentToShow.onResumeFragment();
//
//        FragmentLifecycle fragmentToHide = (FragmentLifecycle)mSectionsPagerAdapter.getItem(currentPosition);
//        fragmentToHide.onPauseFragment();

//        currentPosition = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View view) {
        new UpdateParameters(mSectionsPagerAdapter.getItem(currentPosition).getActivity(),(BaseForm)mSectionsPagerAdapter.getItem(currentPosition)).execute();
        //((BaseForm)mSectionsPagerAdapter.getItem(currentPosition)).populateViews();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.d("parameter","Back key pressed");
        //PatientRecord.getInstance().initializeInstance();
    }


    @Override
    public void onFinishedListener(DialogFragment dialogFragment, double v) {

    }
}

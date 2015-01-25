package com.nhf.adapter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

import com.nhf.icu.R;
import com.nhf.parameters.BloodGas;
import com.nhf.parameters.DialDose;
import com.nhf.parameters.IvParams;
import com.nhf.parameters.Output;
import com.nhf.parameters.Ventilator;
import com.nhf.parameters.ZBlankFragment;
import com.nhf.parameters.MonitoringParams;

import java.util.Locale;

/**
 * Created by ZAKIR_224 on 2/21/14.
 */
public class IcuParameterPagerAdapter extends FragmentPagerAdapter {

    public IcuParameterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return MonitoringParams.getInstance();
            case 1:
                return Ventilator.getInstance();
            case 2:
                return BloodGas.getInstance();
            case 3:
                return Output.getInstance();
            case 4:
                return IvParams.getInstance();
            case 5:
                return DialDose.getInstance();
            default:
                return new ZBlankFragment();
        }

    }

    @Override
    public int getCount() {
        // Show 5 total pages.
        return 6;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();

        switch (position) {
            case 0:
                return "Monitoring Parameter";
            case 1:
                return "Ventilator Parameter";
            case 2:
                return "Blood & Biochemical Parameter";
            case 3:
                return "Output";
            case 4:
                return "IV";
            case 5:
                return "Dial Dose";
        }
        return null;
    }
}

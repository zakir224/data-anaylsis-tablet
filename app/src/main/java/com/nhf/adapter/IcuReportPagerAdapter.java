package com.nhf.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nhf.parameters.BloodGas;
import com.nhf.parameters.DialDose;
import com.nhf.parameters.IvParams;
import com.nhf.parameters.MonitoringParams;
import com.nhf.parameters.Output;
import com.nhf.parameters.Ventilator;
import com.nhf.parameters.ZBlankFragment;
import com.nhf.reports.MonitoringParamReport;
import com.nhf.reports.MonitoringParamReportContinued;
import com.nhf.reports.VentilatorReport;

import java.util.Locale;

/**
 * Created by ZAKIR_224 on 2/21/14.
 */
public class IcuReportPagerAdapter extends FragmentPagerAdapter {

    public IcuReportPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return MonitoringParamReport.getInstance();
            case 1:
                return VentilatorReport.getInstance();
            case 2:
                return VentilatorReport.getInstance();
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
        return 2;
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
                return "Ventilator Parameter";
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

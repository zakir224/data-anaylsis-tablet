package com.nhf.reports;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.nhf.adapter.ReportTableAdapter;
import com.nhf.app.PatientRecord;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;

import org.json.JSONException;

/**
 * Created by Zakir on 8/17/2014.
 */
public class VentilatorReport extends BaseReportFragment {

    String[] atts;
    private static VentilatorReport monitoringParamReport;
    private TableLayout table_layout;
    String[][] values;
    ReportTableAdapter reportTableAdapter;

    public static VentilatorReport getInstance() {
        if (monitoringParamReport == null) {
            return new VentilatorReport();
        }
        return monitoringParamReport;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ventilator_report, container, false);
        jsonArray = PatientRecord.getInstance().getJsonArray();
        values = new String[jsonArray.length()+1][13];
        getValues();
        TableFixHeaders tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.table);
        reportTableAdapter = new ReportTableAdapter(getActivity(),values);
        reportTableAdapter.setColumnCount(12);
        reportTableAdapter.setRowCount(jsonArray.length());
        tableFixHeaders.setAdapter(reportTableAdapter);


        return view;
    }

    public void getValues(){

        values[0][0]="Time";
        values[0][1]="Mode";
        values[0][2]="Rate";
        values[0][3]="Rsbi";
        values[0][4]="PIP";
        values[0][5]="PEEP";
        values[0][6]="I:E";
        values[0][7]="TV";
        values[0][8]="MAP";
        values[0][9]="TI";
        values[0][10]="PSUPP";
        values[0][11]="FIO2";
        values[0][12]="Comments";
        try{
            for(int i=0,j=1;i<jsonArray.length();i++,j++){
                values[j][0] = jsonArray.getJSONObject(i).getString("time_picked");
                values[j][1] = jsonArray.getJSONObject(i).getString("mode");
                values[j][2] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("rate"));
                values[j][3] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("rsbi"));
                values[j][4] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("pip"));
                values[j][5] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("peep"));
                values[j][6] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("i")) + " : " + IOUtil.getString(jsonArray.getJSONObject(i).getDouble("e"));
                values[j][7] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("tv"));
                values[j][8] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("map"));
                values[j][9] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("ti"));
                values[j][10] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("psupp"));
                values[j][11] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("fio2"));
                values[j][12] = jsonArray.getJSONObject(i).getString("vent_comment");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


}
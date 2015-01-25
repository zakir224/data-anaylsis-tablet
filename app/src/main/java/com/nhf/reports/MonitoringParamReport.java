package com.nhf.reports;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.nhf.adapter.PatientListAdapter;
import com.nhf.adapter.ReportTableAdapter;
import com.nhf.app.CustomHttpClient;
import com.nhf.app.DataManipulationDialog;
import com.nhf.app.Login;
import com.nhf.app.Patient;
import com.nhf.app.PatientRecord;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;
import com.nhf.task.IcuParameters;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Zakir on 8/17/2014.
 */
public class MonitoringParamReport extends BaseReportFragment {


    private static MonitoringParamReport monitoringParamReport;
    private TableLayout table_layout;
    String[][] values;
    ReportTableAdapter reportTableAdapter;

    public static MonitoringParamReport getInstance() {
        if (monitoringParamReport == null) {
            return new MonitoringParamReport();
        }
        return monitoringParamReport;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_monitoring_param, container, false);
        jsonArray = PatientRecord.getInstance().getJsonArray();
        values = new String[jsonArray.length() + 1][27];
        getValues();
        TableFixHeaders tableFixHeaders = (TableFixHeaders) view.findViewById(R.id.table);
        reportTableAdapter = new ReportTableAdapter(getActivity(), values);
        reportTableAdapter.setColumnCount(26);
        reportTableAdapter.setRowCount(jsonArray.length());
        tableFixHeaders.setAdapter(reportTableAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //BuildTable();
    }

    private void BuildTable() {

        jsonArray = PatientRecord.getInstance().getJsonArray();

        // outer for loop
        for (int i = 0; i < jsonArray.length(); i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            if (i % 2 == 0)
                row.setBackgroundColor(getResources().getColor(R.color.even));
            // inner for loop

            try {
                String[] values = {jsonArray.getJSONObject(i).getString("time_picked"),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("spo2")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getDouble("etco2")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getDouble("rr")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("hr_mt")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("rhytm")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("s_abp")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("d_abp")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("m_abp")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("s_nibp")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("m_nibp")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("d_nibp")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getDouble("p_temp")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getDouble("s_temp")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getDouble("c_temp")),
                        jsonArray.getJSONObject(i).getString("events"),
                        jsonArray.getJSONObject(i).getString("pvr"),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("cvp")),
                        jsonArray.getJSONObject(i).getString("svr"),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("la")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("pap")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("peri")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("ppr")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getInt("ppl")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getDouble("co")),
                        IOUtil.getString(jsonArray.getJSONObject(i).getDouble("ci")),
                        jsonArray.getJSONObject(i).getString("comments")
                };

                for (int j = 0; j < 15; j++) {

                    TextView tv = new TextView(getActivity());
                    tv.setLayoutParams(new TableRow.LayoutParams(84,
                            TableRow.LayoutParams.WRAP_CONTENT, 1));

                    tv.setTextSize(14.0f);
                    tv.setTextColor(getActivity().getResources().getColor(android.R.color.white));
                    tv.setText(values[j]);
                    row.setMinimumHeight(40);
                    tv.setGravity(Gravity.CENTER);
                    row.addView(tv);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            table_layout.addView(row);

        }
    }


    public void getValues() {
        values[0][0]="Time";
        values[0][1]="SPO2";
        values[0][2]="ETCO2";
        values[0][3]="RR";
        values[0][4]="HR/MT";
        values[0][5]="Rhythm";
        values[0][6]="SABP";
        values[0][7]="DABP";
        values[0][8]="MABP";
        values[0][9]="SNIBP";
        values[0][10]="MNIBP";
        values[0][11]="DNIBP";
        values[0][12]="PTEMP";
        values[0][13]="STEMP";
        values[0][14]="CTEMP";
        values[0][15]="Events";
        values[0][16]="PVR";
        values[0][17]="CVP";
        values[0][18]="SVR";
        values[0][19]="LA";
        values[0][20]="PAP";
        values[0][21]="Peri";
        values[0][22]="PPR";
        values[0][23]="PPL";
        values[0][24]="CO";
        values[0][25]="CI";
        values[0][26]="Comments";
        try {
            for (int i = 0, j = 1; i < jsonArray.length(); i++, j++) {

                values[j][0] = jsonArray.getJSONObject(i).getString("time_picked");
                values[j][1] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("spo2"));
                values[j][2] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("etco2"));
                values[j][3] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("rr"));
                values[j][4] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("hr_mt"));
                values[j][5] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("rhytm"));
                values[j][6] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("s_abp"));
                values[j][7] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("d_abp"));
                values[j][8] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("m_abp"));
                values[j][9] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("s_nibp"));
                values[j][10] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("m_nibp"));
                values[j][11] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("d_nibp"));
                values[j][12] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("p_temp"));
                values[j][13] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("s_temp"));
                values[j][14] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("c_temp"));
                values[j][15] = jsonArray.getJSONObject(i).getString("events");
                values[j][16] = jsonArray.getJSONObject(i).getString("pvr");
                values[j][17] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("cvp"));
                values[j][18] = jsonArray.getJSONObject(i).getString("svr");
                values[j][19] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("la"));
                values[j][20] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("pap"));
                values[j][21] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("peri"));
                values[j][22] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("ppr"));
                values[j][23] = IOUtil.getString(jsonArray.getJSONObject(i).getInt("ppl"));
                values[j][24] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("co"));
                values[j][25] = IOUtil.getString(jsonArray.getJSONObject(i).getDouble("ci"));
                values[j][26] = jsonArray.getJSONObject(i).getString("comments");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
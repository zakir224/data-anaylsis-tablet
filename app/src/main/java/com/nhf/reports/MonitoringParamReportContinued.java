package com.nhf.reports;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nhf.app.PatientRecord;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;

import org.json.JSONException;

/**
 * Created by Zakir on 8/17/2014.
 */
public class MonitoringParamReportContinued extends BaseReportFragment {


    private static MonitoringParamReportContinued monitoringParamReport;
    private TableLayout table_layout;


    public static MonitoringParamReportContinued getInstance() {
        if (monitoringParamReport == null) {
            return new MonitoringParamReportContinued();
        }
        return monitoringParamReport;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_monitoring_param_cont, container, false);


        table_layout = (TableLayout) view.findViewById(R.id.monitoring_table_2);

        BuildTable();
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
            //Log.d("zakir",jsonArray.toString());
            try {
                String[] values = {jsonArray.getJSONObject(i).getString("time_picked"),
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

                for (int j = 0; j < 13; j++) {

                    TextView tv = new TextView(getActivity());
                    tv.setLayoutParams(new TableRow.LayoutParams(100,
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


}
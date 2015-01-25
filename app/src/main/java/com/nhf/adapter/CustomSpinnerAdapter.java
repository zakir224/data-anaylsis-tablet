package com.nhf.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nhf.icu.R;

import java.util.ArrayList;

/**
 * Created by Zakir on 9/6/2014.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private Activity context;
    String[] deer;

    public CustomSpinnerAdapter(Activity context, int resource, String[] deer) {

        super(context, resource, deer);
        this.context = context;
        this.deer = deer;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView,
                               ViewGroup parent){
        View row = convertView;

        if (row == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.spinner_row, parent, false);

        }

        //String current = deer.get(position);


        TextView name = (TextView) row.findViewById(R.id.spinnerText);
        name.setText(deer[position]);

        return row;
    }

}
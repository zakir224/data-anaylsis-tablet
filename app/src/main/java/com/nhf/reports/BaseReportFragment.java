package com.nhf.reports;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

/**
 * Created by Zakir on 8/17/2014.
 */
public class BaseReportFragment extends Fragment {

    protected JSONArray jsonArray = new JSONArray();

    private static BaseReportFragment baseReportFragment = null;

    public static BaseReportFragment getInstance(){
        if(baseReportFragment==null)
            return new BaseReportFragment();
       return baseReportFragment;
    }

    public BaseReportFragment(){

    }
    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }
}
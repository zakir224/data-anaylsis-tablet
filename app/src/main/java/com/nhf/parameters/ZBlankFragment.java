package com.nhf.parameters;

/**
 * Created by ZAKIR_224 on 2/21/14.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhf.icu.R;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public final class ZBlankFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public ZBlankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_parameters_dummy, container, false);
        return rootView;
    }
}
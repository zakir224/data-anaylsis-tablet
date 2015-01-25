package com.nhf.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.nhf.icu.R;

/**
 * Created by Zakir on 4/9/14.
 */
public class NetworkConnectionDialog extends DialogFragment{

    public interface NetworkStateDialogListener {
        public void onDialogPositiveClick(DialogFragment dialogFragment);
        public void onDialogNegativeClick(DialogFragment dialogFragment);
    }

    NetworkStateDialogListener networkStateDialogListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            networkStateDialogListener = (NetworkStateDialogListener)activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+
                    activity.getString(R.string.network_exeption_msg));
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Network connection error!").setMessage("This device is currently not connected to Internet")
                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            networkStateDialogListener.onDialogPositiveClick(NetworkConnectionDialog.this);
                    }
                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                networkStateDialogListener.onDialogNegativeClick(NetworkConnectionDialog.this);
            }
        });



        return builder.create();
    }
}

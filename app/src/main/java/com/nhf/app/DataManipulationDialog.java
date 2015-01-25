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
public class DataManipulationDialog extends DialogFragment {

    String title = "re";
    String message = "tete";

    public DataManipulationDialog() {

    }

    public interface DataManipulationDialogListener{
        public void onNegativeClick(DialogFragment dialogFragment);
    }

    DataManipulationDialogListener dataManipulationDialogListener;


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//               dataManipulationDialogListener = (DataManipulationDialogListener)activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()+
//                        " Must implement DataManipulationDialogListener");
//        }
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Drawable img = (Drawable)getActivity().getResources(R.drawable.heart);
        builder.setIcon(android.R.drawable.progress_indeterminate_horizontal);
        builder.setTitle((String)getArguments().getCharSequence("title","Loading")).setMessage(this.message=(String)getArguments().getCharSequence("message","Loading Background task")).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataManipulationDialogListener.onNegativeClick(DataManipulationDialog.this);
            }
        });

       return builder.create();
    }
}

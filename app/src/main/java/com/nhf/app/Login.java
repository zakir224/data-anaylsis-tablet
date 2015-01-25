package com.nhf.app;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.nhf.icu.R;

public class Login extends Activity implements OnClickListener,NetworkConnectionDialog.NetworkStateDialogListener{


	Button login;
	EditText user;
	EditText pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getActionBar().hide();
		
		login = (Button)findViewById(R.id.loginBtn);
		user = (EditText)findViewById(R.id.loginUser);
		pass = (EditText)findViewById(R.id.loginPassword);
		 
        
		login.setOnClickListener(this);

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	void peformLogin(){
		
		user.setError(null);
		pass.setError(null);
		boolean cancel = false;
		View focusView = null;
		
		if(TextUtils.isEmpty(user.getText().toString()))
			{
			user.setError("Insert Username");
			focusView = user;
			cancel = true;
			}
		else if(TextUtils.isEmpty(pass.getText().toString())) 
			{
			//login.setAnimation(alphaUp);
			pass.setError("Insert password");
				//login.setImageAlpha(100);
				focusView = pass;
				cancel = true;
			}
		
		if(cancel){
			//login.setAnimation(alphaUp);
			focusView.requestFocus();
		}
		
		else{
			startActivity(new Intent(Login.this,PatientListActivity.class));
		}
	}


	@Override
	public void onClick(View v) {

		if(v==login){
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();

                if(isConnected)
                    peformLogin();
                else  {
                    Log.d("NetWorkStateError","else");
                    NetworkConnectionDialog networkConnectionDialog = new NetworkConnectionDialog();
                    networkConnectionDialog.show(getFragmentManager(),"network");
                }
            } catch (Exception e) {
                Log.d("NetWorkStateError","error");
            }
        }
	}

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {
        moveTaskToBack(true);
    }
}

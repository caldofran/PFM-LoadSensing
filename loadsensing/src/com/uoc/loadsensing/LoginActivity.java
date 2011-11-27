package com.uoc.loadsensing;

/**
 * Cabecera de Clase
 */

import com.uoc.loadsensing.utils.Environment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	TextView twVisitWS;
	Button btnLogin;
	EditText etUser;
	EditText etPass;
	
	private Toast toast;
	private long lastBackPressTime = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        twVisitWS = (TextView) findViewById(R.id.visit_worldsensing);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        
        etUser = (EditText)findViewById(R.id.et_user);
        etPass= (EditText)findViewById(R.id.et_pass);
        
        // Listening to click on LoginButton 
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				//twVisitWS.setText("Request Loging "+etUser.getText().toString()+"-"+etPass.getText().toString());
				if (!Environment.internetIsAvailable(LoginActivity.this))
				{
					Environment.errorAlert(LoginActivity.this, getApplicationContext().getString(R.string.no_connection));
				}else{				
					Intent i = new Intent(getApplicationContext(), ListNetworksActivity.class);
					startActivity(i);
				}
			}
		});
        
        // Listening to visit WorldSensing Web
        twVisitWS.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (!Environment.internetIsAvailable(LoginActivity.this))
				{
					Environment.errorAlert(LoginActivity.this, getApplicationContext().getString(R.string.no_connection));
				}else{				
	
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(getResources().getString(R.string.url_ws)));
					startActivity(i);
					
				}
			}
		});
    }

    
    
    @Override
    public void onBackPressed() {
      if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
        toast = Toast.makeText(this, getApplicationContext().getString(R.string.exit_app), 4000);
        toast.show();
        this.lastBackPressTime = System.currentTimeMillis();
      } else {
        if (toast != null) {
        toast.cancel();
      }
      super.onBackPressed();
     }
    }    
}
package com.uoc.loadsensing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	TextView twVisitWS;
	Button btnLogin;
	EditText etUser;
	EditText etPass;
	
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
				
				Intent i = new Intent(getApplicationContext(), TabMenuActivity.class);
				startActivity(i);
			}
		});
        
        // Listening to visit WorldSensing Web
        twVisitWS.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(getResources().getString(R.string.url_ws)));
				startActivity(i);				
			}
		});
    }

    
}
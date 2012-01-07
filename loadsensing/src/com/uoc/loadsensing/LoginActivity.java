package com.uoc.loadsensing;

/**
 * 
 * UOC - Universitat Oberta de Catalunya
 * Proyecto Final Master Software Libre
 * Septiembre 2011
 * 
 * LoadSensing para WorldSensing
 * 
 * @authors
 * 		Ruben Mendez Puente
 * 		Jesus Sanchez-Migallon Perez
 * 
 * Licensed to the Apache Software Foundation (ASF) under one 
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 *       
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 */

import java.util.Iterator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uoc.loadsensing.beans.Users;
import com.uoc.loadsensing.utils.Environment;

public class LoginActivity extends LoadSensingActivity {
	
	Context mContext;
	TextView twVisitWS;
	Button btnLogin;
	EditText etUser;
	EditText etPass;
	
	LoadSensingActivity loadSensing = new LoadSensingActivity();
	
	private Toast toast;
	private long lastBackPressTime = 0;
	private Boolean matched = false;
	
	public static void errorAlert(Context mContext, String title, String text) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(title);
		dialog.setMessage(text);
		dialog.setIcon(R.drawable.alert_dialog_icon);
		dialog.setPositiveButton(mContext.getString(R.string.ok), null);
		dialog.show();
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mContext = this;
    	
        setContentView(R.layout.login);
        
        twVisitWS = (TextView) findViewById(R.id.visit_worldsensing);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        
        etUser = (EditText)findViewById(R.id.et_user);
        etPass= (EditText)findViewById(R.id.et_pass);
        
        // Listening to click on LoginButton 
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(mContext.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				if (!Environment.internetIsAvailable(LoginActivity.this))
				{
					Environment.errorAlert(LoginActivity.this, getApplicationContext().getString(R.string.no_connection));
				}else{
					System.out.println(etUser.getText());
					System.out.println(etPass.getText());
					Iterator<Users> item = loadSensing.array_users.iterator();
					Users user = new Users();
					while (item.hasNext()) {
						System.out.println("Entramos en el bucle");
						user = (Users) item.next();
						System.out.println(etUser.getText());
						System.out.println(etPass.getText());
						System.out.println(user.getName());
						System.out.println(user.getPassword());
						if(etUser.getText().toString().equals(user.getName()) && etPass.getText().toString().equals(user.getPassword())) {
							System.out.println("EL usuario y el password coinciden!");
							matched = true;
							break;
						} else {
							matched = false;
						}
					}
					if(matched) {
						//Intent i = new Intent(getApplicationContext(), ListNetworksActivity.class);
						Intent i = new Intent(getApplicationContext(), MapNetworkActivity.class);
						startActivity(i);
					} else {
						errorAlert(mContext, "Ups!", "Usuario o contraseña no validos");
					}
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
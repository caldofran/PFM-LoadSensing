package com.uoc.loadsensing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uoc.loadsensing.LoginActivity;
import com.uoc.loadsensing.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends LoadSensingActivity {
	
	protected boolean _active = true;
	protected int _splashTime = 3000; 
	public Context mContext;
	private JSONObject jUsersObject;
	
	private String jUsersString = "{\"users\":" +
			"[{\"userName\": \"Ruben\", \"password\": \"1234\"}," +
			" {\"userName\": \"Jesus\", \"password\": \"4321\"}]}";
	
	private void loadJSON() {
		try {
			jUsersObject = new JSONObject(jUsersString);
			JSONArray menuitemArray = jUsersObject.getJSONArray("users");
			
			for (int i = 0; i < menuitemArray.length(); i++) {
				System.out.println(menuitemArray.getJSONObject(i)
						.getString("userName").toString());
				System.out.println(menuitemArray.getJSONObject(i).getString(
						"password").toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.splash_screen_layout);
        mContext = this;
        AppContext = getApplicationContext();
        
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                	loadJSON();
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                    	sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                   	startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        };
        splashTread.start();
 	}

}

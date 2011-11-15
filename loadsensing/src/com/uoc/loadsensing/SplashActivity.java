package com.uoc.loadsensing;

import com.uoc.loadsensing.LoginActivity;
import com.uoc.loadsensing.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends LoadSensingActivity {
	
	protected boolean _active = true;
	protected int _splashTime = 3000; 
	public Context mContext;
	
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

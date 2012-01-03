package com.uoc.loadsensing;

/**
 * UOC - Universitat Oberta de Catalunya
 * Proyecto Final Máster Software Libre
 * Septiembre 2011
 * 
 * LoadSensing para WorldSensing
 * 
 * @authors
 * 		Rubén Méndez Puente
 * 		Jesús Sánchez-Migallón Pérez
 * 
 */


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QRCodeActivity extends LoadSensingActivity {

    TextView txtQRCode;
    Button btnScanQR;
    Button btnSendQR;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.qrcode_layout);
            
            enableBottomMenu(QRCODE_SECTION);
            
            txtQRCode = (TextView) findViewById(R.id.editTextShowLocation);
            btnSendQR = (Button)findViewById(R.id.btnSendQR);
            btnScanQR = (Button)findViewById(R.id.btnScanQR);
            
            /** Called when click button to Scan QR Code */
            btnScanQR.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {

    	            if ( isIntentAvailable(getApplicationContext(), "com.google.zxing.client.android.SCAN") ) {
    		            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
    		            intent.setPackage("com.google.zxing.client.android");
    		
    		            intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
    		            startActivityForResult(intent, 0);       
    	            } else {
    	            	Toast.makeText(getApplicationContext(), "Instale Barcode Scanner desde Android Market", Toast.LENGTH_LONG).show();
    	            }
    			}
    		});
            
            /** Called when click button to Send QR to the Server */
            btnSendQR.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    					
    	            	Toast.makeText(getApplicationContext(), txtQRCode.getText(), Toast.LENGTH_LONG).show();
    			}
    		});            

    }


    /** Called When the Zxing Activity return the Result */ 
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
       
            if (requestCode == 0) {
                    if (resultCode == RESULT_OK) {
                            String contents = intent.getStringExtra("SCAN_RESULT");
                            txtQRCode.setText(contents);
                            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                            
                    } else if (resultCode == RESULT_CANCELED) {
                            txtQRCode.setText("Scan Cancelled. Please try again");
               }
            }
    }
    
    /** Called to check Intent is Available on the Device */
    public boolean isIntentAvailable( Context context, String action ) {
    	final PackageManager packageManager = context.getPackageManager();
    	final Intent intent = new Intent(action);
    	List<ResolveInfo> list = 
    			packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
    	
    	return list.size() >0;
    }
}
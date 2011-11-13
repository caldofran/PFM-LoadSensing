package com.uoc.loadsensing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;


public class LoadSensingActivity extends Activity {
	
	private static final int MAPNETWORK_ACTIVITY = 0;
	private static final int LISTNETWORKS_ACTIVITY = 1;
	private static final int QRCODE_ACTIVITY = 2;
	
	public static final String MAP_SECTION = "MAP";
	public static final String LISTNETWORKS_SECTION = "LIST";
	public static final String QRCODE_SECTION = "QRCODE";
	
	public static Context AppContext = null;
	
	public void startActivity(int activityReference) {

		final Intent ActivityIntent = new Intent();

		switch (activityReference) 
		{
			case MAPNETWORK_ACTIVITY: 
				ActivityIntent.setClass(getApplicationContext(), MapNetworkActivity.class);
				break;

			case LISTNETWORKS_ACTIVITY: 
				ActivityIntent.setClass(getApplicationContext(), ListNetworkActivity.class);
				break;
				
			case QRCODE_ACTIVITY: 
				ActivityIntent.setClass(getApplicationContext(), QRCodeActivity.class);
				break;				
		}

		try {
			startActivity(ActivityIntent);
		} catch (Exception e) {}
	}
	
	public void enableBottomMenu(final String section) {

		final ImageButton img_btn_map 	= (ImageButton) findViewById(R.id.btn_map);
		final ImageButton img_btn_list 	= (ImageButton) findViewById(R.id.btn_listnetwork);
		final ImageButton img_bt_qrcode	= (ImageButton) findViewById(R.id.btn_qrcode);

		if (section.compareTo(MAP_SECTION) != 0) {
			img_btn_map.setImageResource(R.drawable.btn_map);

			img_btn_map.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(0);
				}
			});
		} else {
			img_btn_map.setImageResource(R.drawable.btn_map_enabled);
		}

		if (section.compareTo(LISTNETWORKS_SECTION) != 0) {
			img_btn_list.setImageResource(R.drawable.btn_listnetwork);

			img_btn_list.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(1);
				}
			});
		} else {
			img_btn_list.setImageResource(R.drawable.btn_listnetwork_enabled);
		}

		if (section.compareTo(QRCODE_SECTION) != 0) {
			img_bt_qrcode.setImageResource(R.drawable.btn_qrcode);
			img_bt_qrcode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(2);
				}
			});

		} else {
			img_bt_qrcode.setImageResource(R.drawable.btn_qrcode_enabled);
		}

	}
	
}

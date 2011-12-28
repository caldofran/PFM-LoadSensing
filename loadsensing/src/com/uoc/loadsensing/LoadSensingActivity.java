package com.uoc.loadsensing;

import java.util.ArrayList;
import java.util.Vector;

import com.uoc.loadsensing.beans.DeviceBean;
import com.uoc.loadsensing.beans.NetworkBean;
import com.uoc.loadsensing.beans.SensorBean;
import com.uoc.loadsensing.beans.Users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;


public class LoadSensingActivity extends Activity {
	
	private static final int MAPNETWORK_ACTIVITY 	= 0;
	private static final int LISTNETWORKS_ACTIVITY	= 1;
	private static final int QRCODE_ACTIVITY 		= 2;
	
	public static final String MAP_SECTION 			= "MAP";
	public static final String LISTNETWORKS_SECTION = "LIST";
	public static final String QRCODE_SECTION 		= "QRCODE";
	
	private static final int NT_STATE_ACTIVITY	= 3;
	private static final int NT_IMAGES_ACTIVITY = 4;
	private static final int NT_NETWORK_ACTIVITY = 5;
	
	public static final String NT_NETWORK_SECTION	= "NTNETWORK";
	public static final String NT_STATE_SECTION		= "NTSTATE";
	public static final String NT_IMAGES_SECTION	= "NTIMAGES";
	
	public static int CAMERA_INTENT = 6000;
	public static int SELECT_PICTURE_INTENT = 5000;
	
	public static Context AppContext = null;
	
	// Lista de Redes
	public static ArrayList<NetworkBean> array_networks = null;
	public static int network_selected = -1;
	
	// Lista de Sensores
	public static ArrayList<SensorBean> array_sensors = null;
	public static int sensor_selected = -1;
	
<<<<<<< HEAD
	// Lista de Usuarios
	public static ArrayList<Users> array_users = null;
=======
	// Lista de Devices
	public static ArrayList<DeviceBean> array_devices = null;
	
	// Imagenes
	//public static ArrayList<String> PicturesPathList = null;
	//public static Vector<String> PicturesPathList = null;
	
>>>>>>> 7e5637bb546f7b90c4d25711e5975ebe86ea1aa6
	
	public void startActivity(int activityReference) {

		final Intent ActivityIntent = new Intent();

		switch (activityReference) 
		{
			case MAPNETWORK_ACTIVITY: 
				ActivityIntent.setClass(getApplicationContext(), MapNetworkActivity.class);
				break;

			case LISTNETWORKS_ACTIVITY: 
				ActivityIntent.setClass(getApplicationContext(), ListNetworksActivity.class);
				break;
				
			case QRCODE_ACTIVITY: 
				ActivityIntent.setClass(getApplicationContext(), QRCodeActivity.class);
				break;			
			
			case NT_STATE_ACTIVITY:
				ActivityIntent.setClass(getApplicationContext(), SingleNetworkStateActivity.class);
				break;			

			case NT_IMAGES_ACTIVITY:
				ActivityIntent.setClass(getApplicationContext(), SingleNetworkImagesActivity.class);
				break;			

			case NT_NETWORK_ACTIVITY:
				ActivityIntent.setClass(getApplicationContext(), SingleNetworkActivity.class);
				break;				
		}

		try {
			if ( array_networks != null && network_selected >= 0 ) {
				ActivityIntent.putExtra("current_network", network_selected);
			}
			startActivity(ActivityIntent);
		} catch (Exception e) {}
	}
	
	public void enableBottomMenu(final String section) {

		final ImageButton img_btn_map 	= (ImageButton) findViewById(R.id.btn_map);
		final ImageButton img_btn_list 	= (ImageButton) findViewById(R.id.btn_listnetwork);
		final ImageButton img_bt_qrcode	= (ImageButton) findViewById(R.id.btn_qrcode);

		if (section.compareTo(MAP_SECTION) != 0) {
			img_btn_map.setImageResource(R.drawable.btn_map_btn);

			img_btn_map.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(MAPNETWORK_ACTIVITY);
				}
			});
		} else {
			img_btn_map.setImageResource(R.drawable.btn_map_btn_enabled);
		}

		if (section.compareTo(LISTNETWORKS_SECTION) != 0) {
			img_btn_list.setImageResource(R.drawable.btn_listnetwork_btn);

			img_btn_list.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(LISTNETWORKS_ACTIVITY);
				}
			});
		} else {
			img_btn_list.setImageResource(R.drawable.btn_listnetwork_btn_enabled);
		}

		if (section.compareTo(QRCODE_SECTION) != 0) {
			img_bt_qrcode.setImageResource(R.drawable.btn_qrcode_btn);
			img_bt_qrcode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(QRCODE_ACTIVITY);
				}
			});

		} else {
			img_bt_qrcode.setImageResource(R.drawable.btn_qrcode_btn_enabled);
		}

	}
	
	public void enableNetworkBottomMenu(final String section) {

		final ImageButton img_btn_state 	= (ImageButton) findViewById(R.id.btn_state);
		final ImageButton img_btn_images 	= (ImageButton) findViewById(R.id.btn_images);
		//final ImageButton img_btn_refresh	= (ImageButton) findViewById(R.id.btn_refresh);
		final ImageButton img_btn_network	= (ImageButton) findViewById(R.id.btn_network);

		if (section.compareTo(NT_NETWORK_SECTION) != 0) {
			img_btn_network.setImageResource(R.drawable.btn_network_btn);

			img_btn_network.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(NT_NETWORK_ACTIVITY);
				}
			});
		} else {
			img_btn_network.setImageResource(R.drawable.btn_network_btn_enabled);
		}		
		
		if (section.compareTo(NT_STATE_SECTION) != 0) {
			img_btn_state.setImageResource(R.drawable.btn_state_btn);

			img_btn_state.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(NT_STATE_ACTIVITY);
				}
			});
		} else {
			img_btn_state.setImageResource(R.drawable.btn_state_btn_enabled);
		}

		if (section.compareTo(NT_IMAGES_SECTION) != 0) {
			img_btn_images.setImageResource(R.drawable.btn_images_btn);

			img_btn_images.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(NT_IMAGES_ACTIVITY);
				}
			});
		} else {
			img_btn_images.setImageResource(R.drawable.btn_images_btn_enabled);
		}

//		if (section.compareTo(NT_REFRESH_SECTION) != 0) {
//			img_btn_refresh.setImageResource(R.drawable.btn_refresh_btn);
//			img_btn_refresh.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					startActivity(NT_NETWORK_ACTIVITY);
//					//startActivity(getIntent()); finish();
//				}
//			});
//
//		} else {
//			img_btn_refresh.setImageResource(R.drawable.btn_refresh_btn_enabled);
//		}

	}	
	
	public void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
}

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

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;
import com.uoc.loadsensing.beans.NetworkBean;
import com.uoc.loadsensing.beans.SensorBean;
import com.uoc.loadsensing.items.ItemizedOverlayItemsForSensors;
import com.uoc.loadsensing.LoadSensingActivity;

import com.uoc.loadsensing.R;


public class MapSensorActivity extends MapActivity {
	private static final int LISTNETWORKS_ACTIVITY	= 1;
	private static final int QRCODE_ACTIVITY 		= 2;
	
	LoadSensingActivity loadSensing = new LoadSensingActivity();
	
	MapView mapV;
	MapController mc;
	Context mContext;
	
	//Menu bar methods
    public void startActivity(int activityReference) {

		final Intent activityIntent = new Intent();

		switch (activityReference) 
		{
			case LISTNETWORKS_ACTIVITY:
				activityIntent.setClass(getApplicationContext(), ListNetworksActivity.class);
				break;
				
			case QRCODE_ACTIVITY: 
				activityIntent.setClass(getApplicationContext(), QRCodeActivity.class);
				break;				
		}
		
		startActivity(activityIntent);
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        final Bundle bundle = getIntent().getExtras();
        // Recogemos informacion del Intent
        int sNetworkId = bundle.getInt("current_network");
        System.out.println("Red pasada al mapa: " + sNetworkId);
        NetworkBean mNetwork = new NetworkBean();
        mNetwork = loadSensing.array_networks.get(sNetworkId);
        
        mContext = this;
        setContentView(R.layout.mapnetwork_layout);
        
        TextView title = (TextView) findViewById(R.id.map_name);
        System.out.println("Nombre del mapa: " + mNetwork.getName());
        title.setText("" + mNetwork.getName());
        
        System.out.println("ID del layout mapa: "+R.id.mapa);
        mapV = (MapView) findViewById(R.id.mapa);
        if(mapV == null) {
        	System.out.println("MapView es null");
        }
        
        //Iteramos sobre los sensores
        System.out.println("Creamos iterator para recorrer la lista sensores...");
        Iterator<SensorBean> iter = loadSensing.array_sensors.iterator();
		SensorBean sensor = new SensorBean();
		System.out.println("Obtenemos los recursos para pintar el mapa...");
		//Usando MyItemizedOverlay
        Drawable drawable = this.getResources().getDrawable(R.drawable.world);
        System.out.println("Obtenida imagen para los markers...");
        ItemizedOverlayItemsForSensors itemizedOverlay = new ItemizedOverlayItemsForSensors(drawable,mapV);
        System.out.println("Inicializamos los itemizedOverlay...");
        Double lat;
		Double lng;
		System.out.println("Vamos a iterar sobre los sensores...");
		while (iter.hasNext()) {
			System.out.println("Entramos en el bucle");
			sensor = (SensorBean) iter.next();
			System.out.println("NetworkId del sensor: " + sensor.getNetworkId());
			System.out.println("NetworkId pasada desde la vista previa: " + sNetworkId);
			if(Integer.parseInt(sensor.getNetworkId()) == sNetworkId + 1) {
				lat = sensor.getLatitude() * 1E6;
				lng = sensor.getLongitude() * 1E6;
				GeoPoint geoPoint = new GeoPoint(lat.intValue(), lng.intValue());
				OverlayItem overlayItem = new OverlayItem(geoPoint, sensor.getSensor(), sensor.getType());
				itemizedOverlay.addOverlay(overlayItem);
			}
		}
		mapV.getOverlays().add(itemizedOverlay);
        mc = mapV.getController();
        
        //TODO: centrar el mapa en una de las redes
        //TODO: setear el zoom para que se vean los puntos correctamente
        lat = mNetwork.getLatitude() * 1E6;
		lng = mNetwork.getLongitude() * 1E6;
		GeoPoint geoPoint = new GeoPoint(lat.intValue(), lng.intValue());
        mc.animateTo(geoPoint);
        mc.setZoom(16);

        // Eliminar este codigo temporal
        mapV.setBuiltInZoomControls(true);
        mapV.setSatellite(false);
        mapV.invalidate();
        
        /**
         * When the Location button is clicked this starts to locate user and
         * add an overlay to the current mapView.
         * */
        final ImageButton my_location_button = (ImageButton)findViewById(R.id.my_location_button);
        my_location_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mContext, mapV);
                mapV.getOverlays().add(myLocationOverlay);
                myLocationOverlay.enableCompass();
                myLocationOverlay.enableMyLocation();
                System.out.println("Obteniendo tu ubicacion");
                myLocationOverlay.runOnFirstFix(new Runnable() {
                    public void run() {
                        mc.animateTo(myLocationOverlay.getMyLocation());
                    }
                });
            }
        });
        
        mapV.invalidate();
        
        // Opciones del tabBar
        final ImageButton tabBarMapButton = (ImageButton) findViewById(R.id.btn_map);
        final ImageButton tabBarListButton = (ImageButton) findViewById(R.id.btn_listnetwork);
        final ImageButton tabBarQrcodeButton = (ImageButton) findViewById(R.id.btn_qrcode);
        //Marcamos boton mapa del tabBar como seleccionado
        tabBarMapButton.setImageResource(R.drawable.btn_map_btn_enabled);
        tabBarListButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tabBarListButton.setImageResource(R.drawable.btn_listnetwork_btn_enabled);
				startActivity(LISTNETWORKS_ACTIVITY);
			}
		});
        
        tabBarQrcodeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tabBarQrcodeButton.setImageResource(R.drawable.btn_qrcode_btn_enabled);
				startActivity(QRCODE_ACTIVITY);
			}
		});
    }
 
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
    /**
     * requery on the Network result set whenever this Activity becomes visible.
     * */
    @Override
    public void onResume() {
    	super.onResume();
    }
    
    /**
     * Optimize use of Cursor resources when View is paused or destroyed
     * */
    @Override
    public void onPause() {
    	super.onPause();
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy(); }
}

package com.uoc.loadsensing;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
//import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;
import com.uoc.loadsensing.items.ItemizedOverlayItems;

import com.uoc.loadsensing.R;


public class MapNetworkActivity extends MapActivity {
	private static final int LISTNETWORKS_ACTIVITY	= 1;
	private static final int QRCODE_ACTIVITY 		= 2;
	
	MapView mapView;
	MapController mc;
	Context mContext;
	//Cursor networkCursor; //Cursor that returns the networks we want to display on the map
	
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
        
        mContext = this;
        setContentView(R.layout.mapnetwork_layout);
        
        //String networkURI = NetworkProvider.CONTENT_URI;
        //networkCursor = getContentResolver().query(networkURI, null, null, null, null);
        
        mapView = (MapView) findViewById(R.id.mapa);
        //NetworksOverlay no = new NetworksOverlay(mContext); // Temporal
        //NetworksOverlay no = new NetworksOverlay(networkCursor);
        
        //Usando MyItemizedOverlay
        Drawable drawable = this.getResources().getDrawable(R.drawable.world);
        ItemizedOverlayItems itemizedOverlay = new ItemizedOverlayItems(drawable,mapView);
        Double lat = 40.40281 * 1E6;
		Double lng = -3.710461 * 1E6;
		GeoPoint geoPoint1 = new GeoPoint(lat.intValue(), lng.intValue());
		OverlayItem overlayItem1 = new OverlayItem(geoPoint1, "Primer punto", "Subtitulo1");
		itemizedOverlay.addOverlay(overlayItem1);
		lat = 40.408627 * 1E6;
		lng = -3.700998 * 1E6;
		GeoPoint geoPoint2 = new GeoPoint(lat.intValue(), lng.intValue());
		OverlayItem overlayItem2 = new OverlayItem(geoPoint2, "Segundo punto", "Subtitulo2");
		itemizedOverlay.addOverlay(overlayItem2);
		mapView.getOverlays().add(itemizedOverlay);
        //Fin del uso de ItemizedOverlay
        //mapView.getOverlays().add(no);
        
        mc = mapView.getController();
        
        //TODO: centrar el mapa en una de las redes
        //TODO: setear el zoom para que se vean los puntos correctamente
        lat = 40.40281 * 1E6;
		lng = -3.710461 * 1E6;
		GeoPoint geoPoint = new GeoPoint(lat.intValue(), lng.intValue());
        mc.animateTo(geoPoint);
        mc.setZoom(15);
        // Eliminar este codigo temporal
        
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(false);
        mapView.invalidate();
        
        /**
         * When the Location button is clicked this starts to locate user and
         * add an overlay to the current mapView.
         * */
        final ImageButton my_location_button = (ImageButton)findViewById(R.id.my_location_button);
        my_location_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mContext, mapView);
                mapView.getOverlays().add(myLocationOverlay);
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
        
        mapView.invalidate();
        
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
    	//networkCursor.requery();
    	super.onResume();
    }
    
    /**
     * Optimize use of Cursor resources when View is paused or destroyed
     * */
    @Override
    public void onPause() {
    	//networkCursor.deactivate();
    	super.onPause();
    }
    
    @Override
    public void onDestroy() {
    	//networkCursor.close();
    	super.onDestroy(); }
}

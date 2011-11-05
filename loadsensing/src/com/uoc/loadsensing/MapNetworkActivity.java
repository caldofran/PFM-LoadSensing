package com.uoc.loadsensing;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MapNetworkActivity extends MapActivity {
	
	/** Zoom por defecto */
	private static int ZOOM = 15;
	
	private MapView mapView = null;
	private MyLocationOverlay myLocationOverlay;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapnetwork_layout);
        
        //Obtenemos una referencia al control MapView
        mapView = (MapView)findViewById(R.id.mapa);
 
        //Mostramos los controles de zoom sobre el mapa y activamos modo Satelite
        mapView.setBuiltInZoomControls(true);     
        mapView.setSatellite(true);
        
		// Creamos Overlay que mostrara el Current Location
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		
		// Anyadimos el Overlay al MapView y actualizamos
		mapView.getOverlays().add(myLocationOverlay);
		mapView.postInvalidate();
		
		// Locazimos ubicacion y aumentamos Zoom
		zoomMyLocation();
        
    }
	
	protected boolean isRouteDisplayed() {
			return false;
	}

	protected void onResume() {
		super.onResume();
		// Cuando se lance el metodo onResume nos registramos a posibles actualizacions de posicion
		myLocationOverlay.enableMyLocation();
	}

	protected void onPause() {
		super.onPause();
		// Cuando se pause la Activity eliminamos el listening para actualizaciones de posicion
		myLocationOverlay.disableMyLocation();
	}
		
	/**
	 * Recogemos Ubicacion y establecemos zoom
	 */
	private void zoomMyLocation() {
		// TODO Aqui deberemos recoger los GeoPoint de las Redes disponibles y añadirlos mediante animateTo()
		GeoPoint myLocationGeoPoint = myLocationOverlay.getMyLocation();
		if(myLocationGeoPoint != null) {
			mapView.getController().animateTo(myLocationGeoPoint);
			mapView.getController().setZoom(ZOOM);
		}

	}
	
}

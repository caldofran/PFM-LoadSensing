package com.uoc.loadsensing;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


public class MapNetworkActivity extends MapActivity {
	MapView mapView;
	MapController mc;
	GeoPoint p;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapnetwork_layout);
        
        mapView = (MapView) findViewById(R.id.mapa);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(false);
        
        mc = mapView.getController();
        
        //Punto al azar, para nosotros lo obtendriamos del Sensor o Red
        String coordinates[] = {"40.40941", "-3.70259"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
        
        p = new GeoPoint(
                (int) (lat * 1E6), 
                (int) (lng * 1E6));
     
        mc.animateTo(p);
        mc.setZoom(17); 
        mapView.invalidate();
    }
 
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}

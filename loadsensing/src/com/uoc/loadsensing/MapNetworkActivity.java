package com.uoc.loadsensing;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import com.uoc.loadsensing.R;


public class MapNetworkActivity extends MapActivity {
	MapView mapView;
	MapController mc;
	GeoPoint p;
	Context mContext;
	
	/** Extendemos la clase overlay para a–adir markers */
	class MapOverlay extends com.google.android.maps.Overlay
	{
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when)
		{
			super.draw(canvas, mapView, shadow);
			
			// Translate the geopoint to screen pixels
			Point screenPts = new Point();
			mapView.getProjection().toPixels(p, screenPts);
			
			// Add the marker
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.world);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 37, null);
			return true;
			
		}
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = this;
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
        
        // Add a location marker
        MapOverlay mapOverlay = new MapOverlay();
        List<Overlay> listOfOverlays = mapView.getOverlays();
        listOfOverlays.clear();
        listOfOverlays.add(mapOverlay);
        
        mc.animateTo(p);
        mc.setZoom(17);
        
        final ImageButton my_location_button = (ImageButton)findViewById(R.id.my_location_button);
        my_location_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mContext, mapView);
                mapView.getOverlays().add(myLocationOverlay);
                myLocationOverlay.enableCompass();
                myLocationOverlay.enableMyLocation();
                System.out.println("Obteniendo tu ubucacion");
                myLocationOverlay.runOnFirstFix(new Runnable() {
                    public void run() {
                        mc.animateTo(myLocationOverlay.getMyLocation());
                    }
                });
            }
        });
        
        mapView.invalidate();
    }
 
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}

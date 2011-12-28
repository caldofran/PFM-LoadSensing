package com.uoc.loadsensing;

import java.util.ArrayList;

//import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

import com.uoc.loadsensing.R;

public class NetworksOverlay extends Overlay {
	Cursor networks; // To pass the networks coordinates from the DDBB
	ArrayList<GeoPoint> networkLocations; // To store all the networks locations
	Context myContext;
	
	// Constructor temporal
	public NetworksOverlay(Context context) {
		super();
		
		myContext = context;
		networkLocations = new ArrayList<GeoPoint>();
		refreshNetworkLocations();
	}
	// Fin de contructor temporal
	
	/**
	 * Constructor that accepts a Cursor to the current network data,
	 * and store that Cursor as an instance variable.
	 * */
	public NetworksOverlay(Cursor cursor) {
		super();
		networks = cursor;
		
		networkLocations = new ArrayList<GeoPoint>();
		refreshNetworkLocations();
		/**
		 * It is registered a DataSetObserver on the results Cursor
		 * that refreshes the Network Location list if a change
		 * in the Network Cursor is detected.
		 * */
		networks.registerDataSetObserver(new DataSetObserver() {
			@Override
			public void onChanged() {
			refreshNetworkLocations(); }
			});
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Projection projection = mapView.getProjection();
		
		if (shadow == false) {
			for(GeoPoint point : networkLocations) {
				// Translate the geopoint to screen pixels
				Point myPoint = new Point();
				projection.toPixels(point, myPoint);
				
				// Add the marker
				Bitmap bmp = BitmapFactory.decodeResource(myContext.getResources(), R.drawable.world);
				canvas.drawBitmap(bmp, myPoint.x, myPoint.y - 37, null);
			}
		}
	}
	
	/** 
	 * refreshNetworkLocations method iterates over the results Cursor 
	 * and extracts the location of each network, extracting the latitude and longitude
	 *  before storing each coordinate in a List of GeoPoints.
	 * */
	private void refreshNetworkLocations() {
		//Esto es temporal hasta que consigamos el API
		Double lat = 40.40281 * 1E6;
		Double lng = -3.710461 * 1E6;
		GeoPoint geoPoint1 = new GeoPoint(lat.intValue(), lng.intValue());
		networkLocations.add(geoPoint1);
		lat = 40.408627 * 1E6;
		lng = -3.700998 * 1E6;
		GeoPoint geoPoint2 = new GeoPoint(lat.intValue(), lng.intValue());
		networkLocations.add(geoPoint2);
		System.out.println(networkLocations.size());
		// Aqui acaba el temporal
		
		/*
		if (networks.moveToFirst())
			do {
			Double lat = networks.getFloat(NetworkProvider.LATITUDE_COLUMN) * 1E6;
			Double lng = networks.getFloat(NetworkProvider.LONGITUDE_COLUMN) * 1E6;
			GeoPoint geoPoint = new GeoPoint(lng.intValue(), lat.intValue());
			networkLocations.add(geoPoint);
			} while(networks.moveToNext());
			*/
	}
}

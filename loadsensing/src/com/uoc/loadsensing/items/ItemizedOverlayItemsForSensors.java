package com.uoc.loadsensing.items;

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
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.uoc.loadsensing.BalloonItemizedOverlay;
import com.uoc.loadsensing.SensorActivity;

public class ItemizedOverlayItemsForSensors extends BalloonItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	private Context c;
		
	public ItemizedOverlayItemsForSensors(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		c = mapView.getContext();
		System.out.println("Inicializado el itemizedOverlayItems");
	}


	public void addOverlay(OverlayItem overlay) {
	    m_overlays.add(overlay);
	    populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	@Override
	public int size() {
		return m_overlays.size();
	}
	
	public void Tap(int index){
		tap_tap(index);
	}
	
	@Override
	protected boolean onBalloonTap(int index) {
		Intent intent = new Intent(c, SensorActivity.class);
		intent.putExtra("current_sensor", index);
		c.startActivity(intent);
		return true;
	}
}

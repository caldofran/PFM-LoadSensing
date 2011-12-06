package com.uoc.loadsensing.items;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.uoc.loadsensing.BalloonItemizedOverlay;

public class ItemizedOverlayItems extends BalloonItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	private Context c;

		
	public ItemizedOverlayItems(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		c = mapView.getContext();
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
		GeoPoint point = m_overlays.get(index).getPoint();
		double lat = point.getLatitudeE6() / 1.0E6;
		double lon = point.getLongitudeE6() / 1.0E6;
		//http://developer.android.com/guide/appendix/g-app-intents.html
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.es/maps?f=q&source=s_q&hl=es&geocode=&q="+lat+","+lon+"&aq=&sll="+lat+","+lon+"&ie=UTF8&z=17"));
		c.startActivity(intent);
		return true;
	}
}

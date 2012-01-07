package com.uoc.loadsensing.items;

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

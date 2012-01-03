package com.uoc.loadsensing;

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

import android.os.Bundle;
import android.webkit.WebView;


public class ImageItemActivity extends LoadSensingActivity {
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_item_layout);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        final WebView i = (WebView) findViewById(R.id.image_item);
        i.getSettings().setBuiltInZoomControls(true);
        i.loadUrl(url);
	}	

}

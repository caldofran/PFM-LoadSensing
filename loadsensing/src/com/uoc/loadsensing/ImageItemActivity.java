package com.uoc.loadsensing;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;


public class ImageItemActivity extends LoadSensingActivity {
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_item_layout);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        final WebView i = (WebView) findViewById(R.id.image_item);
        i.getSettings().setBuiltInZoomControls(true);
        //i.setBackgroundColor(R.color.blue_back);
        
        i.loadUrl(url);
	}	
	

}

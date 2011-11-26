package com.uoc.loadsensing;

import com.uoc.loadsensing.beans.NetworkBean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleNetworkActivity extends LoadSensingActivity {

	NetworkBean mNetwork = null;
	
	WebView embeddedWebView;
	String embeddedWeb = "http://chart.apis.google.com/chart?chxl=0:|2012|2011|2010|2009|2008|2007|1:|0|50|100|2:|min|average|max&chxp=2,10,50.83,90&chxr=0,-5,100&chxt=x,y,r&chs=300x150&cht=bvg&chco=76A4FB,FF9900&chd=t:20,30,10,40|50,50,50,50&chdl=Level+of+Tension|Not+Configured&chdlp=t&chg=20,50&chma=|5&chtt=Network+Current+State&chts=008000,11.5";
	  
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_listnetwork);
        
        TextView txtNetworkName = (TextView) findViewById(R.id.network_name);

        final Bundle bundle = getIntent().getExtras();
        // Recogemos informacion del Intent
        int sNetworkId = bundle.getInt("current_network");
        
        mNetwork = array_networks.get(sNetworkId);

        // Mostramos la informacion recogida
        txtNetworkName.setText(mNetwork.getName());
        
        // Mostramos grafica
        embeddedWebView = (WebView)findViewById(R.id.embeddedwebview);
        embeddedWebView.loadUrl(embeddedWeb);
        
	}
	
	/** 
	 * Metodo que inicializa el Menu Network mediante XML (menu_network.xml) 
	 **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu_network, menu);
        return true;
    }
    
    /**
     * Evento que lanzamos cuando seleccionamos un Item de la Lista
     * 
     **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	// TODO Segun el item (red) seleccionado mostramos Activity asociada
        switch (item.getItemId())
        {
        case R.id.menu_state:
            Toast.makeText(SingleNetworkActivity.this, "State is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_sensors:
        	Toast.makeText(SingleNetworkActivity.this, "Sensors is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_images:
        	Toast.makeText(SingleNetworkActivity.this, "Images is Selected", Toast.LENGTH_SHORT).show();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    	
	
}

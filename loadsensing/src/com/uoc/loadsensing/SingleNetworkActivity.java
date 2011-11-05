package com.uoc.loadsensing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class SingleNetworkActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_listnetwork);
        
        TextView txtNetworkName = (TextView) findViewById(R.id.network_name);

        Intent i = getIntent();
        // Recogemos informacion del Intent
        String sNetworkName = i.getStringExtra("network_name");

        // Mostramos la informacion recogida
        txtNetworkName.setText(sNetworkName);
        
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

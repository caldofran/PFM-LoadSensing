package com.uoc.loadsensing;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uoc.loadsensing.beans.NetworkBean;
import com.uoc.loadsensing.beans.SensorBean;
import com.uoc.loadsensing.utils.Environment;

/**
 * @author  armisael
 */
public class SingleNetworkActivity extends LoadSensingActivity {

	/**
	 * @uml.property  name="mNetwork"
	 * @uml.associationEnd  
	 */
	NetworkBean mNetwork = null;
	public ProgressDialog dialog;
	ListView sensorList;
	ArrayList<SensorBean> aSensorsList; 
    /**
	 * Clase propia que extiende de ArrayAdapter
	 * @uml.property  name="sAdapter"
	 * @uml.associationEnd  
	 */
    private SensorAdapter sAdapter;
 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_listnetwork);
        
        enableNetworkBottomMenu(NT_NETWORK_SECTION);
        
        TextView txtNetworkName = (TextView) findViewById(R.id.network_name);
        TextView txtNetworkLatitude   = (TextView) findViewById(R.id.network_latitude);
        TextView txtNetworkLongitude  = (TextView) findViewById(R.id.network_longitude);

        final Bundle bundle = getIntent().getExtras();
        // Recogemos informacion del Intent
        int sNetworkId = bundle.getInt("current_network");
        
        mNetwork = array_networks.get(sNetworkId);

        // Mostramos la informacion recogida
        txtNetworkName.setText(mNetwork.getName());
        txtNetworkLatitude.setText(String.valueOf("Latitude: "+mNetwork.getLatitude()));
        txtNetworkLongitude.setText(String.valueOf("Longitude: "+mNetwork.getLongitude()));
        
        // Creamos objetos contenedores
        sensorList = (ListView)findViewById(R.id.listSensors);
        aSensorsList = new ArrayList<SensorBean>();        
        
        // Obtenemos la lista de redes
        requestListSensors();
        
	}
	
	
	private void requestListSensors()
    {
		if (!Environment.internetIsAvailable(SingleNetworkActivity.this))
		{
			Environment.errorAlert(getApplicationContext(), getApplicationContext().getString(R.string.no_connection));
		}else{

	        dialog = new ProgressDialog(this);
	        dialog.setProgressStyle(0);
	        dialog.setMessage(this.getString(R.string.lst_retreive_data));
	        dialog.setCancelable(true);
	        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) 
				{
					finish();
				}
				});
	        dialog.setProgress(0);
	        dialog.setMax(100);
	        dialog.show();
			
	        // Recogemos lista de Redes
	        getSensors task = new getSensors();
	        task.execute();

		}
    }		
	
	private class getSensors extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
        	
        }
 
        @Override
        protected Void doInBackground(String... params) {
            // TODO Peticion Asyn a API WS?
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result) {
        	
        	// TODO Utilizar Sensors de WS: Now Fake Items 
        	for ( int i=0; i<10; i++ ) {
        		SensorBean t = new SensorBean();
        		t.setId(0);
        		t.setDescription("2003 sensor strain, channel "+i);
        		t.setSensor("2003-ch."+i);
        		t.setType("A");

        		aSensorsList.add(t);
        	}
        	
        	sAdapter = new SensorAdapter(getApplicationContext(), R.layout.row_sensor, aSensorsList);
        	sensorList.setAdapter(sAdapter);              	
        	
        	sAdapter.notifyDataSetChanged();
        	
                    	
        	// Establecemos accion para click en Network Item
            sensorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
                {
                	
                	Toast.makeText(getApplicationContext(), "Es el "+position, Toast.LENGTH_LONG).show();
                	
    				// Launching new Activity on selecting single List Item
    				Intent i = new Intent(getApplicationContext(), SensorActivity.class);
    				 
    				// sending data to new activity
    				i.putExtra("current_sensor", position);
    				
    				// establish sensor identifiers
    				sensor_selected = position;
    				array_sensors = aSensorsList;
    				
    				// launch activity
    				startActivity(i);                 	
           	
                }
            });            	
            
        	// Al final quitamos dialog
        	dialog.dismiss();
        }
		
	}
	

    /**
     * Clase Adapter customizada para nuestra propia lista de Redes
     *
     */
    private class SensorAdapter extends ArrayAdapter<SensorBean> {

        private ArrayList<SensorBean> items;

        public SensorAdapter(Context context, int textViewResourceId, ArrayList<SensorBean> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = vi.inflate(R.layout.row_sensor, null);
                }
                
                SensorBean o = items.get(position);
                if (o != null) {
                        TextView tId		= (TextView) convertView.findViewById(R.id.sensor_id);
                        TextView tSensor	= (TextView) convertView.findViewById(R.id.sensor_name);
                        TextView tDesc		= (TextView) convertView.findViewById(R.id.sensor_description);
                        TextView tType		= (TextView) convertView.findViewById(R.id.sensor_type);
                        
                        if (tId != null) {
                        	tId.setText(String.valueOf(o.getId()));         
                        }
                        
                        if (tSensor != null) {
                        	tSensor.setText(o.getSensor());
                        }
                        
                        if (tDesc != null) {
                        	tDesc.setText(o.getDescription());
                        }                        
                        
                        if (tType != null ) {
                        	tType.setText(o.getType());
                        }
                        
                }
                return convertView;
        }
    }
       		
}

package com.uoc.loadsensing;

import java.util.ArrayList;

import com.uoc.loadsensing.beans.NetworkBean;
import com.uoc.loadsensing.beans.SensorBean;
import com.uoc.loadsensing.utils.Environment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.uoc.loadsensing.R;

public class SingleNetworkActivity extends Activity {
=======
public class SingleNetworkActivity extends LoadSensingActivity {
>>>>>>> de714f52eef47fe03c38db67efbec91e89a45a83

	NetworkBean mNetwork = null;
	public ProgressDialog dialog;
	ListView sensorList;
	ArrayList<SensorBean> aSensorsList; 
    /** Clase propia que extiende de ArrayAdapter */
    private SensorAdapter sAdapter;
	
	WebView embeddedWebView;
	String embeddedWeb = "http://chart.apis.google.com/chart?chxl=0:|2012|2011|2010|2009|2008|2007|1:|0|50|100|2:|min|average|max&chxp=2,10,50.83,90&chxr=0,-5,100&chxt=x,y,r&chs=300x150&cht=bvg&chco=76A4FB,FF9900&chd=t:20,30,10,40|50,50,50,50&chdl=Level+of+Tension|Not+Configured&chdlp=t&chg=20,50&chma=|5&chtt=Network+Current+State&chts=008000,11.5";
	  
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_listnetwork);
        
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
        
        // Mostramos grafica
        embeddedWebView = (WebView)findViewById(R.id.embeddedwebview);
        embeddedWebView.loadUrl(embeddedWeb);
        
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

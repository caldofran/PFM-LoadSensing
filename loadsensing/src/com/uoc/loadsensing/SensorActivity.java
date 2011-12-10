package com.uoc.loadsensing;


import java.util.ArrayList;

import com.uoc.loadsensing.beans.DeviceBean;
import com.uoc.loadsensing.beans.SensorBean;
import com.uoc.loadsensing.utils.Environment;

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



public class SensorActivity extends LoadSensingActivity {

	/**
	 * @uml.property  name="mSensor"
	 * @uml.associationEnd  
	 */
	SensorBean mSensor = null;
	
	public ProgressDialog dialog;
	ListView deviceList;
	ArrayList<DeviceBean> aDeviceList; 
	
    /**
	 * Clase propia que extiende de ArrayAdapter
	 * @uml.property  name="sAdapter"
	 * @uml.associationEnd  
	 */
    private DeviceAdapter sAdapter;
	
	// Grafica demo
	WebView embeddedChart;
	String embeddedWeb ="http://chart.apis.google.com/chart"+
			   "?chxl=1:||Time|3:||Hz"+
			   "&chxr=0,0,50|2,5,100"+
			   "&chxs=0,676767,11.5,0,lt,676767"+
			   "&chxt=x,x,y,y"+
			   "&chs=320x150"+
			   "&cht=lc"+
			   "&chco=76A4FB"+
			   "&chd=s:QRSUWZcejmkjlghgbefe"+
			   "&chls=2"+
			   "&chma=40,20,20,30"+
			   "&chtt=Vibrating+Wire"+
			   "&chts=3072F3,10.5";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sensor_layout);
		
		final Bundle bundle = getIntent().getExtras();
        
		// Recogemos informacion del Intent
        int sSensorId = bundle.getInt("current_sensor");
        mSensor = array_sensors.get(sSensorId);
        
        TextView txtSensorId = (TextView) findViewById(R.id.sensor_title);
        txtSensorId.setText("Sensor "+String.valueOf(mSensor.getId()));
        
        TextView txtSensorSerial = (TextView) findViewById(R.id.sensor_serial);
        txtSensorSerial.setText(String.valueOf(mSensor.getSerial_number()));
        
        TextView txtSensorMaxLoad = (TextView) findViewById(R.id.sensor_maxload);
        txtSensorMaxLoad.setText(String.valueOf(mSensor.getMax_load()));
        
        TextView txtSensorSensitivity = (TextView) findViewById(R.id.sensor_sensitivity);
        txtSensorSensitivity.setText(String.valueOf(mSensor.getSensitivity()));
        
        TextView txtSensorOffset = (TextView) findViewById(R.id.sensor_offset);
        txtSensorOffset.setText(String.valueOf(mSensor.getOffset()));
        
        TextView txtSensorAlarm = (TextView) findViewById(R.id.sensor_alarm);
        txtSensorAlarm.setText(String.valueOf(mSensor.getAlarm()));
        
        
        // Mostramos grafica de estado
        embeddedChart = (WebView)findViewById(R.id.sensor_state_chart);
        embeddedChart.loadUrl(embeddedWeb);          
        
        // Creamos objetos contenedores
        deviceList = (ListView)findViewById(R.id.listDevices);
        aDeviceList = new ArrayList<DeviceBean>();        
        
        // Obtenemos la lista de redes
        requestListDevices();        
        
	}
	
	private void requestListDevices()
    {
		if (!Environment.internetIsAvailable(SensorActivity.this))
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
	        getDevices task = new getDevices();
	        task.execute();

		}
    }		
	
	private class getDevices extends AsyncTask<String, Void, Void>{

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
        	
        	// TODO Utilizar Devices de WS: Now Fake Items 
        	for ( int i=0; i<10; i++ ) {
        		DeviceBean d = new DeviceBean();
        		d.setName("M"+i);
        		d.setId(i);
        		d.setTemperature(10*1);
        		d.setVoltage(25);
        		
        		aDeviceList.add(d);
        	}
        	
        	sAdapter = new DeviceAdapter(getApplicationContext(), R.layout.row_device, aDeviceList);
        	deviceList.setAdapter(sAdapter);              	
        	sAdapter.notifyDataSetChanged();

        	// Al final quitamos dialog
        	dialog.dismiss();
        }

	}	
	
    /**
     * Clase Adapter customizada para nuestra propia lista de Redes
     *
     */
    private class DeviceAdapter extends ArrayAdapter<DeviceBean> {

        private ArrayList<DeviceBean> items;

        public DeviceAdapter(Context context, int textViewResourceId, ArrayList<DeviceBean> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = vi.inflate(R.layout.row_device, null);
                }
                
                DeviceBean o = items.get(position);
                if (o != null) {
                		TextView tName		= (TextView) convertView.findViewById(R.id.dev_name);
                        TextView tId		= (TextView) convertView.findViewById(R.id.dev_id);
                        TextView tTemp		= (TextView) convertView.findViewById(R.id.dev_temp);
                        TextView tVoltage	= (TextView) convertView.findViewById(R.id.dev_voltage);
                        
                        if (tName != null ) {
                        	tName.setText(o.getName());
                        }
                        
                        if (tId != null) {
                        	tId.setText(String.valueOf(o.getId()));         
                        }
                        
                        if (tTemp != null) {
                        	tTemp.setText(String.valueOf(o.getTemperature()));
                        }
                        
                        if (tVoltage != null) {
                        	tVoltage.setText(String.valueOf(o.getVoltage()));
                        }                        
                        
                }
                return convertView;
        }
    }	

}

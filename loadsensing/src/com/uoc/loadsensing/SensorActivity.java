package com.uoc.loadsensing;

import com.uoc.loadsensing.beans.SensorBean;

import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends LoadSensingActivity {

	SensorBean mSensor = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sensor_layout);
		
		final Bundle bundle = getIntent().getExtras();
        
		// Recogemos informacion del Intent
        int sSensorId = bundle.getInt("current_sensor");
        mSensor = array_sensors.get(sSensorId);
        
        TextView txtSensorId = (TextView) findViewById(R.id.sensor_title);
        txtSensorId.setText(String.valueOf("Sensor "+mSensor.getId()));
	}

}

package com.uoc.loadsensing;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uoc.loadsensing.LoginActivity;
import com.uoc.loadsensing.R;
import com.uoc.loadsensing.beans.NetworkBean;
import com.uoc.loadsensing.beans.SensorBean;
import com.uoc.loadsensing.beans.Users;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends LoadSensingActivity {
	
	protected boolean _active = true;
	protected int _splashTime = 3000; 
	public Context mContext;
	private JSONObject jUsersObject;
	private JSONObject jNetworksObject;
	private JSONObject jSensorsObject;
	
	private String jUsersString = "{\"users\":" +
			"[{\"userName\": \"Ruben\", \"password\": \"1234\"}," +
			" {\"userName\": \"Jesus\", \"password\": \"4321\"}]}";
	
	private String jNetworks = "{\"networks\":" +
			"[{\"id\": \"01\", \"name\": \"Madrid\", \"description\": \"Blah blah blah\", \"numberOfSensors\": \"4\", \"latitude\": \"40.40255\", \"longitude\": \"-3.71128\"," +
				"\"images\": [{\"path\": \"localPath1\"}, " +
						"{\"path\": \"localPath2\"}]}," +
			"{\"id\": \"02\", \"name\": \"Barcelona\", \"description\": \"Bleh bleh bleh\", \"numberOfSensors\": \"3\", \"latitude\": \"41.38085\", \"longitude\": \"2.12161\"," +
				"\"images\": [{\"path\": \"localPath3\"}, " +
					"{\"path\": \"localPath4\"}]}]}";
	
	private String jSensorsString = "{\"sensors\": " +
			"[{\"id\": \"01\", \"sensor\": \"Sensor01_Madrid\", \"type\": \"temperatura\", \"description\": \"Sensor de temperatura de la Red de Madrid\", \"networkId\": \"01\", \"latitude\": \"40.403137\", \"longitude\": \"-3.712134\"}, " +
			"{\"id\": \"02\", \"sensor\": \"Sensor02_Madrid\", \"type\": \"temperatura\", \"description\":  \"Sensor de temperatura de la Red de Madrid\", \"networkId\": \"01\", \"latitude\": \"40.404444\", \"longitude\": \"-3.709559\"}, " +
			"{\"id\": \"03\", \"sensor\": \"Sensor03_Madrid\", \"type\": \"temperatura\", \"description\":  \"Sensor de temperatura de la Red de Madrid\", \"networkId\": \"01\", \"latitude\": \"40.403954\", \"longitude\": \"-3.707886\"}, " +
			"{\"id\": \"04\", \"sensor\": \"Sensor04_Madrid\", \"type\": \"temperatura\", \"description\":  \"Sensor de temperatura de la Red de Madrid\", \"networkId\": \"01\", \"latitude\": \"40.402010\", \"longitude\": \"-3.707328\"}, " +
			"{\"id\": \"05\", \"sensor\": \"Sensor05_Barcelona\", \"type\": \"presion\", \"description\": \"Sensor de presion de Barcelona\", \"networkId\": \"02\", \"latitude\": \"41.382025\", \"longitude\": \"2.123387\"}, " +
			"{\"id\": \"06\", \"sensor\": \"Sensor06_Barcelona\", \"type\": \"presion\", \"description\": \"Sensor de presion de Barcelona\", \"networkId\": \"02\", \"latitude\": \"41.380383\", \"longitude\": \"2.124417\"}, " +
			"{\"id\": \"07\", \"sensor\": \"Sensor07_Barcelona\", \"type\": \"presion\", \"description\": \"Sensor de presion de Barcelona\", \"networkId\": \"02\", \"latitude\": \"41.380174\", \"longitude\": \"2.119610\"}]}";
	
	private void loadJSON() {
		array_users = new ArrayList<Users>();
		array_networks = new ArrayList<NetworkBean>();
		array_sensors = new ArrayList<SensorBean>();
		
		try {
			/* Parseo de usuarios */
			jUsersObject = new JSONObject(jUsersString);
			JSONArray usersItemArray = jUsersObject.getJSONArray("users");
			
			for (int i = 0; i < usersItemArray.length(); i++) {
				Users user = new Users();
				
				user.setName(usersItemArray.getJSONObject(i).getString("userName").toString());
				System.out.println(user.getName());
				
				user.setPassword(usersItemArray.getJSONObject(i).getString("password").toString());
				System.out.println(user.getPassword());
				
				array_users.add(user);
			}
			
			System.out.println("Numero de usuarios en el array: " + array_users.size());
			
			/* Parseo de redes */
			jNetworksObject = new JSONObject(jNetworks);
			JSONArray networksItemArray = jNetworksObject.getJSONArray("networks");
			
			for (int i = 0; i < networksItemArray.length(); i++) {
				NetworkBean network = new NetworkBean();
				
				network.setId(Integer.parseInt(networksItemArray.getJSONObject(i)
						.getString("id").toString()));
				System.out.println(network.getId());
				
				network.setName(networksItemArray.getJSONObject(i)
						.getString("name").toString());
				System.out.println(network.getName());
				
				network.setDescription(networksItemArray.getJSONObject(i).getString(
						"description").toString());
				System.out.println(network.getDescription());
				
				network.setNum_of_sensors(Integer.parseInt(networksItemArray.getJSONObject(i).getString(
						"numberOfSensors").toString()));
				System.out.println(String.valueOf(network.getNum_of_sensors()));
				
				network.setLatitude(Float.parseFloat(networksItemArray.getJSONObject(i).getString(
						"latitude").toString()));
				System.out.println(String.valueOf(network.getLatitude()));
				
				network.setLongitude(Float.parseFloat(networksItemArray.getJSONObject(i).getString(
						"longitude").toString()));
				System.out.println(String.valueOf(network.getLongitude()));
				
				JSONArray arrayPathToImages = networksItemArray.getJSONObject(i).getJSONArray("images");
				System.out.println(""+arrayPathToImages.length());
				for (int j = 0; j < arrayPathToImages.length(); j++) {
					network.addStringToArrayPathsToImages(arrayPathToImages.getJSONObject(j).getString("path").toString());
					System.out.println("Iteracion numero: "+j);
				}
				network.printImagesPaths(); 
				
				array_networks.add(network);
			}
			System.out.println("Numero de redes en el array: " + array_networks.size());
			
			/* Parseo de sensores */
			jSensorsObject = new JSONObject(jSensorsString);
			JSONArray sensorsItemArray = jSensorsObject.getJSONArray("sensors");
			
			for (int i = 0; i < sensorsItemArray.length(); i++) {
				SensorBean sensor = new SensorBean();
				
				sensor.setId(Integer.parseInt(sensorsItemArray.getJSONObject(i)
						.getString("id").toString()));
				System.out.println(sensor.getId());
				
				sensor.setSensor(sensorsItemArray.getJSONObject(i).getString("sensor").toString());
				System.out.println(sensor.getSensor());
				
				sensor.setType(sensorsItemArray.getJSONObject(i).getString("type").toString());
				System.out.println(sensor.getType());
				
				sensor.setDescription(sensorsItemArray.getJSONObject(i).getString("description").toString());
				System.out.println(sensor.getDescription());
				
				sensor.setNetworkId(sensorsItemArray.getJSONObject(i).getString("networkId").toString());
				System.out.println(sensor.getNetworkId());
				
				sensor.setLatitude(Float.parseFloat(sensorsItemArray.getJSONObject(i).getString("latitude").toString()));
				System.out.println(sensor.getLatitude());
				
				sensor.setLongitude(Float.parseFloat(sensorsItemArray.getJSONObject(i).getString("longitude").toString()));
				System.out.println(sensor.getLongitude());
				
				array_sensors.add(sensor);
			}
			
			System.out.println("Numero de sensores en el array: " + array_sensors.size());
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.splash_screen_layout);
        mContext = this;
        AppContext = getApplicationContext();
        
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                	loadJSON();
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                    	sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                   	startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        };
        splashTread.start();
 	}

}

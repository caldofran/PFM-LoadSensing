package com.uoc.loadsensing;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uoc.loadsensing.LoginActivity;
import com.uoc.loadsensing.R;
import com.uoc.loadsensing.beans.NetworkBean;
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
	
	private String jUsersString = "{\"users\":" +
			"[{\"userName\": \"Ruben\", \"password\": \"1234\"}," +
			" {\"userName\": \"Jesus\", \"password\": \"4321\"}]}";
	
	private String jNetworks = "{\"networks\":" +
			"[{\"name\": \"Madrid\", \"description\": \"Blah blah blah\", \"numberOfSensors\": \"4\", \"latitude\": \"40.40255\", \"longitude\": \"-3.71128\"," +
			"\"images\": [{\"path\": \"localPath1\"}, " +
			"{\"path\": \"localPath2\"}]}," +
			"{\"name\": \"Barcelona\", \"description\": \"Bleh bleh bleh\", \"numberOfSensors\": \"6\", \"latitude\": \"41.38085\", \"longitude\": \"2.12161\"," +
			"\"images\": [{\"path\": \"localPath3\"}, " +
			"{\"path\": \"localPath4\"}]}]}";
	
	private void loadJSON() {
		array_networks = new ArrayList<NetworkBean>();
		array_users = new ArrayList<Users>();
		
		try {
			/* Parseo de usuarios */
			jUsersObject = new JSONObject(jUsersString);
			JSONArray usersItemArray = jUsersObject.getJSONArray("users");
			
			for (int i = 0; i < usersItemArray.length(); i++) {
				Users user = new Users();
				user.setName(usersItemArray.getJSONObject(i)
						.getString("userName").toString());
				System.out.println(user.getName());
				
				user.setPassword(usersItemArray.getJSONObject(i).getString(
						"password").toString());
				System.out.println(user.getPassword());
				
				array_users.add(user);
			}
			
			System.out.println("Numero de usuarios en el array: " + array_users.size());
			
			/* Parseo de redes */
			jNetworksObject = new JSONObject(jNetworks);
			JSONArray networksItemArray = jNetworksObject.getJSONArray("networks");
			
			for (int i = 0; i < networksItemArray.length(); i++) {
				NetworkBean network = new NetworkBean();
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

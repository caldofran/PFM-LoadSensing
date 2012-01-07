package com.uoc.loadsensing;

/**
 * 
 * UOC - Universitat Oberta de Catalunya
 * Proyecto Final Master Software Libre
 * Septiembre 2011
 * 
 * LoadSensing para WorldSensing
 * 
 * @authors
 * 		Ruben Mendez Puente
 * 		Jesus Sanchez-Migallon Perez
 * 
 * Licensed to the Apache Software Foundation (ASF) under one 
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 *       
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 */

import com.uoc.loadsensing.beans.NetworkBean;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class SingleNetworkStateActivity extends LoadSensingActivity {
	
	/**
	 * @uml.property  name="mNetwork"
	 * @uml.associationEnd  
	 */
	NetworkBean mNetwork = null;
	
	WebView embeddedChart;
	String embeddedWeb = "http://chart.apis.google.com/chart?chxl=0:|2012|2011|2010|2009|2008|2007|1:|0|50|100|2:|min|average|max&chxp=2,10,50.83,90&chxr=0,-5,100&chxt=x,y,r&chs=300x150&cht=bvg&chco=76A4FB,FF9900&chd=t:20,30,10,40|50,50,50,50&chdl=Level+of+Tension|Not+Configured&chdlp=t&chg=20,50&chma=|5&chtt=Network+Current+State&chts=008000,11.5";
	  
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_listnetwork_state);
        
        enableNetworkBottomMenu(NT_STATE_SECTION);
        
        final Bundle bundle = getIntent().getExtras();
        // Recogemos informacion del Intent
        int sNetworkId = bundle.getInt("current_network");
        mNetwork = array_networks.get(sNetworkId);        
        
        // Establecemos titulo de activity
        TextView txtTitle = (TextView) findViewById(R.id.network_state_title);
        txtTitle.setText(mNetwork.getName()+ " State");
        
        // Mostramos grafica de estado
        embeddedChart = (WebView)findViewById(R.id.network_state_chart);
        embeddedChart.loadUrl(embeddedWeb);        
	}	

}

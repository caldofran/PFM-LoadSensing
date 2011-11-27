package com.uoc.loadsensing;

import com.uoc.loadsensing.beans.NetworkBean;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class SingleNetworkStateActivity extends LoadSensingActivity {
	
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

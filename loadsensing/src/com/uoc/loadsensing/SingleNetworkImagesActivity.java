package com.uoc.loadsensing;

import com.uoc.loadsensing.beans.NetworkBean;

import android.os.Bundle;
import android.widget.TextView;

public class SingleNetworkImagesActivity extends LoadSensingActivity {

	NetworkBean mNetwork = null;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_listnetwork_images);
        
        enableNetworkBottomMenu(NT_IMAGES_SECTION);

        final Bundle bundle = getIntent().getExtras();
        // Recogemos informacion del Intent
        int sNetworkId = bundle.getInt("current_network");
        
        mNetwork = array_networks.get(sNetworkId);        
        
        
        TextView txtTitle = (TextView) findViewById(R.id.network_images_title);
        
        txtTitle.setText(mNetwork.getName()+ " Images");
	}
}

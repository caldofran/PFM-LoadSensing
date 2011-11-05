package com.uoc.loadsensing;

/**
 * Activity encargada de mostrar el Menu Tab principal
 * 
 * @author JSMP
 * 
 */

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabMenuActivity extends TabActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabmenu);
 
        TabHost tabHost = getTabHost();
 
        // Tab para el Mapa
        TabSpec mapspec = tabHost.newTabSpec(getResources().getString(R.string.tab_map));
        mapspec.setIndicator(getResources().getString(R.string.tab_map), getResources().getDrawable(R.drawable.icon_globe_tab));
        Intent mapIntent = new Intent(this, MapNetworkActivity.class);
        mapspec.setContent(mapIntent);
 
        
        // Tab para la lista de Redes
        TabSpec networkspec = tabHost.newTabSpec(getResources().getString(R.string.tab_list));
        networkspec.setIndicator(getResources().getString(R.string.tab_list), getResources().getDrawable(R.drawable.icon_list_tab));
        Intent listNetworkIntent = new Intent(this, ListNetworkActivity.class);
        networkspec.setContent(listNetworkIntent);
 
        // Tab para los QR Codes
        TabSpec qrcodespec = tabHost.newTabSpec(getResources().getString(R.string.tab_qrcode));
        qrcodespec.setIndicator(getResources().getString(R.string.tab_qrcode), getResources().getDrawable(R.drawable.icon_barcode_tab));
        Intent qrIntent = new Intent(this, QRCodeActivity.class);
        qrcodespec.setContent(qrIntent);
 
        // Anyadimos todos los Tabs a TabHost
        tabHost.addTab(mapspec); 
        tabHost.addTab(networkspec); 
        tabHost.addTab(qrcodespec); 
    }
}

package com.uoc.loadsensing;

import java.util.ArrayList;
import java.util.Iterator;

//import com.google.android.maps.GeoPoint;
//import com.google.android.maps.OverlayItem;
import com.uoc.loadsensing.beans.NetworkBean;
import com.uoc.loadsensing.utils.Environment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toast;


public class ListNetworksActivity extends LoadSensingActivity implements ListView.OnScrollListener {

    public Activity activity;
	public Context mContext;
	public ListView list;
	public ProgressDialog dialog;
	
	public ArrayList<NetworkBean> aNetworkList = null;
	
    /**
	 * Clase propia que extiende de ArrayAdapter
	 * @uml.property  name="oAdapter"
	 * @uml.associationEnd  
	 */
    private OrderAdapter oAdapter;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.networklist_layout);
        enableBottomMenu(LISTNETWORKS_SECTION);
        mContext=this;
        activity=this;
        
        // Creamos objetos contenedores
        list=(ListView)findViewById(R.id.list);
        aNetworkList = new ArrayList<NetworkBean>();
        
        // Obtenemos la lista de redes
        requestListNetwork();
        
        
    	list.setOnScrollListener(this);
    	
    	// Establecemos accion para click en Network Item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            {

				// Launching new Activity on selecting single List Item
				Intent i = new Intent(mContext, SingleNetworkActivity.class);
				 
				// sending data to new activity
				System.out.println("Posicion que se le pasa: "+position);
				i.putExtra("current_network", position);
				
				// establish network identifiers
				network_selected = position;
				array_networks = aNetworkList;
				
				// launch activity
				startActivity(i);             	
            }
        });    	
    }	
	
    public void onResume() {
		super.onResume();
	}	

    @Override
    public void onDestroy()
    {
        try{
        	//adapter.imageLoader.stopThread();
        	list.setAdapter(null);
        }catch(Exception e){}
        super.onDestroy();
    }    
    
	
	private void requestListNetwork()
    {
		if (!Environment.internetIsAvailable(ListNetworksActivity.this))
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
	        getNetworks task = new getNetworks();
	        task.execute();

		}
    }	

	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	
	
	private class getNetworks extends AsyncTask<String, Void, Void>{

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
        	
        	// TODO Utilizar Redes de WS: Now Fake Items
        	//Iteramos sobre las redes
            Iterator<NetworkBean> iter = array_networks.iterator();
    		NetworkBean network = new NetworkBean();
    		while (iter.hasNext()) {
    			System.out.println("Entramos en el bucle");
    			network = (NetworkBean) iter.next();
    			//t.setName("Red "+network.getName());
        		//t.setDescription("Description "+network.getDescription());
        		aNetworkList.add(network);
    		}
        	
            oAdapter = new OrderAdapter(mContext, R.layout.row, aNetworkList);
            list.setAdapter(oAdapter);              	
        	
        	//oAdapter.setDataSet(aNetworkList);
        	oAdapter.notifyDataSetChanged();            
            
        	// Al final quitamos dialog
        	dialog.dismiss();
        }
		
	}
			

    /**
     * Clase Adapter customizada para nuestra propia lista de Redes
     *
     */
    private class OrderAdapter extends ArrayAdapter<NetworkBean> {

        private ArrayList<NetworkBean> items;

        public OrderAdapter(Context context, int textViewResourceId, ArrayList<NetworkBean> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = vi.inflate(R.layout.row, null);
                }
                
                NetworkBean o = items.get(position);
                if (o != null) {
                        TextView tName			= (TextView) convertView.findViewById(R.id.network_name);
                        TextView tLatitude		= (TextView) convertView.findViewById(R.id.network_latitude);
                        TextView tLongitude		= (TextView) convertView.findViewById(R.id.network_longitude);
                        TextView tNumSensors	= (TextView) convertView.findViewById(R.id.num_sensors);
                        
                        if (tName != null) {
                        	tName.setText(o.getName());         
                        }
                        
                        if (tLatitude != null) {
                        	tLatitude.setText(mContext.getString(R.string.ntwk_latitude)+ " " + String.valueOf(o.getLatitude()));
                        }
                        
                        if (tLongitude != null) {
                        	tLongitude.setText(mContext.getString(R.string.ntwk_longitude)+ " " + String.valueOf(o.getLongitude()));
                        }                        
                        
                        if (tNumSensors != null ) {
                        	tNumSensors.setText(String.valueOf(o.getNum_of_sensors()));
                        }
                        
                }
                return convertView;
        }
    }
       	
	
}

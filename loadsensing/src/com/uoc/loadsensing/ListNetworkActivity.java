package com.uoc.loadsensing;

/**
 * 
 * Activity utilizada para mostrar la lista de Redes Disponibles 
 * en modo Lista, obteniendo dicha información de los WS proporcionados
 * por la API de WorldSensing para LoadSensing
 * 
 * @author JSMP
 *
 */

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListNetworkActivity extends ListActivity {
	
	/** Cuadro de dialogo */
    private ProgressDialog mProgressDialog = null; 
    
    /** Lista de Opciones */
    private ArrayList<ItemList> aOpciones = null;
    
    /** Clase propia que extiende de ArrayAdapter */
    private OrderAdapter oAdapter;
    
    /** Runnable para obtener la lista de opciones via WS mediante API WorldSensing */
    private Runnable viewOpciones;
    
    /** Runnable que se encarga de anyadir la lista obtenida al Adapter */
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(aOpciones != null && aOpciones.size() > 0){
            	oAdapter.notifyDataSetChanged();
                for(int i=0;i<aOpciones.size();i++)
                	oAdapter.add(aOpciones.get(i));
            }
            // Eliminamos progressdialog y avisamos al Adapter 
            mProgressDialog.dismiss();
            oAdapter.notifyDataSetChanged();
        }
    };    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listnetwork_layout);
        
        // Creamos el array de opciones
        aOpciones = new ArrayList<ItemList>();
        
        // Creamos el Adapter y establecemos layout
        this.oAdapter = new OrderAdapter(this, R.layout.row, aOpciones);
        setListAdapter(this.oAdapter);
        
        // Iniciamos la peticion de la lista
        viewOpciones = new Runnable(){
            @Override
            public void run() {
                getOrders();
            }
        };
        Thread thread =  new Thread(viewOpciones);
        thread.start();

        // Mostramos ProgressDialog mientras no recibimos datos...
        mProgressDialog = ProgressDialog.show(ListNetworkActivity.this,    
        	getResources().getString(R.string.lst_wait), getResources().getString(R.string.lst_retreive_data), true);
    }

    /**
     * Metodo utilizado para obtener la lista de Redes Disponibles
     */
    private void getOrders(){
	  aOpciones = new ArrayList<ItemList>();
      
	  // Aqui deberemos obtener la lista de item mediante API WS 
	  ItemList o1 = new ItemList();
      o1.setItemName("Red Disponible");
      o1.setItemDescription("Descripcion...");
      
      ItemList o2 = new ItemList();
      o2.setItemName("Red Disponible");
      o2.setItemDescription("Descripcion...");
      
      aOpciones.add(o1);
      aOpciones.add(o2);
      
      // TODO Eliminar...solo pruebas
      try {
		Thread.sleep(5000);
      } catch (InterruptedException e) {
    	  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
      }
      // Lanzamos el Thread para obtener la lista (respuesta) en el mismo thread actual
      runOnUiThread(returnRes);
    }
    

    /**
     * Metodo utilizado para anaydir listener al Click de cada Item de la lista de Redes
     */
    protected void onListItemClick(ListView l, View v, int position, long id)  {
    	try
    	{
    		super.onListItemClick(l, v, position, id);
    		ItemList m_order = (ItemList)l.getItemAtPosition(position);

    		Toast.makeText(this, getResources().getString(R.string.lst_click_network), Toast.LENGTH_LONG).show();
    		
      	  // Launching new Activity on selecting single List Item
      	  Intent i = new Intent(getApplicationContext(), SingleNetworkActivity.class);
      	  
      	  // sending data to new activity
      	  i.putExtra("network_name", m_order.getItemName());
      	  startActivity(i);    		
    		
    	}
    	catch(Exception ex)  {
    		Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
    	}

    }    
    
    /**
     * Clase Adapter customizada para nuestra propia lista de Redes
     *
     */
    private class OrderAdapter extends ArrayAdapter<ItemList> {

        private ArrayList<ItemList> items;

        public OrderAdapter(Context context, int textViewResourceId, ArrayList<ItemList> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = vi.inflate(R.layout.row, null);
                }
                
                ItemList o = items.get(position);
                if (o != null) {
                        TextView tName = (TextView) convertView.findViewById(R.id.nametext);
                        TextView tDescript = (TextView) convertView.findViewById(R.id.descripttext);
                        
                        if (tName != null) {
                        	tName.setText(o.getItemName());         
                        }
                        
                        if(tDescript != null){
                        	tDescript.setText(o.getItemDescription());
                        }
                        
                }
                return convertView;
        }
    }
        
}


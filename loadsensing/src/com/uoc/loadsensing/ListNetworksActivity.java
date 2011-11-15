package com.uoc.loadsensing;

import java.util.ArrayList;

import com.uoc.loadsensing.beans.NetworkBean;
import com.uoc.loadsensing.utils.Environment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

public class ListNetworksActivity extends LoadSensingActivity implements ListView.OnScrollListener {

    public Activity activity;
	public Context mContext;
	public ListView list;
	//public ActivityAdapter adapter;
	public ProgressDialog dialog;
	
	public ArrayList<NetworkBean> aNetworkList = null;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.networklist_layout);
        enableBottomMenu(LISTNETWORKS_SECTION);
        mContext=this;
        activity=this;
        
        requestListNetwork();
        
        if ( list != null ) {
        	list.setOnScrollListener(this);
        }
    }	
	
	private void requestListNetwork()
    {
		if (!Environment.internetIsAvailable(ListNetworksActivity.this))
		{
			Environment.errorAlert(getApplicationContext(), getApplicationContext().getString(R.string.no_connection));
		}else{
			aNetworkList = new ArrayList<NetworkBean>();
	        list=(ListView)findViewById(R.id.list);
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
			
//	    	if (!busy)
//	    	{
//	    		busy=true;
	        getNetworks task = new getNetworks();
	        task.execute();
	        dialog.dismiss();
//	    	}
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
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
			

}

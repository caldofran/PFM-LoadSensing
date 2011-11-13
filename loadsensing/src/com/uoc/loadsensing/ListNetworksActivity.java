package com.uoc.loadsensing;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

public class ListNetworksActivity extends LoadSensingActivity implements ListView.OnScrollListener {

    public Activity activity;
	public Context mContext;
	public ListView list;
	public ProgressDialog dialog;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list);
        enableBottomMenu(this.LISTNETWORKS_SECTION);
        mContext=this;
        activity=this;
        
        requestListNetwork();
        
        list.setOnScrollListener(this);
    }	
	
	private void requestListNetwork()
    {
//		if (!Protocol.checkInternet(ListNetworksActivity.this))
//		{
//			Util.noInternetNotification(ListNetworksActivity.this);
//		}else{
//			if(cp == 1){
//				activity_array = new ArrayList<ActivityItem>();
//		        list=(ListView)findViewById(R.id.list);
//		        dialog = new ProgressDialog(this);
//		        dialog.setProgressStyle(0);
//		        dialog.setMessage(this.getString(R.string.wait_activity));
//		        dialog.setCancelable(true);
//		        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//					@Override
//					public void onCancel(DialogInterface dialog) 
//					{
//						finish();
//					}
//				});
//		        dialog.setProgress(0);
//		        dialog.setMax(100);
//		        dialog.show();
//			}
//			
//	    	if (!busy)
//	    	{
//	    		busy=true;
//		        current_page = cp;
//		        Analytics.trackPageView("/activity/" + section);
//		        getActivityJson("" + cp);
//	    	}
//		}
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

}

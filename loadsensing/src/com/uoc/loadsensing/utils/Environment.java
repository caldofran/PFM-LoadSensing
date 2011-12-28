package com.uoc.loadsensing.utils;


import com.uoc.loadsensing.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

public class Environment {
	
	public static ProgressDialog dialog;
	
	@SuppressWarnings("static-access")
	public static Boolean internetIsAvailable(Context mContext) 
	{
		boolean bSuccess = false;
		ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
		
		try{
			if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && 
						conMgr.getActiveNetworkInfo().isConnectedOrConnecting() ) 
			{
				bSuccess = true;
			}
		}catch(Exception e){}
		
		return bSuccess;
	}
	
	public static void errorAlert(Context mContext, String text) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(mContext.getString(R.string.title_alert_error));
		dialog.setMessage(text);
		dialog.setIcon(R.drawable.error);
		dialog.setPositiveButton(mContext.getString(R.string.accept), null);
		dialog.show();
	}	

	
	public static void showDialog(String message, Boolean cancelable, Context context) {
		dialog = new ProgressDialog(context);
		dialog.setProgressStyle(0);
		dialog.setMessage(message);
		if (!cancelable)
		{
			dialog.setCancelable(cancelable);
		}else{
			dialog.setCancelable(true);
			if (cancelable) {
				dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) 
					{
						// do nothing
					}
				});
			}
		}
		dialog.setProgress(0);
		dialog.setMax(100);
		dialog.show();
	}	
	
	public static void hideDialog() {
		dialog.dismiss();
	}	
}

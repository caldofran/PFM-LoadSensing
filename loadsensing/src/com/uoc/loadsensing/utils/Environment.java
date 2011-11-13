package com.uoc.loadsensing.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Environment {
	
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
	

}

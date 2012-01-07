package com.uoc.loadsensing.utils;

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

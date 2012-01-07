package com.uoc.loadsensing.adapters;

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

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.uoc.loadsensing.R;
import com.uoc.loadsensing.utils.ImageLoader;

public class ImagesAdapter extends BaseAdapter {
    
    private Activity activity;
    private Vector<String> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    public ListView list;
    
    public ImagesAdapter(Activity a, Vector<String> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext(),R.drawable.no_image,false);
        list = (ListView)activity.findViewById(R.id.list);
    }

    public void setDataSet(Vector<String> data)
    {
    	this.data = data;
    }
    
    public int getCount() {
       	return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public static class ViewHolder{
        public TextView id;
        public ImageView avatar;
        public TextView name;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        ViewHolder holder = null;
        
        String Path = data.get(position);
        
		vi = inflater.inflate(R.layout.image_list_item, null);
        holder=new ViewHolder();
        holder.name=(TextView)vi.findViewById(R.id.image_name);
        holder.avatar=(ImageView)vi.findViewById(R.id.image);
    	vi.setTag(holder);
		holder=(ViewHolder)vi.getTag();
        
		String[] tmp = Path.split("/");
		
		String photo_name = tmp[tmp.length - 1]; 
		
   		holder.name.setText(photo_name);
		holder.avatar.setTag("file://"+Path);
		imageLoader.setScale(6);
		imageLoader.DisplayImage("file://"+Path, activity, holder.avatar);
   		return vi;
    }
}	

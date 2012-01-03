package com.uoc.loadsensing.utils;

/**
 * UOC - Universitat Oberta de Catalunya
 * Proyecto Final Máster Software Libre
 * Septiembre 2011
 * 
 * LoadSensing para WorldSensing
 * 
 * @authors
 * 		Rubén Méndez Puente
 * 		Jesús Sánchez-Migallón Pérez
 * 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Stack;

import com.uoc.loadsensing.SingleNetworkActivity;
import com.uoc.loadsensing.SingleNetworkImagesActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;


public class ImageLoader {
    public Context context;
    public int stub_id=0;
    private HashMap<String, Bitmap> cache=new HashMap<String, Bitmap>();
    public Bitmap bitmap=null;
    public Bitmap b=null;
    private File cacheDir;
    private Boolean cacheable=false;
    int resize_scale=2;
    
    public ImageLoader(Context context, int noimage, Boolean is_cacheable){
    	this.context = context;
        photoLoaderThread.setPriority(Thread.NORM_PRIORITY-2);
        
        cacheable=is_cacheable;
        
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"Minube");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
        stub_id = noimage;
    }
    
    public void setScale(int scale)
    {
    	resize_scale = scale;
    }
    
    public void setNoImage(int resource)
    {
    	stub_id = resource;
    }
    
    public void DisplayImage(String url, Activity activity, ImageView imageView)
    {
        if(cache.containsKey(url))
        {
            imageView.setImageBitmap(cache.get(url));
        }else{
            queuePhoto(url, activity, imageView);
            if (stub_id != 0)
            	imageView.setImageResource(stub_id);
        }    
    }
    
    private void queuePhoto(String url, Activity activity, ImageView imageView)
    {
        photosQueue.Clean(imageView);
        PhotoToLoad p=new PhotoToLoad(url, imageView);
        synchronized(photosQueue.photosToLoad){
            photosQueue.photosToLoad.push(p);
            photosQueue.photosToLoad.notifyAll();
        }

        if(photoLoaderThread.getState()==Thread.State.NEW)
        {
            photoLoaderThread.start();
        }
    }
    
    private Bitmap getBitmap(String url) 
    {
        String filename=String.valueOf(url.hashCode());
        File f=new File(cacheDir, filename);

        if (this.cacheable)
        {
        	if (b!= null)
        	{
        		b = null;
        		System.gc();
        	}
        	
        	b = decodeFile(f);
        	if(b!=null)
            {
                return b;
            }
        }
        
        
        try {
        	if (Runtime.getRuntime().freeMemory() / 100 > 800)
			{
	            InputStream is;
	            is=new URL(url).openStream();
	            OutputStream os;
	            os = new FileOutputStream(f);
	            SingleNetworkImagesActivity.CopyStream(is, os);
	            os.close();
			}
            try{
            	bitmap = decodeFile(f);
            }catch(Exception e){
            }
            if (bitmap!=null)
            {
            	return bitmap;
        	}else {
            	return BitmapFactory.decodeResource(context.getResources(),stub_id);
            }
        } catch (Exception ex){
           ex.printStackTrace();
           return null;
        }
    }

    private Bitmap decodeFile(File f){
    	Bitmap tmp=null;
        try {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            final int REQUIRED_SIZE=70;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }
            
            if (resize_scale != 2)
            {
            	scale = resize_scale;
            }
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;

            try{
            	tmp = BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
            }catch(Exception e){}
        } catch (Exception e) {}
        return tmp;
    }

    private class PhotoToLoad
    {
        public String url;
        public ImageView imageView;
        public PhotoToLoad(String u, ImageView i){
            url=u; 
            imageView=i;
        }
    }
    
    PhotosQueue photosQueue=new PhotosQueue();
    
    public void stopThread()
    {
        photoLoaderThread.interrupt();
    }

    class PhotosQueue
    {
        private Stack<PhotoToLoad> photosToLoad=new Stack<PhotoToLoad>();
        
        public void Clean(ImageView image)
        {
            for(int j=0 ;j<photosToLoad.size();){
                if(photosToLoad.get(j).imageView==image)
                    photosToLoad.remove(j);
                else
                    ++j;
            }
        }
    }
    
    class PhotosLoader extends Thread {
        public void run() {
            try {
                while(true)
                {

                    if(photosQueue.photosToLoad.size()==0)
                        synchronized(photosQueue.photosToLoad){
                            photosQueue.photosToLoad.wait();
                        }

                    if(photosQueue.photosToLoad.size()!=0)
                    {
                        PhotoToLoad photoToLoad;
                        synchronized(photosQueue.photosToLoad){
                            photoToLoad=photosQueue.photosToLoad.pop();
                        }
                        try{
	                        Bitmap bmp=getBitmap(photoToLoad.url);
	                        
	                        if (cacheable)
	                        	cache.put(photoToLoad.url, bmp);
	                        
	                        Object tag=photoToLoad.imageView.getTag();
	                        
	                        if(tag!=null && ((String)tag).equals(photoToLoad.url)){
	                            BitmapDisplayer bd=new BitmapDisplayer(bmp, photoToLoad.imageView);
	                            Activity a=(Activity)photoToLoad.imageView.getContext();
	                            a.runOnUiThread(bd);
	                        }
                        }catch(Exception e){}
                    }
                    if(Thread.interrupted())
                        break;
                }
            } catch (InterruptedException e) {}
        }
    }
    
    PhotosLoader photoLoaderThread=new PhotosLoader();
    

    class BitmapDisplayer implements Runnable
    {
        Bitmap bitmap;
        ImageView imageView;
        public BitmapDisplayer(Bitmap b, ImageView i){bitmap=b;imageView=i;}
        public void run()
        {
            if(bitmap!=null)
            {
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
            }else{
                imageView.setImageResource(stub_id);
            }
        }
    }

    public void clearCache() {
        cache.clear();
        File[] files=cacheDir.listFiles();
        for(File f:files)
        {
            f.delete();
        }
    }

}

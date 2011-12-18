package com.uoc.loadsensing;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uoc.loadsensing.adapters.ImagesAdapter;
import com.uoc.loadsensing.beans.NetworkBean;
import com.uoc.loadsensing.utils.Environment;


public class SingleNetworkImagesActivity extends LoadSensingActivity {

	/**
	 * @uml.property  name="mNetwork"
	 * @uml.associationEnd  
	 */
	private NetworkBean mNetwork = null;
	private int selected_report = 0;
	private Uri imageUri;
	private String selectedImagePath;
	private ListView lImages;
	private int selected_button;
	ImagesAdapter adapter;
	
	//ArrayList<String> aImages = new ArrayList<String>();
	Vector<String> aImages = new Vector<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_listnetwork_images);

        // Activamos menu inferior
        enableNetworkBottomMenu(NT_IMAGES_SECTION);

        // Recogemos informacion del Intent
        final Bundle bundle = getIntent().getExtras();
        int sNetworkId = bundle.getInt("current_network");
        mNetwork = array_networks.get(sNetworkId);        
        
        // Establecemos titulo de activity
        TextView txtTitle = (TextView) findViewById(R.id.network_images_title);
        txtTitle.setText(mNetwork.getName()+ getApplicationContext().getString(R.string.img_title));
      
        // Establecemos accion boton camara
        final ImageButton btnTakePicture = (ImageButton) findViewById(R.id.btnDoPicture);
        
        btnTakePicture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						SingleNetworkImagesActivity.this);

				dialog.setTitle(getApplicationContext().getString(R.string.img_add_images));
				

				dialog.setSingleChoiceItems(R.array.take_picture,
						selected_report, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								selected_report = whichButton;
							}
						});
				dialog.setPositiveButton(SingleNetworkImagesActivity.this
						.getString(R.string.ok),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Intent intent;
								switch (selected_report) {
									case 0:
										// Opcion Camara
										String fileName = "new-photo-name.jpg";
										ContentValues values = new ContentValues();
										values.put(MediaStore.Images.Media.TITLE,fileName);
										imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
										intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
										intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
										intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
										startActivityForResult(intent, CAMERA_INTENT);
										dialog.dismiss();
										break;
									case 1:
										// Opcion Galeria
										intent = new Intent(Intent.ACTION_PICK,Media.EXTERNAL_CONTENT_URI);
										startActivityForResult(intent,SELECT_PICTURE_INTENT);
										dialog.dismiss();
										break;
								}

							}
						});
				dialog.setNegativeButton(getApplicationContext().getString(R.string.cancel), null);
				dialog.show();
			}
		});
        
        
        // Referenciamos lista de imagenes
        lImages = (ListView)findViewById(R.id.list_images);
        
	    Environment.showDialog(this.getString(R.string.loading), false, this);
	    showImages task = new showImages();
		task.execute("");        
       
	    
	    lImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) 
            {
            	AlertDialog.Builder dialog = new AlertDialog.Builder(SingleNetworkImagesActivity.this);
            	
				dialog.setTitle(getApplicationContext().getString(R.string.options));
				
            	dialog.setSingleChoiceItems(R.array.image_list_options, selected_button,new DialogInterface.OnClickListener() 
				{
                    public void onClick(DialogInterface dialog, int whichButton) 
                    {
                    	selected_button = whichButton;
                    }
                });
            	dialog.setPositiveButton(SingleNetworkImagesActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) 
                    {
                        switch(selected_button)
                        {
                        	case 0:
                        		//Ver la foto
                        		Intent GalleryItemWindow = new Intent(getApplicationContext(), ImageItemActivity.class);
                            	GalleryItemWindow.putExtra("url", "file://"+aImages.get(position));
                        		startActivity(GalleryItemWindow);
                        		break;
                        	case 1:
                        		//Quitar del listado y notificar al adaptador
                        		aImages.remove(position);
                        		adapter.setDataSet(aImages);
                        		adapter.notifyDataSetChanged();
                        		break;
                        }
                        
                    }
                });
            	dialog.setNegativeButton("Cancelar", null);
            	dialog.show();
            }
	    });	    
	    
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String image = "";
		if (requestCode == CAMERA_INTENT) 
		{
			image =convertCameraImageUriToFile(imageUri, SingleNetworkImagesActivity.this);
		}

		if (requestCode == SELECT_PICTURE_INTENT) {
			if (resultCode == RESULT_OK) {
				if (requestCode == SELECT_PICTURE_INTENT) {
					Uri selectedImageUri = data.getData();
					selectedImagePath = getImagePathFromGallery(selectedImageUri);
					image = selectedImagePath;
				}
			}
		}
		if ( !image.equals("") ){
			aImages.add(image);
			adapter.notifyDataSetChanged();
		}
		//Toast.makeText(getApplicationContext(), image, Toast.LENGTH_LONG).show();
		
	}
	
	/**
	 * Metodo utilizado para convertir imagen capturada desde la camara a una URI
	 * @param imageUri
	 * @param activity
	 * @return
	 */
	public static String convertCameraImageUriToFile(Uri imageUri,
			Activity activity) {
		Cursor cursor = null;
		try {
			String[] proj = { MediaStore.Images.Media.DATA,
					MediaStore.Images.Media._ID,
					MediaStore.Images.ImageColumns.ORIENTATION };
			cursor = activity.managedQuery(imageUri, proj, // Which columns to
					// return
					null, // WHERE clause; which rows to return (all rows)
					null, // WHERE clause selection arguments (none)
					null); // Order-by clause (ascending by name)
			int file_ColumnIndex = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

			if (cursor.moveToFirst()) {
				return cursor.getString(file_ColumnIndex);
			}
			return "";
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}	
	
	public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }	
	
	/**
	 * Metodo utilizado para obetener el path de la imagen recuperada de la Galeria
	 * @param uri
	 * @return
	 */
	public String getImagePathFromGallery(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}	
	
	private class showImages extends AsyncTask<String, Void, Void>
	{
        @Override
        protected Void doInBackground(String... params){return null;}
 
        @Override
        protected void onPostExecute(Void result) 
        {
        	adapter = new ImagesAdapter(SingleNetworkImagesActivity.this, aImages);
        	
        	//@TODO recoger imagenes si existen (via API LoadSensing o XML)
        	adapter.setDataSet(aImages);
        	
        	
    		lImages.setAdapter(adapter);
        	adapter.notifyDataSetChanged();
        	Environment.hideDialog();
        }
    }	

	
}

package com.uoc.loadsensing;

import com.uoc.loadsensing.beans.NetworkBean;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleNetworkImagesActivity extends LoadSensingActivity {

	NetworkBean mNetwork = null;
	int selected_report = 0;
	Uri imageUri;
	private String selectedImagePath;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_listnetwork_images);
        
        enableNetworkBottomMenu(NT_IMAGES_SECTION);

        final Bundle bundle = getIntent().getExtras();
        // Recogemos informacion del Intent
        int sNetworkId = bundle.getInt("current_network");
        mNetwork = array_networks.get(sNetworkId);        
        
        TextView txtTitle = (TextView) findViewById(R.id.network_images_title);
        txtTitle.setText(mNetwork.getName()+ " Images");
        
        final ImageView imgCamera = (ImageView) findViewById(R.id.camera_icon);        
        
        imgCamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						SingleNetworkImagesActivity.this);

				dialog.setTitle("Add Images");

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
										//Cámara
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
										// Galería
										intent = new Intent(Intent.ACTION_PICK,Media.EXTERNAL_CONTENT_URI);
										startActivityForResult(intent,SELECT_PICTURE_INTENT);
										dialog.dismiss();
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
		Toast.makeText(getApplicationContext(), image, Toast.LENGTH_LONG).show();
		
	}
	
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
	
	public String getImagePathFromGallery(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}	
}

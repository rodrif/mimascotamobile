package com.mimascota.mimascota;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SubirFoto extends Fragment {
	private Uri outputFileUri;

	 private static final int TAKE_PICTURE = 5;
	@Override
	    public View onCreateView(LayoutInflater inflater,
	                             ViewGroup container,
	                             Bundle savedInstanceState) {
	 
	        return inflater.inflate(R.layout.fragment_subir_foto, container, false);
	    }
	 
	    @Override
	    public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);	 

	    }
	    
	    public void onClickSacarFoto(View Boton) {
	    	//FIXME
	    	// Create an output file.
//	    	File file = new File(Environment.getExternalStorageDirectory(),
//	    	"test.jpg");
//	    	outputFileUri = Uri.fromFile(file);
//	    	// Generate the Intent.
//	    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//	    	intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//	    	// Launch the camera app.
//	    	startActivityForResult(intent, TAKE_PICTURE);
	    	startActivityForResult(
	    			new Intent(MediaStore.ACTION_IMAGE_CAPTURE), TAKE_PICTURE);
	    }
	    
	    public void onClickSubirFoto(View Boton) {
	    	//TODO
	    }

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TAKE_PICTURE) {
			ImageView imageView = (ImageView)getView().findViewById(R.id.vFoto);
			// Check if the result includes a thumbnail Bitmap
			if (data != null) {
				if (data.hasExtra("data")) {
					Bitmap thumbnail = data.getParcelableExtra("data");
					imageView.setImageBitmap(thumbnail);
				}
			} else {
				// If there is no thumbnail image data, the image
				// will have been stored in the target output URI.
				// Resize the full image to fit in out image view.
				int width = imageView.getWidth();
				int height = imageView.getHeight();
				BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
				factoryOptions.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(outputFileUri.getPath(),
						factoryOptions);
				int imageWidth = factoryOptions.outWidth;
				int imageHeight = factoryOptions.outHeight;
				// Determine how much to scale down the image
				int scaleFactor = Math.min(imageWidth / width, imageHeight
						/ height);
				// Decode the image file into a Bitmap sized to fill the View
				factoryOptions.inJustDecodeBounds = false;
				factoryOptions.inSampleSize = scaleFactor;
				factoryOptions.inPurgeable = true;
				Bitmap bitmap = BitmapFactory.decodeFile(
						outputFileUri.getPath(), factoryOptions);
				imageView.setImageBitmap(bitmap);
			}
		}
	}	    

}

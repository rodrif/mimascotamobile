package com.mimascota.mimascota;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class SubirFotoYFormulario extends Fragment {
	private Uri outputFileUri;

	private static final int TAKE_PICTURE = 5;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_subir_foto, container,
				false);

		final Button buttonSFoto = (Button) view.findViewById(R.id.bSacarFoto);
		final Button buttonSubir = (Button) view.findViewById(R.id.bSubir);
		
		buttonSFoto.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onClickSacarFoto(v);
			}
		});
		
		buttonSubir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onClickSubir(v);
			}
		});

		return view;
	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);

	}

	public void onClickSacarFoto(View Boton) {
		// FIXME
		// Create an output file.
		File file = new File(Environment.getExternalStorageDirectory(),
				"test.jpg");
		outputFileUri = Uri.fromFile(file);
		// Generate the Intent.
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
		// Launch the camera app.
		startActivityForResult(intent, TAKE_PICTURE);
		// startActivityForResult(
		// new Intent(MediaStore.ACTION_IMAGE_CAPTURE), TAKE_PICTURE);
	}

	public void onClickSubir(View Boton) {
		Log.d("InputStream", "onClick");
		String result = "";
    	File ruta_sd = Environment.getExternalStorageDirectory();
        String miFoto = ruta_sd.getAbsolutePath()+"/i.jpeg";
		  try {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.accumulate("id", "2");
	            jsonObject.accumulate("age", "20");
	            jsonObject.accumulate("breed", "agagaegfag");
	            jsonObject.accumulate("user_id", "agagaegfag");
	            jsonObject.accumulate("color", "agagaegfag");
	            jsonObject.accumulate("description", "agagaegfag");
	            jsonObject.accumulate("name", "Juan2");
	            jsonObject.accumulate("latitude", "10.4198");
	            jsonObject.accumulate("longitude", "10.3012");
	            jsonObject.accumulate("gmaps", "true");
	            String jsonString = jsonObject.toString();
	            Log.d("InputStream", "String" + jsonString);
			  
	            // 1. create HttpClient
              HttpClient httpclient = new DefaultHttpClient();
              httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
              HttpPost httppost = new HttpPost("http://192.168.1.36:3000/cargador/subirPerroEncontrado");
        //      HttpPost httppost = new HttpPost("http://192.168.1.40/subir2");
              File file = new File(miFoto);
              MultipartEntity mpEntity = new MultipartEntity();
              ContentBody foto = new FileBody(file, "image/jpeg");
              mpEntity.addPart("fotoUp", foto);
              mpEntity.addPart("jsonString", new StringBody(jsonString));
              httppost.setEntity(mpEntity);
              httpclient.execute(httppost);
           //   httpclient.getConnectionManager().shutdown(); 
		  
			  
			  
/*	            HttpClient httpclient = new DefaultHttpClient();
	            Log.d("InputStream", "onClick2");
	            // 2. make POST request to the given URL
	            HttpPost httpPost = new HttpPost("http://192.168.1.36:3000/cargador/subirPerroEncontrado");//FIXME
	 
	            String jsonString = "";
	            
	            Log.d("InputStream", "onClick3");
	            
	            // 3. build jsonObject
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.accumulate("name", "Juan2");
	            jsonObject.accumulate("latitude", "-25.4198");
	            jsonObject.accumulate("longitude", "-40.3012");
	            jsonObject.accumulate("gmaps", "true");
	      //      jsonObject.accumulate("country", "Argentina");
	            
	            Log.d("InputStream", "onClick4"); 
	            
	            // 4. convert JSONObject to JSON to String
	            jsonString = jsonObject.toString();
	            Log.d("InputStream", "String" + jsonString);
	 
	            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
	            // ObjectMapper mapper = new ObjectMapper();
	            // json = mapper.writeValueAsString(person); 
	 
	            // 5. set json to StringEntity
	  //          StringEntity se = new StringEntity(jsonString);
	            
	//  		  	List<NameValuePair> params = new ArrayList<NameValuePair>();
	  		  	params.add(new BasicNameValuePair("jsonstring",jsonString));
	 
	            // 6. set httpPost Entity	            
	  		  	httpPost.setEntity(new UrlEncodedFormEntity(params));
	  	//	  	httpPost.setEntity(se);	
	  		  	
	            // 7. Set some headers to inform server about the type of the content   
	       //     httpPost.setHeader("Accept", "application/json");
	       //     httpPost.setHeader("Content-type", "application/json");
	 
	            // 8. Execute POST request to the given URL
	            HttpResponse httpResponse = httpclient.execute(httpPost);
	 
	            // 9. receive response as inputStream
	            InputStream inputStream = httpResponse.getEntity().getContent();
	 
	            
				// 10. convert inputstream to string
	            if(inputStream != null)
	                result = convertInputStreamToString(inputStream);
	            else
	                result = "Did not work!";*/
	 
	        } catch (Exception e) {
	            Log.d("InputStream", e.getLocalizedMessage());
	        }
		  	
		  	Log.d("InputStream", "Recibio:" + result);
	}
	
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    } 

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TAKE_PICTURE) {
			ImageView imageView = (ImageView) getView()
					.findViewById(R.id.vFoto);
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
				Log.d("MiMascota", "imagen entera cargada");
			}
		}
	}

}

package com.mimascota.mimascota;


import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class LoginFragment extends Fragment {
	private String json = "";
	private EditText eMail;
	private EditText ePassword;
	
	InterfaceLogin mCallback;
    // La Activity contenedora, debe implementar la interface
    public interface InterfaceLogin {
       public void Loguear(int userId);
   }
    
    @Override
    public void onAttach(Activity activity) {
       super.onAttach(activity);
       // Acá nos aseguramos que la Activity contenedora implementa
       // la interface. Si no es así, disparamos una excepción
       try {
          mCallback = (InterfaceLogin) activity;
       } catch (ClassCastException e) {
          throw new ClassCastException(activity.toString() + " se debe implementar InterfaceLogin");
       }
    }
    
	 @Override
	    public View onCreateView(LayoutInflater inflater,
	                             ViewGroup container,
	                             Bundle savedInstanceState) {	 
	        View view = inflater.inflate(R.layout.fragment_login, container, false);
	        
	        final Button buttonConectar = (Button) view.findViewById(R.id.bConectar);
			eMail = (EditText)view.findViewById(R.id.eUsuario);
			ePassword = (EditText)view.findViewById(R.id.ePassword);
	        
	        buttonConectar.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					onClickConectar(v);
				}
			});	        
	        return view;
	    }
	 
	    @Override
	    public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);	 
	    }
	    
	private void onClickConectar(View v) {
		String sMail = eMail.getText().toString();
		String sPassword = ePassword.getText().toString();

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("email", sMail);
			jsonObject.put("password", sPassword);

			json = jsonObject.toString();

			// 1. create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			HttpPost httppost = new HttpPost("http://" + Constantes.IPSERVER
					+ ":3000/cargador/subirPerroEncontrado");
			MultipartEntity mpEntity = new MultipartEntity();
			mpEntity.addPart("jsonString", new StringBody(json));
			httppost.setEntity(mpEntity);
			httpclient.execute(httppost);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mCallback.Loguear(2); //FIXME Se tiene que ejecutar con la respuesta del server del user_id

	}
}

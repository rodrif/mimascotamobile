package com.mimascota.mimascota;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class LoginFragment extends Fragment {
	private String json = "";
	private EditText eMail;
	private EditText ePassword;
	private int userId= 1;
	
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
					+ ":3000/observador/loginCelular");
			MultipartEntity mpEntity = new MultipartEntity();
			mpEntity.addPart("jsonString", new StringBody(json));
			
			httppost.setEntity(mpEntity);
			
			HttpResponse resp = httpclient.execute(httppost);
			HttpEntity ent = resp.getEntity();/*y obtenemos una respuesta*/
			String text = EntityUtils.toString(ent);
			Log.d("MiMascota", "Respuesta login: " + text);
			userId = Integer.parseInt(text);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
/*		
		if(userId > 0) {//solo si el usuario es valido
			mCallback.Loguear(userId);
		}else{
			AlertDialog.Builder cartel = new AlertDialog.Builder(this.getActivity());
								cartel.setMessage("Usuario o Password incorrectos");
								cartel.show();
		}
*/		mCallback.Loguear(1);  //FIXME harcodeado

	}
}

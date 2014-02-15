package com.mimascota.mimascota;

import org.json.JSONObject;

import android.app.Activity;
import android.content.pm.ActivityInfo;
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
	private int userId= -1;
	
	InterfaceLogin mCallback;
    // La Activity contenedora, debe implementar la interface
    public interface InterfaceLogin {
       public void Loguear(int userId);
   }
    
    @Override
    public void onAttach(Activity activity) {
       super.onAttach(activity);
       // Ac� nos aseguramos que la Activity contenedora implementa
       // la interface. Si no es as�, disparamos una excepci�n
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
     
	        return view;
	    }
	 
	    @Override
	    public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);	 
	        
		 	getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
		 	
	        final Button buttonConectar = (Button) this.getView().findViewById(R.id.bConectar);
			eMail = (EditText)this.getView().findViewById(R.id.eUsuario);
			ePassword = (EditText)this.getView().findViewById(R.id.ePassword);
	        
	        buttonConectar.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					onClickConectar(v);
				}
			});
	    }
	    
	private void onClickConectar(View v) {
		String sMail = eMail.getText().toString();
		String sPassword = ePassword.getText().toString();

		JSONObject jsonObject = new JSONObject();
	/*	try {
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
			HttpEntity ent = resp.getEntity();//y obtenemos una respuesta
			String text = EntityUtils.toString(ent);
			Log.d("MiMascota", "Respuesta login: " + text);
			userId = Integer.parseInt(text);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userId > 0) {//solo si el usuario es valido
			mCallback.Loguear(userId);
		}else{
			Toast.makeText(this.getActivity(),
							"Usuario o Password incorrectos", Toast.LENGTH_SHORT).show();								
		}*/
		mCallback.Loguear(2);  //FIXME harcodeado

	}
}

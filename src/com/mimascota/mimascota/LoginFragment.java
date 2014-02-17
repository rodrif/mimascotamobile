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
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {
	private String json = "";
	private EditText eMail;
	private EditText ePassword;
	private int userId = -1;

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
			throw new ClassCastException(activity.toString()
					+ " se debe implementar InterfaceLogin");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

		setearBotones();
	}

	private void onClickConectar(View v) {
		String sMail = eMail.getText().toString();
		String sPassword = ePassword.getText().toString();

		JSONObject jsonObject = new JSONObject();

		if (!Constantes.saltearLogin) {
			try {
				jsonObject.put("email", sMail);
				jsonObject.put("password", sPassword);

				json = jsonObject.toString();

				// 1. create HttpClient
				HttpClient httpclient = new DefaultHttpClient();
				httpclient.getParams().setParameter(
						CoreProtocolPNames.PROTOCOL_VERSION,
						HttpVersion.HTTP_1_1);
				HttpPost httppost = new HttpPost("http://"
						+ Constantes.IPSERVER + ":3000/observador/loginCelular");
				MultipartEntity mpEntity = new MultipartEntity();
				mpEntity.addPart("jsonString", new StringBody(json));

				httppost.setEntity(mpEntity);

				HttpResponse resp = httpclient.execute(httppost);
				HttpEntity ent = resp.getEntity();// y obtenemos una respuesta
				String text = EntityUtils.toString(ent);
				Log.d("MiMascota", "Respuesta login: " + text);
				userId = Integer.parseInt(text);

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (userId > 0) {// solo si el usuario es valido
				mCallback.Loguear(userId);
			} else {
				Toast.makeText(this.getActivity(),
						"Usuario o Password incorrectos", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			mCallback.Loguear(1);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		populateViewForOrientation(inflater, (ViewGroup) getView());
	}

	private void populateViewForOrientation(LayoutInflater inflater,
			ViewGroup viewGroup) {
		viewGroup.removeAllViewsInLayout();
		inflater.inflate(R.layout.fragment_login, viewGroup);

		// Find your buttons in subview, set up onclicks, set up callbacks to
		// your parent fragment or activity here.
		setearBotones();
		// You can create ViewHolder or separate method for that.
		// example of accessing views: TextView textViewExample = (TextView)
		// view.findViewById(R.id.text_view_example);
		// textViewExample.setText("example");
	}

	private void setearBotones() {
		final Button buttonConectar = (Button) this.getView().findViewById(
				R.id.bConectar);
		eMail = (EditText) this.getView().findViewById(R.id.eUsuario);
		ePassword = (EditText) this.getView().findViewById(R.id.ePassword);

		buttonConectar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onClickConectar(v);
			}
		});
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("email", eMail.getText().toString());
		outState.putString("password", ePassword.getText().toString());
	}
	
	public void onRetoreInstanceState(Bundle inState){
	    if(inState!=null)
	    {
	        eMail.setText(inState.getString("email"));
	        ePassword.setText(inState.getString("password"));
	    }
	}

}

package com.mimascota.mimascota;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FormularioFragment extends Fragment {
	private String json = "";
	private EditText eNombre;
	private EditText eColor;
	private EditText eDescripcion;
	private EditText eRaza;
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                   Bundle savedInstanceState) {	
		 
		 View view = inflater.inflate(R.layout.fragment_formulario, container, false);
	        
	        
			final Button buttonOk = (Button)view.findViewById(R.id.bOkFormulario);
			eNombre = (EditText)view.findViewById(R.id.tNombre);
			eColor = (EditText)view.findViewById(R.id.tColor);
			eDescripcion = (EditText)view.findViewById(R.id.tDescripcion);
			eRaza = (EditText)view.findViewById(R.id.tBreed);
			
			buttonOk.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					onClickOk(v);
				}
			});
			
			return view;
	    }
	 
	    @Override
	    public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);	 

	    }
	    
		private void onClickOk(View v) {
			String sNombre = eNombre.getText().toString();
			String sColor = eColor.getText().toString();
			String sDescripcion = eDescripcion.getText().toString();
			String sRaza = eRaza.getText().toString();
			
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("name", sNombre);
				jsonObject.put("color", sColor);
				jsonObject.put("description", sDescripcion);
				jsonObject.put("breed", sRaza);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			json = jsonObject.toString();
			
			Intent i = new Intent();
			i.putExtra("json", json);
			super.getActivity().setResult(Activity.RESULT_OK, i);
			super.getActivity().finish();
		}		


}

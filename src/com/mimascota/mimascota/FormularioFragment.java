package com.mimascota.mimascota;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FormularioFragment extends Fragment {
	private String json = "";
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                   Bundle savedInstanceState) {	
		 
		 View view = inflater.inflate(R.layout.fragment_formulario, container, false);
	        
	        
			final Button buttonOk = (Button)view.findViewById(R.id.bOkFormulario);
			
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
			json = "afafaffaegag";
			
			Intent i = new Intent();
			i.putExtra("json", json);
			super.getActivity().setResult(Activity.RESULT_OK, i);
			super.getActivity().finish();
		}		


}

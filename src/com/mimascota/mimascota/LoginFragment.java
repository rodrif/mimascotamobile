package com.mimascota.mimascota;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class LoginFragment extends Fragment {
	
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
			// TODO Auto-generated method stub
			mCallback.Loguear(2);
			
		}
}

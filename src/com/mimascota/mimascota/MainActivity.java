package com.mimascota.mimascota;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements LoginFragment.InterfaceLogin {
	
	public void Loguear(int userId) {
		//FIXME agregar datos de userId fragment
		
        SubirFotoYFormulario nuevoFragment = new SubirFotoYFormulario();
        
        nuevoFragment.setUserId(userId);
    /*    Bundle args = new Bundle();
        args.putInt(SubirFotoYFormulario.ARG_POSITION, position);
        nuevoFragment.setArguments(args);*/
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		 // Reemplazar en fragment_container cualquier Fragment con este

		 transaction.replace(R.id.fragment1, nuevoFragment);
//		 transaction.addToBackStack(null);

		 // Realizar la transacción
		 transaction.commit();	

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LoginFragment nuevoFragment = new LoginFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		 // Reemplazar en fragment_container cualquier Fragment con este

		 transaction.replace(R.id.fragment1, nuevoFragment);
//		 transaction.addToBackStack(null);

		 // Realizar la transacción
		 transaction.commit();	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

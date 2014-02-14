package com.mimascota.mimascota;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

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
		
		SubMenu smnu = menu.
			    addSubMenu(Menu.NONE, 0, Menu.NONE, "Servidor");
			//        .setIcon(android.R.drawable.ic_menu_agenda);
		
		for(int i = 2 ; i <=200 ; i++) {
			smnu.add(Menu.NONE, i, Menu.NONE, "192.168.1." + Integer.toString(i));
		}
			
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		Log.d("MiMascota", Integer.toString(item.getItemId()));
		if(item.getItemId() > 0) {
			Constantes.setIpServer("192.168.1." +Integer.toString(item.getItemId()));
		}
		Log.d("MiMascota", "IpServer es: " + Constantes.IPSERVER);
		
	    return true;
	}
}

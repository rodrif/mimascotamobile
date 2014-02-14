package com.mimascota.mimascota;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity {
	private GoogleMap mapa = null;
	private LatLng latLng = new LatLng(0.0, 0.0);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);

		mapa = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		mapa.setOnMapClickListener(new OnMapClickListener() {
			public void onMapClick(LatLng point) {
				mapa.clear();
				mapa.addMarker(new MarkerOptions().position(point).title(
						"Perro perdido"));
				latLng = point;
			}
		});	
		
		CameraUpdate camUpd = CameraUpdateFactory.newLatLngZoom(new LatLng(-34.6209083, -58.4587529), 10);
		mapa.moveCamera(camUpd);		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)	{	
		switch(item.getItemId()){
			case R.id.action_confirmar:
				Intent i = new Intent();
				i.putExtra("lat", latLng.latitude);
				i.putExtra("lng", latLng.longitude);

				this.setResult(Activity.RESULT_OK, i);
				this.finish();
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapa, menu);
		return true;
	}
}

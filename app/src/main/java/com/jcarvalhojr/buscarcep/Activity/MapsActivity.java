package com.jcarvalhojr.buscarcep.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jcarvalhojr.buscacep.R;
import com.jcarvalhojr.buscarcep.Helpers.AlertUtils;
import com.jcarvalhojr.buscarcep.ServiceUtils.ConnectivityServices;


/**
 * Criado por JcarvalhoJr em 27/06/2018.
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double lat;
    private Double lng;
    private String endereco;
    private LatLng local;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

       // setupToobar();


        if (!ConnectivityServices.isNetworkAvailable(MapsActivity.this)) {
            AlertUtils.alertDialog(MapsActivity.this,
                    R.string.info_title_alertdialog,
                    R.string.error_conexao_indisponivel);
            return;
        }

        lat = getIntent().getExtras().getDouble("lat");
        lng = getIntent().getExtras().getDouble("lng");
        endereco = getIntent().getStringExtra("endereco");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        local = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(local).title(endereco));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));

        CameraUpdate zoonLocal = CameraUpdateFactory.newLatLngZoom(local, 15);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mMap.animateCamera(zoom);
        mMap.animateCamera(zoonLocal);

       setUpMap();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("lat", lat);
        outState.putDouble("lng", lng);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        lat = (savedInstanceState.getDouble("lat"));
        lng = (savedInstanceState.getDouble("lng"));
    }

    public void setUpMap() {

        UiSettings mapUi = mMap.getUiSettings();
        mapUi.setMapToolbarEnabled(false);
        mapUi.setAllGesturesEnabled(true);
        mapUi.setTiltGesturesEnabled(false);

        mapUi.setZoomControlsEnabled(true);

     /*   mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);*/


        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    private void setupToobar() {

        getLayoutInflater().inflate(R.layout.toolbar, (ViewGroup) findViewById(android.R.id.content));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(endereco);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }


}

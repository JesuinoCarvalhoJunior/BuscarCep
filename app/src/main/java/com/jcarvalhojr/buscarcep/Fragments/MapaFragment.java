package com.jcarvalhojr.buscarcep.Fragments;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jcarvalhojr.buscacep.R;
import com.jcarvalhojr.buscarcep.Domain.Cep;
import com.jcarvalhojr.buscarcep.Helpers.AlertUtils;
import com.jcarvalhojr.buscarcep.ServiceUtils.PermissionUtils;

import org.parceler.Parcels;

public class MapaFragment extends BaseFragment implements OnMapReadyCallback {
    protected LocationManager locationManager;

    private GoogleMap map;
    private Cep cep;
    private Double lat;
    private Double lng;
    private String endereco;

    public Context getContext() {
        return getActivity();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);
        // Recupera o fragment que está no layout
        // Utiliza o getChildFragmentManager() pois é um fragment dentro do outro
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.mapFragment);
        // Inicia o Google Maps dentro do fragment
        mapFragment.getMapAsync(this);

        this.cep = Parcels.unwrap(getArguments().getParcelable("cep"));

        lat = Double.parseDouble(cep.getLat().toString());
        lng = Double.parseDouble(cep.getLng().toString());
        endereco = cep.getAddress_name().toString();

        setHasOptionsMenu(true);

        return view;

    }

    @Override
    public void onMapReady(GoogleMap map) {
        // O método onMapReady(map) é chamado quando a inicialização do mapa estiver Ok.
        this.map = map;
        if (cep != null) {

            setUpMap();

            LatLng location = new LatLng(lat, lng);


            //1º opcao - Posiciona o mapa na coordenada da fábrica (zoom = 13)
            //CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location, 13);

            //2ª - opacao
            CameraPosition position = new CameraPosition.Builder().target(location).zoom(15).build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);

            //Com animacao
            map.animateCamera(update, 3000, new GoogleMap.CancelableCallback() {


                @Override
                public void onFinish() {
                    Toast.makeText(getContext(), "Mapa Centralizado", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel() {
                    Toast.makeText(getContext(), "Animação Cancelada", Toast.LENGTH_SHORT).show();
                }
            });

            map.addMarker(new MarkerOptions()
                    .title(cep.getAddress_name().toString())
                    .snippet(cep.getCity().toString())
                    .position(location));

            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //  inflater.inflate(R.menu.menu_frag_mapa, menu);
        inflater.inflate(R.menu.menu_frag_mapa, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (map != null && cep != null) {
            if (item.getItemId() == R.id.action_location_carro) {

                LatLng location = new LatLng(Double.parseDouble(cep.getLat()),
                        Double.parseDouble(cep.getLng()));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

            } else if (item.getItemId() == R.id.ic_street_view) {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + lat + "," + lng);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            } else if (item.getItemId() == R.id.action_zoom_in) {
                toast("zoom +");
                map.animateCamera(CameraUpdateFactory.zoomIn());
            } else if (item.getItemId() == R.id.action_zoom_out) {
                toast("zoom -");
                map.animateCamera(CameraUpdateFactory.zoomOut());
            } else if (item.getItemId() == R.id.action_mapa_normal) {
                // Modo Normal
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            } else if (item.getItemId() == R.id.action_mapa_satelite) {
                // Modo Satélite
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            } else if (item.getItemId() == R.id.action_mapa_terreno) {
                // Modo Terreno
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            } else if (item.getItemId() == R.id.action_mapa_hibrido) {
                // Modo Híbrido
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            } else if (item.getItemId() == R.id.action_about) {
                AboutDialog.showAbout(getChildFragmentManager());
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpMap() {

        UiSettings mapUi = map.getUiSettings();
        mapUi.setMapToolbarEnabled(true);
        mapUi.setAllGesturesEnabled(true);
        mapUi.setTiltGesturesEnabled(true);
        mapUi.setMyLocationButtonEnabled(true);
        mapUi.setZoomControlsEnabled(true);
        mapUi.setMapToolbarEnabled(true);

        try {
            String permissions[] = getResources().getStringArray(R.array.array_permission);
            boolean ok = PermissionUtils.validate(getActivity(), 0, permissions);
            if (ok) {
                map.setMyLocationEnabled(true);
            }
        } catch (SecurityException e) {
            map.setMyLocationEnabled(false);
            AlertUtils.alert(getActivity(), e.getClass().getSimpleName(), e.getMessage());
        }

      /*  map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(MapaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
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
        map.setMyLocationEnabled(true);*/
    }

}

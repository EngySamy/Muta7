package com.muta7.muta7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by DeLL on 11/07/2017.
 */

public class GetAddressMap extends FragmentActivity implements OnMapReadyCallback{
    GoogleMap googleMap;
    LatLng latlng;
    Marker mark;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_address_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap=map;
        mark=googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mark.setPosition(latLng);
                latlng=latLng;
                Toast.makeText(
                        GetAddressMap.this, "Lat " + latLng.latitude + " " + "Long " + latLng.longitude, Toast.LENGTH_LONG).show();

            }
        });

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent data = new Intent();
                data.putExtra("Lat", marker.getPosition());
                data.putExtra("Lng", marker.getPosition().longitude);
                // Activity finished ok, return the data
                setResult(RESULT_OK, data);
                finish();
                return true;
            }
        });
    }

}
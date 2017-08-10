package com.muta7.muta7.create_space.controllers.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.muta7.muta7.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by DeLL on 11/07/2017.
 */

public class GetAddressMap extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap googleMap;
    LatLng latlng;
    Marker mark;
    PlaceAutocompleteFragment autocompleteFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_address_map);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.e("TAG selected place", "Place: " + place.getName());

                Geocoder coder = new Geocoder(getBaseContext());
                List<Address> address;
                //GeoPoint p1 = null;

                try {
                    address = coder.getFromLocationName(place.getName().toString(),5);
                    if (address==null ||address.size()==0) {
                        Toast.makeText(getBaseContext(),"This address is not found",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Address location = address.get(0);
                        latlng = new LatLng(location.getLatitude(), location.getLongitude());
                        mark.setPosition(latlng);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i("TAG", "An error occurred: " + status);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        autocompleteFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap=map;
        latlng=new LatLng(26.8206, 30.8025);
        mark=googleMap.addMarker(new MarkerOptions()
                .position(latlng)
                .title("Marker"));
        float zoomLevel = 5; //This goes up to 21
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoomLevel));


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
                data.putExtra("Lat", marker.getPosition().latitude);
                data.putExtra("Lng", marker.getPosition().longitude);
                //Toast.makeText(
                  //      GetAddressMap.this, "Lat " + latlng.latitude + " " + "Long " + latng.longitude, Toast.LENGTH_LONG).show();
                // Activity finished ok, return the data
                setResult(RESULT_OK, data);
                finish();
                return true;
            }
        });
    }

}
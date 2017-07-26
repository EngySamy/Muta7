package com.muta7.muta7;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import static android.app.Activity.RESULT_OK;

/**
 * Created by DeLL on 11/07/2017.
 */

public class LocationFragment extends CreateSpaceFragment {

    AutoCompleteTextView city,district;
    ArrayAdapter<CharSequence> districtAdapter ;
    String[] districtArray;
    EditText fullAddress;
    LinearLayout layout;
    Button add,remove;

    LayoutInflater inflat;
    ViewGroup group;
    int id=0;
    long lat,lng;
    Vector<Object> nearbyPlaces;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflat=inflater;
        group=container;
        nearbyPlaces=new Vector<>();

        final View rootView = inflater.inflate(R.layout.location, container, false);

        city = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceCityValue);
        final ArrayAdapter<CharSequence> cityAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.cities,android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapter.notifyDataSetChanged();

        city.setValidator(new AutoCompleteTextView.Validator() {
            @Override
            public boolean isValid(CharSequence text) {
                String[] array = getResources().getStringArray(R.array.cities);
                Arrays.sort(array);
                if ( Arrays.binarySearch(array, text.toString())>= 0) {
                    city.setError(null);
                    return true;
                }
                return false;
            }

            @Override
            public CharSequence fixText(CharSequence invalidText) {
                city.setError("Invalid city");
                return invalidText;
            }
        });

        city.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ((AutoCompleteTextView)v).performValidation();
                }
                else
                    city.showDropDown();
            }
        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city.showDropDown();
            }
        });
        city.setThreshold(1);



        district = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceDistrictValue);
        districtArray=getResources().getStringArray(R.array.empty);
        district.setThreshold(1);

        district.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ((AutoCompleteTextView)v).performValidation();
                }
            }
        });

        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                district.showDropDown();
            }
        });
        district.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                validateCityAdaptDistrict(rootView);
                district.showDropDown();
                return false;
            }
        });

        district.setValidator(new AutoCompleteTextView.Validator() {
            @Override
            public boolean isValid(CharSequence text) {
                Arrays.sort(districtArray);
                if (Arrays.binarySearch(districtArray, text.toString()) > 0) {
                    district.setError(null);
                    return true;
                }
                return false;
            }

            @Override
            public CharSequence fixText(CharSequence invalidText) {
                district.setError("Invalid district");
                return invalidText;
            }
        });




        Button map=(Button) rootView.findViewById(R.id.goToMap);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMap=new Intent(getActivity(),GetAddressMap.class);
                startActivityForResult(openMap,111);
            }
        });

        fullAddress=(EditText) rootView.findViewById(R.id.SpaceAddressValue);

        layout=(LinearLayout) rootView.findViewById(R.id.locationLayout);
        AddNearbyPlace(layout);
        add=(Button) rootView.findViewById(R.id.addNearbyPlace);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNearbyPlace(layout);
            }
        });
        remove=(Button) rootView.findViewById(R.id.removeNearbyPlace);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveNearbyPlace(layout);
            }
        });


        return rootView;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 111) {
            if (data.hasExtra("Lat") &&data.hasExtra("Lng")) {
                double latitude=data.getExtras().getDouble("Lat");
                double longitude=data.getExtras().getDouble("Lng");
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getContext(), Locale.getDefault());

                try {

                    addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    if(address==null) address="";
                    if(city==null) city="";
                    if(state==null) state="";
                    if(country==null) country="";
                    //Toast.makeText(getActivity(),"Lat "+latitude+" Lng "+longitude+" Address "+ address+" "+city+" "+state+" "+country, Toast.LENGTH_SHORT).show();
                    fullAddress.setText(address+" "+city+" "+state+" "+country);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    @Override
    public boolean validate(){
        //TODO : implement the validation
        return true;
    }

    @Override
    public Object getData() {
        //return new Location(city.getText().toString(),district.getText().toString(),lat,lng,fullAddress.getText().toString(),);
        return null;
    }

    private void AddNearbyPlace(LinearLayout base){
        View place=inflat.inflate(R.layout.nearby_place,group,false);
        base.addView(place);
        nearbyPlaces.add(place);
    }

    private void RemoveNearbyPlace(LinearLayout base){
        if(nearbyPlaces.size()>1) {
            View toRemove = (View) nearbyPlaces.lastElement();
            ((ViewManager) toRemove.getParent()).removeView(toRemove);
            nearbyPlaces.remove(toRemove);
        }
        else {
            Toast.makeText(getContext(),"You should have at least one nearby place",Toast.LENGTH_LONG).show();
        }

    }

    private void validateCityAdaptDistrict(View rootView){
        String sel=city.getText().toString();
        if(sel.equals("Alexandria")) {
            //district.setError(null);
            districtAdapter= ArrayAdapter.createFromResource(rootView.getContext(), R.array.alex, android.R.layout.simple_spinner_item);
            districtArray=getResources().getStringArray(R.array.alex);
        }
        else if(sel.equals("Cairo")) {
            //district.setError(null);
            districtAdapter= ArrayAdapter.createFromResource(rootView.getContext(), R.array.cairo, android.R.layout.simple_spinner_item);
            districtArray=getResources().getStringArray(R.array.cairo);
        }
        else{
            districtAdapter= ArrayAdapter.createFromResource(rootView.getContext(), R.array.empty, android.R.layout.simple_spinner_item);
            districtArray=getResources().getStringArray(R.array.empty);
            district.setError("Choose a valid city first");
        }

        district.setAdapter(districtAdapter);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtAdapter.notifyDataSetChanged();


    }


}

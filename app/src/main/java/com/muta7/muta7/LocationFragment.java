package com.muta7.muta7;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by DeLL on 11/07/2017.
 */

public class LocationFragment extends CreateSpaceFragment {

    SubmitListener mCallback;
    AutoCompleteTextView city,district;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.location, container, false);

        city = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceCityValue);
        ArrayAdapter<CharSequence> cityAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.cities,android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);
        city.setValidator(new AutoCompleteTextView.Validator() {
            @Override
            public boolean isValid(CharSequence text) {
                String[] array = getResources().getStringArray(R.array.cities);
                Arrays.sort(array);
                if (Arrays.binarySearch(array, text.toString()) > 0) {
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
            }
        });


        district = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceDistrictValue);
        //ArrayAdapter<CharSequence> districtAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.cairo_districts,android.R.layout.simple_spinner_item);
        //districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //district.setAdapter(districtAdapter);
        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] array = getResources().getStringArray(R.array.cities);
                Arrays.sort(array);
                if (Arrays.binarySearch(array, city.getText().toString()) > 0) {
                    // assign for every city some array of districts
                    //R.array
                }

            }
        });


        Button map=(Button) rootView.findViewById(R.id.goToMap);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMap=new Intent(getActivity(),GetAddressMap.class);
                startActivityForResult(openMap,111);
                //startActivity(openMap);
            }
        });

        Button submit=(Button) rootView.findViewById(R.id.Submit);
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCallback.Submit(1);
                    }
                }
        );
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SubmitListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SubmitListener");
        }
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
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    Toast.makeText(getActivity(), address+" "+city+" "+state+" "+country+" "+knownName,
                            Toast.LENGTH_SHORT).show();

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
        return null;
    }


}

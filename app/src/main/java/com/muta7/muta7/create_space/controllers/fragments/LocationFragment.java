package com.muta7.muta7.create_space.controllers.fragments;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.muta7.muta7.R;
import com.muta7.muta7.create_space.controllers.activities.CreateSpaceActivity;
import com.muta7.muta7.create_space.controllers.activities.GetAddressMap;
import com.muta7.muta7.database.models.Location;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import static android.app.Activity.RESULT_OK;

/**
 * Created by DeLL on 11/07/2017.
 */

public class LocationFragment extends CreateSpaceFragmentBase {

    View rootView;
    AutoCompleteTextView city,district;
    ArrayAdapter<CharSequence> districtAdapter ;
    String[] districtArray;
    EditText fullAddress;
    LinearLayout layout;
    Button add,remove;

    LayoutInflater inflat;
    ViewGroup group;
    int id=0;
    double lat,lng;
    Vector<View> nearbyPlaces;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflat=inflater;
        group=container;
        nearbyPlaces=new Vector<>();
        rootView = inflater.inflate(R.layout.location, container, false);
        lat=0;
        lng=0;

        city = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceCityValue);
        final ArrayAdapter<CharSequence> cityAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.cities,android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapter.notifyDataSetChanged();

        city.setValidator(new AutoCompleteTextView.Validator() {
            @Override
            public boolean isValid(CharSequence text) {
                return validateCity(text);
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
                AdaptDistrictToCity();
                district.showDropDown();
                return false;
            }
        });

        district.setValidator(new AutoCompleteTextView.Validator() {
            @Override
            public boolean isValid(CharSequence text) {
                return validateDistrict(text);
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

        Button next=(Button) rootView.findViewById(R.id.nextInLocation);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateSpaceActivity.viewPager.setCurrentItem(2,true);
            }
        });

        Button back=(Button) rootView.findViewById(R.id.backInLocation);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateSpaceActivity.viewPager.setCurrentItem(0,true);
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
                lat=latitude;
                lng=longitude;

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
        if(validateCity(city.getText().toString())){ //city first
            AdaptDistrictToCity();
            if(validateDistrict(district.getText().toString())){
                if(lat==0&&lng==0 ){
                 Toast.makeText(getContext(),"Please select your place on the map",Toast.LENGTH_LONG).show();
                 return false;

                } else if(validateNearbyPlaces() )//district then map then nearby
                     return true;
            }
        }

        return false;
    }

    @Override
    public Object getData() {
        return new Location(city.getText().toString(),district.getText().toString(),lat,lng,
                fullAddress.getText().toString(),getNearbyPlaces());
    }

    private void AddNearbyPlace(LinearLayout base){
        if(nearbyPlaces.size()>=getResources().getInteger(R.integer.max_nearby_places)) {
            Toast.makeText(getContext(),"You reached maximum nearby places",Toast.LENGTH_SHORT).show();
            return;
        }
        View place=inflat.inflate(R.layout.nearby_place,group,false);
        TextInputEditText p= (TextInputEditText)(place.findViewById(R.id.SpaceNearPlaceValue));
        //set max length
        p.setFilters(new InputFilter[]{new InputFilter.LengthFilter(getResources().getInteger(R.integer.max_length_nearby_place))});
        base.addView(place);
        nearbyPlaces.add(place);
    }

    private void RemoveNearbyPlace(LinearLayout base){
        if(nearbyPlaces.size()>1) {
            View toRemove = nearbyPlaces.lastElement();
            ((ViewManager) toRemove.getParent()).removeView(toRemove);
            nearbyPlaces.remove(toRemove);
        }
        else {
            //Toast.makeText(getContext(),"You should have at least one nearby place",Toast.LENGTH_LONG).show();
        }
    }

    private Vector<String> getNearbyPlaces(){
        int len=nearbyPlaces.size();
        Vector<String> places=new Vector<>();
        for(int i=0;i<len;i++){
            String str=((TextInputEditText)nearbyPlaces.elementAt(i).findViewById(R.id.SpaceNearPlaceValue)).getText().toString().trim();
            if(!str.equals(""))
                places.add(str);
        }
        return places;
    }

    private boolean validateNearbyPlaces(){
        int len=nearbyPlaces.size();
        TextInputEditText place;
        for(int i=0;i<len;i++){
            place=((TextInputEditText)nearbyPlaces.elementAt(i).findViewById(R.id.SpaceNearPlaceValue));
            if(!validateOnePlace(place))
                return false;
        }
        return true;
    }
    private boolean validateOnePlace(TextInputEditText place){
        String str=place.getText().toString();
        if(str.length()>getResources().getInteger(R.integer.max_length_nearby_place)){
            place.setError("This field exceeds the max limit");
            return false;
        }

        place.setError(null);
        return true;

    }

    private void AdaptDistrictToCity(){
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

    private boolean validateCity(CharSequence text){
        String[] array = getResources().getStringArray(R.array.cities);
        Arrays.sort(array);
        if ( Arrays.binarySearch(array, text.toString())>= 0) {
            city.setError(null);
            return true;
        }
        city.setError(null);
        return false;
    }

    private boolean validateDistrict(CharSequence text) {
        Arrays.sort(districtArray);
        if (Arrays.binarySearch(districtArray, text.toString()) > 0) {
            district.setError(null);
            return true;
        }
        district.setError(null);
        return false;
    }

}

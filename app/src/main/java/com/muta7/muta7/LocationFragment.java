package com.muta7.muta7;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by DeLL on 11/07/2017.
 */

public class LocationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.location, container, false);

        AutoCompleteTextView city = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceCityValue);
        ArrayAdapter<CharSequence> cityAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.cities,android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);


        AutoCompleteTextView district = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceDistrictValue);
        ArrayAdapter<CharSequence> districtAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.districts,android.R.layout.simple_spinner_item);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(districtAdapter);



/*
        Button submit=(Button) rootView.findViewById(R.id.Submit);
        final EditText Name=(EditText) rootView.findViewById(R.id.SpaceNameValue);
        final EditText Desc=(EditText) rootView.findViewById(R.id.SpaceDescValue);

        final EditText Mobile=(EditText) rootView.findViewById(R.id.SpaceMobileValue);
        final EditText Email=(EditText) rootView.findViewById(R.id.SpaceEmailValue);
        final EditText Website=(EditText) rootView.findViewById(R.id.SpaceWebsiteValue);
        final EditText Fb=(EditText) rootView.findViewById(R.id.SpaceFbValue);
        final EditText Tw=(EditText) rootView.findViewById(R.id.SpaceTwValue);
        final EditText Insta=(EditText) rootView.findViewById(R.id.SpaceInstaValue);
        final EditText Yt=(EditText) rootView.findViewById(R.id.SpaceYtValue);

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Validate
                        //Log.v("EditText", Name.getText().toString());
                        CoworkingSpaceController.addNewSpace("00",Name.getText().toString(),Desc.getText().toString(),Mobile.getText().toString(),
                                Email.getText().toString(),Website.getText().toString(),Fb.getText().toString(),Tw.getText().toString(),
                                Insta.getText().toString(),Yt.getText().toString());
                    }
                }
        );*/
        return rootView;
    }
}

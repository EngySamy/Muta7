package com.muta7.muta7;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by DeLL on 11/07/2017.
 */

public class LocationFragment extends CreateSpaceFragment {

    SubmitListener mCallback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.location, container, false);

        AutoCompleteTextView city = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceCityValue);
        ArrayAdapter<CharSequence> cityAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.cities,android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);


        AutoCompleteTextView district = (AutoCompleteTextView)rootView.findViewById(R.id.SpaceDistrictValue);
        ArrayAdapter<CharSequence> districtAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.cairo_districts,android.R.layout.simple_spinner_item);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(districtAdapter);



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
    public boolean validate(){
        //TODO : implement the validation
        return true;
    }

    @Override
    public Object getData() {
        return null;
    }


}

package com.muta7.muta7;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by DeLL on 15/07/2017.
 */

public class RoomsAndAmenitiesFragment extends CreateSpaceFragment {
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.rooms_amenities, container, false);
        String[] names = getResources().getStringArray(R.array.general_amenities_names);
        TypedArray ids=getResources().obtainTypedArray(R.array.general_amenities_icons);

        gridView=(GridView)  rootView.findViewById(R.id.amnts_grid);
        AmenityAdapter adapter=new AmenityAdapter(this,names,ids);
        gridView.setAdapter(adapter);


        return rootView;
    }
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public Object getData() {
        return null;
    }
}

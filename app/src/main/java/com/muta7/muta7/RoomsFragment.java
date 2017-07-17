package com.muta7.muta7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DeLL on 15/07/2017.
 */

public class RoomsFragment extends CreateSpaceFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.location, container, false);


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

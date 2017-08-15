package com.muta7.muta7.navigation.controllers.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muta7.muta7.R;

public class FindSpaceFragment extends Fragment {

    private OnCreateFindSpaceFragment activityListener;

    public FindSpaceFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find_space, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activityListener.createFilterDrawer();
    }

    public interface OnCreateFindSpaceFragment{
        void createFilterDrawer();
        void removeFilterDrawer();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityListener = (OnCreateFindSpaceFragment) context;
        }catch (ClassCastException exception){

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activityListener.removeFilterDrawer();
    }
}

package com.muta7.muta7.navigation.controllers.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.muta7.muta7.R;


public class HomeFragment extends Fragment {
    private FloatingSearchView searchBar;
    private Button findSpaceButton;
    private Button createSpaceButton;
    private ConstraintLayout homeFragmentMainLayout;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBar = (FloatingSearchView) view.findViewById(R.id.fsv_search_bar);
        findSpaceButton = (Button) view.findViewById(R.id.b_find_space);
        createSpaceButton = (Button) view.findViewById(R.id.b_create_space);
        homeFragmentMainLayout = (ConstraintLayout) view.findViewById(R.id.home_fragment_main_layout);
    }

    @Override
    public void onResume() {
        super.onResume();
        homeFragmentMainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                searchBar.setSearchFocused(false);
                return false;
            }
        });
    }
}

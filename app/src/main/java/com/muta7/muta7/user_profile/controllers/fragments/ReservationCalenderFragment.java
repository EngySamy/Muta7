package com.muta7.muta7.user_profile.controllers.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muta7.muta7.R;
import com.muta7.muta7.user_profile.helpers.ReservationFragmentListener;

/**
 * Created by DeLL on 12/08/2017.
 */

public class ReservationCalenderFragment extends Fragment {
    ReservationFragmentListener reservationFragmentListener;

    public static ReservationCalenderFragment newInstance(ReservationFragmentListener r) {
        ReservationCalenderFragment f = new ReservationCalenderFragment();
        f.reservationFragmentListener=r;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.reservation_calender, container, false);

        FloatingActionButton goToList=(FloatingActionButton) rootView.findViewById(R.id.goToList);
        goToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationFragmentListener.onSwitchToNextFragment();
            }
        });

        return rootView;
    }
}

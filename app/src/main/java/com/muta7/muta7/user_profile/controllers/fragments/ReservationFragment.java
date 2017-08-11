package com.muta7.muta7.user_profile.controllers.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muta7.muta7.R;
import com.muta7.muta7.user_profile.helpers.ReservationRecyclerAdapter;

/**
 * Created by DeLL on 10/08/2017.
 */

public class ReservationFragment extends Fragment {
    RecyclerView reservationRecyclerView;
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager  mLayoutManager;
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.reservation_list, container, false);

        reservationList();
        return rootView;
    }

    private void reservationList(){
        reservationRecyclerView = (RecyclerView) rootView.findViewById(R.id.reservation_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //reservationRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reservationRecyclerView.setLayoutManager(mLayoutManager);

        String[] mDataset={"eeng","mmm","00000","5555","2222222","33333333","66666666","99999999"};
        mAdapter = new ReservationRecyclerAdapter(mDataset);
        reservationRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}

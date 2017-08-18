package com.muta7.muta7.user_profile.controllers.fragments;

import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muta7.muta7.R;
import com.muta7.muta7.database.models.DAY_HOURS;
import com.muta7.muta7.database.models.Reservation;
import com.muta7.muta7.user_profile.helpers.ReservationFragmentListener;
import com.muta7.muta7.user_profile.helpers.ReservationRecyclerAdapter;

import java.util.Date;
import java.util.Vector;

/**
 * Created by DeLL on 10/08/2017.
 */

public class ReservationListFragment extends Fragment {
    RecyclerView reservationRecyclerView;
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager  mLayoutManager;
    View rootView;
    ReservationFragmentListener reservationListFragmentListener;

    public static ReservationListFragment newInstance(ReservationFragmentListener r) {
        ReservationListFragment f = new ReservationListFragment();
        f.reservationListFragmentListener=r;
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.reservation_list, container, false);

        reservationList();
        FloatingActionButton goToCalender=(FloatingActionButton) rootView.findViewById(R.id.goToCalender);
        goToCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationListFragmentListener.onSwitchToNextFragment();
            }
        });
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

        Vector<Reservation>mDataset=getDataForTest() ;//={"eeng","mmm","00000","5555","2222222","33333333","66666666","99999999"};

        mAdapter = new ReservationRecyclerAdapter(mDataset);
        reservationRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    private Vector<Reservation> getDataForTest(){
        Vector<Reservation>mDataset=new Vector<>();
        for(Integer i=0;i<10;i++){
            Vector<DAY_HOURS> t=new Vector<>(2);
            t.add(DAY_HOURS.AM10_00);
            Reservation rs=new Reservation(i.toString(),"00","00","00", t,new Date((long)999999999));
            mDataset.add(rs);
        }
        return mDataset;
    }
}

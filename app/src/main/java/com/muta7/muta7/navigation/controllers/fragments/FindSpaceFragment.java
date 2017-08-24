package com.muta7.muta7.navigation.controllers.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muta7.muta7.R;
import com.muta7.muta7.database.models.Space;
import com.muta7.muta7.navigation.helpers.FindSpaceCardsAdapter;

public class FindSpaceFragment extends Fragment implements FindSpaceCardsAdapter.FindSpaceCardClickListener{

    private OnCreateFindSpaceFragment activityListener;
    private RecyclerView findSpaceList;
    private FindSpaceCardsAdapter findSpaceCardsAdapter;
    private OnCardClickListener activityCardClickListeneer;

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
        findSpaceList = (RecyclerView) view.findViewById(R.id.rv_favourite_spaces_list);
        findSpaceList.setLayoutManager(new LinearLayoutManager(getContext()));
        findSpaceList.setHasFixedSize(true);
        findSpaceCardsAdapter = new FindSpaceCardsAdapter(this);
        findSpaceList.setAdapter(findSpaceCardsAdapter);
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
            activityCardClickListeneer = (OnCardClickListener) context;
        }catch (ClassCastException exception){

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activityListener.removeFilterDrawer();
    }

    @Override
    public void onClick(Space space) {
        activityCardClickListeneer.onFindSpaceCardClickListener(space);
    }

    public interface OnCardClickListener{
        void onFindSpaceCardClickListener(Space space);
    }
}

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
import com.muta7.muta7.navigation.helpers.FavouriteSpacesCardsAdapter;

public class FavouriteSpacesFragment extends Fragment implements FavouriteSpacesCardsAdapter.FavouriteSpaceCardClickListener{

    private RecyclerView favouriteSpacesList;
    private FavouriteSpacesCardsAdapter favouriteSpacesCardsAdapter;
    private OnCardClickListener activityListener;

    public FavouriteSpacesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityListener = (OnCardClickListener) context;
        }catch (ClassCastException exception){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite_spaces, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favouriteSpacesList = (RecyclerView) view.findViewById(R.id.rv_favourite_spaces_list);
        favouriteSpacesList.setLayoutManager(new LinearLayoutManager(getContext()));
        favouriteSpacesList.setHasFixedSize(true);
        favouriteSpacesCardsAdapter = new FavouriteSpacesCardsAdapter(this);
        favouriteSpacesList.setAdapter(favouriteSpacesCardsAdapter);
    }

    @Override
    public void onClick(Space space) {
        activityListener.onCardClickListner(space);
    }

    public interface OnCardClickListener{
        void onCardClickListner(Space space);
    }
}

package com.muta7.muta7.navigation.helpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muta7.muta7.R;
import com.muta7.muta7.database.models.Space;

/**
 * Created by Kareem Waleed on 8/20/2017.
 */

public class FavouriteSpacesCardsAdapter extends RecyclerView.Adapter<FavouriteSpacesCardsAdapter.SpaceViewHolder>{

    private Space[] favouriteSpaces;
    private FavouriteSpaceCardClickListener listener;

    public FavouriteSpacesCardsAdapter(FavouriteSpaceCardClickListener listener){
            this.listener = listener;
    }


    public void setFavouriteSpaces(Space[] favouriteSpaces){
        this.favouriteSpaces = favouriteSpaces;
        notifyDataSetChanged();
    }

    @Override
    public SpaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View favouriteSpaceCardLayout = layoutInflater.inflate(R.layout.favourite_space_card, parent, false);
        return new SpaceViewHolder(favouriteSpaceCardLayout);
    }

    @Override
    public void onBindViewHolder(SpaceViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class SpaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public SpaceViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(int position){
            //TODO: set the card with real data
            ImageView spaceImage = (ImageView) itemView.findViewById(R.id.iv_space_logo);
            TextView spaceName = (TextView) itemView.findViewById(R.id.tv_space_name);
            spaceImage.setImageResource(R.drawable.creativo);
            spaceName.setText("Creativo");
        }

        @Override
        public void onClick(View v) {
            //listener.onClick(favouriteSpaces[getAdapterPosition()]);
        }
    }

    public interface FavouriteSpaceCardClickListener{
        void onClick(Space space);
    }
}

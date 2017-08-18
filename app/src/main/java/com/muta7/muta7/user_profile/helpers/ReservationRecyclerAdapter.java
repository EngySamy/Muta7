package com.muta7.muta7.user_profile.helpers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muta7.muta7.R;
import com.muta7.muta7.database.models.Reservation;

import java.util.Vector;

/**
 * Created by DeLL on 10/08/2017.
 */

public class ReservationRecyclerAdapter extends RecyclerView.Adapter<ReservationRecyclerAdapter.ViewHolder> {

    private Vector<Reservation> mDataset;

    public ReservationRecyclerAdapter(Vector<Reservation> data){
        mDataset=data;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CharSequence reservation="";
        Reservation currentR=mDataset.elementAt(position);
        String space=currentR.getSpaceName(); //can't work for now
        String group=currentR.getGroupName(); //can't work for now
        String day="";
        if(currentR.getDay()!=null)
            day=currentR.getDay().toString();
        String hour=currentR.getFirstHour();
        //day.concat(hour);
        String temp="You have a reservation in ";
        String temp2=" with ";
        String temp3=" at ";

        SpannableStringBuilder span1 = new SpannableStringBuilder(temp+space+temp2+group+temp3+day);

        span1.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), temp.length(), temp.length()+space.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        span1.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), temp.length()+space.length()+temp2.length(),
                temp.length()+space.length()+temp2.length()+group.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        span1.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), temp.length()+space.length()+temp2.length()+group.length()+temp3.length()
                , temp.length()+space.length()+temp2.length()+group.length()+temp3.length()+day.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        holder.textView.setText(span1);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;

        ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.reservation_card_view);
            textView = (TextView) v.findViewById(R.id.reservation_text_view);
        }
    }
}

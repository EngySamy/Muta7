package com.muta7.muta7.navigation.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.muta7.muta7.R;

/**
 * Created by Kareem Waleed on 8/24/2017.
 */

public class GovernorateSpinnerAdapter extends BaseAdapter {

    private Context applicationContext;
    private String[] governorates;
    private LayoutInflater inflater;


    public GovernorateSpinnerAdapter(Context applicationContext, String[] governorates){
        this.applicationContext = applicationContext;
        this.governorates = governorates;
        this.inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return governorates.length;
    }

    @Override
    public Object getItem(int position) {
        return governorates[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.governorate_spinner_item, null);
        TextView selectedGovernorate = (TextView) convertView.findViewById(R.id.selected_governorate);
        selectedGovernorate.setText(governorates[position]);
        if(position != 0)
            selectedGovernorate.setTextColor(applicationContext.getResources().getColor(R.color.colorAccent));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.governorate_spinner_dropdown_item, null);
        TextView selectedGovernorate = (TextView) convertView.findViewById(R.id.selected_governorate);
        selectedGovernorate.setText(governorates[position]);
        return convertView;
    }
}

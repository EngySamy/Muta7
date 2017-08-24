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

public class DistrictSpinnerAdapter extends BaseAdapter {

    private Context applicationContext;
    private String[] districts;
    private LayoutInflater inflater;


    public DistrictSpinnerAdapter(Context applicationContext, String[] districts) {
        this.applicationContext = applicationContext;
        this.districts = districts;
        this.inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return districts.length;
    }

    @Override
    public Object getItem(int position) {
        return districts[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.district_spinner_item, null);
        TextView selectedDistrict = (TextView) convertView.findViewById(R.id.selected_district);
        selectedDistrict.setText(districts[position]);
        if(position != 0)
            selectedDistrict.setTextColor(applicationContext.getResources().getColor(R.color.colorAccent));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.district_spinner_dropdown_item, null);
        TextView selectedDistrict = (TextView) convertView.findViewById(R.id.selected_district);
        selectedDistrict.setText(districts[position]);
        return convertView;
    }
}

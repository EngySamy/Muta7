package com.muta7.muta7.createSpace;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.muta7.muta7.R;

import java.util.Vector;

/**
 * Created by DeLL on 17/07/2017.
 */

public class AmenityAdapter extends BaseAdapter {
    String[] names;
    TypedArray ids;
    Context context;
    Vector<AmenityHolder> items;
    private static LayoutInflater inflater=null;

    public AmenityAdapter(RoomsAndAmenitiesFragment roomsFragment, String [] namesArray, TypedArray idsArray){
        names=namesArray;
        ids=idsArray;
        context=roomsFragment.getContext();
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        items=new Vector<AmenityHolder>();

    }
    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final AmenityHolder holder=new AmenityHolder();
        items.add(holder);
        View view;
        view = inflater.inflate(R.layout.amenity_item, null);
        holder.amenityText =(TextView) view.findViewById(R.id.amenity_text);
        holder.amenityCheck=(CheckBox) view.findViewById(R.id.amenity_checkbox) ;

        holder.amenityText.setText(names[position]);
        Drawable icon=ids.getDrawable(position);
        holder.amenityCheck.setButtonDrawable(icon);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                holder.amenityCheck.setChecked(!holder.amenityCheck.isChecked());
                Toast.makeText(context, "You Clicked "+names[position], Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public class AmenityHolder{
        TextView amenityText;
        CheckBox amenityCheck;
    }

    public Vector<String> getSelectedAmenities(){
        Vector<String> checked=new Vector<>();
        for(int i=0;i<items.size();i++){
            if(items.elementAt(i).amenityCheck.isChecked()){
                checked.add(items.elementAt(i).amenityText.getText().toString());
            }
        }
        return checked;
    }

    public boolean checkIfAtLeastOneSelected(){
        for(int i=0;i<items.size();i++){
            if(items.elementAt(i).amenityCheck.isChecked()){
               return true;
            }
        }
        return false;
    }
}

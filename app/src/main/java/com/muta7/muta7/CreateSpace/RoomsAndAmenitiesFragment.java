package com.muta7.muta7.CreateSpace;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.muta7.muta7.R;
import com.muta7.muta7.generalResourses.ExpandableHeightGridView;
import com.muta7.muta7.generalResourses.MultiSelectionSpinner;
import com.muta7.muta7.generalResourses.NothingSelectedSpinnerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DeLL on 15/07/2017.
 */

public class RoomsAndAmenitiesFragment extends CreateSpaceFragmentBase {
    ExpandableHeightGridView gridView;
    LayoutInflater inflat;
    ViewGroup group;
    AmenityAdapter amenityAdapter;
    int id=0;
    Map<Integer,RoomHolder> roomHolderMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflat=inflater;
        group=container;

        final View rootView = inflater.inflate(R.layout.rooms_amenities, container, false);
        String[] names = getResources().getStringArray(R.array.general_amenities_names);
        TypedArray ids=getResources().obtainTypedArray(R.array.general_amenities_icons);

        gridView=(ExpandableHeightGridView)  rootView.findViewById(R.id.amnts_grid);
        gridView.setExpanded(true);
        amenityAdapter=new AmenityAdapter(this,names,ids);
        gridView.setAdapter(amenityAdapter);
        amenityAdapter.notifyDataSetChanged();

        final LinearLayout base=(LinearLayout) rootView.findViewById(R.id.rooms_am_layout);
        roomHolderMap=new HashMap<>();


        AddRoom(base,rootView,false);

        Button add=(Button) rootView.findViewById(R.id.addRoom);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRoom(base,rootView,true);
                for (Map.Entry<Integer, RoomHolder> entry : roomHolderMap.entrySet())
                {
                    //Log.w("taaaaag","HHHHHHHHHHhhh"+entry.getKey()+" //545//" + entry.getValue().roomType.getSelectedItem().toString());
                }
            }
        });

        return rootView;
    }

    @Override
    public boolean validate() {
        return false;
    }


    @Override
    public Object getData() {
        return amenityAdapter.getSelectedAmenities();

    }

    private void AddRoom(LinearLayout base,View rootView,boolean removeButton){
        final View room=inflat.inflate(R.layout.room,group,false);
        final RoomHolder holder=new RoomHolder();
        ////settings for the room layout
        holder.roomType=(Spinner)room.findViewById(R.id.RoomTypeValue);
        ArrayAdapter<CharSequence> typesAdapter =  ArrayAdapter.createFromResource(rootView.getContext(), R.array.rooms_types_array,android.R.layout.simple_spinner_item);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.roomType.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        typesAdapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        getContext()));

        holder.roomAment=(MultiSelectionSpinner) room.findViewById(R.id.RoomAmentValue);
        String[] amenities=getResources().getStringArray(R.array.general_amenities_names);
        holder.roomAment.setItems(amenities);
        base.addView(room);
        roomHolderMap.put(id,holder);
        id++;
        if(true){
            //holder.options=(TextView) rootView.findViewById(R.id.options);
            holder.popupMenu = new PopupMenu(getContext(), rootView.findViewById(R.id.options));
            holder.popupMenu.getMenu().add(Menu.NONE, 0, Menu.NONE, "Remove");

            holder.popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    //if(item.getItemId()==1){
                    ((ViewManager)room.getParent()).removeView(room);
                    //}
                    return false;
                }
            });
            rootView.findViewById(R.id.options).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.popupMenu.show();
                }
            });
        }



    }
    class RoomHolder{
        Spinner roomType;
        MultiSelectionSpinner roomAment;
        //TextView options;
        PopupMenu popupMenu;
    }
}

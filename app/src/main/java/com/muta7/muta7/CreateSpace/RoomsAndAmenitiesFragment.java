package com.muta7.muta7.CreateSpace;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
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
import android.widget.TextView;
import android.widget.Toast;

import com.muta7.muta7.R;
import com.muta7.muta7.database.models.Room;
import com.muta7.muta7.database.models.RoomsAndAmenities;
import com.muta7.muta7.generalResourses.ExpandableHeightGridView;
import com.muta7.muta7.generalResourses.MultiSelectionSpinner;
import com.muta7.muta7.generalResourses.NothingSelectedSpinnerAdapter;
import com.muta7.muta7.generalResourses.Validations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
            }
        });

        return rootView;
    }

    private void AddRoom(LinearLayout base,View rootView,boolean removeButton){
        int max=getResources().getInteger(R.integer.max_num_rooms);
        if(roomHolderMap.size()>=max){
            Toast.makeText(getContext(),"You reached the maximum number of rooms",Toast.LENGTH_SHORT).show();
            return;
        }
        final View room=inflat.inflate(R.layout.room,group,false);
        final RoomHolder holder=new RoomHolder();
        ////settings for the room layout
        holder.roomName=(TextInputEditText)room.findViewById(R.id.RoomNameValue);
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
        String[] amenities=getResources().getStringArray(R.array.room_amenities_names);
        holder.roomAment.setItems(amenities);
        ///
        (holder.roomAment).setListener(new MultiSelectionSpinner.OnMultipleItemsSelectedListener() {
            @Override
            public void selectedIndices(List<Integer> indices) {
            }

            @Override
            public void selectedStrings(List<String> strings) {
                Toast.makeText(getContext(), strings.toString(), Toast.LENGTH_LONG).show();
            }
        });

        holder.roomCapacity=(TextInputEditText) room.findViewById(R.id.RoomCapacityValue);
        //////////
        base.addView(room);
        roomHolderMap.put(id,holder);
        id++;
        if(removeButton){
            rootView.findViewById(R.id.options).setBackgroundResource(R.drawable.down_arrow);
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


    @Override
    public boolean validate() {
        return amenityAdapter.checkIfAtLeastOneSelected()&&validateRooms();
    }


    @Override
    public Object getData() {
        Vector<String> roomsNames=new Vector<>(roomHolderMap.size());
        Vector<Room> rooms=GetRoomsData(roomsNames);
        return new RoomsAndAmenities(amenityAdapter.getSelectedAmenities(),rooms,roomsNames);
    }

    private Vector<Room> GetRoomsData(Vector<String> roomsNames){
        Vector<Room> rooms=new Vector<>(roomHolderMap.size());
        int i=0;
        for (Map.Entry<Integer, RoomHolder> entry : roomHolderMap.entrySet()) {
            Room room=new Room(Integer.parseInt(entry.getValue().roomCapacity.getText().toString())
                    ,entry.getValue().roomType.getSelectedItem().toString()
                    ,entry.getValue().roomAment.getSelectedStrings());
            rooms.add(room);
            roomsNames.add(entry.getValue().roomName.getText().toString());
            i++;
        }
        return rooms;

    }

    private boolean validateRooms(){
        for (Map.Entry<Integer, RoomHolder> entry : roomHolderMap.entrySet()) {
            if(!Validations.validateRoomName(entry.getValue().roomName,getContext()))
                return false;
            if(!Validations.validateRoomCapacity(entry.getValue().roomCapacity))
                return false;
            if(entry.getValue().roomType.getSelectedItem()==null){
                Toast.makeText(getContext(),"Select room type for all rooms",Toast.LENGTH_LONG).show();
                return false;
            }
            if(entry.getValue().roomAment.getSelectedStrings().size()==0){
                Toast.makeText(getContext(),"Select room amenities for all rooms",Toast.LENGTH_LONG).show();
                return false;
            }

        }
        return true;
    }

    class RoomHolder{
        TextInputEditText roomName;
        Spinner roomType;
        TextInputEditText roomCapacity;
        MultiSelectionSpinner roomAment;
        //TextView options;
        PopupMenu popupMenu;
    }
}

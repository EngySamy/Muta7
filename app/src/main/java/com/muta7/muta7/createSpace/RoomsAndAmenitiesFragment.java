package com.muta7.muta7.createSpace;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
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

        FloatingActionButton add=(FloatingActionButton) rootView.findViewById(R.id.addRoom);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRoom(base,rootView,true);
            }
        });

        Button next=(Button) rootView.findViewById(R.id.nextInRooms);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateSpaceActivity.viewPager.setCurrentItem(3,true);
            }
        });

        Button back=(Button) rootView.findViewById(R.id.backInRooms);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateSpaceActivity.viewPager.setCurrentItem(1,true);
            }
        });

        return rootView;
    }

    private void AddRoom(final LinearLayout base, View rootView, boolean removeButton){
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

        addFloatingButton(room,base,id);
        //////////
        base.addView(room);
        roomHolderMap.put(id,holder);
        id++;
        if(removeButton){
            room.findViewById(R.id.options).setBackgroundResource(R.drawable.down_arrow);
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

    private void addFloatingButton(final View room, LinearLayout parent, final int id){
        //FloatingActionButton fab=new FloatingActionButton(getContext());
        //CoordinatorLayout.LayoutParams params=new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //params.setAnchorId(xxxx);
        //params.anchorGravity= Gravity.CENTER_HORIZONTAL | GravityCompat.START;
        //fab.setLayoutParams(params);

        final View fab=inflat.inflate(R.layout.floating_button,group,false);
        Integer i=roomHolderMap.size()+1;
        ((TextView)fab.findViewById(R.id.button_text)).setText(i.toString());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean c=roomHolderMap.get(id).collapse;
                if(c){
                    expand(room);
                }
                else {
                    collapse(room);
                }
                roomHolderMap.get(id).collapse=!c;
            }
        });
        parent.addView(fab);
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
        TextView options;
        PopupMenu popupMenu;
        //Button expandCollapse;
        boolean collapse=false;
    }

    public static void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}

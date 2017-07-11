package com.muta7.muta7.database.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muta7.muta7.database.GENERAL;
import com.muta7.muta7.database.models.AMENITIES;
import com.muta7.muta7.database.models.CoworkingSpace;
import com.muta7.muta7.database.models.DAY;
import com.muta7.muta7.database.models.DAY_HOURS;
import com.muta7.muta7.database.models.GeneralInfo;
import com.muta7.muta7.database.models.Location;
import com.muta7.muta7.database.models.Room;

import java.util.Map;

/**
 * Created by DeLL on 23/04/2017.
 */

public final class CoworkingSpaceController {
    public final static String type="CoworkingSpace"; //type name in the database
    public static DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();


    public static void addNewSpace(String spaceID,GeneralInfo gi){//,Location l){
        CoworkingSpace space=new CoworkingSpace(gi);
        DatabaseReference spaceRef=mDatabase.child(GENERAL.SPACES).child(type).child(spaceID);
        //To add the general info
        SpaceController.setGeneralInfo(spaceRef,space.generalInfo);
        //To add Location
        //SpaceController.setLocation(spaceRef,space.location);
    }



    void setRooms(DatabaseReference db,String[] roomsNames, Room[] rooms) {
        int len=rooms.length;
        for(int i=0;i<len;i++){
            db.child(GENERAL.ROOMS).child(roomsNames[i]).setValue(rooms[i]);
        }
    }

    void setAmenities(DatabaseReference db,AMENITIES[] generalAmenities){
        int len=generalAmenities.length;
        for(int i=0;i<len;i++){
            db.child(GENERAL.AMENITIES).push().setValue(generalAmenities[i]);
        }

    }


}

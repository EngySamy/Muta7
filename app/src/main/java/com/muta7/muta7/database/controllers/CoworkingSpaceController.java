package com.muta7.muta7.database.controllers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muta7.muta7.database.GENERAL;
import com.muta7.muta7.database.models.CoworkingSpace;
import com.muta7.muta7.database.models.GeneralInfo;
import com.muta7.muta7.database.models.Room;

import java.util.Vector;

/**
 * Created by DeLL on 23/04/2017.
 */

public final class CoworkingSpaceController extends SpaceController {
    public final static String type = "CoworkingSpace"; //type name in the database
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    public static void addNewSpace(String spaceID,GeneralInfo gi,Vector<String>amenities){//,Location l){
        CoworkingSpace space=new CoworkingSpace(gi);
        //To add the general info
        SpaceController.setGeneralInfo(mDatabase,spaceID,space.generalInfo,type);
        //To add Location
        //SpaceController.setLocation(mDatabase,spaceID,space.location,type);
        //general amenities
        setAmenities(mDatabase,spaceID,amenities);
    }



    void setRooms(DatabaseReference db,String id,String[] roomsNames, Room[] rooms) {
        int len=rooms.length;
        //final boolean[][] done=new boolean[len][1];
        for(int i=0;i<len;i++){
            db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.ROOMS).child(roomsNames[i]).setValue(rooms[i]).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //done[i]=false;
                }
            });
        }
    }
    public static Room[][] getRooms(DatabaseReference db,String id){
        final Room[][] rooms=new Room[1][];
        db.child(id).child(type).child(GENERAL.ROOMS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rooms[0]=dataSnapshot.getValue(Room[].class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rooms;
    }


    static void setAmenities(DatabaseReference db,String id,Vector<String> generalAmenities){
        int len=generalAmenities.size();

        for(int i=0;i<len;i++){
            db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.AMENITIES).push().setValue(generalAmenities.elementAt(i));
        }
    }


    //may be changed to string[][]
    public static Vector<String>[] getAmenities(DatabaseReference db, String id){
        @SuppressWarnings("unchecked")
        final Vector<String>[] amenities= (Vector<String>[])new Vector[1];
        db.child(id).child(type).child(GENERAL.AMENITIES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                amenities[0]=dataSnapshot.getValue(Vector.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return amenities;
    }
}

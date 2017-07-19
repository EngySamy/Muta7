package com.muta7.muta7.database.controllers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.muta7.muta7.database.GENERAL;
import com.muta7.muta7.database.models.DAY;
import com.muta7.muta7.database.models.DAY_HOURS;
import com.muta7.muta7.database.models.GeneralInfo;
import com.muta7.muta7.database.models.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DeLL on 23/04/2017.
 */

//Put in this class the generic categories to use in any space
public class SpaceController {

    public static boolean[] setGeneralInfo(DatabaseReference db,String id, GeneralInfo gen,String type){
        final boolean[] done={true};
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.GENERAL_INFO).setValue(gen).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                done[0]=false;
            }
        });
        return done;
    }
    public static boolean[] setLocation(DatabaseReference db,String id, Location loc,String type){
        final boolean[] done={true};
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.LOCATION).setValue(loc).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                done[0]=false;
            }
        });
        return done;
    }

    public static String[] getInfoFromGeneralInfo(DatabaseReference db,String id,String typeOfSpace,String typeOfInfo){ //typeOfInfo is the info you want from the general info from the class GENERAL constants
        final String[] info={""};
        db.child(GENERAL.SPACES).child(typeOfSpace).child(id).child(GENERAL.GENERAL_INFO).child(typeOfInfo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                info[0]=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return info;
    }

    /////To modify
    public static String[] getMob(DatabaseReference db,String id,String type){
        final String[] mobile={""};
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.GENERAL_INFO).child(GENERAL.MOB).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mobile[0]=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mobile;
    }


    //Get all the general info object
    public static GeneralInfo[] getGeneralInfo(DatabaseReference db,String id,String type){
        final GeneralInfo[] gen=new GeneralInfo[1];
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.GENERAL_INFO).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gen[0]=dataSnapshot.getValue(GeneralInfo.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return  gen;

    }

    public static String[] getInfoFromGLocation(DatabaseReference db,String id,String typeOfSpace,String typeOfInfo){ //typeOfInfo is one of these: city,district,address from the class GENERAL constants
        final String[] info={""};
        db.child(GENERAL.SPACES).child(typeOfSpace).child(id).child(GENERAL.LOCATION).child(typeOfInfo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                info[0]=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return info;
    }

    public static long[] getLatLng(DatabaseReference db,String id,String type,String LatOrLng){ //LatOrLng LAT or LNG from General class
        final long[] lat={0};
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.LOCATION).child(LatOrLng).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lat[0]=dataSnapshot.getValue(long.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return lat;
    }

    public static String[][] getNearbyPlaces(DatabaseReference db,String id,String typeOfSpace){
        final String[][] info=new String[1][];
        db.child(GENERAL.SPACES).child(typeOfSpace).child(id).child(GENERAL.LOCATION).child(GENERAL.NEAR_PLACES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                info[0]=dataSnapshot.getValue(String[].class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return info;
    }

    //Get all the Location object
    public static Location[] getLocation(DatabaseReference db,String id,String type){
        final Location[] loc=new Location[1];
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.LOCATION).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loc[0]=dataSnapshot.getValue(Location.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return  loc;

    }


    ////Opening Hours
    public static void setVacations(DatabaseReference db, String id, DAY[] vacations, String type){
        int len=vacations.length;
        //final boolean[][] done=new boolean[len][1];
        for(int i=0;i<len;i++){
            db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.OPENING_HOURS).child(GENERAL.VACATIONS).push().setValue(vacations[i]).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //done[i]=false;
                }
            });
        }
    }

    public static DAY[][] getVacations(DatabaseReference db, String id,String type){
        final DAY[][] vacations=new DAY[1][];
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.OPENING_HOURS).child(GENERAL.VACATIONS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vacations[0]=dataSnapshot.getValue(DAY[].class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return vacations;
    }


    public static void setWorkingHoursPerDay(DatabaseReference db, String id, Map<DAY_HOURS, Boolean> workingHours, String type,String day){
        //final boolean[][] done=new boolean[len][1];
            db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.OPENING_HOURS).child(GENERAL.WORK_HOURS).child(day).setValue(workingHours).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //done[i]=false;
                }
            });

    }

    /*public static Map<DAY_HOURS, Boolean>[] getWorkingHoursPerDay(DatabaseReference db, String id,String type,String day){
        final Map<DAY_HOURS, Boolean>[] workingHours=new HashMap<DAY_HOURS, Boolean>()[1];
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.OPENING_HOURS).child(GENERAL.WORK_HOURS).child(day).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                workingHours[0]=dataSnapshot.getValue(Map<DAY_HOURS, Boolean>[].class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return workingHours;
    }*/

    public static boolean[] setHalfHourAllowed(DatabaseReference db,String id, boolean half,String type){
        final boolean[] done={true};
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.OPENING_HOURS).child(GENERAL.HALf_HOUR).setValue(half).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                done[0]=false;
            }
        });
        return done;
    }

    public static boolean[] getHalfHourAllowed(DatabaseReference db,String id,String type){
        final boolean[] half={true};
        db.child(GENERAL.SPACES).child(type).child(id).child(GENERAL.OPENING_HOURS).child(GENERAL.HALf_HOUR).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                half[0]=dataSnapshot.getValue(boolean.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return half;
    }



}

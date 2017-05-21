package com.muta7.muta7.database;

import android.util.Log;

import com.muta7.muta7.database.controllers.CoworkingSpaceController;
import com.muta7.muta7.database.models.CoworkingSpace;

import static android.content.ContentValues.TAG;

/**
 * Created by DeLL on 20/05/2017.
 */

public class TestClass {

    public static void main(String[] args){
        /*
        * CoworkingSpace( String spaceName, String description, String[] mobile, String email, String website,
                  String facebook, String twitter, String instagram, String youtube, String city, String district, long lat, long lng,
                  String address, String[] nearbyPlaces,AMENITIES[] generalAmenities,Room[] rooms, DAY[] vacations,
                  boolean halfHourAllowed,Map<DAY_HOURS, Boolean>[] workingHours) { //String spaceID
                  */
        String [] mob =new String[2];
        mob[0]="0123456789";
        mob[1]="0112345678";
        //CoworkingSpace co=new CoworkingSpace();
        CoworkingSpaceController.addNewSpace("EEBBDa3","Ebda3","good",mob,"ee@e.com","www.ee.com","ee@facebook","","","","Cairo","Dokki",11,
                22,"ccddddee",null);
        System.out.println("Hiiiii");
    }
}

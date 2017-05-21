package com.muta7.muta7.database.controllers;

import com.google.firebase.database.DatabaseReference;
import com.muta7.muta7.database.GENERAL;
import com.muta7.muta7.database.models.GeneralInfo;
import com.muta7.muta7.database.models.Location;

/**
 * Created by DeLL on 23/04/2017.
 */

//Put in this class the generic categories to use in any space
public final class SpaceController {

    public static void setGeneralInfo(DatabaseReference db, GeneralInfo gen){
        db.child(GENERAL.GENERAL_INFO).setValue(gen);
    }
    public static void setLocation(DatabaseReference db, Location loc){
        db.child(GENERAL.LOCATION).setValue(loc);
    }

    public void setOpeningHours(){

    }

    public void getGeneralInfo(){

    }

    public void getLocation(){

    }

    public void getOpeningHours(){

    }

    //TODO : last category
}

package com.muta7.muta7.database.models;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DeLL on 22/04/2017.
 */

public class CoworkingSpace {
    public String spaceType;
    public GeneralInfo generalInfo;
    public Location location;

    //Rooms and Amenities
    public String[] generalAmenities;
    public Room[] rooms;

    //opening hours
    public DAY[] vacations;
    public boolean halfHourAllowed;
    public Map<DAY_HOURS, Boolean>[] workingHours;

    public CoworkingSpace( GeneralInfo gi){//},Location l){

        this.generalInfo=gi;
        //this.location=l;

        /*this.generalAmenities=generalAmenities;
        this.rooms_amenities=rooms_amenities;

        this.vacations=vacations;
        this.halfHourAllowed=halfHourAllowed;
        this.workingHours=workingHours;*/
    }
}

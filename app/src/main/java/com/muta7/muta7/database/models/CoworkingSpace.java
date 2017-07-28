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

    public RoomsAndAmenities roomsAndAmenities;
    public OpeningHours openingHours;


    public CoworkingSpace( GeneralInfo gi,Location l,RoomsAndAmenities roomsAndAmenities){//),OpeningHours openingHours){

        this.generalInfo=gi;
        this.location=l;
        this.roomsAndAmenities=roomsAndAmenities;
        //this.openingHours=openingHours;
    }
}

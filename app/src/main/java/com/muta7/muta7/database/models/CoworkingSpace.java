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
    public AMENITIES[] generalAmenities;
    public Room[] rooms;

    //opening hours
    public DAY[] vacations;
    public boolean halfHourAllowed;
    public Map<DAY_HOURS, Boolean>[] workingHours;

    public CoworkingSpace( String spaceName, String description, String[] mobile, String email, String website,
                  String facebook, String twitter, String instagram, String youtube, String city, String district, long lat, long lng,
                  String address, String[] nearbyPlaces){//,AMENITIES[] generalAmenities,Room[] rooms, DAY[] vacations,
                  //boolean halfHourAllowed,Map<DAY_HOURS, Boolean>[] workingHours) { //String spaceID

        this.generalInfo=new GeneralInfo(spaceName,description, mobile, email, website,facebook, twitter, instagram, youtube);
        this.location=new Location( city,  district, lat, lng,address, nearbyPlaces);

        /*this.generalAmenities=generalAmenities;
        this.rooms=rooms;

        this.vacations=vacations;
        this.halfHourAllowed=halfHourAllowed;
        this.workingHours=workingHours;*/
    }
}

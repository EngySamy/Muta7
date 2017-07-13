package com.muta7.muta7.database.models;

/**
 * Created by DeLL on 22/04/2017.
 */

public class space {
    //General Information
    public String space_id;
    public String space_name;
    public String description;
    public String[] mobile;
    public String email;
    public String website;
    public String facebook;
    public String twitter;
    public String instagram;
    public String youtube;
    //Location
    public String city;
    public String district;
    public long lat;
    public long lng;
    public String address;
    public String[] nearbyPlaces;

    public String getSpaceID(){
        return space_id;
    }

}

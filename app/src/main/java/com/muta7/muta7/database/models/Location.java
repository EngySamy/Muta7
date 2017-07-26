package com.muta7.muta7.database.models;

/**
 * Created by DeLL on 20/05/2017.
 */

public class Location {

    //Location
    public String city;
    public String district;
    public long lat;
    public long lng;
    public String address;
    public String[] nearbyPlaces;

    public Location(String city, String district, long lat, long lng, String address, String[] nearbyPlaces){
        this.city=city;
        this.district=district;
        this.lat=lat;
        this.lng=lng;
        this.address=address;
        this.nearbyPlaces=nearbyPlaces;
    }
}

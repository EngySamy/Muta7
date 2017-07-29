package com.muta7.muta7.database.models;

import java.util.List;
import java.util.Vector;

/**
 * Created by DeLL on 20/05/2017.
 */

public class Location {

    //Location
    public String city;
    public String district;
    public double lat;
    public double lng;
    public String address;
    public Vector<String> nearbyPlaces;

    public Location(String city, String district, double lat, double lng, String address, Vector<String> nearbyPlaces){
        this.city=city;
        this.district=district;
        this.lat=lat;
        this.lng=lng;
        this.address=address;
        this.nearbyPlaces=nearbyPlaces;
    }
}

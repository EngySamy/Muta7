package com.muta7.muta7.database.models;

import java.util.Vector;

/**
 * Created by DeLL on 20/05/2017.
 */

public class Location {
    private String city;
    private String district;
    private double lat;
    private double lng;
    private String address;
    private Vector<String> nearbyPlaces;

    public Location(String city, String district, double lat, double lng, String address, Vector<String> nearbyPlaces){
        this.city=city;
        this.district=district;
        this.lat=lat;
        this.lng=lng;
        this.address=address;
        this.nearbyPlaces=nearbyPlaces;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Vector<String> getNearbyPlaces() {
        return nearbyPlaces;
    }

    public void setNearbyPlaces(Vector<String> nearbyPlaces) {
        this.nearbyPlaces = nearbyPlaces;
    }
}

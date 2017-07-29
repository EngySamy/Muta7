package com.muta7.muta7.database.models;

import java.util.Vector;

/**
 * Created by DeLL on 28/07/2017.
 */

public class RoomsAndAmenities {

    public Vector<String> generalAmenities;
    public Vector<Room> rooms;
    public Vector<String> roomsNames;

    public RoomsAndAmenities(Vector<String> generalAmenities,Vector<Room> rooms,Vector<String> roomsNames){
        this.generalAmenities=generalAmenities;
        this.rooms=rooms;
        this.roomsNames=roomsNames;
    }
}

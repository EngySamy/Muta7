package com.muta7.muta7.database.models;

import java.util.List;

/**
 * Created by DeLL on 20/05/2017.
 */

public class Room {
    //public String roomName;     //unique in the same Space //i removed it from the object as it will be the id of the room to be able to identify and get it
    public int capacity;
    public double area;
    public String  type;
    public List<String> amenities;

    public Room(int capacity,String type,List<String> amenities){
        //this.roomName=roomName;
        this.capacity=capacity;
        //this.area=area;
        this.type=type;
        this.amenities=amenities;
    }
}
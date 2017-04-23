package com.muta7.muta7.database.models;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DeLL on 22/04/2017.
 */

public class CoworkingSpace extends Space {

    //Rooms and Amenities
    public AMENITIES[] generalAmenities;
    public String roomName;
    public Room[] rooms;
    //opening hours
    public DAY[] vacations;
    public boolean halfHourAllowed;
    public Map<DAY_HOURS, Boolean>[] workingHours;
}

enum ROOM_TYPE{
    MEETING_ROOM,HALL,OFFICE,TRAINING_ROOM
}

enum AMENITIES{

}

enum DAY{
    SATURDAY,SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY
}
enum DAY_HOURS{
    AM10_00,AM10_30,AM11_00,AM11_30,PM12_00
}

class Room {
    public String roomName;
    public int capacity;
    public double area;
    public ROOM_TYPE type;
    public AMENITIES[] amenities;
}

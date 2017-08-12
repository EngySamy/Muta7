package com.muta7.muta7.database.models;

import java.util.Date;
import java.util.Vector;

/**
 * Created by DeLL on 11/08/2017.
 */

public class Reservation {
    String spaceId;
    String userId;
    String roomId;
    String groupId;
    Vector<DAY_HOURS> slots;
    Date day;

    public Reservation(String spaceId ,String userId ,String roomId,String groupId,Vector<DAY_HOURS> slots, Date day){
        this.spaceId=spaceId;
        this.userId=userId;
        this.roomId=roomId;
        this.groupId=groupId;
        this.slots=slots;
        this.day=day;
    }
}

package com.muta7.muta7.database.models;

import android.graphics.Path;

import java.util.Map;
import java.util.Vector;

/**
 * Created by DeLL on 28/07/2017.
 */

public class OpeningHours {

    //public DAY[] vacations;
    public boolean halfHourAllowed;
    public Map<DAY, Vector<DAY_HOURS>> workingHours;

    public OpeningHours(boolean halfHourAllowed, Map<DAY, Vector<DAY_HOURS>> workingHours){
        this.halfHourAllowed=halfHourAllowed;
        this.workingHours=workingHours;
    }
}

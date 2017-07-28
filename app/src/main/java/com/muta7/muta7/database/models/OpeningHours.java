package com.muta7.muta7.database.models;

import android.graphics.Path;

import java.util.Map;

/**
 * Created by DeLL on 28/07/2017.
 */

public class OpeningHours {

    //public DAY[] vacations;
    public boolean halfHourAllowed;
    public Map<DAY_HOURS, Boolean>[] workingHours;

    public OpeningHours(boolean halfHourAllowed, Map<DAY_HOURS, Boolean>[] workingHours){
        //this.vacations=vacations;
        this.halfHourAllowed=halfHourAllowed;
        this.workingHours=workingHours;
    }
}

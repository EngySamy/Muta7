package com.muta7.muta7.database.models;

/**
 * Created by DeLL on 22/04/2017.
 */

public class space {
    protected GeneralInfo generalInfo;
    protected Location location;

    public space(){

    }

    public space(GeneralInfo generalInfo, Location location){
        this.generalInfo = generalInfo;
        this.location = location;
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

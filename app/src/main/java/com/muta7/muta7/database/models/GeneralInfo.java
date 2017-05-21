package com.muta7.muta7.database.models;

/**
 * Created by DeLL on 20/05/2017.
 */

public class GeneralInfo {

    //General Information
    //public String spaceID;
    public String spaceName;
    public String description;
    public String[] mobile;
    public String email;
    public String website;
    public String facebook;
    public String twitter;
    public String instagram;
    public String youtube;
    //public User[] admins;

    protected GeneralInfo(String spaceName, String description, String[] mobile, String email, String website,
                          String facebook, String twitter, String instagram, String youtube){
        //this.spaceID=spaceID;
        this.spaceName=spaceName;
        this.description=description;
        this.mobile=mobile; ///////////////////////////initialize tha array or not?
        this.email=email;
        this.website=website;
        this.facebook=facebook;
        this.twitter=twitter;
        this.instagram=instagram;
        this.youtube=youtube;

    }
}

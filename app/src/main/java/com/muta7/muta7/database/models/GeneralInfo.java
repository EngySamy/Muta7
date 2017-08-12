package com.muta7.muta7.database.models;

/**
 * Created by DeLL on 20/05/2017.
 */

public class GeneralInfo {
    //public String spaceID;
    private String spaceName;
    private String description;
    private String mobile;  //[]
    private String email;
    private String website;
    private String facebook;
    private String twitter;
    private String instagram;
    private String youtube;
    //public User[] admins;

    public GeneralInfo(String spaceName, String description, String mobile, String email, String website,
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

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}

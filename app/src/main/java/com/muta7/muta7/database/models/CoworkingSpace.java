package com.muta7.muta7.database.models;

/**
 * Created by DeLL on 22/04/2017.
 */

public class CoworkingSpace extends Space {
    public static final String SPACE_TYPE = "CoworkingSpace";

    private RoomsAndAmenities roomsAndAmenities;
    private OpeningHours openingHours;


    public CoworkingSpace( GeneralInfo generalInfo,Location location,RoomsAndAmenities roomsAndAmenities){//),OpeningHours openingHours){
        super(generalInfo, location);
        this.roomsAndAmenities=roomsAndAmenities;
        //this.openingHours=openingHours;
    }

    public RoomsAndAmenities getRoomsAndAmenities() {
        return roomsAndAmenities;
    }

    public void setRoomsAndAmenities(RoomsAndAmenities roomsAndAmenities) {
        this.roomsAndAmenities = roomsAndAmenities;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }
}

package com.muta7.muta7.database.models;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Kareem Waleed on 4/28/2017.
 */

public class User {
    private String userID;
    private String userName;
    private String fullName;
    private String mobileNumber;
    private String email;
    private String password;
    private Bitmap photo;
    private ArrayList<space> spaces;
    private ArrayList<space> favouriteSpaces;
    private ArrayList<User> friends;
    //TODO: add ArrayList<Group> groups;
    //TODO: add ArrayList<Reservation> reservations;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public ArrayList<space> getSpaces() {
        return spaces;
    }

    public void setSpaces(ArrayList<space> spaces) {
        this.spaces = spaces;
    }

    public void addSpace(space space){
        spaces.add(space);
    }

    //TODO: add removeSpace(space space)


    public ArrayList<space> getFavouriteSpaces() {
        return favouriteSpaces;
    }

    public void setFavouriteSpaces(ArrayList<space> favouriteSpaces) {
        this.favouriteSpaces = favouriteSpaces;
    }

    public void addFavouriteSpace(space space){
        favouriteSpaces.add(space);
    }

    //TODO: add removeFavouriteSpace(space space)

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User user){
        friends.add(user);
    }

    //TODO: add removeFriend(User user)
}

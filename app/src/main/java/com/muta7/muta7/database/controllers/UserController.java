package com.muta7.muta7.database.controllers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muta7.muta7.database.models.User;
import com.muta7.muta7.database.models.space;

import java.util.ArrayList;

/**
 * Created by Kareem Waleed on 4/28/2017.
 */

public class UserController {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = database.getReference();
    private static DatabaseReference userReference = databaseReference.child("Users");

    public static boolean setUserName(String userID, String userName){
        final boolean[] succeeded = {true};
        userReference.child(userID).child("user_name").setValue(userName).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succeeded[0] = false;
            }
        });
        return succeeded[0];
    }

    public static String getUserName(String userID){
        final String[] userName = {""};
        userReference.child(userID).child("user_name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName[0] = String.valueOf(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return userName[0];
    }

    public static boolean setFullName(String userID, String fullName){
        final boolean[] succeeded = {true};
        userReference.child(userID).child("full_name").setValue(fullName).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succeeded[0] = false;
            }
        });
        return succeeded[0];
    }

    public static String getFullName(String userID){
        final String[] fullName = {""};
        userReference.child(userID).child("full_name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullName[0] = String.valueOf(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return fullName[0];
    }

    public static boolean setMobileNumber(String userID, String mobileNumber){
        final boolean[] succeeded = {true};
        userReference.child(userID).child("mobile_number").setValue(mobileNumber).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succeeded[0] = false;
            }
        });
        return succeeded[0];
    }

    public static String getMobileNumber(String userID){
        final String[] mobileNumber = {""};
        userReference.child(userID).child("mobile_number").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mobileNumber[0] = String.valueOf(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mobileNumber[0];
    }

    public static boolean setEmail(String userID, String email){
        final boolean[] succeeded = {true};
        userReference.child(userID).child("email").setValue(email).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succeeded[0] = false;
            }
        });
        return succeeded[0];
    }

    public static String getEmail(String userID){
        final String[] email = {""};
        userReference.child(userID).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email[0] = String.valueOf(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return email[0];
    }

    public static boolean setPassword(String userID, String password){
        final boolean[] succeeded = {true};
        userReference.child(userID).child("password").setValue(password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succeeded[0] = false;
            }
        });
        return succeeded[0];
    }

    public static String getPassword(String userID){
        final String[] password = {""};
        userReference.child(userID).child("password").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                password[0] = String.valueOf(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return password[0];
    }

    public static boolean setSpaces(String userID, ArrayList<space> spaces){
        final boolean[] succeeded = {true};
        ArrayList<String> spacesIDs = new ArrayList<>();
        for(space temp : spaces){
            spacesIDs.add(temp.getSpaceID());
        }
        userReference.child(userID).child("spaces").setValue(spacesIDs).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succeeded[0] = false;
            }
        });
        return succeeded[0];
    }

    public static ArrayList<String> getSpaces(String userID){
        final ArrayList<ArrayList<String>> spacesID = new ArrayList<>(1);
        userReference.child(userID).child("spaces").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                spacesID.set(0, (ArrayList<String>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return spacesID.get(0);
    }

    public static boolean setFavouriteSpaces(String userID, ArrayList<space> favouriteSpaces){
        final boolean[] succeeded = {true};
        ArrayList<String> favouriteSpacesIDs = new ArrayList<>();
        for(space temp : favouriteSpaces){
            favouriteSpacesIDs.add(temp.getSpaceID());
        }
        userReference.child(userID).child("favourite_spaces").setValue(favouriteSpacesIDs).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succeeded[0] = false;
            }
        });
        return succeeded[0];
    }

    public static ArrayList<String> getFavouriteSpaces(String userID){
        final ArrayList<ArrayList<String>> favouriteSpaces = new ArrayList<>(1);
        userReference.child(userID).child("favourite_spaces").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                favouriteSpaces.set(0, (ArrayList<String>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return favouriteSpaces.get(0);
    }

    public static boolean setFriends(String userID, ArrayList<User> friends){
        final boolean[] succeeded = {true};
        ArrayList<String> friendsIDs = new ArrayList<>();
        for(User temp : friends){
            friendsIDs.add(temp.getUserID());
        }
        userReference.child(userID).child("friends").setValue(friendsIDs).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succeeded[0] = false;
            }
        });
        return succeeded[0];
    }

    public static ArrayList<String> getFriends(String userID){
        final ArrayList<ArrayList<String>> friends = new ArrayList<>(1);
        userReference.child(userID).child("friends").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                friends.set(0, (ArrayList<String>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return friends.get(0);
    }

    //TODO: add setGroups(String userID, ArrayList<Group> groups)
    //TODO: add getGroups(String userID)
    //TODO: add setReservations(String userID, ArrayList<Reservation> reservations)
    //TODO: add getReservations(String userID)

    public static boolean createUser(User user){
        boolean succeeded = true;
        String userID = user.getUserID();
        succeeded = setUserName(userID, user.getUserName());
        succeeded = setFullName(userID, user.getFullName());
        succeeded = setMobileNumber(userID, user.getMobileNumber());
        succeeded = setEmail(userID, user.getEmail());
        succeeded = setPassword(userID, user.getPassword());
        succeeded = setSpaces(userID, user.getSpaces());
        succeeded = setFavouriteSpaces(userID, user.getFavouriteSpaces());
        succeeded = setFriends(userID, user.getFriends());
        //TODO: succeeded = setGroups(userID, user.getGroups())
        //TODO: succeeded = setReservations(userID, user.getReservations))
        return succeeded;
    }

    public static User getUser(String userID){
        User user = new User();
        user.setUserName(getUserName(userID));
        user.setFullName(getFullName(userID));
        user.setEmail(getEmail(userID));
        user.setPassword(getPassword(userID));
        //TODO: add setSpaces()
        //TODO: add setFavouriteSpaces()
        //TODO: add setFriends()
        //TODO: add setGroups()
        //TODO: add setReservations()
        return user;
    }
}

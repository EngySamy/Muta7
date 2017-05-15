package com.muta7.muta7.database.controllers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muta7.muta7.database.models.Space;
import com.muta7.muta7.database.models.User;

import java.util.ArrayList;

/**
 * Created by Kareem Waleed on 4/28/2017.
 */

public class UserController {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = database.getReference();
    private static DatabaseReference userReference = databaseReference.child("Users");

    public static boolean setUserName(String userID, String userName){
        final boolean[] succedeed = {true};
        userReference.child(userID).child("user_name").setValue(userName).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                succedeed[0] = false;
            }
        });
        return succedeed[0];
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

    public static boolean setSpaces(String userID, ArrayList<Space> spaces){
        final boolean[] succeeded = {true};
        ArrayList<String> spacesIDs = new ArrayList<>();
        for(Space temp : spaces){
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

    public static boolean setFavouriteSpaces(String userID, ArrayList<Space> favouriteSpaces){
        final boolean[] succeeded = {true};
        ArrayList<String> favouriteSpacesIDs = new ArrayList<>();
        for(Space temp : favouriteSpaces){
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

    //TODO: add setGroups(String userID, ArrayList<Group> groups)
    //TODO: add setReservations(String userID, ArrayList<Reservation> reservations)

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
}

package com.muta7.muta7.database.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Kareem Waleed on 4/28/2017.
 */

public class UserController {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = database.getReference();
}

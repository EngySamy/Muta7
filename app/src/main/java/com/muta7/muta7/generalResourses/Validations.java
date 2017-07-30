package com.muta7.muta7.generalResourses;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;

import com.muta7.muta7.R;

/**
 * Created by DeLL on 27/07/2017.
 */

public final class Validations {
    public static boolean validateEmail(TextInputEditText Email, Context context){
        String email = Email.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_email);
        if(TextUtils.isEmpty(email) || email.length()>max || !Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
            Email.setError( "This email address is not valid" );
            return false;
        }
        Email.setError(null);
        return true;
    }

    public static boolean validateWebsite(TextInputEditText Website,Context context){
        String str=Website.getText().toString();
        int max= context.getResources().getInteger(R.integer.max_length_website);
        if(!TextUtils.isEmpty(str)) {
            if(str.length()>max) { //to change
                Website.setError("This website address exceeds the max limit ");
                return false;
            }
            if(!Patterns.WEB_URL.matcher(str).matches()) {
                Website.setError("This website address is not valid");
                return false;
            }
        }
        Website.setError(null);
        return true;
    }

    public static boolean validatePassword(TextInputEditText Password,Context context){
        String password = Password.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_password);
        if (TextUtils.isEmpty(password)) {
            Password.setError("Enter password");
            return false;
        }

        if (password.length() < 6) {
            Password.setError( "Password too short, enter minimum 6 characters!");
            return false;
        }
        if(password.length()>max) {
            Password.setError("This password exceeds the max limit ");
            return false;
        }
        Password.setError(null);
        return true;
    }

    public static boolean validateMobile(TextInputEditText Mobile){
        String NumRegex="\\d{11}";
        String str=Mobile.getText().toString().trim();
        boolean t=str.matches(NumRegex);
        if(!t){
            Mobile.setError( "Mobile must be 11 numbers" );
            return false;
        }
        Mobile.setError(null);
        return true;
    }

    public static boolean validateUserName(TextInputEditText UserName,Context context){
        String name = UserName.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_username);
        if (TextUtils.isEmpty(name)) {
            UserName.setError("Username is required");
            return false;
        }
        if(name.length()>max) {
            UserName.setError("This username exceeds the max limit ");
            return false;
        }
        UserName.setError(null);
        return true;
    }

    public static boolean validateFullName(TextInputEditText FullName , Context context){
        String name = FullName.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_full_name);
        if (TextUtils.isEmpty(name)) {
            FullName.setError("Full Name is required");
            return false;
        }
        String Regex="[a-zA-Z\\s']+";
        if(!name.matches(Regex)){
            FullName.setError( "Full name should be alphabetic" );
            return false;
        }
        if(name.length()>max) {
            FullName.setError("This name exceeds the max limit ");
            return false;
        }
        FullName.setError(null);
        return true;
    }

    public static boolean validateDesc(TextInputEditText Desc, Context context){
        String str=Desc.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_desc);
        if (str.isEmpty()) {
            Desc.setError("Description is required");
            return false;
        }
        if (str.length() > max) {
            Desc.setError("This description exceeds the max limit");
            return false;
        }
        return true;
    }

    public static boolean validateSpaceName(TextInputEditText SpaceName,Context context){
        String name = SpaceName.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_space_name);
        if (TextUtils.isEmpty(name)) {
            SpaceName.setError("Space name is required");
            return false;
        }
        if (name.length() > max) {
            SpaceName.setError("This name exceeds the max limit");
            return false;
        }
        SpaceName.setError(null);
        return true;
    }

    public static boolean validateRoomName(TextInputEditText RoomName,Context context){
        String name = RoomName.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_room_name);
        if (TextUtils.isEmpty(name)) {
            RoomName.setError("Room name is required");
            return false;
        }
        if (name.length() > max) {
            RoomName.setError("This name exceeds the max limit");
            return false;
        }
        RoomName.setError(null);
        return true;
    }

    public static boolean validateSocialMedia(TextInputEditText media,Context context){
        String str = media.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_social_media);
        if(!TextUtils.isEmpty(str)) {
            if (str.length() > max) {
                media.setError("This field exceeds the max limit ");
                return false;
            }
        }
        media.setError(null);
        return true;
    }

    public static boolean validateRoomCapacity(TextInputEditText Capacity){
        String cap = Capacity.getText().toString().trim();
        if (TextUtils.isEmpty(cap)) {
            Capacity.setError("Room capacity is required");
            return false;
        }
        try {
            int d = Integer.parseInt(cap);
        } catch(NumberFormatException nfe) {
            Capacity.setError("This is not valid capacity");
            return false;
        }
        Capacity.setError(null);
        return true;
    }

}

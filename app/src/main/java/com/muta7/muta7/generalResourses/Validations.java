package com.muta7.muta7.generalResourses;

import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by DeLL on 27/07/2017.
 */

public final class Validations {
    public static boolean validateEmail(TextInputEditText Email){
        String email = Email.getText().toString().trim();
        if(TextUtils.isEmpty(email) || email.length()>256 || !Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
            Email.setError( "This email address is not valid" );
            return false;
        }
        return true;
    }

    public static boolean validateWebsite(TextInputEditText Website){
        String str=Website.getText().toString();
        if(!TextUtils.isEmpty(str)) {
            if(str.length()>500) { //to change
                Website.setError("This website address exceeds the max limit ");
                return false;
            }
            if(!Patterns.WEB_URL.matcher(str).matches()) {
                Website.setError("This website address is not valid");
                return false;
            }
        }
        return true;
    }

    public static boolean validatePassword(TextInputEditText Password){
        String password = Password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Password.setError("Enter password");
            return false;
        }

        if (password.length() < 6) {
            Password.setError( "Password too short, enter minimum 6 characters!");
            return false;
        }
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
        return true;
    }

    public static boolean validateUserName(TextInputEditText UserName){
        String name = UserName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            UserName.setError("Username is required");
            return false;
        }
        return true;
    }

    public static boolean validateFullName(TextInputEditText FullName){
        String name = FullName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            FullName.setError("Full Name is required");
            return false;
        }
        String Regex="[a-zA-Z\\s']+";
        String str=FullName.getText().toString();
        boolean t=str.matches(Regex);
        if(!t){
            FullName.setError( "Full name should be alphabetic" );
            return false;
        }
        return true;
    }

    /*public static boolean validateLength(String text,int len){
        if(text.length()>len){

            return false;
        }

    }*/
}

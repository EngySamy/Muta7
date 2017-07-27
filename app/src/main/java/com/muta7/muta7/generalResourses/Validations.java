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
        if(!name.matches(Regex)){
            FullName.setError( "Full name should be alphabetic" );
            return false;
        }
        return true;
    }

    public static boolean validateDesc(TextInputEditText Desc, Context context){
        String str=Desc.getText().toString().trim();
        int max= context.getResources().getInteger(R.integer.max_length_desc);
        if (TextUtils.isEmpty(str)) {
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
        return true;
    }
}

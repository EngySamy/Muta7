package com.muta7.muta7;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muta7.muta7.database.controllers.CoworkingSpaceController;
import com.muta7.muta7.database.models.GeneralInfo;

/**
 * Created by DeLL on 10/07/2017.
 */

public class GeneralInfoFragment extends CreateSpaceFragment{

     EditText Name,Desc,Mobile,Email,Website,Fb,Tw,Insta,Yt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.general_info, container, false);


        Name=(EditText) rootView.findViewById(R.id.SpaceNameValue);
        Desc=(EditText) rootView.findViewById(R.id.SpaceDescValue);
        Mobile=(EditText) rootView.findViewById(R.id.SpaceMobileValue);
        Email=(EditText) rootView.findViewById(R.id.SpaceEmailValue);
        Website=(EditText) rootView.findViewById(R.id.SpaceWebsiteValue);
        Fb=(EditText) rootView.findViewById(R.id.SpaceFbValue);
        Tw=(EditText) rootView.findViewById(R.id.SpaceTwValue);
        Insta=(EditText) rootView.findViewById(R.id.SpaceInstaValue);
        Yt=(EditText) rootView.findViewById(R.id.SpaceYtValue);

        Desc.addTextChangedListener(new TextValidator(Desc) {
            @Override public void validate(TextView textView, String text) {
                String str=Desc.getText().toString();
                if(str.length()>10)
                    Desc.setError("Description should not exceed 200 character");
            }
        });


        Mobile.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    String NumRegex="\\d{11}";
                    String str=Mobile.getText().toString();
                    boolean t=str.matches(NumRegex);
                    if(!t)
                        Mobile.setError( "Mobile must be 11 numbers" );
                }
            }
        });

        Email.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    String str=Email.getText().toString();
                    if(TextUtils.isEmpty(str) || str.length()>256 || !Patterns.EMAIL_ADDRESS.matcher(str).matches() )
                        Email.setError( "This email address is not valid" );
                }
            }
        });


        Website.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    String str=Website.getText().toString();
                    if(!TextUtils.isEmpty(str))
                    {
                        if(str.length()>500) //to change
                            Website.setError( "This website address exceeds the max limit " );
                        if(!Patterns.WEB_URL.matcher(str).matches())
                            Website.setError( "This website address is not valid" );
                    }
                }
            }
        });



        return rootView;
    }

    @Override
    public boolean validate(){
        //TODO : implement the validation
        return true;
    }

    @Override
    public Object getData() {
        return new GeneralInfo(Name.getText().toString(),Desc.getText().toString(),Mobile.getText().toString(),
                Email.getText().toString(),Website.getText().toString(),Fb.getText().toString(),Tw.getText().toString(),
                Insta.getText().toString(),Yt.getText().toString());
    }

}

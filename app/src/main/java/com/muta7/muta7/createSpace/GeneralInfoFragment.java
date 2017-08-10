package com.muta7.muta7.createSpace;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.muta7.muta7.R;
import com.muta7.muta7.database.models.GeneralInfo;
import com.muta7.muta7.generalResourses.Validations;

/**
 * Created by DeLL on 10/07/2017.
 */

public class GeneralInfoFragment extends CreateSpaceFragmentBase {

     TextInputEditText Name,Desc,Mobile,Email,Website,Fb,Tw,Insta,Yt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.general_info, container, false);


        Name=(TextInputEditText) rootView.findViewById(R.id.SpaceNameValue);
        Desc=(TextInputEditText) rootView.findViewById(R.id.SpaceDescValue);
        Mobile=(TextInputEditText) rootView.findViewById(R.id.SpaceMobileValue);
        Email=(TextInputEditText) rootView.findViewById(R.id.SpaceEmailValue);
        Website=(TextInputEditText) rootView.findViewById(R.id.SpaceWebsiteValue);
        Fb=(TextInputEditText) rootView.findViewById(R.id.SpaceFbValue);
        Tw=(TextInputEditText) rootView.findViewById(R.id.SpaceTwValue);
        Insta=(TextInputEditText) rootView.findViewById(R.id.SpaceInstaValue);
        Yt=(TextInputEditText) rootView.findViewById(R.id.SpaceYtValue);

        Name.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus) {
                    Validations.validateSpaceName(Name,getContext());
                }
            }
        });


        Desc.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus) {
                    Validations.validateDesc(Desc,getContext());
                }
            }
        });


        Mobile.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus) {
                    Validations.validateMobile(Mobile);
                }
            }
        });

        Email.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus) {
                    Validations.validateEmail(Email,getContext());
                }
            }
        });


        Website.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus) {
                    Validations.validateWebsite(Website,getContext());
                }
            }
        });

        //I didn't add the social media as they don't need thing except not to exceed the max limit , we check this finally

        Button next=(Button) rootView.findViewById(R.id.nextInGeneralInfo);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateSpaceActivity.viewPager.setCurrentItem(1,true);
            }
        });

        return rootView;
    }

    @Override
    public boolean validate(){
        return Validations.validateSpaceName(Name,getContext())&&Validations.validateDesc(Desc,getContext())
                &&Validations.validateMobile(Mobile) && Validations.validateEmail(Email,getContext())
                &&Validations.validateWebsite(Website,getContext())&&Validations.validateSocialMedia(Fb,getContext())
                &&Validations.validateSocialMedia(Tw,getContext())&&Validations.validateSocialMedia(Insta,getContext())
                &&Validations.validateSocialMedia(Yt,getContext());
    }

    @Override
    public Object getData() {
        return new GeneralInfo(Name.getText().toString(),Desc.getText().toString(),Mobile.getText().toString(),
                Email.getText().toString(),Website.getText().toString(),Fb.getText().toString(),Tw.getText().toString(),
                Insta.getText().toString(),Yt.getText().toString());
    }

}

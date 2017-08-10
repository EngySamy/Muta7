package com.muta7.muta7.userProfile.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.muta7.muta7.userProfile.controllers.fragments.InfoFragment;

/**
 * Created by DeLL on 03/08/2017.
 */

public class UserProfilePagerAdapter extends FragmentPagerAdapter {
    public UserProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment obj;
        switch (position){
            case 0:obj=new InfoFragment();
                break;
            default:obj=new InfoFragment();
                break;
        }
        return obj;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

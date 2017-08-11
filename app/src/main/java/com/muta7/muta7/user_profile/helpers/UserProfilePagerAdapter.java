package com.muta7.muta7.user_profile.helpers;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.muta7.muta7.user_profile.controllers.fragments.InfoFragment;
import com.muta7.muta7.user_profile.controllers.fragments.ReservationFragment;

/**
 * Created by DeLL on 03/08/2017.
 */

public class UserProfilePagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTitles = new String[]{"Info", "My reservations"};
    SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public UserProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment obj;
        switch (position){
            case 0:obj=new InfoFragment();
                break;
            default:obj=new ReservationFragment();
                break;
        }
        return obj;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment =(Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

}

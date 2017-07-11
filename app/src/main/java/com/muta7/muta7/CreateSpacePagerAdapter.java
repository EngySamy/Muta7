package com.muta7.muta7;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeLL on 10/07/2017.
 */

public class CreateSpacePagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public CreateSpacePagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment obj;
        switch (position){
            case 0: obj= new GeneralInfoFragment();
                break;
            case 1: obj= new LocationFragment();
                break;
            default:obj= new LocationFragment();
                break;
        }
        return  obj;
    }

    @Override
    public int getCount() {
        return 2;
    }

}

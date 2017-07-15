package com.muta7.muta7;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeLL on 10/07/2017.
 */

public class CreateSpacePagerAdapter extends FragmentPagerAdapter {

    //private final List<Fragment> mFragmentList = new ArrayList<>();
    //private final List<String> mFragmentTitleList = new ArrayList<>();
    SparseArray<CreateSpaceFragment> registeredFragments = new SparseArray<CreateSpaceFragment>();
    private String[] tabTitles = new String[]{"General Info", "Location", "Tab3"};

    public CreateSpacePagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public CreateSpaceFragment getItem(int position) {
        CreateSpaceFragment obj;
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

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CreateSpaceFragment fragment = (CreateSpaceFragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public CreateSpaceFragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}

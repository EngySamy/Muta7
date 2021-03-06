package com.muta7.muta7.create_space.helpers;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.muta7.muta7.create_space.controllers.fragments.CreateSpaceFragmentBase;
import com.muta7.muta7.create_space.controllers.fragments.GeneralInfoFragment;
import com.muta7.muta7.create_space.controllers.fragments.LocationFragment;
import com.muta7.muta7.create_space.controllers.fragments.OpeningHoursFragment;
import com.muta7.muta7.create_space.controllers.fragments.RoomsAndAmenitiesFragment;

/**
 * Created by DeLL on 10/07/2017.
 */

public class CreateSpacePagerAdapter extends FragmentPagerAdapter {

    //private final List<Fragment> mFragmentList = new ArrayList<>();
    //private final List<String> mFragmentTitleList = new ArrayList<>();
    SparseArray<CreateSpaceFragmentBase> registeredFragments = new SparseArray<CreateSpaceFragmentBase>();
    private String[] tabTitles = new String[]{"General Info", "Location", "Rooms & Amenities","Opening Hours"};

    public CreateSpacePagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public CreateSpaceFragmentBase getItem(int position) {
        CreateSpaceFragmentBase obj;
        switch (position){
            case 0: obj= new GeneralInfoFragment();
                break;
            case 1: obj= new LocationFragment();
                break;
            case 2: obj=new RoomsAndAmenitiesFragment();
                break;
            default:obj= new OpeningHoursFragment();
                break;
        }
        return  obj;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CreateSpaceFragmentBase fragment = (CreateSpaceFragmentBase) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public CreateSpaceFragmentBase getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}

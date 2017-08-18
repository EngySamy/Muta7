package com.muta7.muta7.user_profile.helpers;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.muta7.muta7.user_profile.controllers.fragments.InfoFragment;
import com.muta7.muta7.user_profile.controllers.fragments.ReservationCalenderFragment;
import com.muta7.muta7.user_profile.controllers.fragments.ReservationListFragment;

/**
 * Created by DeLL on 03/08/2017.
 */

public class UserProfilePagerAdapter{ /*extends FragmentStatePagerAdapter {
    private String[] tabTitles = new String[]{"Info", "My reservations"};
    //SparseArray<Fragment> registeredFragments = new SparseArray<>();
    private final FragmentManager mFragmentManager;
    private Fragment mFragmentAtPos1;
    private ReservationFragmentListener listener = new ReservationFragmentListener() {
        @Override
        public void onSwitchToNextFragment() {
            mFragmentManager.beginTransaction().remove(mFragmentAtPos1)
                    .commit();
            if (mFragmentAtPos1 instanceof ReservationListFragment){
                mFragmentAtPos1 = ReservationCalenderFragment.newInstance(listener);
            }else{ // Instance of NextFragment
                mFragmentAtPos1 = ReservationListFragment.newInstance(listener);
            }
            notifyDataSetChanged();
        }
    };;


    public UserProfilePagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new InfoFragment();
        else{
            if (mFragmentAtPos1 == null) {
                mFragmentAtPos1 = ReservationListFragment.newInstance(listener);
            }
            return mFragmentAtPos1;
        }



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
        //registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object)
    {
        if (object instanceof ReservationListFragment && mFragmentAtPos1 instanceof ReservationCalenderFragment)
            return POSITION_NONE;
        if (object instanceof ReservationCalenderFragment && mFragmentAtPos1 instanceof ReservationListFragment)
            return POSITION_NONE;
        return POSITION_UNCHANGED;
    }
*/
}


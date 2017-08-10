package com.muta7.muta7.user_profile.controllers.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.muta7.muta7.R;
import com.muta7.muta7.user_profile.helpers.UserProfilePagerAdapter;

/**
 * Created by DeLL on 03/08/2017.
 */

public class UserProfileActivity extends AppCompatActivity {

    private UserProfilePagerAdapter adapter;
    static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = (ViewPager) findViewById(R.id.create_space_pager);

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new UserProfilePagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.UserToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setElevation(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.UserTabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}

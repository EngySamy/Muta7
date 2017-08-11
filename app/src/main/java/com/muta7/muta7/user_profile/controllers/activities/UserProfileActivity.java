package com.muta7.muta7.user_profile.controllers.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muta7.muta7.R;
import com.muta7.muta7.create_space.helpers.CreateSpacePagerAdapter;
import com.muta7.muta7.database.controllers.UserController;
import com.muta7.muta7.user_profile.helpers.ReservationRecyclerAdapter;
import com.muta7.muta7.user_profile.helpers.UserProfilePagerAdapter;

/**
 * Created by DeLL on 03/08/2017.
 */

public class UserProfileActivity extends AppCompatActivity {

    private UserProfilePagerAdapter adapter;
    static ViewPager viewPager;
    TextView fullName,userName;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = (ViewPager) findViewById(R.id.UserPager);

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

        initialize();
        //getUserData();

    }


    private void initialize(){
        fullName =(TextView) findViewById(R.id.fullName);
        userName=(TextView) findViewById(R.id.username);

    }

    private void getUserData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            String id=user.getUid();
            fullName.setText(UserController.getFullName(id));
            userName.setText(UserController.getUserName(id));

        }
        else
            finish();

    }
}

package com.muta7.muta7;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.muta7.muta7.database.controllers.CoworkingSpaceController;

/**
 * Created by DeLL on 10/07/2017.
 */

public class CreateSpace extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.create_space);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.create_space_pager);

        // Create an adapter that knows which fragment should be shown on each page
        CreateSpacePagerAdapter adapter = new CreateSpacePagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

    }


}



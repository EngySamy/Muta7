package com.muta7.muta7;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.muta7.muta7.database.controllers.CoworkingSpaceController;
import com.muta7.muta7.database.models.GeneralInfo;

/**
 * Created by DeLL on 10/07/2017.
 */

public class CreateSpace extends AppCompatActivity implements SubmitListener {
    CreateSpacePagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.create_space);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.create_space_pager);

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new CreateSpacePagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }


    @Override
    public void Submit(int s){

        if(s==1){ //submit is clicked
            //need to take all values from all fragments
            //GeneralInfoFragment generalInfoFragment =(GeneralInfoFragment) getSupportFragmentManager().findFragmentById(R.);

            CreateSpaceFragment frag;
            //Here loop for all the fragments to get data
            boolean test=true;
            for(int i=0;i<adapter.getCount();i++)
            {
                frag=adapter.getRegisteredFragment(i);
                if(!frag.validate())
                {
                    test=false;
                    //show error msg
                    break;
                }
            }
            if(test)//add the new space
            {
                //general info
                CoworkingSpaceController.addNewSpace("0001",(GeneralInfo)adapter.getRegisteredFragment(0).getData());
            }

        }
    }

    /*@Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }*/

}


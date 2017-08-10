package com.muta7.muta7.createSpace;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import com.muta7.muta7.R;
import com.muta7.muta7.database.controllers.CoworkingSpaceController;
import com.muta7.muta7.database.models.GeneralInfo;
import com.muta7.muta7.database.models.Location;
import com.muta7.muta7.database.models.RoomsAndAmenities;

/**
 * Created by DeLL on 10/07/2017.
 */

public class CreateSpaceActivity extends AppCompatActivity implements SubmitListener {
    private CreateSpacePagerAdapter adapter;
    static ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.create_space);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = (ViewPager) findViewById(R.id.create_space_pager);

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new CreateSpacePagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setElevation(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }


    @Override
    public void Submit(int s){
        if(s==1){ //submit is clicked
            //need to take all values from all fragments
            //GeneralInfoFragment generalInfoFragment =(GeneralInfoFragment) getSupportFragmentManager().findFragmentById(R.);

            CreateSpaceFragmentBase frag;
            //Here loop for all the fragments to get data
            boolean test=true;
            for(int i=0;i<adapter.getCount()-1;i++) {
                //frag=adapter.getRegisteredFragment(i);
                frag=getFragment(i);
                if(!frag.validate()) {
                    test=false;
                    Toast.makeText(getApplicationContext(),"arg3 tany shof 3mlt a 8lt fe "+ i,Toast.LENGTH_LONG).show();
                    return;

                }
                Toast.makeText(getApplicationContext(),"validate "+i,Toast.LENGTH_LONG).show();
                //Log.e("vvvvvvvvvvvvv","vaaalidate "+i);
            }
            //if(test)//add the new space
                //general info
            CoworkingSpaceController.addNewSpace("0565",(GeneralInfo)getFragment(0).getData(),
                    (Location)getFragment(1).getData(),
                    (RoomsAndAmenities) getFragment(2).getData());
            Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();


        }
    }

    private String getFragmentTag(int viewPagerId, int fragmentPosition)
    {
        return "android:switcher:" + viewPagerId + ":" + fragmentPosition;
    }
    private CreateSpaceFragmentBase getFragment(int i){
        return (CreateSpaceFragmentBase)getSupportFragmentManager().findFragmentByTag(getFragmentTag(viewPager.getId(),i));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



}



package com.muta7.muta7.user_profile.controllers.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muta7.muta7.R;
import com.muta7.muta7.database.controllers.UserController;
import com.muta7.muta7.user_profile.controllers.fragments.ReservationCalenderFragment;
import com.muta7.muta7.user_profile.controllers.fragments.ReservationListFragment;
import com.muta7.muta7.user_profile.helpers.ReservationFragmentListener;
import com.muta7.muta7.user_profile.helpers.UserProfilePagerAdapter;

;

/**
 * Created by DeLL on 03/08/2017.
 */

public class UserProfileActivity extends AppCompatActivity {

    //public static UserProfilePagerAdapter adapter;
    //static ViewPager viewPager;
    TextView fullName,userName;
    private FragmentManager mFragmentManager;
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
            FrameLayout ll = (FrameLayout) findViewById(R.id.fragment);
            getFragmentManager().beginTransaction().add( ll.getId(),mFragmentAtPos1).commit();
        }
    };;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        initialize();

        FrameLayout ll = (FrameLayout) findViewById(R.id.fragment);
        mFragmentAtPos1=ReservationListFragment.newInstance(listener);

        getFragmentManager().beginTransaction().add( ll.getId(),mFragmentAtPos1).commit();


        // Find the view pager that will allow the user to swipe between fragments
        //viewPager = (ViewPager) findViewById(R.id.UserPager);

        // Create an adapter that knows which fragment should be shown on each page
        //adapter = new UserProfilePagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        //viewPager.setAdapter(adapter);

        ActionBar bar=getSupportActionBar();
        if(bar!=null){
            getSupportActionBar().setElevation(0);
            bar.setDisplayHomeAsUpEnabled(true);
        }
       /* final AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                appBar.setAlpha(1.0f - Math.abs(verticalOffset / (float)
                        appBarLayout.getTotalScrollRange()));


            }
        });
*/
        //getUserData();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.engy);
        ImageView profileImg=(ImageView) findViewById(R.id.profile_photo_user);
        profileImg.setImageBitmap(getCircularBitmapWithWhiteBorder(bitmap,25));


    }


    private void initialize(){
        fullName =(TextView) findViewById(R.id.fullName);
        userName=(TextView) findViewById(R.id.username);
        mFragmentManager=getFragmentManager();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks
        int id = item.getItemId();
        if (id == R.id.edit_profile) {
            Intent k = new Intent(UserProfileActivity.this, EditProfile.class);
            startActivity(k);
        }
        else {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public static Bitmap getCircularBitmapWithWhiteBorder(Bitmap bitmap,
                                                          int borderWidth) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        final int width = bitmap.getWidth() + borderWidth;
        final int height = bitmap.getHeight() + borderWidth;

        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        Canvas canvas = new Canvas(canvasBitmap);
        float radius = width > height ? ((float) height) / 2f : ((float) width) / 2f;
        canvas.drawCircle(width / 2, height / 2, radius, paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(width / 2, height / 2, radius - borderWidth / 2, paint);
        return canvasBitmap;
    }

}

package com.muta7.muta7.navigation.controllers.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.muta7.muta7.R;
import com.muta7.muta7.database.controllers.UserController;
import com.muta7.muta7.database.models.Space;
import com.muta7.muta7.navigation.controllers.fragments.FavouriteSpacesFragment;
import com.muta7.muta7.navigation.controllers.fragments.FindSpaceFragment;
import com.muta7.muta7.navigation.controllers.fragments.GroupsFragment;
import com.muta7.muta7.navigation.controllers.fragments.HomeFragment;
import com.muta7.muta7.navigation.controllers.fragments.NewsFeedFragment;
import com.muta7.muta7.navigation.helpers.CircleTransform;
import com.muta7.muta7.user_profile.controllers.activities.UserProfileActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FindSpaceFragment.OnCreateFindSpaceFragment,
        FavouriteSpacesFragment.OnCardClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private ViewStub viewStub;
    private LinearLayout filterLinearLayout;
    private Calendar myCalendar;

    private final String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private final String urlProfileImg = "https://firebasestorage.googleapis.com/v0/b/muta7-f44d2.appspot.com/o/IMG_1373.JPG?alt=media&token=8babffbb-a585-45de-8f6e-c6f5e28b3d19";

    private static int navItemIndex = 0;

    private static final String TAG_HOME = "home";
    private static final String TAG_FIND_SPACE = "find_space";
    private static final String TAG_NEWS_FEED = "news_feed";
    private static final String TAG_FAVOURITE_SPACES = "favourite_spaces";
    private static final String TAG_GROUPS = "groups";
    public static String CURRENT_TAG = TAG_HOME;

    private String[] activityTitles;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeViewVars();
        firebaseAuth = FirebaseAuth.getInstance();
        handler = new Handler();
        activityTitles = getResources().getStringArray(R.array.navigation_activity_titles);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        loadNavHeader();
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            CURRENT_TAG = TAG_HOME;
            navItemIndex = 0;
            loadFragment();
        }
    }

    private void initializeViewVars() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.job_title);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);
    }

    private void loadNavHeader() {
        if (firebaseAuth.getCurrentUser() != null) {
            String userID = firebaseAuth.getCurrentUser().getUid();
            String userFullName = UserController.getFullName(userID);
            String userEmail = UserController.getEmail(userID);
            txtName.setText(userFullName);
            txtWebsite.setText(userEmail);
            navHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NavigationActivity.this, UserProfileActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            txtName.setText("Your Full Name");
            txtWebsite.setText("example@domain.com");
        }

        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);
    }

    private void loadFragment() {
        selectNavMenu();

        setToolbarTitle();
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        if (mPendingRunnable != null) {
            handler.post(mPendingRunnable);
        }
        drawer.closeDrawers();
    }

    private Fragment getFragment() {
        switch (navItemIndex) {
            case 0:
                return new HomeFragment();
            case 1:
                return new FindSpaceFragment();
            case 2:
                return new NewsFeedFragment();
            case 3:
                return new FavouriteSpacesFragment();
            case 4:
                return new GroupsFragment();
            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (navItemIndex != 0) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                CURRENT_TAG = TAG_HOME;
                navItemIndex = 0;
                break;
            case R.id.nav_find_space:
                CURRENT_TAG = TAG_FIND_SPACE;
                navItemIndex = 1;
                break;
            case R.id.nav_news_feed:
                CURRENT_TAG = TAG_NEWS_FEED;
                navItemIndex = 2;
                break;
            case R.id.nav_favourite_spaces:
                CURRENT_TAG = TAG_FAVOURITE_SPACES;
                navItemIndex = 3;
                break;
            case R.id.nav_groups:
                CURRENT_TAG = TAG_GROUPS;
                navItemIndex = 4;
                break;
            default:
                CURRENT_TAG = TAG_HOME;
                navItemIndex = 0;
        }
        loadFragment();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                drawer.openDrawer(filterLinearLayout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(CURRENT_TAG == TAG_FIND_SPACE)
            menu.getItem(0).setVisible(true);
        else
            menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public void createFilterDrawer() {
        invalidateOptionsMenu();
        myCalendar = Calendar.getInstance();
        viewStub = (ViewStub) getLayoutInflater().inflate(R.layout.stub_layout, null);
        drawer.addView(viewStub);
        filterLinearLayout = (LinearLayout) viewStub.inflate();
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        float dpwidth = getApplicationContext().getResources().getDisplayMetrics().widthPixels / scale;
        DrawerLayout.LayoutParams layoutParams = new DrawerLayout.LayoutParams((int) ((dpwidth * scale + 0.5)-(50*scale+0.5)), ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.END;
        filterLinearLayout.setLayoutParams(layoutParams);
        setupFilter();
    }

    @Override
    public void removeFilterDrawer() {
        invalidateOptionsMenu();
        drawer.removeView(filterLinearLayout);
    }

    @Override
    public void onFavouriteSpaceCardClickListener(Space space) {

    }

    private void setupFilter(){
        ImageButton submitButton = (ImageButton) filterLinearLayout.findViewById(R.id.b_submit_filter);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(filterLinearLayout);
                Toast.makeText(NavigationActivity.this, "Submit Filter", Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout dateFilter = (LinearLayout) filterLinearLayout.findViewById(R.id.date_filter);
        dateFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NavigationActivity.this, NavigationActivity.this, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        LinearLayout timeFilter = (LinearLayout) filterLinearLayout.findViewById(R.id.time_filter);
        timeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(NavigationActivity.this, NavigationActivity.this,
                        myCalendar.HOUR_OF_DAY, myCalendar.MINUTE,
                        android.text.format.DateFormat.is24HourFormat(NavigationActivity.this)).show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String myFormat = "EEE, d MMM";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        TextView selectedDate = (TextView) filterLinearLayout.findViewById(R.id.selected_date);
        selectedDate.setText(sdf.format(myCalendar.getTime()));
        selectedDate.setTextColor(getResources().getColor(R.color.colorAccent));
        activateResetButton();
    }

    private void activateResetButton(){
        final Button resetButton = (Button) filterLinearLayout.findViewById(R.id.reset_filter);
        resetButton.setTextColor(getResources().getColor(R.color.white));
        resetButton.setClickable(true);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButton.setTextColor(getResources().getColor(R.color.gray_active_icon));
                resetButton.setClickable(false);
                TextView selectedDate = (TextView) filterLinearLayout.findViewById(R.id.selected_date);
                selectedDate.setText("Any");
                selectedDate.setTextColor(getResources().getColor(R.color.dark_gray));

                TextView selectedTime = (TextView) filterLinearLayout.findViewById(R.id.selected_time);
                selectedTime.setText("Any");
                selectedTime.setTextColor(getResources().getColor(R.color.dark_gray));
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        myCalendar.set(Calendar.MINUTE, minute);
        String format = "";
        if(DateFormat.is24HourFormat(this))
            format = "HH:mm a";
        else
            format = "hh:mm a";

        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        TextView selectedTime = (TextView) filterLinearLayout.findViewById(R.id.selected_time);
        selectedTime.setText(sdf.format(myCalendar.getTime()));
        selectedTime.setTextColor(getResources().getColor(R.color.colorAccent));
        activateResetButton();

    }
}

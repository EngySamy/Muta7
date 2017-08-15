package com.muta7.muta7.splash.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.muta7.muta7.R;
import com.muta7.muta7.navigation.controllers.activities.NavigationActivity;
import com.muta7.muta7.sign.controllers.activities.SignIn;

public class SplashActivity extends AppCompatActivity {

    private final String NAVIGATION_ACTIVITY = "NavigationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);

                } catch (InterruptedException e) {
                    /// nothing impossible
                } finally {
                    setActivity(NAVIGATION_ACTIVITY);
                }
            }
        };
        splashThread.start();
    }

    public void setActivity(String toClass){
        Intent intent = null;
        if(toClass.equals(NAVIGATION_ACTIVITY)) {
            intent = new Intent(this , NavigationActivity.class);
        } else {
            intent = new Intent(this , SignIn.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

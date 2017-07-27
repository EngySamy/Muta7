package com.muta7.muta7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muta7.muta7.CreateSpace.CreateSpaceActivity;
import com.muta7.muta7.activities.NavigationActivity;
import com.muta7.muta7.generalResourses.Validations;

/**
 * Created by DeLL on 26/07/2017.
 */

public class SignUp extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TextInputEditText FullName,UserName,Mobile,Email,Password;
    private Button SignUp,SignIn;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        auth = FirebaseAuth.getInstance();
        FullName=(TextInputEditText) findViewById(R.id.UserFullName);
        UserName=(TextInputEditText) findViewById(R.id.UserUserName);
        Mobile=(TextInputEditText) findViewById(R.id.UserMobile);
        Email=(TextInputEditText) findViewById(R.id.UserEmail);
        Password=(TextInputEditText) findViewById(R.id.UserPassword);
        SignUp=(Button) findViewById(R.id.signUp);
        SignIn=(Button) findViewById(R.id.sign_in_link);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        FullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Validations.validateFullName(FullName);
            }
        });

        UserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Validations.validateUserName(UserName);
            }
        });

        Mobile.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus) {
                    Validations.validateMobile(Mobile);
                }
            }
        });


        Email.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus) {
                    Validations.validateEmail(Email);
                }
            }
        });

        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Validations.validatePassword(Password);
                }
            }
        });


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validation())
                    return;

                progressBar.setVisibility(View.VISIBLE);
                String password = Password.getText().toString().trim();
                String email = Email.getText().toString().trim();
                //create user
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                //startActivity(new Intent(SignUp.this, CreateSpaceActivity.class));
                                finish();
                            }
                        }
                    });
            }
        });


        //called when auth changed (sign in or out)
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // NOTE: this Activity should get onpen only when the user is not signed in, otherwise
                    // the user will receive another verification email.
                    sendVerificationEmail();
                } else {
                    // User is signed out
                }
            }
        };

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });

    }

    private boolean validation(){
        return(!(Validations.validateEmail(Email)&&Validations.validatePassword(Password)
                &&Validations.validateMobile(Mobile)&&Validations.validateUserName(UserName)
                &&Validations.validateFullName(FullName)));

    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent
                            Toast.makeText(getApplicationContext(),"Verification Email is sent. Please follow it.",Toast.LENGTH_LONG).show();

                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(SignUp.this, NavigationActivity.class));
                            //finish();
                        }
                        else {
                            // email not sent, so display message and restart the activity or do whatever you wish to do

                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }
    /*
    *
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }*/
}

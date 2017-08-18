package com.muta7.muta7.sign.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muta7.muta7.R;
import com.muta7.muta7.navigation.controllers.activities.NavigationActivity;
import com.muta7.muta7.database.controllers.UserController;
import com.muta7.muta7.general_resources.Validations;

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
                if (!hasFocus) {
                    Validations.validateFullName(FullName,getApplicationContext());
                }
            }
        });

        UserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Validations.validateUserName(UserName,getApplicationContext());
                }
            }
        });

        Mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                    Validations.validateEmail(Email,getApplicationContext());
                }
            }
        });

        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Validations.validatePassword(Password,getApplicationContext());
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
                                //finish();
                                Toast.makeText(SignUp.this, "Authentication Succeed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                                AddUser();
                                sendVerificationEmail();
                            }
                        }
                    });
            }
        });


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });

        Button ForgotPassword=(Button) findViewById(R.id.btn_reset_password_in_sign_up);
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, com.muta7.muta7.sign.controllers.activities.ForgotPassword.class));
            }
        });

    }

    private boolean validation(){
        return(Validations.validateEmail(Email,getApplicationContext())&&Validations.validatePassword(Password,getApplicationContext())
                &&Validations.validateMobile(Mobile)&&Validations.validateUserName(UserName,getApplicationContext())
                &&Validations.validateFullName(FullName,getApplicationContext()));

    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // email sent
                        Toast.makeText(getApplicationContext(),"Verification Email is sent. Please follow it.",Toast.LENGTH_LONG).show();

                        // after email is sent just logout the user and finish this activity
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(SignUp.this, NavigationActivity.class));
                    }
                    else {
                        // email not sent, so display message and restart the activity or do whatever you wish to do
                        Toast.makeText(getApplicationContext(),"Something went wrong, please try again.",Toast.LENGTH_LONG).show();

                        //restart this activity
                        overridePendingTransition(0, 0);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());

                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"Something went wrong, please try again.",Toast.LENGTH_LONG).show();
        }
    }
    private void AddUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            String id = user.getUid();
            UserController.setFullName(id,FullName.getText().toString());
            UserController.setUserName(id,UserName.getText().toString());
            UserController.setMobileNumber(id,Mobile.getText().toString());

        }
    }

}

package com.muta7.muta7.signInAndUp.controllers.activities;

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
import com.muta7.muta7.general_resources.Validations;

/**
 * Created by DeLL on 27/07/2017.
 */

public class SignIn extends AppCompatActivity {
    private TextInputEditText Email,Password;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        auth = FirebaseAuth.getInstance();
        Email=(TextInputEditText) findViewById(R.id.UserEmailSignIn);
        Password=(TextInputEditText) findViewById(R.id.UserPasswordSignIn);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_signIn);
        Button signIn = (Button) findViewById(R.id.signIn);

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

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validation())
                    return;
                progressBar.setVisibility(View.VISIBLE);
                String password = Password.getText().toString().trim();
                String email = Email.getText().toString().trim();

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(SignIn.this, "Sign in :onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignIn.this, "Sign in failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    checkIfEmailVerified();
                                }
                            }
                        });

            }
        });

        Button ForgotPassword=(Button) findViewById(R.id.btn_reset_password_in_sign_in);
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, com.muta7.muta7.signInAndUp.controllers.activities.ForgotPassword.class));
            }
        });


    }

    private boolean validation(){
        return(Validations.validateEmail(Email,getApplicationContext())&&Validations.validatePassword(Password,getApplicationContext()));
    }

    private void checkIfEmailVerified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            if (user.isEmailVerified()) {
                // user is verified
                Toast.makeText(SignIn.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(SignIn.this, NavigationActivity.class));
            }
            else {
                // email is not verified
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(SignIn.this, "Not verified", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Something went wrong, please try again.",Toast.LENGTH_LONG).show();
        }


    }

}

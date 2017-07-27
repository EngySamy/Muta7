package com.muta7.muta7;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muta7.muta7.generalResourses.Validations;

/**
 * Created by DeLL on 27/07/2017.
 */

public class SignIn extends AppCompatActivity {
    private TextInputEditText Email,Password;
    private FirebaseAuth auth;
    private Button SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        Email=(TextInputEditText) findViewById(R.id.UserEmailSignIn);
        Password=(TextInputEditText) findViewById(R.id.UserPasswordSignIn);
        SignIn=(Button) findViewById(R.id.signIn);

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

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validation())
                    return;
                //progressBar.setVisibility(View.VISIBLE);
                String password = Password.getText().toString().trim();
                String email = Email.getText().toString().trim();

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(SignIn.this, "Sign in :onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                //progressBar.setVisibility(View.GONE);
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



    }

    private boolean validation(){
        return(!(Validations.validateEmail(Email)&&Validations.validatePassword(Password)));

    }

    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            //finish();
            Toast.makeText(SignIn.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(SignIn.this, "Log Out!!", Toast.LENGTH_SHORT).show();

            //restart this activity

        }
    }

}

package com.muta7.muta7.sign.controllers.activities;

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
import com.google.firebase.auth.FirebaseAuth;
import com.muta7.muta7.R;
import com.muta7.muta7.general_resources.Validations;

/**
 * Created by DeLL on 30/07/2017.
 */

public class ForgotPassword extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private TextInputEditText Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        auth = FirebaseAuth.getInstance();
        Email=(TextInputEditText) findViewById(R.id.UserEmailForgot);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_forgotPassword);

        Button reset=(Button) findViewById(R.id.resetPassword);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Validations.validateEmail(Email,ForgotPassword.this))
                    return;
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(Email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(ForgotPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });

            }
        });
    }
}

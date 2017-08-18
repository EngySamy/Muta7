package com.muta7.muta7.user_profile.controllers.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muta7.muta7.R;
import com.muta7.muta7.database.controllers.UserController;
import com.muta7.muta7.general_resources.Validations;

/**
 * Created by DeLL on 14/08/2017.
 */

public class EditProfile extends AppCompatActivity {
    private TextView fullName,userName,mobile,email;
    private ImageView editFullName,editUserName,editMobile,editEmail;
    private FirebaseUser user ;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_fragment);
        ActionBar bar=getSupportActionBar();
        if(bar!=null)
            bar.setDisplayHomeAsUpEnabled(true);

        initialize();
        //getUserData();
        setListenersForEdit();
    }

    private void initialize(){
        fullName=(TextView) findViewById(R.id.full_name_text);
        userName=(TextView) findViewById(R.id.user_name_text);
        mobile=(TextView) findViewById(R.id.user_mobile_text);
        email=(TextView) findViewById(R.id.user_email_text);

        editFullName=(ImageView) findViewById(R.id.change_full_name);
        editUserName=(ImageView) findViewById(R.id.change_user_name);
        editMobile=(ImageView) findViewById(R.id.change_mobile);
        editEmail=(ImageView) findViewById(R.id.change_email);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null) {
            finish();
        }
        userId=user.getUid();
    }

    private void getUserData(){

        fullName.setText(UserController.getFullName(userId));
        userName.setText(UserController.getUserName(userId));
        mobile.setText(UserController.getMobileNumber(userId));
        email.setText(user.getEmail());

    }

    private void setListenersForEdit(){
        editFullName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(EditProfile.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.edit_dialog);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;

                dialog.getWindow().setAttributes(lp);


                final TextInputEditText ed=(TextInputEditText) dialog.findViewById(R.id.edit_info);
                ed.setHint(getResources().getString(R.string.edit_full_name));

                Button submit=(Button) dialog.findViewById(R.id.submit_edit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Validations.validateFullName(ed,getApplicationContext())) {
                            /*
                            if(UserController.setFullName(userId, ed.getText().toString()))
                                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_SHORT).show();*/
                            dialog.dismiss();
                        }

                    }
                });

                Button cancel=(Button) dialog.findViewById(R.id.cancel_edit);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;

    }
}

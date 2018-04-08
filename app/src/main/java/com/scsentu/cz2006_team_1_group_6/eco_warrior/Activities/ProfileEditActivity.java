package com.scsentu.cz2006_team_1_group_6.eco_warrior.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.R;

public class ProfileEditActivity extends Activity{

    private static final String TAG = "ProfileEditActivity";

    private EditText usernameEditText;
    private EditText userDescriptionEditText;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    private String mUserID;
    private String mUsername;
    private String mUserDescription;

    private Button editBtn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");

        usernameEditText = (EditText) findViewById(R.id.profile_username);
        userDescriptionEditText = (EditText) findViewById(R.id.profile_user_description);

        mUserID = getIntent().getStringExtra("currentUserID");
        mUsername = getIntent().getStringExtra("currentUsername");
        mUserDescription = getIntent().getStringExtra("currentUserDescription");

        usernameEditText.setHint(mUsername);
        userDescriptionEditText.setHint(mUserDescription);

        editBtn = (Button) findViewById(R.id.profile_edit_btn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String userDescription = userDescriptionEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && (username.length() < 4 || username.length() > 10)) {
                    makeToast("Username must be between 4-10 characters in length");
                    return;
                }

                mRef.child(mUserID).child("username").setValue(username);
                if(!TextUtils.isEmpty(userDescription)){
                    mRef.child(mUserID).child("userDescription").setValue(userDescription);
                }
                finish();
            }
        });
    }

    private void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

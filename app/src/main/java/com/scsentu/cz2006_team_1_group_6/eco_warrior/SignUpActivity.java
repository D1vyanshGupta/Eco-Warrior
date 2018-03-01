package com.scsentu.cz2006_team_1_group_6.eco_warrior;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity{

    private static final String TAG = "SignUpActivity";

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mUserDescriptionEditText;

    private ProgressBar mProgressBar;

    private Button mSignUpBtn;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        mNameEditText = (EditText) findViewById(R.id.sign_up_name);
        mEmailEditText = (EditText) findViewById(R.id.sign_up_email);
        mPasswordEditText = (EditText) findViewById(R.id.sign_up_password);
        mUserDescriptionEditText = (EditText) findViewById(R.id.sign_up_user_description);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });


        mSignUpBtn = (Button) findViewById(R.id.email_sign_up_button);
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = mNameEditText.getText().toString().trim();
                String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                final String userDescription = mUserDescriptionEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mProgressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                mProgressBar.setVisibility(View.GONE);

                                if(!task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String userID = user.getUid();
                                    mRef.child(userID).child("username").setValue(username);
                                    mRef.child(userID).child("userDescription").setValue(userDescription);
                                    mRef.child(userID).child("eWaste").setValue(0.0);
                                    mRef.child(userID).child("lightningWaste").setValue(0.0);
                                    mRef.child(userID).child("secondHandWaste").setValue(0.0);
                                    mRef.child(userID).child("cashForTrashWaste").setValue(0.0);
                                    mRef.child(userID).child("eWasteRecords").child("0,0").setValue(0.0);
                                    mRef.child(userID).child("lightningWasteRecords").child("0,0").setValue(0.0);
                                    mRef.child(userID).child("secondHandWasteRecords").child("0,0").setValue(0.0);
                                    mRef.child(userID).child("cashForTrashWasteRecords").child("0,0").setValue(0.0);
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}

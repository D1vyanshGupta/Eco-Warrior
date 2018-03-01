package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MarkerDialogActivity extends Activity {

    private static final String TAG = "MarkerDialogActivity";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    private Double mLatitude;
    private Double mLongitude;

    private String mWasteType;
    private String mCoordinateString;
    private String mUserID;

    private Double mWasteAmountAtLocation;
    private Double mTotalWasteAmount;

    private TextView mWasteDepositedTV;
    private EditText newWasteAmountET;

    private Button confirmWasteBtn;
    private Button googleMapsDirectionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_dialog);

        mAuth = FirebaseAuth.getInstance();
        mUserID = mAuth.getCurrentUser().getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users").child(mUserID);

        mLatitude = getIntent().getDoubleExtra("latitude", 0);
        mLongitude = getIntent().getDoubleExtra("longitude", 0);
        mWasteType = getIntent().getStringExtra("wasteType");

        mCoordinateString = Double.toString(mLatitude) + "," + Double.toString(mLongitude);
        mCoordinateString = mCoordinateString.replace(".", "-");

        mWasteDepositedTV = (TextView) findViewById(R.id.waste_deposited_tv);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getPreviousWasteAmount(dataSnapshot);
                mWasteDepositedTV.setText(Double.toString(mWasteAmountAtLocation));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        newWasteAmountET = (EditText) findViewById(R.id.new_waste_deposited_et);

        confirmWasteBtn = (Button) findViewById(R.id.confirm_waste_deposit_btn);

        confirmWasteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newWasteAmount = newWasteAmountET.getText().toString().trim();
                if(newWasteAmount == null || newWasteAmount.length() == 0){
                    Toast.makeText(MarkerDialogActivity.this, "Please enter a correct amount.", Toast.LENGTH_SHORT).show();
                }
                else{
                    writeWasteAmountToFirebase(Double.parseDouble(newWasteAmount));
                    finish();
                }
            }
        });

        googleMapsDirectionsBtn = (Button) findViewById(R.id.google_maps_direction_btn);
        googleMapsDirectionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String geoString = "google.navigation:" + "q=" + mLatitude + "," + mLongitude;
                Uri gmmIntentUri = Uri.parse(geoString);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                mapIntent.setPackage("com.google.android.apps.maps");

                if(mapIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mapIntent);
                }
                else {
                    Toast.makeText(MarkerDialogActivity.this, "Google Maps App not installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getPreviousWasteAmount(DataSnapshot dataSnapshot){

        mTotalWasteAmount = Double.parseDouble(dataSnapshot.child(mWasteType).getValue().toString());

        DataSnapshot recordsSnapshot = dataSnapshot.child(mWasteType + "Records");
        if(recordsSnapshot.child(mCoordinateString).getValue() != null){
            mWasteAmountAtLocation = Double.parseDouble(recordsSnapshot.child(mCoordinateString).getValue().toString());
        }
        else{
            mRef.child(mWasteType + "Records").child(mCoordinateString).setValue(0.0);
            mWasteAmountAtLocation = 0.0;
        }
    }

    private void writeWasteAmountToFirebase(Double newWasteAmount){
        Double totalAmountAtLocation = newWasteAmount + mWasteAmountAtLocation;
        mRef.child(mWasteType + "Records").child(mCoordinateString).setValue(totalAmountAtLocation);

        DatabaseReference dbRef = mFirebaseDatabase.getReference().child("users").child(mUserID);
        Double newTotalWasteAmount = mTotalWasteAmount + newWasteAmount;
        dbRef.child(mWasteType).setValue(newTotalWasteAmount);
    }
}

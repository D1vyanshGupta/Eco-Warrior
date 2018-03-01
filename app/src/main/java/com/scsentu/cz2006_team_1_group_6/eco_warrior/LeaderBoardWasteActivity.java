package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class LeaderBoardWasteActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    private HashMap<String, Double> mRankingHashMap;
    private ArrayList<String> mArrayList;

    private ListView mLeaderboardLV;

    private String mWasteType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_waste);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");

        mWasteType = getIntent().getStringExtra("wasteType");

        mLeaderboardLV = (ListView) findViewById(R.id.waste_leaderboard_lv);

        setTitle(mWasteType + " LeaderBoard");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getRankingForWaste(dataSnapshot);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(LeaderBoardWasteActivity.this, android.R.layout.simple_list_item_1, mArrayList);
                mLeaderboardLV.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getRankingForWaste(DataSnapshot dataSnapshot){
        mRankingHashMap = new HashMap<String, Double>();

        for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
            String username = userSnapshot.child("username").getValue().toString();
            Double amountRecycled = Double.parseDouble(userSnapshot.child(mWasteType).getValue().toString());
            mRankingHashMap.put(username, amountRecycled);
        }

        mArrayList = (ArrayList) Utils.entriesSortedByValuesDescending(mRankingHashMap);

    }
}

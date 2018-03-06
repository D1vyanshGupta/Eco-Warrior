package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeaderBoardWasteActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    private ArrayList<String> mUserArrayList;
    private ArrayList<Double> mRecycledAmountsList;

    private ListView mLeaderboardLV;
    private TextView mLeaderboardTitle;
    private String mWasteType;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_waste);

        mAuth = FirebaseAuth.getInstance();
        mUser = new User(mAuth.getCurrentUser());

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");

        mWasteType = getIntent().getStringExtra("wasteType");

        mLeaderboardLV = (ListView) findViewById(R.id.waste_leaderboard_lv);

        mLeaderboardTitle = (TextView) findViewById(R.id.leaderboard_title);

        mLeaderboardTitle.setText(mWasteType + " LeaderBoard");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getRankingForWaste(dataSnapshot);
                LeaderBoardAdapter adapter = new LeaderBoardAdapter(LeaderBoardWasteActivity.this, mUserArrayList, mRecycledAmountsList);
                mLeaderboardLV.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getRankingForWaste(DataSnapshot dataSnapshot){
        HashMap<String, Double> rankingHashMap = new HashMap<String, Double>();

        mUserArrayList = new ArrayList<String>();
        mRecycledAmountsList = new ArrayList<Double>();

        for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
            String username = userSnapshot.child("username").getValue().toString();
            Double amountRecycled = Double.parseDouble(userSnapshot.child(mWasteType).getValue().toString());
            rankingHashMap.put(username, amountRecycled);
        }

        Set<Map.Entry<String, Double>> set = rankingHashMap.entrySet();
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Double>>()
        {
            public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        for(Map.Entry<String, Double> entry:list){
            mUserArrayList.add(entry.getKey());
            mRecycledAmountsList.add(entry.getValue());
        }
    }
}

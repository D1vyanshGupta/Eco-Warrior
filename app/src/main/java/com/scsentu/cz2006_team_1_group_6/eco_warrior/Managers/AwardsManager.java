package com.scsentu.cz2006_team_1_group_6.eco_warrior.Managers;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.Adapters.AwardsAdapter;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.Models.Award;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.Models.User;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.R;

import java.util.ArrayList;

public class AwardsManager extends Fragment {

    private static final String TAG = "AwardsManager";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    private RecyclerView awardsRV;
    private User mUser;
    private ArrayList<String> awardsArrayList;

    public static AwardsManager newInstance() {
        AwardsManager fragment = new AwardsManager();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView =  inflater.inflate(R.layout.fragment_awards, container, false);

        awardsRV = (RecyclerView) fragmentView.findViewById(R.id.awards_rv);

        mAuth = FirebaseAuth.getInstance();
        mUser = new User(mAuth.getCurrentUser());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.updateUserInfo(dataSnapshot);
                getAwardsList(mUser.getAwardsArrayList());
                AwardsAdapter adapter = new AwardsAdapter(getContext(), mUser.getAwardsArrayList());
                awardsRV.setAdapter(adapter);
                awardsRV.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return fragmentView;
    }
    private void getAwardsList(ArrayList<Award> awardsList){
        awardsArrayList = new ArrayList<>();
        for(Award awardIterator : awardsList){
            String awardString = awardIterator.getTitle() + " - " + awardIterator.getIsLocked();
            awardsArrayList.add(awardString);
        }
    }
}

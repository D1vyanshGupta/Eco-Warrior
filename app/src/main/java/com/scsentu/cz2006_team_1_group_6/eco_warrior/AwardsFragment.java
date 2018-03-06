package com.scsentu.cz2006_team_1_group_6.eco_warrior;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AwardsFragment extends Fragment {

    private static final String TAG = "AwardsFragment";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    private RecyclerView awardsRV;
    private User mUser;
    private ArrayList<String> awardsArrayList;

    public static AwardsFragment newInstance() {
        AwardsFragment fragment = new AwardsFragment();
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
                // Attach the adapter to the recyclerview to populate items
                awardsRV.setAdapter(adapter);
                // Set layout manager to position the items
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

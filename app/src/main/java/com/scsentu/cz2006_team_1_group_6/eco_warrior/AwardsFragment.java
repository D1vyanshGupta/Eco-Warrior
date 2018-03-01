package com.scsentu.cz2006_team_1_group_6.eco_warrior;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    private ListView awardsLV;
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

        awardsLV = (ListView) fragmentView.findViewById(R.id.awards_lv);

        mAuth = FirebaseAuth.getInstance();
        mUser = new User(mAuth.getCurrentUser());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("users");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.updateUserInfo(dataSnapshot);
                getAwardsList(mUser.getAwardsArrayList());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, awardsArrayList);
                awardsLV.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return fragmentView;
    }

    private void getAwardsList(ArrayList<Award> awardsList){
        awardsArrayList = new ArrayList<String>();
        for(Award awardIterator : awardsList){
            Log.d(TAG, "--------------------------------------");
            Log.d(TAG, awardIterator.getTitle());
            Log.d(TAG, "--------------------------------------");
            String awardString = awardIterator.getTitle() + " - " + awardIterator.getIsLocked();
            awardsArrayList.add(awardString);
        }
    }
}

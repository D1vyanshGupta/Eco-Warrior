package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment{

    private static final String TAG = "ProfileFragment";

    private TextView usernameTV;
    private TextView userDescriptionTV;
    private TextView eWasteTV;
    private TextView lightningWasteTV;
    private TextView secondHandWasteTV;
    private TextView cashForTrashTV;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    private User mUser;


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference();

        mUser = new User(mAuth.getCurrentUser());

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.setUserInfo(dataSnapshot.child("users"));
                updateAllTextViews();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        usernameTV = (TextView) fragmentView.findViewById(R.id.username);
        userDescriptionTV = (TextView) fragmentView.findViewById(R.id.user_description);
        eWasteTV = (TextView) fragmentView.findViewById(R.id.e_waste_tv);
        lightningWasteTV = (TextView) fragmentView.findViewById(R.id.lightning_waste_tv);
        secondHandWasteTV = (TextView) fragmentView.findViewById(R.id.second_hand_tv);
        cashForTrashTV = (TextView) fragmentView.findViewById(R.id.cash_for_trash_tv);

        return fragmentView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile_edit_action:{
                Intent profileEditIntent = new Intent(getContext(), ProfileEditActivity.class);
                profileEditIntent.putExtra("currentUserID", mUser.getUserID());
                profileEditIntent.putExtra("currentUsername", mUser.getUsername());
                profileEditIntent.putExtra("currentUserDescription", mUser.getUserDescription());

                startActivity(profileEditIntent);
                return true;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void updateAllTextViews(){
        usernameTV.setText(mUser.getUsername());
        userDescriptionTV.setText(mUser.getUserDescription());
        eWasteTV.setText(mUser.getEWasteAmount());
        lightningWasteTV.setText(mUser.getLightningWasteAmount());
        secondHandWasteTV.setText(mUser.getSecondHandWasteAmount());
        cashForTrashTV.setText(mUser.getCashForTrashAmount());
    }

}

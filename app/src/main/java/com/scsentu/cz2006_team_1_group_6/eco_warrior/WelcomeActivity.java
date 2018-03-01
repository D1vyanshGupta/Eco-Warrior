package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";

    private BottomNavigationView mBottomNavigationView;
    private Fragment mSelectedFragment;

    private User mUser;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.fragment_navigator);

        mBottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        mSelectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_fragment_location:
                                mSelectedFragment = LocationFragment.newInstance();
                                break;
                            case R.id.action_fragment_videos:
                                mSelectedFragment = VideosFragment.newInstance();
                                break;
                            case R.id.action_fragment_awards:
                                mSelectedFragment = AwardsFragment.newInstance();
                                break;
                            case R.id.action_fragment_leaderboard:
                                mSelectedFragment = LeaderBoardFragment.newInstance();
                                break;
                            case R.id.action_fragment_profile:
                                mSelectedFragment = ProfileFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, mSelectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, LocationFragment.newInstance());
        transaction.commit();

        mUser = new User(FirebaseAuth.getInstance().getCurrentUser());

        mRef = FirebaseDatabase.getInstance().getReference().child("users");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.updateUserInfo(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.action_sign_out){
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

}

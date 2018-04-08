package com.scsentu.cz2006_team_1_group_6.eco_warrior.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.Managers.AwardsManager;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.Managers.LeaderBoardManager;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.Managers.LocationManager;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.Managers.ProfileManager;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.Managers.VideosManager;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.R;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";

    private BottomNavigationView mBottomNavigationView;
    private Fragment mSelectedFragment;

//    private User mUser;
//    private DatabaseReference mRef;

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
                                mSelectedFragment = LocationManager.newInstance();
                                break;
                            case R.id.action_fragment_videos:
                                mSelectedFragment = VideosManager.newInstance();
                                break;
                            case R.id.action_fragment_awards:
                                mSelectedFragment = AwardsManager.newInstance();
                                break;
                            case R.id.action_fragment_leaderboard:
                                mSelectedFragment = LeaderBoardManager.newInstance();
                                break;
                            case R.id.action_fragment_profile:
                                mSelectedFragment = ProfileManager.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, mSelectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, LocationManager.newInstance());
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.action_sign_out){
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }


}

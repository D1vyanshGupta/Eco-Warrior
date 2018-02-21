package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";
    private Button eWasteBtn;
    private Button lightWasteBtn;
    private Button cashForTrashBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = ItemFragmentOne.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemFragmentTwo.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }

                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemFragmentOne.newInstance());
        transaction.commit();

//        eWasteBtn = (Button) findViewById(R.id.e_waste_btn);
//        eWasteBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent mapIntent = new Intent(WelcomeActivity.this, MapActivity.class);
//                mapIntent.putExtra("kml_ref", R.raw.e_waste_recycling_kml);
//                startActivity(mapIntent);
//            }
//        });
//
//        lightWasteBtn = (Button) findViewById(R.id.light_waste_btn);
//        lightWasteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mapIntent = new Intent(WelcomeActivity.this, MapActivity.class);
//                mapIntent.putExtra("kml_ref", R.raw.lightning_waste);
//                startActivity(mapIntent);
//            }
//        });
//
//        cashForTrashBtn = (Button) findViewById(R.id.paper_waste_btn);
//        cashForTrashBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mapIntent = new Intent(WelcomeActivity.this, MapActivity.class);
//                mapIntent.putExtra("kml_ref", R.raw.cash_for_trash_kml);
//                startActivity(mapIntent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Log.d(TAG, "Inside onOptionsItemSelected:");
        if(menuItem.getItemId() == R.id.action_sign_out){
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

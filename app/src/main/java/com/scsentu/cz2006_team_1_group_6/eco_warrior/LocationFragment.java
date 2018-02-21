package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LocationFragment extends Fragment{


    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View containerView =  inflater.inflate(R.layout.fragment_location, container, false);
        return containerView;
    }

    public void e_waste(View view){
        Toast.makeText(getContext(), "E-Waste", Toast.LENGTH_SHORT);
//                Intent mapIntent = new Intent(getActivity(), MapActivity.class);
//                mapIntent.putExtra("kml_ref", R.raw.e_waste_recycling_kml);
//                startActivity(mapIntent);
    }
}

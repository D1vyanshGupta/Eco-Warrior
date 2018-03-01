package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LocationFragment extends Fragment {

    private static final String TAG = "LocationFragment";

    private LinearLayout eWasteLayout;
    private LinearLayout lightningWasteLayout;
    private LinearLayout secondHandLayout;
    private LinearLayout cashForTrashLayout;

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
        View fragmentView =  inflater.inflate(R.layout.fragment_location, container, false);

        eWasteLayout = (LinearLayout) fragmentView.findViewById(R.id.e_waste_layout_location);
        eWasteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(getActivity(), MapActivity.class);
                mapIntent.putExtra("kml_ref", R.raw.e_waste_recycling_kml);
                startActivity(mapIntent);
            }
        });

        lightningWasteLayout = (LinearLayout) fragmentView.findViewById(R.id.lightning_waste_layout_location);
        lightningWasteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(getActivity(), MapActivity.class);
                mapIntent.putExtra("kml_ref", R.raw.lightning_waste_kml);
                startActivity(mapIntent);
            }
        });

        secondHandLayout = (LinearLayout) fragmentView.findViewById(R.id.second_hand_layout_location);
        secondHandLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(getActivity(), MapActivity.class);
                mapIntent.putExtra("kml_ref", R.raw.second_hand_goods_collection_points_kml);
                startActivity(mapIntent);
            }
        });

        cashForTrashLayout = (LinearLayout) fragmentView.findViewById(R.id.cash_for_trash_layout_location);
        cashForTrashLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(getActivity(), MapActivity.class);
                mapIntent.putExtra("kml_ref", R.raw.cash_for_trash_kml);
                startActivity(mapIntent);
            }
        });

        return fragmentView;
    }
}

package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LeaderBoardFragment extends Fragment{

    private static final String TAG = "LeaderBoardFragment";

    private LinearLayout eWasteLayout;
    private LinearLayout lightningWasteLayout;
    private LinearLayout secondHandLayout;
    private LinearLayout cashForTrashLayout;

    public static LeaderBoardFragment newInstance() {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView =  inflater.inflate(R.layout.fragment_leaderboard, container, false);

        eWasteLayout = (LinearLayout) fragmentView.findViewById(R.id.e_waste_layout_leaderboard);
        eWasteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaderboardIntent = new Intent(getActivity(), LeaderBoardWasteActivity.class);
                leaderboardIntent.putExtra("wasteType", "eWaste");
                startActivity(leaderboardIntent);
            }
        });

        lightningWasteLayout = (LinearLayout) fragmentView.findViewById(R.id.lightning_waste_layout_leaderboard);
        lightningWasteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaderboardIntent = new Intent(getActivity(), LeaderBoardWasteActivity.class);
                leaderboardIntent.putExtra("wasteType", "lightningWaste");
                startActivity(leaderboardIntent);
            }
        });

        secondHandLayout = (LinearLayout) fragmentView.findViewById(R.id.second_hand_layout_leaderboard);
        secondHandLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaderboardIntent = new Intent(getActivity(), LeaderBoardWasteActivity.class);
                leaderboardIntent.putExtra("wasteType", "secondHandWaste");
                startActivity(leaderboardIntent);
            }
        });

        cashForTrashLayout = (LinearLayout) fragmentView.findViewById(R.id.cash_for_trash_layout_leaderboard);
        cashForTrashLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaderboardIntent = new Intent(getActivity(), LeaderBoardWasteActivity.class);
                leaderboardIntent.putExtra("wasteType", "cashForTrashWaste");
                startActivity(leaderboardIntent);
            }
        });

        return fragmentView;
    }
}

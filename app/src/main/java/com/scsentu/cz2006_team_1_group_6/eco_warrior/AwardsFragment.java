package com.scsentu.cz2006_team_1_group_6.eco_warrior;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AwardsFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_awards, container, false);
    }
}

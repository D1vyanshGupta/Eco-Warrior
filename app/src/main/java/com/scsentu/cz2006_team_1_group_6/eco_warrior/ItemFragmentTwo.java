package com.scsentu.cz2006_team_1_group_6.eco_warrior;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemFragmentTwo extends Fragment {
    public static ItemFragmentTwo newInstance() {
        ItemFragmentTwo fragment = new ItemFragmentTwo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_fragment_two, container, false);
    }
}

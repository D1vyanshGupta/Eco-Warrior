package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemFragmentOne extends Fragment{
    public static ItemFragmentOne newInstance() {
        ItemFragmentOne fragment = new ItemFragmentOne();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_fragment_one, container, false);
    }
}

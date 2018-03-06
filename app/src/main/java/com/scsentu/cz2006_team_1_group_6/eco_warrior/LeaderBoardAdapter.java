package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LeaderBoardAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<String> mUserArrayList;
    private ArrayList<Double> mRecycledAmountsList;

    public LeaderBoardAdapter(Context context, ArrayList<String> userArrayList, ArrayList<Double> recycledAmountsList){
        this.mContext = context;
        this.mUserArrayList = userArrayList;
        this.mRecycledAmountsList = recycledAmountsList;
    }

    @Override
    public int getCount() {
        return mUserArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        ArrayList userScorePair = new ArrayList();
        userScorePair.add(mUserArrayList.get(i));
        userScorePair.add(mRecycledAmountsList.get(i));
        return userScorePair;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View currentView, ViewGroup parentViewGroup) {

        if(currentView == null){
            currentView = LayoutInflater.from(mContext).inflate(R.layout.leaderboard_item_list, parentViewGroup, false);
        }

        TextView userTV = (TextView) currentView.findViewById(R.id.user_tv);
        TextView recycledAmountTV = (TextView) currentView.findViewById(R.id.user_recycled_tv);

        ImageView trophyView = (ImageView) currentView.findViewById(R.id.leader_img);

        ArrayList userScorePair = (ArrayList) getItem(i);
        String username = (String) userScorePair.get(0);
        Double recycledAmount = (Double) userScorePair.get(1);

        userTV.setText(username);
        recycledAmountTV.setText(Double.toString(recycledAmount));

        trophyView.setImageResource(getTrophyForUser(i));
        return currentView;
    }

    private int getTrophyForUser(int ranking){
        switch (ranking){
            case 0: return R.drawable.first_place;
            case 1: return R.drawable.second_place;
            case 2: return R.drawable.third_place;
            default: return R.drawable.medal;
        }
    }
}
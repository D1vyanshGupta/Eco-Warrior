package com.scsentu.cz2006_team_1_group_6.eco_warrior;

/**
 * Created by tony_zeng on 6/3/18.
 */

public class Leader {

    private String mName;
    private double mRecycledAmount;
    private String mWasteType;
    private int mLeaderImagePath;
    private int mRank;

    public Leader(String name, Double recycledAmount, String wasteType, int rank){
        mName = name;
        mRecycledAmount = recycledAmount;
        mWasteType = wasteType;
        mRank = rank;
        mLeaderImagePath = R.drawable.medal;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmRecycledAmount() {
        return mRecycledAmount;
    }

    public void setmRecycledAmount(double mRecycledAmount) {
        this.mRecycledAmount = mRecycledAmount;
    }

    public String getmWasteType() {
        return mWasteType;
    }

    public void setmWasteType(String mWasteType) {
        this.mWasteType = mWasteType;
    }

    public int getmLeaderImagePath() {
        return mLeaderImagePath;
    }

    public void setmLeaderImagePath(int mLeaderImagePath) {
        this.mLeaderImagePath = mLeaderImagePath;
    }

    public int getmRank() {
        return mRank;
    }

    public void setmRank(int mRank) {
        this.mRank = mRank;
    }
}

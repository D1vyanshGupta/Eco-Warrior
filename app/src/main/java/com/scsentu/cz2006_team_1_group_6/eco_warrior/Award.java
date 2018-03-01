package com.scsentu.cz2006_team_1_group_6.eco_warrior;

public class Award {

    private String mTitle;
    private String mRequirement;
    private String mWasteType;
    private Double mWasteAmount;
    private Double mProgressPercentage;
    private Boolean mIsLocked;

    public Award(String title, String requirement, String wasteType, Double wasteAmount){
        mTitle = title;
        mRequirement = requirement;
        mWasteType = wasteType;
        mWasteAmount = wasteAmount;
        mIsLocked = true;
    }

    public Boolean getIsLocked() {
        return mIsLocked;
    }

    public void setIsLocked(Boolean mIsLocked) {
        this.mIsLocked = mIsLocked;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getRequirement() {
        return mRequirement;
    }

    public void setRequirement(String mRequirement) {
        this.mRequirement = mRequirement;
    }

    public String getWasteType() {
        return mWasteType;
    }

    public void setWasteType(String mWasteType) {
        this.mWasteType = mWasteType;
    }

    public Double getWasteAmount() {
        return mWasteAmount;
    }

    public void setWasteAmount(Double mWasteAmount) {
        this.mWasteAmount = mWasteAmount;
    }

    public Double getProgressPercentage() {
        return mProgressPercentage;
    }

    public void setProgressPercentage(Double mProgressPercentage) {
        this.mProgressPercentage = mProgressPercentage;
    }
}

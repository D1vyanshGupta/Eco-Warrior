package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class User {

    private static final String TAG = "User";

    private String userID;
    private String username;
    private String userDescription;
    private String eWasteAmount;
    private String lightningWasteAmount;
    private String secondHandWasteAmount;
    private String cashForTrashAmount;

    private ArrayList<Award> mAwardsArrayList;

    public User(FirebaseUser user){
        userID = user.getUid();
        mAwardsArrayList = getDefaultAwardListForUser();
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<Award> getAwardsArrayList() {
        return mAwardsArrayList;
    }

    public void updateUserInfo(DataSnapshot dataSnapshot){
        DataSnapshot userSnapshot = dataSnapshot.child(userID);
        String username = userSnapshot.child("username").getValue().toString();
        String userDescription = userSnapshot.child("userDescription").getValue().toString();
        String eWasteAmount = userSnapshot.child("eWaste").getValue().toString();
        String lightningWasteAmount = userSnapshot.child("lightningWaste").getValue().toString();
        String secondHandWasteAmount = userSnapshot.child("secondHandWaste").getValue().toString();
        String cashForTrashAmount = userSnapshot.child("cashForTrashWaste").getValue().toString();

        setUsername(username);
        setUserDescription(userDescription);
        setEWasteAmount(eWasteAmount);
        setLightningWasteAmount(lightningWasteAmount);
        setSecondHandWasteAmount(secondHandWasteAmount);
        setCashForTrashAmount(cashForTrashAmount);
        updateAwardStatus(userSnapshot);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getEWasteAmount() {
        return eWasteAmount;
    }

    public void setEWasteAmount(String eWasteAmount) {
        this.eWasteAmount = eWasteAmount;
    }

    public String getLightningWasteAmount() {
        return lightningWasteAmount;
    }

    public void setLightningWasteAmount(String lightningWasteAmount) {
        this.lightningWasteAmount = lightningWasteAmount;
    }

    public String getSecondHandWasteAmount() {
        return secondHandWasteAmount;
    }

    public void setSecondHandWasteAmount(String secondHandWasteAmount) {
        this.secondHandWasteAmount = secondHandWasteAmount;
    }

    public String getCashForTrashAmount() {
        return cashForTrashAmount;
    }

    public void setCashForTrashAmount(String cashForTrashAmount) {
        this.cashForTrashAmount = cashForTrashAmount;
    }

    public void updateAwardStatus(DataSnapshot userSnapshot){

        for(Award awardIterator : mAwardsArrayList){
            String wasteType = awardIterator.getWasteType();
            Double currentWasteAmount = Double.parseDouble(userSnapshot.child(wasteType).getValue().toString());
            if(currentWasteAmount >= awardIterator.getWasteAmount())
                awardIterator.setIsLocked(false);
        }
    }

    private static ArrayList<Award> getDefaultAwardListForUser(){
        String[] titleArray = {"E-Waste Recruiter", "E-Waste Warrior", "ABCDE", "GHHIKJH", "VVDGSVGD" };
        String[] requirementArray = {"Recycle 10 kgs of E-Waste", "Recycle 20 kgs of E-Waste", "Recycle 30 kgs of E-Waste", "Recycle 40 kgs of E-Waste", "Recycle 50 kgs of E-Waste"};
        String[] wasteType = {"eWaste", "eWaste", "eWaste", "eWaste","eWaste"};
        Double[] wasteAmountArray = {10.0, 20.0, 30.0, 40.0, 50.0};

        ArrayList<Award> awardsList = new ArrayList<Award>();

        for(int i = 0; i < 5; ++i){
            Award award = new Award(titleArray[i], requirementArray[i], wasteType[i], wasteAmountArray[i]);
            awardsList.add(award);
        }

        return awardsList;
    }
}

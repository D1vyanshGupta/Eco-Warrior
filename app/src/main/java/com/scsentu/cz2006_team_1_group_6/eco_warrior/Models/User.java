package com.scsentu.cz2006_team_1_group_6.eco_warrior.Models;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.R;

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
            awardIterator.setCurrentWasteAmount(currentWasteAmount);
            if(currentWasteAmount >= awardIterator.getWasteAmount()){
                awardIterator.setIsLocked(false);
                switch (awardIterator.getTitle()){
                    case "E-Waste Recruiter":
                        awardIterator.setImagePath(R.drawable.e_waste10);
                        break;
                    case "E-Waste Warrior":
                        awardIterator.setImagePath(R.drawable.e_waste50);
                        break;
                    case "Philanthropist":
                        awardIterator.setImagePath(R.drawable.philanthropist);
                        break;
                    case "Millionare":
                        awardIterator.setImagePath(R.drawable.millionaire);
                        break;
                    case "Flash":
                        awardIterator.setImagePath(R.drawable.flash);
                        break;
                }

            }
        }
    }

    private static ArrayList<Award> getDefaultAwardListForUser(){
        String[] titleArray = {"E-Waste Recruiter", "E-Waste Warrior", "Philanthropist", "Millionaire", "Flash" };

        String[] requirementArray = {
                "Recycle at least 10kg of E-waste, to unlock the award.",
                "Recycle at least 50kg of E-waste, to unlock the award.",
                "Recycle at least 30kg of lightning waste, to unlock the award.",
                "Receive cash for at least 80kg trash, to unlock the award.",
                "Donate at least 50kg second hand goods, to unlock the award."
        };
        String[] wasteType = {"eWaste", "eWaste", "lightningWaste", "cashForTrashWaste", "secondHandWaste"};
        Double[] wasteAmountArray = {10.0, 50.0, 30.0, 80.0, 50.0};
        int[] imagePath = {
                R.drawable.e_waste10,
                R.drawable.e_waste50,
                R.drawable.flash,
                R.drawable.millionaire,
                R.drawable.philanthropist};

        ArrayList<Award> awardsList = new ArrayList<Award>();

        for(int i = 0; i < 5; ++i){
            Award award = new Award(titleArray[i], requirementArray[i], wasteType[i], wasteAmountArray[i], imagePath[i]);
            awardsList.add(award);
        }

        return awardsList;
    }
}

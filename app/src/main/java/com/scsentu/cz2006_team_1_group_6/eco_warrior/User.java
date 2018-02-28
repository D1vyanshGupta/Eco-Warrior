package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

public class User {

    private String userID;
    private String username;
    private String userDescription;
    private String eWasteAmount;
    private String lightningWasteAmount;
    private String secondHandWasteAmount;
    private String cashForTrashAmount;

    public User(FirebaseUser user){
        userID = user.getUid();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserInfo(DataSnapshot dataSnapshot){
        DataSnapshot userSnapshot = dataSnapshot.child(userID);
        String username = userSnapshot.child("username").getValue().toString();
        String userDescription = userSnapshot.child("userDescription").getValue().toString();
        String eWasteAmount = userSnapshot.child("eWaste").getValue().toString();
        String lightningWasteAmount = userSnapshot.child("lightningWaste").getValue().toString();
        String secondHandWasteAmount = userSnapshot.child("secondHandWaste").getValue().toString();
        String cashForTrashAmount = userSnapshot.child("cashForTrash").getValue().toString();

        setUsername(username);
        setUserDescription(userDescription);
        setEWasteAmount(eWasteAmount);
        setLightningWasteAmount(lightningWasteAmount);
        setSecondHandWasteAmount(secondHandWasteAmount);
        setCashForTrashAmount(cashForTrashAmount);
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
}

package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String TAG = "Utils";

    private static final String emailRegexString = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static HashMap<String, String> getMarkerInfo(Marker marker) {

        HashMap<String, String> infoHashMap = new HashMap<String, String>();
        String[] keyArray = {"PHOTOURL", "HYPERLINK", "DESCRIPTION", "ADDRESSUNITNUMBER",
                             "ADDRESSSTREETNAME", "ADDRESSPOSTALCODE", "ADDRESSFLOORNUMBER", "ADDRESSBUILDINGNAME",
                             "ADDRESSBLOCKHOUSENUMBER", "NAME", "DESCRIPTION"};

        String markerInfo = marker.getSnippet();

        if(markerInfo == null || markerInfo.length() == 0)
            return null;

        markerInfo = markerInfo.replaceAll("\\r\\n|\\r|\\n", " ");

        for(String keyIterator : keyArray){
            String regexStringOne = "(?<=(<th>" + keyIterator + "</th>(\\s)<td>))(.*?)(?=</td>)";
            Pattern regexPatternOne = Pattern.compile(regexStringOne);
            Matcher regexMatcherOne = regexPatternOne.matcher(markerInfo);

            String keyInfo = "";

            if(regexMatcherOne.find())
                keyInfo = regexMatcherOne.group();
            else{
                String regexStringTwo = "(?<=(<td>" + keyIterator + "</td>))(.*?)(?=</tr>)";
                Pattern regexPatternTwo = Pattern.compile(regexStringTwo);
                Matcher regexMatcherTwo = regexPatternTwo.matcher(markerInfo);

                if(regexMatcherTwo.find()){
                    keyInfo = regexMatcherTwo.group();
                    keyInfo = keyInfo.trim().replace("<td>", "").replace("</td>", "");
                }
            }

            infoHashMap.put(keyIterator, keyInfo);
        }

        return infoHashMap;
    }

    public static boolean checkValidEmail(String email){
        return email.matches(emailRegexString);
    }

    public static void createUserInFirebaseDB(DatabaseReference dbRef, String userID, String username, String userDescription){
        dbRef.child(userID).child("username").setValue(username);
        dbRef.child(userID).child("userDescription").setValue(userDescription);
        dbRef.child(userID).child("eWaste").setValue(0.0);
        dbRef.child(userID).child("lightningWaste").setValue(0.0);
        dbRef.child(userID).child("secondHandWaste").setValue(0.0);
        dbRef.child(userID).child("cashForTrashWaste").setValue(0.0);
        dbRef.child(userID).child("eWasteRecords").child("0,0").setValue(0.0);
        dbRef.child(userID).child("lightningWasteRecords").child("0,0").setValue(0.0);
        dbRef.child(userID).child("secondHandWasteRecords").child("0,0").setValue(0.0);
        dbRef.child(userID).child("cashForTrashWasteRecords").child("0,0").setValue(0.0);
    }

}

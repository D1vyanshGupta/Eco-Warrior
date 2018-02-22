package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.util.Log;

import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String TAG = "Utils";

    public static HashMap<String, String> getMarkerInfo(Marker marker) {

        HashMap<String, String> infoHashMap = new HashMap<String, String>();
        String[] keyArray = {"PHOTOURL", "HYPERLINK", "DESCRIPTION", "ADDRESSUNITNUMBER",
                             "ADDRESSSTREETNAME", "ADDRESSPOSTALCODE", "ADDRESSFLOORNUMBER", "ADDRESSBUILDINGNAME",
                             "ADDRESSBLOCKHOUSENUMBER", "NAME", "DESCRIPTION"};

        String markerInfo = marker.getSnippet();

        if(markerInfo == null || markerInfo.length() == 0)
            return null;

        markerInfo = markerInfo.replaceAll("\\s", "");

        for(String keyIterator : keyArray){
            String regexStringOne = "(?<=(<th>" + keyIterator + "</th><td>))(.*?)(?=</td>)";
            Pattern regexPatternOne = Pattern.compile(regexStringOne);
            Matcher regexMatcherOne = regexPatternOne.matcher(markerInfo);

            String keyInfo = "";

            if(regexMatcherOne.find())
                keyInfo = regexMatcherOne.group();
            else{
                String regexStringTwo = "(?<=(<td>" + keyIterator + "</td><td>))(.*?)(?=</td>)";
                Pattern regexPatternTwo = Pattern.compile(regexStringTwo);
                Matcher regexMatcherTwo = regexPatternTwo.matcher(markerInfo);

                if(regexMatcherTwo.find())
                    keyInfo = regexMatcherTwo.group();
            }

            infoHashMap.put(keyIterator, keyInfo);
        }

        return infoHashMap;
    }
}
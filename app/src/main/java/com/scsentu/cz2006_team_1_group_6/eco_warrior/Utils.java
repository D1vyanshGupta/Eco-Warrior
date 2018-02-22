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

//        markerInfo = markerInfo.replaceAll("\\s", "");

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

    public static void testSnippet(Marker marker){
        Log.d(TAG, "-------------------------------------------------------");
        if(marker.getSnippet() == null || marker.getSnippet().length() == 0)
            Log.d(TAG, "Marker does not have snipped");

        String markerInfo = marker.getSnippet();

        markerInfo = markerInfo.replaceAll("\\r\\n|\\r|\\n", " ");

        String regexStringOne = "(?<=(<td>" + "ADDRESSUNITNUMBER" + "</td>))(.*?)(?=</tr>)";
        Pattern regexPatternOne = Pattern.compile(regexStringOne);
        Matcher regexMatcherOne = regexPatternOne.matcher(markerInfo);

        if(regexMatcherOne.find())
            Log.d(TAG, regexMatcherOne.group());
        else
            Log.d(TAG, "Bhai kuchh nahi mila");

        Log.d(TAG, markerInfo);



        Log.d(TAG, "-------------------------------------------------------");
    }
}

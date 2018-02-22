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
                             "ADDRESSBLOCKHOUSENUMBER", "NAME"};

        String markerInfo = marker.getSnippet();

        for(String keyIterator : keyArray){

            String regexString = "(?<=(<th>" + keyIterator + "</th>[\\r\\n]<td>))(.*)(?=</td>)";
            Pattern regexPattern = Pattern.compile(regexString);
            Matcher regexMatcher = regexPattern.matcher(markerInfo);

            String keyInfo = "";

            if(regexMatcher.find())
                keyInfo = regexMatcher.group();

            infoHashMap.put(keyIterator, keyInfo);
        }

        return infoHashMap;
    }
}

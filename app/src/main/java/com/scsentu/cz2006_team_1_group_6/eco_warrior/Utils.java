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

    public static <K,V extends Comparable<? super V>>
    List<Map.Entry<K, V>> entriesSortedByValuesDescending(Map<K,V> map) {

        List<Map.Entry<K,V>> sortedEntries = new ArrayList<Map.Entry<K,V>>(map.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Map.Entry<K,V>>() {
                    @Override
                    public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                }
        );

        return sortedEntries;
    }
}

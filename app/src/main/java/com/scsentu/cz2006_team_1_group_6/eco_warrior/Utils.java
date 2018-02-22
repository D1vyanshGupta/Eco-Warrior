package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import com.google.android.gms.maps.model.Marker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String getMarkerInfo(Marker marker){
        String info = marker.getSnippet();
        String pattern = "(?=<th>NAME</th>\n<td>)(.*)(?=</td>)";

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(info);

        return matcher.group(0);
    }
}

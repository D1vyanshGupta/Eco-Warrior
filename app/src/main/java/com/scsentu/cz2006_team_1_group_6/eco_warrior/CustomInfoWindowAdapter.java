package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CustomInfoWindowAdapter(Context context){
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoWindowView = mLayoutInflater.inflate(R.layout.custom_info_window, null);
        TextView nameTV = (TextView) infoWindowView.findViewById(R.id.info_window_marker_name);
        TextView descriptionTV = (TextView) infoWindowView.findViewById(R.id.info_window_marker_description);
        TextView addressTV = (TextView) infoWindowView.findViewById(R.id.info_window_marker_address);

        HashMap<String, String> infoHashMap = Utils.getMarkerInfo(marker);

        if(infoHashMap == null){
            nameTV.setText("No information available.");
            return infoWindowView;
        }

        String nameString = "N/A";
        if(infoHashMap.get("NAME") != null && infoHashMap.get("NAME").length() > 0)
            nameString = infoHashMap.get("NAME");

        nameTV.setText("Name: " + nameString);

        String description = "N/A";

        if(infoHashMap.get("DESCRIPTION") != null && infoHashMap.get("DESCRIPTION").length() > 0)
            description = infoHashMap.get("DESCRIPTION");

        descriptionTV.setText("Description: " + description);

        String streetName = "N/A";
        String buildingName = "N/A";
        String blockNumber = "N/A";
        String unitNumber = "N/A";
        String postalCode = "N/A";

        if(infoHashMap.get("ADDRESSSTREETNAME") != null && infoHashMap.get("ADDRESSSTREETNAME").length() > 0)
            streetName = infoHashMap.get("ADDRESSSTREETNAME");

        if(infoHashMap.get("ADDRESSBUILDINGNAME") != null && infoHashMap.get("ADDRESSBUILDINGNAME").length() > 0)
            buildingName = infoHashMap.get("ADDRESSBUILDINGNAME");

        if(infoHashMap.get("ADDRESSBLOCKHOUSENUMBER") != null && infoHashMap.get("ADDRESSBLOCKHOUSENUMBER").length() > 0)
            blockNumber = infoHashMap.get("ADDRESSBLOCKHOUSENUMBER");

        if(infoHashMap.get("ADDRESSUNITNUMBER") != null && infoHashMap.get("ADDRESSUNITNUMBER").length() > 0)
            unitNumber = infoHashMap.get("ADDRESSUNITNUMBER");

        if(infoHashMap.get("ADDRESSPOSTALCODE") != null && infoHashMap.get("ADDRESSPOSTALCODE").length() > 0)
            postalCode = infoHashMap.get("ADDRESSPOSTALCODE");

        String addressString = "Address: ";
        addressString += streetName + "\n" +
                         buildingName + ", " + blockNumber + "\n" +
                         unitNumber + "\n" +
                         postalCode + ", Singapore";

        addressTV.setText(addressString);

        return infoWindowView;
    }
}

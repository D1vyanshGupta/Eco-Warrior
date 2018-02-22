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
        TextView addressTV = (TextView) infoWindowView.findViewById(R.id.info_window_marker_address);

        HashMap<String, String> infoHashMap = Utils.getMarkerInfo(marker);
        nameTV.setText(infoHashMap.get("NAME"));
        infoHashMap.remove("NAME");
        String addressInfo = "";

        for(String keyIterator : infoHashMap.keySet()){
            addressInfo += keyIterator + "  -->  " + infoHashMap.get(keyIterator) + "\n";
        }
        addressTV.setText(addressInfo);
        return infoWindowView;
    }


}

package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

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

        TextView titleTV = (TextView) infoWindowView.findViewById(R.id.info_window_title);
        TextView subTitleTV = (TextView) infoWindowView.findViewById(R.id.info_window_subtitle);
        titleTV.setText(marker.getTitle());
        subTitleTV.setText(marker.getSnippet());
        return infoWindowView;
    }


}

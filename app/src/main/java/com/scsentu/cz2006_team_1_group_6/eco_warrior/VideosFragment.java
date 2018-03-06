package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class VideosFragment extends Fragment {

    private static final String TAG = "VideosFragment";

    private ListView videoThumbnailsLV;

    public static VideosFragment newInstance() {
        VideosFragment fragment = new VideosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_videos, container, false);

        videoThumbnailsLV = (ListView) fragmentView.findViewById(R.id.videos_lv);
        String[] urlArray = {"v2hHYhkUywE", "VMW1K_se3_M&t=27s", "XBgjycm7akY", "fwjInLCQoY0", "eDgOGNjOCsU"};
        String[] nameArray = {"10 EASY RECYCLING IDEAS YOU MUST TRY|10 AMAZING RECYCLING HACKS AND IDEAS", "5 Recycling Ideas You Must Try| Best Out of Waste ", "12 Amazing Recycle DIY Crafts",
                                "17 Minute Crafts To Do When You're BORED! 10 Quick and Easy DIY Ideas! Amazing DIYs & Craft Hacks!", "NBA Video"};
        ArrayList<String> imageUrls = new ArrayList<String>(Arrays.asList(urlArray));
        ArrayList<String> videoTexts = new ArrayList<String>(Arrays.asList(nameArray));
        VideosAdapter videosAdapter = new VideosAdapter(getActivity(), videoTexts, imageUrls);
        videoThumbnailsLV.setAdapter(videosAdapter);
        return fragmentView;
    }

}

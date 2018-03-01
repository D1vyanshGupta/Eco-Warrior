package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        String[] imageUrls = {"PpVFsdCZNXM"};
        String[] videoTexts = {"DIY LIFE HACKS!"};
        CustomList customList = new CustomList(getActivity(), videoTexts, imageUrls);
        videoThumbnailsLV.setAdapter(customList);
        return fragmentView;
    }

}

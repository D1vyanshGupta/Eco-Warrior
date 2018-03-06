package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class VideosFragment extends Fragment {

    private static final String TAG = "VideosFragment";
    private ListView videoThumbnailsLV;

//    public ArrayList<VideosAdapter.SetVideoThumbnailTask> mAsyncTaskList;

    public static VideosFragment newInstance() {
        VideosFragment fragment = new VideosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_videos, container, false);

//        mAsyncTaskList = new ArrayList<VideosAdapter.SetVideoThumbnailTask>();

        videoThumbnailsLV = (ListView) fragmentView.findViewById(R.id.videos_lv);
        String[] urlArray = {"-A2sHPADUzw", "v2hHYhkUywE", "rTDFmXmTPAE", "XBgjycm7akY", "fwjInLCQoY0", "I5c6_JK4iTE", "6h9_4_Iq3Ak", "zKTWfvJdgCk", "xEAOvFG1AmM"};
        String[] nameArray = {"Smart Ways To Reuse Old Computer Parts", "10 AMAZING RECYCLING HACKS AND IDEAS", "3 Smart Waste Reducing Gadgets For Your Home", "12 Amazing Recycle DIY Crafts",
                                "17 Minute Crafts To Do When You're BORED!", "Colgate Empty Packets Reuse", "Waste crafts idea of recycling papers", "Recycle ideas for plastic bottles", "38 Ideas with Plastic Bottles"};
        ArrayList<String> imageUrls = new ArrayList<String>(Arrays.asList(urlArray));
        ArrayList<String> videoTexts = new ArrayList<String>(Arrays.asList(nameArray));
        VideosAdapter videosAdapter = new VideosAdapter(getActivity(), videoTexts, imageUrls);
        videoThumbnailsLV.setAdapter(videosAdapter);
        return fragmentView;
    }
}


package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class VideosAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mVideosNameList;
    private ArrayList<String> mVideosURLList;

    public VideosAdapter(Context context, ArrayList<String> videosNameList, ArrayList<String> urlList){
        mContext = context;
        mVideosNameList = videosNameList;
        mVideosURLList = urlList;
    }

    @Override
    public int getCount() {
        return mVideosNameList.size();
    }

    @Override
    public Object getItem(int i) {
        Pair<String, String> videoTuple = new Pair<String, String>(mVideosNameList.get(i), mVideosURLList.get(i));
        return videoTuple;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View currentView, ViewGroup parentViewGroup) {
        if(currentView == null){
            currentView = LayoutInflater.from(mContext).inflate(R.layout.video_item_layout, parentViewGroup, false);
        }

        TextView videoNameTV = (TextView) currentView.findViewById(R.id.video_text);
        ImageView videoImageView = (ImageView) currentView.findViewById(R.id.video_thumbnail);

        Pair<String, String> videoTuple = (Pair<String, String>) getItem(index);
        String videoName = videoTuple.first;
        final String videoUrl = videoTuple.second;

        videoNameTV.setText(videoName);

        String imageUrl = "https://img.youtube.com/vi/" + videoUrl + "/1.jpg";
        Pair<ImageView, String> imageViewTuple = new Pair<ImageView, String>(videoImageView, imageUrl);

        new VideosAdapter.SetVideoThumbnailTask().execute(imageViewTuple);

        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri youtubeUri = Uri.parse("http://www.youtube.com/watch?v=" + videoUrl);
                Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, youtubeUri);
                mContext.startActivity(youtubeIntent);
            }
        });

        return currentView;
    }

    private class SetVideoThumbnailTask extends AsyncTask<Pair<ImageView, String>, Void, Void> {

        @Override
        protected Void doInBackground(Pair<ImageView, String>... params) {
            Pair<ImageView, String> imageViewTuple = params[0];
            ImageView thumbnailImageView = imageViewTuple.first;
            String imageUrl = imageViewTuple.second;
            try {

                URL myFileUrl = new URL (imageUrl);
                HttpURLConnection conn =
                        (HttpURLConnection) myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                thumbnailImageView.setImageBitmap(BitmapFactory.decodeStream(is));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

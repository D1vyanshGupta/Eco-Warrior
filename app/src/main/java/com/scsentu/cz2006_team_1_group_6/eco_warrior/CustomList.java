package com.scsentu.cz2006_team_1_group_6.eco_warrior;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomList extends ArrayAdapter<String>{

    private static final String TAG = "CustomList";

    private final Activity context;
    private final String[] videoTexts;
    private final String[] imageUrls;

    public CustomList(Activity context, String[] videoTexts, String[] imageUrls) {
        super(context, R.layout.video_item_layout, imageUrls);
        this.context = context;
        this.videoTexts = videoTexts;
        this.imageUrls = imageUrls;

    }

    @Nullable
    @Override
    public String getItem(int position) {
        return imageUrls[position];
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.video_item_layout, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.video_text);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.video_thumbnail);
        txtTitle.setText(videoTexts[position]);

        final String imageUrl = "http://img.youtube.com/vi/" + imageUrls[position] + "/1.jpg";

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri youtubeUri = Uri.parse("http://www.youtube.com/watch?v=" + imageUrls[position]);
                Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, youtubeUri);
                context.startActivity(youtubeIntent);
            }
        });
        return rowView;
    }
}

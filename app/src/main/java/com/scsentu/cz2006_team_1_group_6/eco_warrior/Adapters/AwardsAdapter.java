package com.scsentu.cz2006_team_1_group_6.eco_warrior.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scsentu.cz2006_team_1_group_6.eco_warrior.Models.Award;
import com.scsentu.cz2006_team_1_group_6.eco_warrior.R;

import java.util.List;

public class AwardsAdapter extends RecyclerView.Adapter<AwardsAdapter.ViewHolder>{

    private List<Award> mAwards;
    private Context mContext;

    public AwardsAdapter(Context context,List<Award> awards){
        mAwards = awards;
        mContext = context;
    }

    private Context getmContext(){return mContext;}

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mAwardImage;
        public TextView mAwardTitle;
        public TextView mAwardRequirement;
        public ProgressBar mProgressBar;

        public ViewHolder(View itemView){
            super(itemView);
            mAwardImage = (ImageView) itemView.findViewById(R.id.award_img);
            mAwardTitle = (TextView) itemView.findViewById(R.id.award_title);
            mAwardRequirement = (TextView) itemView.findViewById(R.id.award_requirement);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.award_progress);
        }
    }

    @Override
    public AwardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View awardView = inflater.inflate(R.layout.award_list_item, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(awardView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(AwardsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Award award = mAwards.get(position);

        // Set award image
        ImageView awardImage = viewHolder. mAwardImage;
        awardImage.setImageResource(award.getIsLocked()? R.drawable.lock : award.getImagePath() );

        // Set award title text view.
        TextView awardTextView = viewHolder.mAwardTitle;
        awardTextView.setText(award.getTitle());

        // Set award requirement text view.
        TextView awardRequirementTextView = viewHolder.mAwardRequirement;
        awardRequirementTextView.setText(award.getRequirement());

        // Set progress bar view
        ProgressBar progressBar = viewHolder.mProgressBar;
        progressBar.invalidate();
        progressBar.setMax(award.getWasteAmount().intValue());
        progressBar.setProgress(award.getCurrentWasteAmount().intValue());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mAwards.size();
    }
}

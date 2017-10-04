// Developer: Ahmet Kaymak
// Date: 29.09.2017

package com.project.ui.hospitalpage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.project.core.hospitalmodule.UserHospitalRate;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;

import io.github.rockerhieu.emojicon.EmojiconTextView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class UserHospitalRateAdapter extends RecyclerView.Adapter<UserHospitalRateAdapter.MyViewHolder> {
    private List<UserHospitalRate> hospitalRates = new ArrayList<>();
    private static Context context;
    private String visitorUserId;

    public UserHospitalRateAdapter(String userId, List<UserHospitalRate> hospitalRates, Context context) {
        this.visitorUserId = userId;
        this.hospitalRates = hospitalRates;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView profileImage;
        public TextView userId, ratingTimestamp;
        public EmojiconTextView comment;
        public MaterialRatingBar hospitalRating;

        public MyViewHolder(final View view) {
            super( view );
            profileImage = (CircleImageView) view.findViewById( R.id.user_review_profile_picture );
            userId = (TextView) view.findViewById( R.id.user_review_user_name );
            userId.setTypeface( Typefaces.getRobotoBold( context ) );
            ratingTimestamp = (TextView) view.findViewById( R.id.user_review_timestamp );
            ratingTimestamp.setTypeface( Typefaces.getRobotoLight( context ) );
            hospitalRating = (MaterialRatingBar) view.findViewById( R.id.user_review_rating_bar );
            comment = (EmojiconTextView) view.findViewById( R.id.user_review_comment );
            comment.setTypeface( Typefaces.getRobotoMedium( context ) );
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_user_hospital_review, parent, false );

        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        try {
            UserHospitalRate rate = hospitalRates.get( position );
            holder.userId.setText( rate.getUserId() );
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                    rate.getCreatedAt().getTime(),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );

            holder.ratingTimestamp.setText( timeAgo );
            holder.hospitalRating.setRating( (float) rate.getUserRate() );
            if(rate.getUserComment()!=null){
                holder.comment.setText( rate.getUserComment() );
            }else{
                holder.comment.setVisibility( View.GONE );
            }
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public int getItemCount() {
        return hospitalRates.size();
    }
}
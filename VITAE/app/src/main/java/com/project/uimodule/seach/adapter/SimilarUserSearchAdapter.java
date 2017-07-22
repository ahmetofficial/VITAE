// Developer: Ahmet Kaymak
// Date: 19.04.2017

package com.project.uimodule.seach.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.uimodule.patient.PatientActivity;
import com.project.usermodule.PatientSimularityResponse;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SimilarUserSearchAdapter extends RecyclerView.Adapter<SimilarUserSearchAdapter.MyViewHolder> {
    private List<PatientSimularityResponse> similarUsers = new ArrayList<>();
    private static Context context;
    private String visitorUserId;
    private int totalHealthItem;

    public SimilarUserSearchAdapter(List<PatientSimularityResponse> similarUsers, Context context, String visitorUserId, int totalHealthItem) {
        this.similarUsers = similarUsers;
        this.context = context;
        this.visitorUserId = visitorUserId;
        this.totalHealthItem=totalHealthItem;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userId, similarityPercent;
        public ImageView userPhoto;
        private CardView userCardView;

        public MyViewHolder(final View view) {
            super( view );
            userName = (TextView) view.findViewById( R.id.search_item_user_name_text );
            userId = (TextView) view.findViewById( R.id.search_item_user_id_text );
            similarityPercent = (TextView) view.findViewById( R.id.search_item_user_similarity_percent );
            userPhoto = (ImageView) view.findViewById( R.id.search_item_user_profile_picture );
            userCardView = (CardView) view.findViewById( R.id.user_search_item );
        }

    }

    @Override
    public SimilarUserSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_search_user, parent, false );

        return new SimilarUserSearchAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final SimilarUserSearchAdapter.MyViewHolder holder, final int position) {
        try {
            PatientSimularityResponse user = similarUsers.get( position );
            holder.userName.setText( user.getUserName() );
            holder.userId.setText( user.getUserId() );
            double percent=(double)user.getSimilarityCount()/totalHealthItem;
            percent*=100;
            DecimalFormat df = new DecimalFormat("#.#");
            holder.similarityPercent.setText( "%"+df.format(percent));
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.userCardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String visitedUserId = holder.userId.getText().toString();
                    Intent intent = new Intent( context, PatientActivity.class );
                    intent.putExtra( "visitorId", visitorUserId );
                    intent.putExtra( "visitedId", visitedUserId );
                    context.startActivity( intent );
                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return similarUsers.size();
    }
}

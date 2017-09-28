// Developer: Ahmet Kaymak
// Date: 10.08.2017

package com.project.ui.patient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.ui.patient.PatientActivity;
import com.project.core.usermodule.User;

import java.util.ArrayList;
import java.util.List;

public class PatientFriendsAdapter extends RecyclerView.Adapter<PatientFriendsAdapter.MyViewHolder> {
    private List<User> users = new ArrayList<>();
    private static Context context;
    private String userId;

    public PatientFriendsAdapter(List<User> users, Context context, String userId) {
        this.users = users;
        this.context = context;
        this.userId = userId;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userId;
        public ImageView userPhoto;
        public ImageButton friendshipStatus;
        private RelativeLayout userCardView;

        public MyViewHolder(final View view) {
            super( view );
            userName = (TextView) view.findViewById( R.id.friend_list_item_user_name);
            userId = (TextView) view.findViewById( R.id.friend_list_item_user_id );
            userPhoto = (ImageView) view.findViewById( R.id.friend_list_item_profile_picture );
            userCardView = (RelativeLayout) view.findViewById( R.id.friend_list_item_container );
            friendshipStatus = (ImageButton) view.findViewById( R.id.friend_list_item_friendship_button );
        }

    }

    @Override
    public PatientFriendsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_friend_list, parent, false );

        return new PatientFriendsAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final PatientFriendsAdapter.MyViewHolder holder, final int position) {
        try {
            User user = users.get( position );
            holder.userName.setText( user.getUserName() );
            holder.userId.setText( user.getUserId() );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.userPhoto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String visitedUserId = holder.userId.getText().toString();
                    Intent intent = new Intent( context, PatientActivity.class );
                    intent.putExtra( "visitorId", userId );
                    intent.putExtra( "visitedId", visitedUserId );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity( intent );
                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );

        holder.friendshipStatus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

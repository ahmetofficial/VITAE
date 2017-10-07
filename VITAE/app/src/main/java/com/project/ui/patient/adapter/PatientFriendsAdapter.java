// Developer: Ahmet Kaymak
// Date: 10.08.2017

package com.project.ui.patient.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.project.core.usermodule.User;
import com.project.ui.patient.PatientActivity;

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
        public CircleImageView userPhoto;
        public ImageButton friendshipStatus;
        private RelativeLayout userCardView;

        public MyViewHolder(final View view) {
            super( view );
            userName = (TextView) view.findViewById( R.id.friend_list_item_user_name);
            userId = (TextView) view.findViewById( R.id.friend_list_item_user_id );
            userPhoto = (CircleImageView) view.findViewById( R.id.friend_list_item_profile_picture );
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

            String photoId = user.getProfilePictureId();
            if (!photoId.equals( "" )) {
                String picturePath = "http://178.62.223.153:3000/images/" + photoId.charAt( 0 ) + "/"
                        + photoId.charAt( 1 ) + "/" + photoId.charAt( 2 ) + "/" + photoId.charAt( 3 ) + "/" + photoId.charAt( 4 ) + "/"
                        + photoId.charAt( 5 ) + "/" + photoId.charAt( 6 ) + "/" + photoId.charAt( 7 ) + "/" + photoId.charAt( 9 ) + "/"
                        + photoId.charAt( 10 ) + "/" + photoId.charAt( 11 ) + "/" + photoId.charAt( 12 ) + "/" + photoId + ".jpg";

                Glide.with( context )
                        .load( picturePath )
                        .into( holder.userPhoto );
            }else{
                Bitmap bitmap = BitmapFactory.decodeResource( context.getResources(), R.drawable.empty_profile );
                Bitmap circleBitmap = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888 );
                BitmapShader shader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
                Paint paint = new Paint();
                paint.setShader( shader );
                paint.setAntiAlias( true );
                Canvas c = new Canvas( circleBitmap );
                c.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint );
                holder.userPhoto.setImageBitmap( circleBitmap );
            }
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

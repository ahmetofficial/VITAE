// Developer: Ahmet Kaymak
// Date: 10.08.2017

package com.project.ui.main.message.adapter;

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
import com.project.core.messagemodule.Conversation;
import com.project.core.usermodule.PatientForConversation;
import com.project.restservice.ApiClient;
import com.project.restservice.serverresponse.ServerResponse;
import com.project.ui.main.message.MessageActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private List<PatientForConversation> users = new ArrayList<>();
    private static Context context;
    private String userId;

    public ContactAdapter(List<PatientForConversation> users, Context context, String userId) {
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
            userName = (TextView) view.findViewById( R.id.friend_list_item_user_name );
            userId = (TextView) view.findViewById( R.id.friend_list_item_user_id );
            userPhoto = (CircleImageView) view.findViewById( R.id.friend_list_item_profile_picture );
            userCardView = (RelativeLayout) view.findViewById( R.id.friend_list_item_container );
            friendshipStatus = (ImageButton) view.findViewById( R.id.friend_list_item_friendship_button );
            friendshipStatus.setVisibility( View.GONE );
        }

    }

    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_friend_list, parent, false );

        return new ContactAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final ContactAdapter.MyViewHolder holder, final int position) {
        final PatientForConversation patient = users.get( position );
        final String[] conversationId = {null};
        if(patient.getReceiver().size()!=0){
            conversationId[0] =patient.getReceiver().get( 0 ).getConversationId();
        }else if(patient.getSender().size()!=0){
            conversationId[0] =patient.getSender().get( 0 ).getConversationId();
        }
        try {
            holder.userName.setText( patient.getUserName() );
            holder.userId.setText( patient.getUserId() );

            String photoId = patient.getProfilePictureId();
            if (!photoId.equals( "" )) {
                String picturePath = "http://178.62.223.153:3000/images/" + photoId.charAt( 0 ) + "/"
                        + photoId.charAt( 1 ) + "/" + photoId.charAt( 2 ) + "/" + photoId.charAt( 3 ) + "/" + photoId.charAt( 4 ) + "/"
                        + photoId.charAt( 5 ) + "/" + photoId.charAt( 6 ) + "/" + photoId.charAt( 7 ) + "/" + photoId.charAt( 9 ) + "/"
                        + photoId.charAt( 10 ) + "/" + photoId.charAt( 11 ) + "/" + photoId.charAt( 12 ) + "/" + photoId + ".jpg";

                Glide.with( context )
                        .load( picturePath )
                        .into( holder.userPhoto );
            } else {
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
                if(conversationId[0] !=null){
                }else{
                    Conversation newConversation = new Conversation();
                    newConversation.setSenderId( userId );
                    newConversation.setReceiverId( patient.getUserId() );
                    try {
                        ApiClient.conversationApi().createConversation( newConversation ).enqueue( new Callback<ServerResponse>() {
                            @Override
                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                if (response.isSuccessful()) {
                                    conversationId[0] = response.body().getConversationId();
                                }
                            }

                            @Override
                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                Toast.makeText( context, t.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        } );
                    } catch (Exception e) {
                        Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }

                try {
                    final Intent intent = new Intent( context, MessageActivity.class );
                    intent.putExtra( "senderUserId", userId);
                    //intent.putExtra( "senderUserName", sender.getUserName() );
                    //intent.putExtra( "senderPhotoPath", senderPhotoPath );
                    intent.putExtra( "receiverUserId", patient.getUserId() );
                    intent.putExtra( "receiverUserName", patient.getUserName() );
                    String receiverPhotoId=patient.getProfilePictureId();
                    String receiverPhotoPath = "";
                    if (!receiverPhotoId.equals( "" )) {
                        receiverPhotoPath = "http://178.62.223.153:3000/images/" + receiverPhotoId.charAt( 0 ) + "/"
                                + receiverPhotoId.charAt( 1 ) + "/" + receiverPhotoId.charAt( 2 ) + "/"
                                + receiverPhotoId.charAt( 3 ) + "/" + receiverPhotoId.charAt( 4 ) + "/"
                                + receiverPhotoId.charAt( 5 ) + "/" + receiverPhotoId.charAt( 6 ) + "/"
                                + receiverPhotoId.charAt( 7 ) + "/" + receiverPhotoId.charAt( 9 ) + "/"
                                + receiverPhotoId.charAt( 10 ) + "/" + receiverPhotoId.charAt( 11 ) + "/"
                                + receiverPhotoId.charAt( 12 ) + "/" + receiverPhotoId + ".jpg";
                    }
                    intent.putExtra( "receiverPhotoPath", receiverPhotoPath );
                    intent.putExtra( "conversationId",conversationId[0] );
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

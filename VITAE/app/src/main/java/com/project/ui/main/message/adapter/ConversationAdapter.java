// Developer: Ahmet Kaymak
// Date: 22.02.2017

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
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.project.core.messagemodule.Conversation;
import com.project.core.usermodule.User;
import com.project.ui.main.message.MessageActivity;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyViewHolder> {
    private ArrayList<Conversation> conversationList = new ArrayList<>();
    private String userId;
    private Context context;

    public ConversationAdapter(ArrayList<Conversation> conversationList, String userId, Context context) {
        this.conversationList = conversationList;
        this.userId = userId;
        this.context = context;
        orderConversations( conversationList );
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, timestamp;
        public RelativeLayout conversation;
        public CircleImageView userPhoto;

        public MyViewHolder(View view) {
            super( view );
            userName = (TextView) view.findViewById( R.id.conversation_item_user_name );
            timestamp = (TextView) view.findViewById( R.id.conversation_item_last_conversation_date );
            conversation = (RelativeLayout) view.findViewById( R.id.conversation_item_container );
            userPhoto = (CircleImageView) view.findViewById( R.id.conversation_item_profile_picture );
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_conversation, parent, false );

        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Conversation conversation = conversationList.get( position );
        final User sender = conversation.getSender();
        final User receiver = conversation.getReceiver();
        final Intent intent = new Intent( context, MessageActivity.class );

        holder.userName.setTypeface( Typefaces.getRobotoBold( context ) );
        holder.timestamp.setTypeface( Typefaces.getRobotoLight( context ) );

        String receiverPhotoId = receiver.getProfilePictureId();
        String senderPhotoId = sender.getProfilePictureId();
        String senderPhotoPath = "";
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
        if (!senderPhotoId.equals( "" )) {
            senderPhotoPath = "http://178.62.223.153:3000/images/" + senderPhotoId.charAt( 0 ) + "/"
                    + senderPhotoId.charAt( 1 ) + "/" + senderPhotoId.charAt( 2 ) + "/"
                    + senderPhotoId.charAt( 3 ) + "/" + senderPhotoId.charAt( 4 ) + "/"
                    + senderPhotoId.charAt( 5 ) + "/" + senderPhotoId.charAt( 6 ) + "/"
                    + senderPhotoId.charAt( 7 ) + "/" + senderPhotoId.charAt( 9 ) + "/"
                    + senderPhotoId.charAt( 10 ) + "/" + senderPhotoId.charAt( 11 ) + "/"
                    + senderPhotoId.charAt( 12 ) + "/" + senderPhotoId + ".jpg";
        }

        if (receiver.getUserId().equals( userId )) {
            holder.userName.setText( sender.getUserName() );
            if (!senderPhotoPath.equals( "" )) {
                Glide.with( context )
                        .load( senderPhotoPath )
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
            intent.putExtra( "senderUserId", receiver.getUserId() );
            intent.putExtra( "senderUserName", receiver.getUserName() );
            intent.putExtra( "senderPhotoPath", receiverPhotoPath );
            intent.putExtra( "receiverUserId", sender.getUserId() );
            intent.putExtra( "receiverUserName", sender.getUserName() );
            intent.putExtra( "receiverPhotoPath", senderPhotoPath );
        } else {
            holder.userName.setText( receiver.getUserName() );
            if (!receiverPhotoPath.equals( "" )) {
                Glide.with( context )
                        .load( receiverPhotoPath )
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
            intent.putExtra( "senderUserId", sender.getUserId() );
            intent.putExtra( "senderUserName", sender.getUserName() );
            intent.putExtra( "senderPhotoPath", senderPhotoPath );
            intent.putExtra( "receiverUserId", receiver.getUserId() );
            intent.putExtra( "receiverUserName", receiver.getUserName() );
            intent.putExtra( "receiverPhotoPath", receiverPhotoPath );
        }

        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                conversation.getUpdatedAt().getTime(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
        holder.timestamp.setText( timeAgo );
        holder.timestamp.setText( timeAgo );

        holder.conversation.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra( "conversationId", conversation.getConversationId() );
                context.startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    public void orderConversations(List<Conversation> conversationList) {
        Conversation temp;
        for (int i = 1; i < conversationList.size(); i++) {
            for (int j = 0; j < conversationList.size() - i; j++) {
                if (conversationList.get( j ).getUpdatedAt().getTime() < conversationList.get( j + 1 ).getUpdatedAt().getTime()) {
                    temp = conversationList.get( j );
                    conversationList.set( j, conversationList.get( j + 1 ) );
                    conversationList.set( j + 1, temp );
                }
            }
        }
    }
}

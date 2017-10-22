// Developer: Ahmet Kaymak
// Date: 22.02.2017

package com.project.ui.main.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.project.core.messagemodule.Message;
import com.project.core.usermodule.User;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<Message> messageList = new ArrayList<>();
    private Context context;
    private User sender;
    private User receiver;
    private boolean isSenderMessage;
    public static final int SENDER = 0;
    public static final int RECEIVER = 1;


    public MessageAdapter(List<Message> messageList, User sender, User receiver, Context context) {
        this.messageList = messageList;
        this.sender = sender;
        this.receiver = receiver;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userId;
        private TextView message;
        private CircleImageView userPhoto;

        public MyViewHolder(View itemView) {
            super( itemView );
            message = (TextView) itemView.findViewById( R.id.message_text );
            userPhoto = (CircleImageView) itemView.findViewById( R.id.profile_picture );
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_message_left, parent, false );
            MyViewHolder vh = new MyViewHolder( itemView );
            return vh;
        } else {
            View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_message_right, parent, false );
            MyViewHolder vh = new MyViewHolder( itemView );
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Message message = messageList.get( position );
        holder.message.setText( message.getMessageText() );
        holder.message.setTypeface( Typefaces.getRobotoLight( context ) );
        if (isSenderMessage == true) {
            Glide.with( context )
                    .load( sender.getProfilePictureId() )
                    .into( holder.userPhoto );
        }else{
            Glide.with( context )
                    .load( receiver.getProfilePictureId() )
                    .into( holder.userPhoto );
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get( position );
        if (message.getSenderId().equals( sender.getUserId() )) {
            isSenderMessage = true;
            return SENDER;
        } else {
            isSenderMessage = false;
            return RECEIVER;
        }
    }

}

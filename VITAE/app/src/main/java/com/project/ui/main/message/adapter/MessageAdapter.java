package com.project.ui.main.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmetkaymak.vitae.R;
import com.project.core.messagemodule.Message;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<Message> messageList = new ArrayList<>();
    private Context context;
    private String userId;
    public static final int SENDER = 0;
    public static final int RECEIVER = 1;


    public MessageAdapter(List<Message> messageList, String userId, Context context) {
        this.messageList = messageList;
        this.userId = userId;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userId;
        private TextView message;

        public MyViewHolder(View itemView) {
            super( itemView );
            message = (TextView) itemView.findViewById( R.id.message_text );
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
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        if (message.getSenderId().equals(userId)) {
            return SENDER;
        } else {
            return RECEIVER;
        }
    }

}

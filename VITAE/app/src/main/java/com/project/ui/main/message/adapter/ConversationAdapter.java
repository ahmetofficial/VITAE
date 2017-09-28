// Developer: Ahmet Kaymak
// Date: 22.02.2017

package com.project.ui.main.message.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmetkaymak.vitae.R;
import com.project.core.messagemodule.Conversation;
import com.project.ui.main.message.MessageActivity;

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
        orderConversations(conversationList);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, timestamp;
        public RelativeLayout conversation;

        public MyViewHolder(View view) {
            super( view );
            userName = (TextView) view.findViewById( R.id.conversation_item_user_name );
            timestamp = (TextView) view.findViewById( R.id.conversation_item_last_conversation_date );
            conversation =(RelativeLayout) view.findViewById( R.id.conversation_item_container );
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
        Typeface typeLight = Typeface.createFromAsset( context.getAssets(), "fonts/Roboto-Light.ttf" );
        Typeface typeBold = Typeface.createFromAsset( context.getAssets(), "fonts/Roboto-Bold.ttf" );
        holder.userName.setTypeface( typeBold );
        holder.timestamp.setTypeface( typeLight );

        final Conversation conversation = conversationList.get( position );
        final Intent intent = new Intent( context, MessageActivity.class );

        if(conversation.getReceiverId().equals( userId )) {
            holder.userName.setText( conversation.getSenderId() );
            intent.putExtra( "senderId", conversation.getReceiverId() );
            intent.putExtra( "receiverId", conversation.getSenderId() );
        }else{
            holder.userName.setText( conversation.getReceiverId() );
            intent.putExtra( "senderId", conversation.getSenderId() );
            intent.putExtra( "receiverId", conversation.getReceiverId() );
        }
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                conversation.getUpdatedAt().getTime(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
        holder.timestamp.setText( timeAgo );
        holder.timestamp.setText( timeAgo );

        holder.conversation.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra( "userId", userId );
                intent.putExtra( "conversationId", conversation.getConversationId() );
                context.startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    public void orderConversations(List<Conversation> conversationList)
    {
        Conversation temp;
        for (int i=1; i<conversationList.size(); i++)
        {
            for(int j=0; j<conversationList.size()-i; j++)
            {
                if (conversationList.get(j).getUpdatedAt().getTime() < conversationList.get(j+1).getUpdatedAt().getTime())
                {
                    temp = conversationList.get(j);
                    conversationList.set(j,conversationList.get(j+1));
                    conversationList.set(j+1,temp);
                }
            }
        }
    }
}

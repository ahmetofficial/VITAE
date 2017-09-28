// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.ui.main.message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.core.messagemodule.Conversation;
import com.project.restservice.ApiClient;
import com.project.ui.main.message.adapter.ConversationAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentConversation extends Fragment {

    private String userId;
    private View messageView;
    private RecyclerView recyclerView;
    private ConversationAdapter adapter;
    private ArrayList<Conversation> conversationList;

    public FragmentConversation(String userId) {
        this.userId=userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messageView = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        getUserConversation(userId);
        return messageView;
    }

    private void getUserConversation(final String userId) {
        try {
            ApiClient.conversationApi().getUserConversations(userId).enqueue( new Callback<Conversation>() {
                @Override
                public void onResponse(Call<Conversation> call, Response<Conversation> response) {
                    if (response.isSuccessful()) {
                        conversationList= response.body().getConversations();
                        recyclerView = (RecyclerView) messageView.findViewById( R.id.recycler_view );
                        adapter = new ConversationAdapter( conversationList,userId,getContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getActivity() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( adapter );
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Conversation> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}

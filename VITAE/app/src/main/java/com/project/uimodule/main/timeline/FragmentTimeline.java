// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.uimodule.main.timeline;

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

import com.lavie.users.R;
import com.project.postmodule.UserPost;
import com.project.uimodule.main.timeline.adapter.UserPostAdapter;
import com.project.restservice.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTimeline extends Fragment {

    private List<UserPost> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserPostAdapter mAdapter;
    private String userId;

    public FragmentTimeline(String userId) {
        this.userId=userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View timelineView = inflater.inflate(R.layout.fragment_timeline, container, false);
        try {
            ApiClient.postApi().getUserPosts(userId).enqueue(new Callback<UserPost>() {
                @Override
                public void onResponse(Call<UserPost> call, Response<UserPost> response) {
                    if (response.isSuccessful()) {
                        postList = (ArrayList) response.body().getPosts();
                        recyclerView = (RecyclerView) timelineView.findViewById(R.id.timeline_recycler_view);
                        mAdapter = new UserPostAdapter(postList);
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<UserPost> call, Throwable t) {
                    Log.e("UserTimeline", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        return timelineView;
    }
}

package com.project.postmodule.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lavie.users.R;
import com.project.postmodule.UserPost;
import com.project.postmodule.adapter.UserPostAdapter;
import com.project.restservice.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTimeline extends AppCompatActivity {
    private List<UserPost> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserPostAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        try {
            ApiClient.postApi().getUserPosts().enqueue(new Callback<UserPost>() {
                @Override
                public void onResponse(Call<UserPost> call, Response<UserPost> response) {
                    if (response.isSuccessful()) {
                        postList = (ArrayList) response.body().getPosts();
                        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        mAdapter = new UserPostAdapter(postList);
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
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
            Log.e("UserTimeline", e.getMessage());
        }


    }
}

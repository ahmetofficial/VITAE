// Developer: Ahmet Kaymak
// Date: 19.04.2017

package com.project.uimodule.seach;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.restservice.ApiClient;
import com.project.restservice.FullTextSearchRequest;
import com.project.uimodule.seach.adapter.UserSearchAdapter;
import com.project.usermodule.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentUserSearch extends Fragment {

    public FragmentUserSearch(String query) {
        this.query = query;
    }

    private String query;
    private View fragmentUserSearchView;
    private ArrayList<User> userSearchList;
    private UserSearchAdapter mAdapter;
    private RecyclerView recyclerView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentUserSearchView = inflater.inflate( R.layout.fragment_recyclerview, container, false );
        listSearchResult( query );
        return fragmentUserSearchView;
    }

    public void listSearchResult(String query) {
        try {
            FullTextSearchRequest fullTextSearchRequest = new FullTextSearchRequest();
            fullTextSearchRequest.setSearchText( query );
            ApiClient.userApi().searchUser( fullTextSearchRequest ).enqueue( new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        userSearchList = response.body().getUsers();
                        recyclerView = (RecyclerView) fragmentUserSearchView.findViewById( R.id.recycler_view );
                        mAdapter = new UserSearchAdapter( userSearchList,getContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( fragmentUserSearchView.getContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}

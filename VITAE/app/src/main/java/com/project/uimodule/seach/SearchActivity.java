// Developer: Ahmet Kaymak
// Date: 16.04.2017

package com.project.uimodule.seach;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.project.uimodule.ViewPagerAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    public static String query;
    public static String userId;
    public static int totalHealthItem;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MaterialSearchView searchView;
    private ViewPagerAdapter adapter;

    private FragmentHospitalSearch fragmentHospitalSearch;
    private FragmentUserSearch fragmentUserSearch;
    private FragmentSimilarPatientSearch fragmentSimilarPatientSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_search );
            viewPager = (ViewPager) findViewById( R.id.search_activity_viewpager );
            setupViewPager( viewPager );

            tabLayout = (TabLayout) findViewById( R.id.search_activity_tabs );
            tabLayout.setupWithViewPager( viewPager );
            setupTabIcons();

            //Search Bar Fields
            Toolbar toolbar = (Toolbar) findViewById( R.id.search_activity_toolbar );
            setSupportActionBar( toolbar );
            searchView = (MaterialSearchView) findViewById( R.id.search_activity_search_view );
            searchView.setVoiceSearch( false );
            searchView.setCursorDrawable( R.drawable.custom_cursor );
            searchView.setEllipsize( true );
            searchView.setSuggestions( null );

            searchView.setOnQueryTextListener( new MaterialSearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    SearchActivity.query = query;
                    fragmentHospitalSearch.listSearchResult( query );
                    fragmentUserSearch.listSearchResult( query );
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    SearchActivity.query = newText;
                    fragmentHospitalSearch.listSearchResult( query );
                    fragmentUserSearch.listSearchResult( query );
                    return false;
                }
            } );

            searchView.setOnSearchViewListener( new MaterialSearchView.SearchViewListener() {
                @Override
                public void onSearchViewShown() {
                    //Do some magic
                }

                @Override
                public void onSearchViewClosed() {
                    //Do some magic
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_main, menu );

        MenuItem item = menu.findItem( R.id.action_search );
        searchView.setMenuItem( item );

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
                ArrayList<String> matches = data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );
                if (matches != null && matches.size() > 0) {
                    String searchWrd = matches.get( 0 );
                    if (!TextUtils.isEmpty( searchWrd )) {
                        searchView.setQuery( searchWrd, false );
                    }
                }

                return;
            }
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
        super.onActivityResult( requestCode, resultCode, data );
    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.icon_hospital_white,
                R.drawable.icon_user_white,
                R.drawable.icon_similar_user
        };
        tabLayout.getTabAt( 0 ).setIcon( tabIcons[0] );
        tabLayout.getTabAt( 1 ).setIcon( tabIcons[1] );
        tabLayout.getTabAt( 2 ).setIcon( tabIcons[2] );
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        fragmentHospitalSearch = new FragmentHospitalSearch( query );
        fragmentUserSearch = new FragmentUserSearch( query, userId );
        fragmentSimilarPatientSearch = new FragmentSimilarPatientSearch( query, userId, totalHealthItem );
        adapter.addFrag( fragmentHospitalSearch, "Hospital Search" );
        adapter.addFrag( fragmentUserSearch, "User Search" );
        adapter.addFrag( fragmentSimilarPatientSearch, "Similar User Search" );
        viewPager.setAdapter( adapter );
    }
}

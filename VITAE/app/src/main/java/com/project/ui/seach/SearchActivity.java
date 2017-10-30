// Developer: Ahmet Kaymak
// Date: 16.04.2017

package com.project.ui.seach;

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
import com.project.ui.ViewPagerAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private String query;
    private String userId;
    private int totalHealthItem;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MaterialSearchView searchView;
    private ViewPagerAdapter adapter;

    private FragmentHospitalSearch fragmentHospitalSearch;
    private FragmentPatientSearch fragmentUserSearch;
    private FragmentSimilarPatientSearch fragmentSimilarPatientSearch;
    private FragmentDoctorSearch fragmentDoctorSearch;
    private int viewPagePosition;
    private boolean isFirstSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_search );
            viewPager = (ViewPager) findViewById( R.id.search_activity_viewpager );
            setupViewPager( viewPager );

            isFirstSearch=true;
            Intent myIntent = getIntent();
            userId = myIntent.getStringExtra( "userId" );
            query = myIntent.getStringExtra( "query" );
            totalHealthItem = myIntent.getIntExtra( "totalHealthItem", -1 );

            fragmentHospitalSearch.listSearchResult( userId, query );

            tabLayout = (TabLayout) findViewById( R.id.search_activity_tabs );
            tabLayout.setupWithViewPager( viewPager );
            setupTabIcons();

            //Search Bar Fields
            Toolbar toolbar = (Toolbar) findViewById( R.id.search_activity_toolbar );
            setSupportActionBar( toolbar );
            getSupportActionBar().setTitle( " " );
            searchView = (MaterialSearchView) findViewById( R.id.search_activity_search_view );
            searchView.setVoiceSearch( false );
            searchView.setCursorDrawable( R.drawable.custom_cursor );
            searchView.setEllipsize( true );
            searchView.setSuggestions( null );

            viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (position == 0 || isFirstSearch) {
                        fragmentHospitalSearch.listSearchResult( userId, query );
                        viewPagePosition=0;
                    } else if (position == 1) {
                        fragmentUserSearch.listSearchResult( userId, query );
                        viewPagePosition=1;
                    } else if (position == 1){
                        fragmentSimilarPatientSearch.listSearchResult( userId, query, totalHealthItem );
                        viewPagePosition=2;
                    } else{
                        fragmentDoctorSearch.listSearchResult( userId,query );
                        viewPagePosition=3;
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    isFirstSearch=false;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            } );

            searchView.setOnQueryTextListener( new MaterialSearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String newQuery) {
                    if (newQuery != null) {
                        query = newQuery;
                        if (viewPagePosition == 0) {
                            fragmentHospitalSearch.listSearchResult( userId, query );
                        } else if (viewPagePosition == 1) {
                            fragmentUserSearch.listSearchResult( userId, query );
                        } else if (viewPagePosition == 1){
                            fragmentSimilarPatientSearch.listSearchResult( userId, query, totalHealthItem );
                        } else{
                            fragmentDoctorSearch.listSearchResult( userId,query );
                        }
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            } );


        } catch (Exception e) {
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
                R.drawable.icon_similar_user,
                R.drawable.ic_stethoscope_white_24dp,
        };
        tabLayout.getTabAt( 0 ).setIcon( tabIcons[0] );
        tabLayout.getTabAt( 1 ).setIcon( tabIcons[1] );
        tabLayout.getTabAt( 2 ).setIcon( tabIcons[2] );
        tabLayout.getTabAt( 3 ).setIcon( tabIcons[3] );
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        fragmentHospitalSearch = new FragmentHospitalSearch( userId, query );
        fragmentUserSearch = new FragmentPatientSearch( userId, query );
        fragmentSimilarPatientSearch = new FragmentSimilarPatientSearch( userId, query, totalHealthItem );
        fragmentDoctorSearch = new FragmentDoctorSearch( userId, query );
        adapter.addFrag( fragmentHospitalSearch, "Hospital Search" );
        adapter.addFrag( fragmentUserSearch, "Patient Search" );
        adapter.addFrag( fragmentSimilarPatientSearch, "Similar User Search" );
        adapter.addFrag( fragmentDoctorSearch, "Doctor Search" );
        viewPager.setAdapter( adapter );
    }
}

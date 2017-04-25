// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.uimodule.main.healthtree.treatmentAdd;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lavie.users.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import io.github.kshitij_jain.indicatorview.IndicatorView;

public class TreatmentAddActivity extends AppCompatActivity {

    private FragmentTreatmentAddOne fragmentTreatmentAddOne;
    private FragmentTreatmentAddTwo fragmentTreatmentAddTwo;
    private FragmentTreatmentAddThree fragmentTreatmentAddThree;

    private ViewPager viewPager;
    private IndicatorView mIndicatorView;

    public static String userId;
    private Toolbar toolbar;
    private MaterialSearchView searchView;
    private MenuItem item;
    public static String diseaseName;
    public static String treatmentName;

    private String mState="VISIBLE_MENU";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_treatment_add );

        viewPager = (ViewPager) findViewById( R.id.activity_treatment_add_viewpager );
        setupViewPager( viewPager );
        mIndicatorView = (IndicatorView) findViewById( R.id.activity_treatment_add_indicator );
        mIndicatorView.setPageIndicators( 3 );

        final String alertDisease= getResources().getString( R.string.please_select_disease );
        final String alertTreatment = getResources().getString( R.string.please_select_treatment );

        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndicatorView.setCurrentPage( position );
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        } );
        mIndicatorView.setActiveIndicatorColor( R.color.treatment_color_light );
        mIndicatorView.setInactiveIndicatorColor( R.color.white );

        //Search Bar Fields
        toolbar = (Toolbar) findViewById( R.id.activity_treatment_add_toolbar);
        setSupportActionBar( toolbar );

        searchView = (MaterialSearchView) findViewById( R.id.activity_treatment_add_search_view);
        searchView.setVoiceSearch( false );
        searchView.setCursorDrawable( R.drawable.custom_cursor );
        searchView.setEllipsize( true );
        searchView.setSuggestions( null );

        searchView.setOnQueryTextListener( new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fragmentTreatmentAddTwo.fillTreatments( query );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fragmentTreatmentAddTwo.fillTreatments( newText );
                return false;
            }
        } );

        searchView.setOnSearchViewListener( new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
            }
        } );

        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    searchView.setVisibility( View.INVISIBLE );
                    mState = "HIDE_MENU";
                    invalidateOptionsMenu();

                }else if( position==2){
                    searchView.setVisibility( View.INVISIBLE );
                    mState = "HIDE_MENU";
                    invalidateOptionsMenu();
                    fragmentTreatmentAddThree.fillDiseaseName(alertDisease,diseaseName);
                    fragmentTreatmentAddThree.fillTreatmentName(alertTreatment,treatmentName);
                }
                else if( position== 1){
                    searchView.setVisibility( View.VISIBLE );
                    mState="VISIBLE_MENU";
                    invalidateOptionsMenu();
                }
            }

            public void onPageSelected(int position) {
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_main, menu );

        item = menu.findItem( R.id.action_search );

        if (mState.equals("HIDE_MENU")) {
            for (int i = 0; i < menu.size(); i++)
                menu.getItem(i).setVisible(false);
        }

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
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
        super.onActivityResult( requestCode, resultCode, data );
    }

    private void setupViewPager(ViewPager viewPager) {
        fragmentTreatmentAddOne = new FragmentTreatmentAddOne(userId,viewPager);
        fragmentTreatmentAddTwo = new FragmentTreatmentAddTwo(viewPager);
        fragmentTreatmentAddThree = new FragmentTreatmentAddThree(userId);

        TreatmentAddActivity.ViewPagerAdapter adapter = new TreatmentAddActivity.ViewPagerAdapter( getSupportFragmentManager() );
        adapter.addFrag( fragmentTreatmentAddOne, "One" );
        adapter.addFrag( fragmentTreatmentAddTwo, "Two" );
        adapter.addFrag( fragmentTreatmentAddThree, "Three" );
        viewPager.setAdapter( adapter );
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super( manager );
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get( position );
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add( fragment );
            mFragmentTitleList.add( title );
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}

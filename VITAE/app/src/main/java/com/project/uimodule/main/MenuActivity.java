// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.uimodule.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.fabtransitionactivity.SheetLayout;
import com.lavie.users.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.project.uimodule.ViewPagerAdapter;
import com.project.uimodule.main.healthtree.FragmentHealthTree;
import com.project.uimodule.main.profile.FragmentProfile;
import com.project.uimodule.main.seach.SearchActivity;
import com.project.uimodule.main.timeline.FragmentTimeline;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity implements SheetLayout.OnFabAnimationEndListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String userId;
    private MaterialSearchView searchView;

    @BindView(R.id.menu_post_sheet)
    SheetLayout mSheetLayout;
    @BindView(R.id.menu_FAB)
    FloatingActionButton mFab;
    private static final int REQUEST_CODE = 1;

    //Post Fields
    private EditText mainPostText;
    private Button mainPostSendButton;
    private View postView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );

        viewPager = (ViewPager) findViewById( R.id.viewpager );
        setupViewPager( viewPager );

        tabLayout = (TabLayout) findViewById( R.id.tabs );
        tabLayout.setupWithViewPager( viewPager );
        setupTabIcons();

        //Post Fields
        LayoutInflater userPostInflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        postView = userPostInflater.inflate( R.layout.activity_post, null );
        mainPostText = (EditText) postView.findViewById( R.id.activity_post_txt_post );
        mainPostSendButton = (Button) postView.findViewById( R.id.activity_post_btn_post );

        ButterKnife.bind( this );
        mSheetLayout.setFab( mFab );
        mSheetLayout.setFabAnimationEndListener( this );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        searchView = (MaterialSearchView) findViewById( R.id.search_view );
        searchView.setVoiceSearch( false );
        searchView.setCursorDrawable( R.drawable.custom_cursor );
        searchView.setEllipsize( true );
        searchView.setSuggestions( null );

        searchView.setOnQueryTextListener( new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.query=query;
                startActivity( new Intent( MenuActivity.this, SearchActivity.class) );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //listSearchResult( newText );
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
            if (requestCode == REQUEST_CODE) {
                mSheetLayout.contractFab();
            }
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

    @OnClick(R.id.menu_FAB)
    void onFabClick() {
        try {
            mSheetLayout.expandFab();
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public void onFabAnimationEnd() {
        try {
            PostActivity.userId = userId;
            Intent intent = new Intent( this, PostActivity.class );
            startActivityForResult( intent, REQUEST_CODE );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.menu_timeline,
                R.drawable.menu_message,
                R.drawable.menu_profile
        };

        tabLayout.getTabAt( 0 ).setIcon( tabIcons[0] );
        tabLayout.getTabAt( 1 ).setIcon( tabIcons[1] );
        tabLayout.getTabAt( 2 ).setIcon( tabIcons[2] );
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        adapter.addFrag( new FragmentTimeline( userId ), "Timeline" );
        adapter.addFrag( new FragmentHealthTree( userId ), "Health Tree" );
        adapter.addFrag( new FragmentProfile( userId ), "Profile" );
        viewPager.setAdapter( adapter );
    }

}

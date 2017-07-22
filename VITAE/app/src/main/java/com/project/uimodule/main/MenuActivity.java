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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.fabtransitionactivity.SheetLayout;
import com.ahmetkaymak.vitae.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.project.uimodule.ViewPagerAdapter;
import com.project.uimodule.main.healthtree.FragmentHealthTree;
import com.project.uimodule.main.profile.FragmentProfile;
import com.project.uimodule.main.timeline.FragmentTimeline;
import com.project.uimodule.seach.SearchActivity;
import com.project.uimodule.userhealthtree.UserHealthTreeActivity;

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

    //Drawer
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerPane;
    private ArrayList<NavigationItem> mNavItems = new ArrayList<NavigationItem>();


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

        //Drawer
        mNavItems.add(new NavigationItem(getString( R.string.my_health_tree ), R.drawable.icon_health_tree));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigation Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Birşeyler
            }
        });

        //Search Bar Fields
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        searchView = (MaterialSearchView) findViewById( R.id.search_view );
        searchView.setVoiceSearch( false );
        searchView.setCursorDrawable( R.drawable.custom_cursor );
        searchView.setEllipsize( true );
        searchView.setSuggestions( null );

        searchView.setOnQueryTextListener( new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.query=query;
                SearchActivity.userId=userId;
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

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });
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
                R.drawable.icon_timeline_white,
                R.drawable.icon_dna_white,
                R.drawable.icon_user_white
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

    //Drawer
    class NavigationItem {
        String mTitle;
        int mIcon;

        public NavigationItem(String title, int icon) {
            mTitle = title;
            mIcon = icon;
        }
    }

    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavigationItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavigationItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_drawer, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText( mNavItems.get(position).mTitle );
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void selectItemFromDrawer(int position) {
        if(position==0){
            UserHealthTreeActivity.userId=userId;
            startActivity( new Intent( MenuActivity.this, UserHealthTreeActivity.class ) );
        }
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

}
// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.ui.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import com.ahmetkaymak.vitae.Manifest;
import com.ahmetkaymak.vitae.R;
import com.github.fabtransitionactivity.SheetLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.project.core.usermodule.User;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.ViewPagerAdapter;
import com.project.ui.location.ActivityBloodAlertMap;
import com.project.ui.location.ActivityDoctorDiseasePerformanceMap;
import com.project.ui.location.ActivityHospitalDiseasePerformanceMap;
import com.project.ui.main.healthtree.FragmentHealthTree;
import com.project.ui.main.message.ContactActivity;
import com.project.ui.main.message.FragmentConversation;
import com.project.ui.main.profile.FragmentDoctorProfile;
import com.project.ui.main.profile.FragmentPatientProfile;
import com.project.ui.main.timeline.FragmentTimeline;
import com.project.ui.patient.UserFriendsActivity;
import com.project.ui.patient.UserSettingsActivity;
import com.project.ui.seach.SearchActivity;
import com.project.ui.userhealthtree.UserHealthTreeActivity;
import com.project.utils.SessionUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity implements SheetLayout.OnFabAnimationEndListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String userId;
    public static String userName;
    private int userTypeId;
    public static String userProfilePictureId;
    private MaterialSearchView searchView;

    private FragmentTimeline fragmentTimeline;
    private FragmentHealthTree fragmentHealthTree;
    private FragmentPatientProfile fragmentPatientProfile;
    private FragmentConversation fragmentConversation;
    private FragmentDoctorProfile fragmentDoctorProfile;

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

    //Permission
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );

        Intent myIntent = getIntent();
        userId = myIntent.getStringExtra( "userId" );
        userTypeId = myIntent.getIntExtra( "userTypeId", 0 );
        tabLayout = (TabLayout) findViewById( R.id.tabs );
        viewPager = (ViewPager) findViewById( R.id.viewpager );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setTitle( "" );

        checkLocationPermission();

        if (userTypeId == 1) {
            setupViewPagerForPatient( viewPager );
            tabLayout.setupWithViewPager( viewPager );
            //tabLayout.setBackgroundResource( R.color.colorPrimary);
            //toolbar.setBackgroundResource( R.color.colorPrimaryDark );
            setupTabIconsForPatientAndDoctors();
        } else if (userTypeId == 2) {
            setupViewPagerForDoctor( viewPager );
            tabLayout.setupWithViewPager( viewPager );
            //tabLayout.setBackgroundResource( R.color.colorPrimaryDoctor );
            //toolbar.setBackgroundResource( R.color.colorPrimaryDarkDoctor );
            setupTabIconsForPatientAndDoctors();
        }

        String token = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer( token );

        //Post Fields
        LayoutInflater userPostInflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        postView = userPostInflater.inflate( R.layout.activity_post, null );
        mainPostText = (EditText) postView.findViewById( R.id.activity_post_txt_post );
        mainPostSendButton = (Button) postView.findViewById( R.id.activity_post_btn_post );

        ButterKnife.bind( this );
        mSheetLayout.setFab( mFab );
        mSheetLayout.setFabAnimationEndListener( this );

        //Drawer
        mNavItems.add( new NavigationItem( getString( R.string.my_health_tree ), R.drawable.ic_barley_white_24dp ) );
        mNavItems.add( new NavigationItem( getString( R.string.friends ), R.drawable.ic_clipboard_account_white_24dp ) );
        mNavItems.add( new NavigationItem( getString( R.string.hospital_performance_map ), R.drawable.ic_hospital_marker_white_24dp ) );
        mNavItems.add( new NavigationItem( getString( R.string.doctor_performance_map ), R.drawable.ic_stethoscope_white_24dp ) );
        mNavItems.add( new NavigationItem( getString( R.string.blood_alarm_map ), R.drawable.ic_blood_white ) );
        mNavItems.add( new NavigationItem( getString( R.string.settings ), R.drawable.ic_settings_white_24dp ) );
        mNavItems.add( new NavigationItem( getString( R.string.log_out ), R.drawable.ic_logout_white_24dp ) );
        mDrawerLayout = (DrawerLayout) findViewById( R.id.drawerLayout );

        // Populate the Navigation Drawer with options
        mDrawerPane = (RelativeLayout) findViewById( R.id.drawerPane );
        mDrawerList = (ListView) findViewById( R.id.navList );
        DrawerListAdapter adapter = new DrawerListAdapter( this, mNavItems );
        mDrawerList.setAdapter( adapter );

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Birşeyler
            }
        } );

        searchView = (MaterialSearchView) findViewById( R.id.search_view );
        searchView.setVoiceSearch( false );
        searchView.setCursorDrawable( R.drawable.custom_cursor );
        searchView.setEllipsize( true );
        searchView.setSuggestions( null );

        searchView.setOnQueryTextListener( new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    Intent intent = new Intent( getBaseContext(), SearchActivity.class );
                    intent.putExtra( "userId", userId );
                    intent.putExtra( "query", query );
                    intent.putExtra( "totalHealthItem", fragmentHealthTree.getTotalHealthItemCount() );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                    getBaseContext().startActivity( intent );
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );

        mDrawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened( drawerView );
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed( drawerView );
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener( mDrawerToggle );

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer( position );
            }
        } );

        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    mFab.setImageDrawable( getResources().getDrawable( R.drawable.ic_plus_white_18dp, getTheme() ) );
                    mFab.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent( getBaseContext(), ContactActivity.class );
                            intent.putExtra( "userId", userId );
                            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                            getBaseContext().startActivity( intent );
                        }
                    } );
                } else {
                    mFab.setImageDrawable( getResources().getDrawable( R.drawable.ic_pencil_white_18dp, getTheme() ) );
                    mFab.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                mSheetLayout.expandFab();
                            } catch (Exception e) {
                                Log.e( "UserTimeline", e.getMessage() );
                                Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        }
                    } );
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );
    }

    private void sendRegistrationToServer(String token) {
        User user = new User();
        user.setUserId( SessionUtils.getUserId() );
        user.setDeviceToken( token );
        try {
            ApiClient.userApi().updateUserDeviceToken( user ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {

                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
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
            //super.onBackPressed();
            Intent intent = new Intent( Intent.ACTION_MAIN );
            intent.addCategory( Intent.CATEGORY_HOME );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );//***Change Here***
            startActivity( intent );
            finish();
            System.exit( 0 );
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
            Intent intent = new Intent( getBaseContext(), PostActivity.class );
            intent.putExtra( "userId", userId );
            intent.putExtra( "userTypeId", Integer.valueOf(userTypeId) );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivityForResult( intent,REQUEST_CODE );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void setupTabIconsForPatientAndDoctors() {
        int[] tabIcons = {
                R.drawable.icon_timeline_white,
                R.drawable.icon_dna_white,
                R.drawable.ic_message_white_24dp,
                R.drawable.ic_account_circle_white_24dp
        };

        tabLayout.getTabAt( 0 ).setIcon( tabIcons[0] );
        tabLayout.getTabAt( 1 ).setIcon( tabIcons[1] );
        tabLayout.getTabAt( 2 ).setIcon( tabIcons[2] );
        tabLayout.getTabAt( 3 ).setIcon( tabIcons[3] );
    }

    private void setupViewPagerForPatient(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        fragmentTimeline = new FragmentTimeline( userId );
        fragmentHealthTree = new FragmentHealthTree( userId,userTypeId );
        fragmentConversation = new FragmentConversation( userId );
        fragmentPatientProfile = new FragmentPatientProfile( userId );
        adapter.addFrag( fragmentTimeline, "Timeline" );
        adapter.addFrag( fragmentHealthTree, "Health Tree" );
        adapter.addFrag( fragmentConversation, "Message" );
        adapter.addFrag( fragmentPatientProfile, "Profile" );
        viewPager.setAdapter( adapter );
    }

    private void setupViewPagerForDoctor(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        fragmentTimeline = new FragmentTimeline( userId );
        fragmentHealthTree = new FragmentHealthTree( userId,userTypeId );
        fragmentConversation = new FragmentConversation( userId );
        fragmentDoctorProfile = new FragmentDoctorProfile( userId );
        adapter.addFrag( fragmentTimeline, "Timeline" );
        adapter.addFrag( fragmentHealthTree, "Health Tree" );
        adapter.addFrag( fragmentConversation, "Message" );
        adapter.addFrag( fragmentDoctorProfile, "Profile" );
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
            return mNavItems.get( position );
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                view = inflater.inflate( R.layout.item_drawer, null );
            } else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById( R.id.title );
            ImageView iconView = (ImageView) view.findViewById( R.id.icon );

            titleView.setText( mNavItems.get( position ).mTitle );
            iconView.setImageResource( mNavItems.get( position ).mIcon );

            return view;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected( item )) {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate( savedInstanceState );
        mDrawerToggle.syncState();
    }

    private void selectItemFromDrawer(int position) {
        if (position == 0) {
            UserHealthTreeActivity.userId = userId;
            startActivity( new Intent( MenuActivity.this, UserHealthTreeActivity.class ) );
        } else if (position == 1) {
            Intent intent = new Intent( getBaseContext(), UserFriendsActivity.class );
            intent.putExtra( "userId", userId );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            getBaseContext().startActivity( intent );
        } else if (position == 2) {
            Intent intent = new Intent( getBaseContext(), ActivityHospitalDiseasePerformanceMap.class );
            intent.putExtra( "userId", userId );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            getBaseContext().startActivity( intent );
        } else if (position == 3) {
            Intent intent = new Intent( getBaseContext(), ActivityDoctorDiseasePerformanceMap.class );
            intent.putExtra( "userId", userId );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            getBaseContext().startActivity( intent );
        } else if (position == 4) {
            Intent intent = new Intent( getBaseContext(), ActivityBloodAlertMap.class );
            intent.putExtra( "userId", userId );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            getBaseContext().startActivity( intent );
        } else if (position == 5) {
            Intent intent = new Intent( getBaseContext(), UserSettingsActivity.class );
            intent.putExtra( "userId", userId );
            intent.putExtra( "userTypeId", userTypeId );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            getBaseContext().startActivity( intent );
        } else if (position == 6) {
            SharedPreferences preferences = getSharedPreferences( "VitaeUserSession", Context.MODE_PRIVATE );
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            finish();
        }
        mDrawerLayout.closeDrawer( mDrawerPane );
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale( this,
                    Manifest.permission.ACCESS_FINE_LOCATION )) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder( this )
                        .setTitle( "Location Permission Needed" )
                        .setMessage( "This app needs the Location permission, please accept to use location functionality" )
                        .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions( MenuActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        } )
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions( this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission( this,
                            Manifest.permission.ACCESS_FINE_LOCATION )
                            == PackageManager.PERMISSION_GRANTED) {
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText( this, "permission denied", Toast.LENGTH_LONG ).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.uimodule.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.github.fabtransitionactivity.SheetLayout;
import com.github.fabtransitionactivity.SheetLayout.OnFabAnimationEndListener;
import com.lavie.users.R;
import com.project.uimodule.main.message.FragmentMessage;
import com.project.uimodule.main.profile.FragmentProfile;
import com.project.uimodule.main.timeline.FragmentTimeline;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity implements OnFabAnimationEndListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String userId;

    @BindView(R.id.menu_post_sheet)
    SheetLayout mSheetLayout;
    @BindView(R.id.menu_FAB)
    FloatingActionButton mFab;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );

        viewPager = (ViewPager) findViewById( R.id.viewpager );
        setupViewPager( viewPager );

        tabLayout = (TabLayout) findViewById( R.id.tabs );
        tabLayout.setupWithViewPager( viewPager );
        setupTabIcons();

        ButterKnife.bind( this );
        mSheetLayout.setFab( mFab );
        mSheetLayout.setFabAnimationEndListener( this );
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
            PostActivity.userId=userId;
            Intent intent = new Intent( this, PostActivity.class );
            startActivityForResult( intent, REQUEST_CODE );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult( requestCode, resultCode, data );
            if (requestCode == REQUEST_CODE) {
                mSheetLayout.contractFab();
            }
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
        adapter.addFrag( new FragmentMessage(), "Message" );
        adapter.addFrag( new FragmentProfile( userId ), "Profile" );
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

            // return null to display only the icon
            return null;
        }
    }
}

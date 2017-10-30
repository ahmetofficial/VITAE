// Developer: Ahmet Kaymak
// Date: 04.03.2016

package com.project.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ahmetkaymak.vitae.R;
import com.project.ui.BaseActivity;
import com.project.ui.signup.doctor.FragmentDoctorSignUpOne;
import com.project.ui.signup.doctor.FragmentDoctorSignUpThree;
import com.project.ui.signup.doctor.FragmentDoctorSignUpTwo;
import com.project.ui.signup.patient.FragmentPatientSignUpOne;
import com.project.ui.signup.patient.FragmentPatientSignUpTwo;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignUpUserActivity extends BaseActivity {

    private ViewPager viewPager;
    private PageIndicatorView pageIndicatorView;
    private int userTypeId;

    //for patient user
    private FragmentPatientSignUpOne signUpPatientOne;
    private FragmentPatientSignUpTwo signUpPatientTwo;
    private String username;
    private String userId;
    private String mail;
    private String password;
    private int gender;
    private int blood;
    private Date birthday;

    //for doctor user
    private FragmentDoctorSignUpOne signUpDoctorOne;
    private FragmentDoctorSignUpTwo signUpDoctorTwo;
    private FragmentDoctorSignUpThree signUpDoctorThree;

    //for hospital user

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );
        viewPager = (ViewPager) findViewById( R.id.signUp_viewpager );
        pageIndicatorView = (PageIndicatorView) findViewById( R.id.page_indicator_view );

        Intent myIntent = getIntent();
        userTypeId = myIntent.getIntExtra( "userTypeId", 0 );

        if (userTypeId == 1) {
            setupViewPagerForPatient( viewPager );
            pageIndicatorView.setViewPager( viewPager );
        } else if (userTypeId == 2) {
            setupViewPagerForDoctor( viewPager );
            pageIndicatorView.setViewPager( viewPager );
        } else if (userTypeId == 3) {
            setupViewPagerForPatient( viewPager );
        }

        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1 && userTypeId == 1) {
                    username = signUpPatientOne.getUsername();
                    userId = signUpPatientOne.getUserId();
                    mail = signUpPatientOne.getMail();
                    password = signUpPatientOne.getPassword();
                    signUpPatientTwo.setUserFields( username, userId, mail, password );
                }
                if (position == 2 && userTypeId == 2) {
                    username = signUpDoctorOne.getUsername();
                    userId = signUpDoctorOne.getUserId();
                    mail = signUpDoctorOne.getMail();
                    password = signUpDoctorOne.getPassword();
                    gender = signUpDoctorTwo.getGender();
                    blood = signUpDoctorTwo.getBlood();
                    birthday = signUpDoctorTwo.getBirthday();
                    signUpDoctorThree.setUserFields( username, userId, mail, password, gender, blood, birthday );
                }
            }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.onPageSelected( position );
                if (position == 1 && userTypeId == 1) {
                    username = signUpPatientOne.getUsername();
                    userId = signUpPatientOne.getUserId();
                    mail = signUpPatientOne.getMail();
                    password = signUpPatientOne.getPassword();
                    signUpPatientTwo.setUserFields( username, userId, mail, password );
                }
                if (position == 2 && userTypeId == 2) {
                    username = signUpDoctorOne.getUsername();
                    userId = signUpDoctorOne.getUserId();
                    mail = signUpDoctorOne.getMail();
                    password = signUpDoctorOne.getPassword();
                    gender = signUpDoctorTwo.getGender();
                    blood = signUpDoctorTwo.getBlood();
                    birthday = signUpDoctorTwo.getBirthday();
                    signUpDoctorThree.setUserFields( username, userId, mail, password, gender, blood, birthday );
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        } );
        if (userTypeId == 1) {
            pageIndicatorView.setCount( 2 );
        } else if (userTypeId == 2) {
            pageIndicatorView.setCount( 3 );
        }
        pageIndicatorView.setAnimationType( AnimationType.WORM );
    }

    private void setupViewPagerForPatient(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        signUpPatientOne = new FragmentPatientSignUpOne();
        signUpPatientTwo = new FragmentPatientSignUpTwo();
        adapter.addFrag( signUpPatientOne, "One" );
        adapter.addFrag( signUpPatientTwo, "Two" );
        viewPager.setAdapter( adapter );
    }

    private void setupViewPagerForDoctor(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        signUpDoctorOne = new FragmentDoctorSignUpOne();
        signUpDoctorTwo = new FragmentDoctorSignUpTwo();
        signUpDoctorThree = new FragmentDoctorSignUpThree();
        adapter.addFrag( signUpDoctorOne, "One" );
        adapter.addFrag( signUpDoctorTwo, "Two" );
        adapter.addFrag( signUpDoctorThree, "Three" );
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


// Developer: Ahmet Kaymak
// Date: 04.03.2016

package com.project.uimodule.signup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lavie.users.R;
import com.project.uimodule.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.github.kshitij_jain.indicatorview.IndicatorView;

public class SignUpActivity extends BaseActivity{

    private ViewPager viewPager;
    private String username;
    private String userId;
    private String mail;
    private String password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        viewPager = (ViewPager) findViewById( R.id.signUp_viewpager );
        setupViewPager( viewPager );

        final IndicatorView mIndicatorView = (IndicatorView) findViewById( R.id.signUp_indicator_view );
        mIndicatorView.setPageIndicators( 2 );
        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==1){

                    /*
                    EditText txt_username = (EditText) signUp1.getView().findViewById( R.id.txt_signup_userName );
                    EditText txt_userId = (EditText) signUp1.getView().findViewById( R.id.txt_signup_userId );
                    EditText txt_mail = (EditText) signUp1.getView().findViewById( R.id.txt_signup_mail );
                    EditText txt_password = (EditText) signUp1.getView().findViewById( R.id.txt_signup_password );

                    userId = txt_userId.getText().toString().trim();
                    username = txt_username.getText().toString().trim();
                    mail = txt_mail.getText().toString().trim();
                    password = txt_password.getText().toString().trim();
*/
                    Bundle bundle = new Bundle();
                    bundle.putString("user_id", userId);
                    bundle.putString("user_name", username);
                    bundle.putString("mail", mail);
                    bundle.putString("password", password);
                    FragmentTwo fragmentTwo = new FragmentTwo();
                    fragmentTwo.setArguments(bundle);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mIndicatorView.setCurrentPage( position );
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        } );
        mIndicatorView.setActiveIndicatorColor( R.color.colorAccent );
        mIndicatorView.setInactiveIndicatorColor( R.color.colorPrimary );

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        adapter.addFrag( new FragmentOne(), "One" );
        adapter.addFrag( new FragmentTwo(), "Two" );
        viewPager.setAdapter( adapter );
    }

    public void setPatientFields(String userId,String username,String mail,String password){
        this.userId=userId;
        this.username=username;
        this.mail=mail;
        this.password=password;
    }
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


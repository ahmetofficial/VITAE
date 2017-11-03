// Developer: Ahmet Kaymak
// Date: 26.10.2017

package com.project.ui.signup.doctor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmetkaymak.vitae.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.utils.DatePickerFragment;

import java.util.Date;

public class FragmentDoctorSignUpTwo extends Fragment {

    private int gender;
    private int blood;
    private Date birthday=new Date();
    private TextView birthdayText;
    private MaterialSpinner genderSpinner;
    private MaterialSpinner bloodSpinner;
    private DatePickerFragment newFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View signUp = inflater.inflate( R.layout.activity_signup_doctor_two, container, false );

        birthdayText = (TextView) signUp.findViewById( R.id.txt_signup_birthday );
        genderSpinner = (MaterialSpinner) signUp.findViewById( R.id.signUp_gender_spinner );
        bloodSpinner = (MaterialSpinner) signUp.findViewById( R.id.signUp_blood_spinner );

        newFragment = new DatePickerFragment( birthdayText, birthday );

        genderSpinner.setItems( getString( R.string.male ), getString( R.string.female ), getString( R.string.undefined ) );
        genderSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                gender = position;
            }
        } );

        bloodSpinner.setItems( getString( R.string.undefined ), "0 Rh+", "0 Rh-", "A Rh+", "A Rh-", "B Rh+", "B Rh-", "AB Rh+", "AB Rh-" );
        bloodSpinner.setDropdownHeight( 600 );
        bloodSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                blood = position;
            }
        } );

        birthdayText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newFragment.show( getFragmentManager(), "Birthday" );
            }
        } );

        return signUp;
    }

    public int getGender() {
        return gender;
    }

    public int getBlood() {
        return blood;
    }

    public Date getBirthday() {
        return newFragment.getDate();
    }
}


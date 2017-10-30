// Developer: Ahmet Kaymak
// Date: 26.10.2017

package com.project.ui.signup.doctor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.ahmetkaymak.vitae.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;
import java.util.Date;

public class FragmentDoctorSignUpTwo extends Fragment {

    private int gender;
    private int blood;
    private Date birthday;
    private TextView birthdayText;
    private MaterialSpinner genderSpinner;
    private MaterialSpinner bloodSpinner;

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

                class DatePickerFragment extends DialogFragment
                        implements DatePickerDialog.OnDateSetListener {

                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        // Use the current date as the default date in the picker
                        final Calendar c = Calendar.getInstance();
                        int year = c.get( Calendar.YEAR ) - 2000;
                        int month = c.get( Calendar.MONTH );
                        int day = c.get( Calendar.DAY_OF_MONTH );
                        return new DatePickerDialog( getActivity(), this, year, month, day );
                    }

                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        birthdayText.setTextSize( 13 );
                        birthdayText.setText( day + "." + month + "." + year );
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, day);
                        cal.set(Calendar.HOUR_OF_DAY, 0);
                        cal.set(Calendar.MINUTE, 0);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                        birthday = cal.getTime();
                    }
                }

                DialogFragment newFragment = new DatePickerFragment();
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
        return birthday;
    }
}


// Developer: Ahmet Kaymak
// Date: 05.03.2016

package com.project.uimodule.signup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lavie.users.R;
import com.project.usermodule.Patient;

import java.util.Calendar;
import java.util.Date;

public class FragmentOne extends Fragment {

    private EditText txt_username;
    private EditText txt_userId;
    private EditText txt_mail;
    private EditText txt_password;
    private EditText txt_bithday;
    private int gender;
    private int blood;
    private String username;
    private String userId;
    private String mail;
    private String password;
    private Date birthday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View signUp1 = inflater.inflate( R.layout.fragment_signup, container, false );

        MaterialSpinner genderSpinner = (MaterialSpinner) signUp1.findViewById( R.id.signUp_gender_spinner );
        genderSpinner.setItems( getString( R.string.male ), getString( R.string.female ), getString( R.string.undefined ) );
        genderSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                gender = position;
            }
        } );

        MaterialSpinner bloodSpinner = (MaterialSpinner) signUp1.findViewById( R.id.signUp_blood_spinner );
        bloodSpinner.setItems( getString( R.string.undefined ), "0 Rh+", "0 Rh-", "A Rh+", "A Rh-", "B Rh+", "B Rh-", "AB Rh+", "AB Rh-" );
        bloodSpinner.setDropdownHeight( 600 );
        bloodSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                blood = position;
            }
        } );

        txt_bithday = (EditText) (EditText) signUp1.findViewById( R.id.txt_signup_birthday );
        txt_bithday.setOnClickListener( new View.OnClickListener() {
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
                        txt_bithday.setTextSize( 13 );
                        txt_bithday.setText( day + "." + month + "." + year );
                        birthday = new Date( year, month, day );
                    }
                }

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show( getFragmentManager(), "Birthday" );

            }
        } );

        Button btn_createProfile = (Button) (Button) signUp1.findViewById( R.id.create_profile );
        btn_createProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_username = (EditText) signUp1.findViewById( R.id.txt_signup_userName );
                txt_userId = (EditText) signUp1.findViewById( R.id.txt_signup_userId );
                txt_mail = (EditText) signUp1.findViewById( R.id.txt_signup_mail );
                txt_password = (EditText) signUp1.findViewById( R.id.txt_signup_password );

                userId = txt_userId.getText().toString().trim();
                username = txt_username.getText().toString().trim();
                mail = txt_mail.getText().toString().trim();
                password = txt_password.getText().toString().trim();

                try {
                    Patient patient = new Patient();
                    patient.setUserId( userId );
                    patient.setUserName( username );
                    patient.setMail( mail );
                    patient.setPassword( password );
                    patient.setPhoneNumber( null );
                    patient.setAboutMe( null );
                    patient.setProfilePictureId( "" );
                    patient.setGender( gender );
                    patient.setBloodTypeId( blood );
                    patient.setBirthday( birthday );
                    Patient.createPatient( patient, FragmentOne.this );
                } catch (Exception e) {
                    Log.e( "UserTimeline", e.getMessage() );
                    Toast.makeText( getActivity(), e.getMessage(), Toast.LENGTH_LONG ).show();
                }

            }

        } );

        return signUp1;
    }
}


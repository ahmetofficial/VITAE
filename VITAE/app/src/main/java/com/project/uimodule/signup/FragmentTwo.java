// Developer: Ahmet Kaymak
// Date: 05.03.2016

package com.project.uimodule.signup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lavie.users.R;
import com.project.generalhealthmodule.BloodType;
import com.project.usermodule.Patient;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FragmentTwo extends Fragment {

    private Calendar birthday = new GregorianCalendar();
    private String username;
    private String userId;
    private String mail;
    private String password;
    private String gender;
    private String blood;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View signUp2 = inflater.inflate( R.layout.fragment_signup_two, container, false );

        MaterialSpinner genderSpinner = (MaterialSpinner) signUp2.findViewById( R.id.signUp_gender_spinner );
        genderSpinner.setItems( getString( R.string.undefined ), getString( R.string.male ), getString( R.string.female ) );
        genderSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                gender = item;
            }
        } );

        MaterialSpinner bloodSpinner = (MaterialSpinner) signUp2.findViewById( R.id.signUp_blood_spinner );
        bloodSpinner.setItems( getString( R.string.undefined ), "0 Rh+", "0 Rh-", "A Rh+", "A Rh-", "B Rh+", "B Rh-", "AB Rh+", "AB Rh-" );
        bloodSpinner.setDropdownHeight( 600 );
        bloodSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                blood = item;
            }
        } );

        final EditText birthdayText = (EditText) (EditText) signUp2.findViewById( R.id.txt_signup_birthday );
        birthdayText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class DatePickerFragment extends DialogFragment
                        implements DatePickerDialog.OnDateSetListener {

                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        // Use the current date as the default date in the picker
                        final Calendar c = Calendar.getInstance();
                        int year = c.get( Calendar.YEAR );
                        int month = c.get( Calendar.MONTH );
                        int day = c.get( Calendar.DAY_OF_MONTH );
                        return new DatePickerDialog( getActivity(), this, year, month, day );
                    }

                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        birthdayText.setTextSize( 13 );
                        birthdayText.setText( day + "." + month + "." + year );
                        birthday.set( year, month, day );
                    }
                }

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show( getFragmentManager(), "Birthday" );

            }
        } );

        Button btn_createProfile = (Button) (Button) signUp2.findViewById( R.id.create_profile );
        btn_createProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userId = getArguments().getString( "user_id" );
                username = getArguments().getString( "user_name" );
                mail = getArguments().getString( "mail" );
                password = getArguments().getString("password");

                Patient patient = new Patient();
                patient.setUserId( userId );
                patient.setUserName( username );
                patient.setMail( mail );
                patient.setPassword( password );
                patient.setPhoneNumber( null );
                patient.setAboutMe( null );
                patient.setProfilePictureId( "" );
                patient.setGender( gender );
                String bloodTypeName = blood.split( " " )[0];
                String rhFactor = blood.split( " " )[1];
                BloodType bt = new BloodType( bloodTypeName, rhFactor );
                //patient.setBloodTypeId(BloodType.getBloodTypeId(bt));
                patient.setBloodTypeId( 1 );
                patient.setBirthday( birthday.toString() );

                Patient.createPatient( patient, FragmentTwo.this );
            }

        } );
        return signUp2;
    }

}

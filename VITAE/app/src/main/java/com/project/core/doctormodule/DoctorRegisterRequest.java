// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.doctormodule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.core.usermodule.User;

import java.util.Date;

public class DoctorRegisterRequest extends User {
    @SerializedName("gender")
    @Expose
    private int gender;

    @SerializedName("blood_type_id")
    @Expose
    private int bloodTypeId;

    @SerializedName("birthday")
    @Expose
    private Date birthday;

    @SerializedName("hospital_id")
    @Expose
    private int hospitalId;

    @SerializedName("clinic_id")
    @Expose
    private int clinicId;

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setBloodTypeId(int bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

}

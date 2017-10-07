// Developer: Ahmet Kaymak
// Date: 10.03.2016

package com.project.core.usermodule;

import java.util.Date;

public class Institute extends User {
    //This type of user also represent organizations and other legal entities apert from just institutes.
    private int instituteTypeId;
    private int focusAreaId;
    private Date establishementDate;
    private String website;

}

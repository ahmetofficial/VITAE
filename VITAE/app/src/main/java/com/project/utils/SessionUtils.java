// Developer: Ahmet Kaymak
// Date: 26.08.2017

package com.project.utils;

public class SessionUtils {

    private static String userId;

    private static int userTypeId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        SessionUtils.userId = userId;
    }

    public static int getUserTypeId() {
        return userTypeId;
    }

    public static void setUserTypeId(int userTypeId) {
        SessionUtils.userTypeId = userTypeId;
    }

}

// Developer: Ahmet Kaymak
// Date: 26.08.2017

package com.project.utils;

public class SessionUtils {

    private static String userId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        SessionUtils.userId = userId;
    }

}

// Developer: Ahmet Kaymak
// Date: 01.10.2017

package com.project.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class LocationUtils {

    public static boolean checkLocationPermission(Context context)
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

}

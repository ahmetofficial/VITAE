// Developer: Ahmet Kaymak
// Date: 20.08.2017

package com.project.utils;

import android.content.Context;
import android.graphics.Typeface;

public class Typefaces {

    private static Typeface RobotoLight;
    private static Typeface RobotoMedium;
    private static Typeface RobotoBold;

    public static Typeface getRobotoLight(Context context) {
        RobotoLight = Typeface.createFromAsset( context.getAssets(), "fonts/Roboto-Light.ttf" );
        return RobotoLight;
    }

    public static Typeface getRobotoMedium(Context context) {
        RobotoMedium = Typeface.createFromAsset( context.getAssets(), "fonts/Roboto-Medium.ttf" );
        return RobotoMedium;
    }

    public static Typeface getRobotoBold(Context context) {
        RobotoBold=Typeface.createFromAsset( context.getAssets(), "fonts/Roboto-Bold.ttf" );
        return RobotoBold;
    }
}

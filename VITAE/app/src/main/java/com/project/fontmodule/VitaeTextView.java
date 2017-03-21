// Developer: Ahmet Kaymak
// Date: 12.03.2016

package com.project.fontmodule;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class VitaeTextView extends android.support.v7.widget.AppCompatTextView {

    public VitaeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public VitaeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VitaeTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Fonts/Roboto-Thin.ttf");
            setTypeface(tf);
        }
    }

}
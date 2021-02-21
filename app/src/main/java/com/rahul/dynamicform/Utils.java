package com.rahul.dynamicform;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class Utils {

    private static final String TAG = Utils.class.getName();

    public static void startActivityWithFinish(Activity context, Class<?> aClass) {
        try {
            context.startActivity(new Intent(context, aClass));
            context.finish();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }
}

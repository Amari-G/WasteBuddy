package com.example.wastebuddy;

import android.app.Activity;
import android.content.Intent;

import com.example.wastebuddy.activities.MainActivity;
import com.example.wastebuddy.activities.SignUpActivity;

public class Navigation {

    public static void goMainActivity(Activity activity) {
        Intent i = new Intent(activity, MainActivity.class);
        activity.startActivity(i);
        activity.finish();
    }

    public static void goSignUpActivity(Activity activity) {
        Intent i = new Intent(activity, SignUpActivity.class);
        activity.startActivity(i);
        activity.finish();
    }
}
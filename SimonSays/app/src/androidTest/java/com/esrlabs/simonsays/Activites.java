package com.esrlabs.simonsays;

import android.app.Activity;
import android.content.Intent;
import org.robolectric.Robolectric;


public class Activites {

    public static Intent nextActivity(Activity activity) {
        return Robolectric.shadowOf(activity).getNextStartedActivity();
    }
}





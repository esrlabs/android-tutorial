package com.esrlabs.simonsays.helpers;

import android.app.Activity;
import android.content.Intent;
import org.robolectric.Robolectric;


public class Activites {

  public static Intent nextActivity(Activity activity) {
    return Robolectric.shadowOf(activity).getNextStartedActivity();
  }
}





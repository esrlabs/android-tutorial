package com.esrlabs.simonsays.helpers;

import android.content.Intent;

import static com.esrlabs.simonsays.NewGameActivity.EXTRA_LEVEL;

public class Intents {
  public static Intent forLevel(int level) {
    Intent intent = new Intent();
    intent.putExtra(EXTRA_LEVEL, level);
    return intent;
  }
}

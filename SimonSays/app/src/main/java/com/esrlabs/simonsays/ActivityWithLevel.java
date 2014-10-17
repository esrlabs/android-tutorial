package com.esrlabs.simonsays;

import android.app.Activity;
import android.content.Intent;

import static com.esrlabs.simonsays.NewGameActivity.EXTRA_LEVEL;

public class ActivityWithLevel extends Activity {

    protected int level() {
      return getIntent().getIntExtra(EXTRA_LEVEL, NewGameActivity.DEFAULT_LEVEL);
    }

    @Override
    public void startActivity(Intent intent) {
        intent.putExtra(NewGameActivity.EXTRA_LEVEL, level());
        super.startActivity(intent);
    }

}

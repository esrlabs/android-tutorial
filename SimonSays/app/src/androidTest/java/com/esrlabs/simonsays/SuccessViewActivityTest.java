package com.esrlabs.simonsays;

import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.esrlabs.simonsays.NewGameActivity.EXTRA_LEVEL;
import static com.esrlabs.simonsays.helpers.Activites.nextActivity;
import static com.esrlabs.simonsays.helpers.Intents.forLevel;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.buildActivity;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class SuccessViewActivityTest {
  private SuccessViewActivity successViewActivity;
  private int currentLevel = 3;

  @Before
  public void setUp() throws Exception {
    successViewActivity = buildActivity(SuccessViewActivity.class).withIntent(forLevel(currentLevel)).create().get();
  }

  @Test
  public void tapStartsNewGameOnNextLevel() throws Exception {
    successViewActivity.findViewById(R.id.successView).callOnClick();
    Intent intent = new Intent(successViewActivity, PatternPlaybackActivity.class);
    intent.putExtra(EXTRA_LEVEL, currentLevel + 1);
    assertThat(nextActivity(successViewActivity), is(intent));
  }

}


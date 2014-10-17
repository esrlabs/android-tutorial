package com.esrlabs.simonsays;

import android.app.Activity;
import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static com.esrlabs.simonsays.Activites.nextActivity;
import static com.esrlabs.simonsays.ColorInputActivity.EXTRA_PATTERN;
import static com.esrlabs.simonsays.NewGameActivity.EXTRA_LEVEL;
import static com.esrlabs.simonsays.PatternColor.GREEN;
import static com.esrlabs.simonsays.PatternColor.RED;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class SuccessViewActivityTest {

    private SuccessViewActivity successViewActivity;
    private int currentLevel = 3;

    @Before
    public void setUp() throws Exception {
        successViewActivity = Robolectric.buildActivity(SuccessViewActivity.class).create().get();
    }

    @Test
    public void tapStartsNewGameOnNextLevel() throws Exception {
        successViewActivity.findViewById(R.id.successView).callOnClick();
        Intent intent = new Intent(successViewActivity, PatternPlaybackActivity.class);
        intent.putExtra(EXTRA_LEVEL, currentLevel + 1);
        assertThat(Activites.nextActivity(successViewActivity), is(intent));
    }

}


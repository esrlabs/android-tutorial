package com.esrlabs.simonsays;

import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.esrlabs.simonsays.NewGameActivity.EXTRA_LEVEL;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class FailViewActivityTest {

    private FailViewActivity failViewActivity;
    private int currentLevel = 3;

    @Before
    public void setUp() throws Exception {
        failViewActivity = Robolectric.buildActivity(FailViewActivity.class).create().get();
    }

    @Test
    public void tapStartsNewGameOnNextLevel() throws Exception {
        failViewActivity.findViewById(R.id.failView).callOnClick();
        Intent intent = new Intent(failViewActivity, PatternPlaybackActivity.class);
        intent.putExtra(EXTRA_LEVEL, currentLevel);
        assertThat(Activites.nextActivity(failViewActivity), is(intent));
    }

}


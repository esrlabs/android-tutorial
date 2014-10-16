package com.esrlabs.simonsays;

import android.content.Intent;
import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.robolectric.Robolectric.shadowOf;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class NewGameActivityTest {

    private NewGameActivity newGameActivity;

    @Before
    public void setUp() throws Exception {
        newGameActivity = Robolectric.buildActivity(NewGameActivity.class).create().get();

    }

    @Test
    public void newGameButtonStartsPatternPlaybackForLevelThree() throws Exception {
        View startButton = newGameActivity.findViewById(R.id.startGameButton);
        startButton.callOnClick();
        Intent expectedIntent = new Intent(newGameActivity, PatternPlaybackActivity.class);
        expectedIntent.putExtra("level", 3);
        assertThat(shadowOf(newGameActivity).getNextStartedActivity(), is(expectedIntent));
    }
}

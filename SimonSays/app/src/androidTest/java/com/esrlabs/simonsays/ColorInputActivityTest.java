package com.esrlabs.simonsays;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static com.esrlabs.simonsays.helpers.Activites.nextActivity;
import static com.esrlabs.simonsays.ColorInputActivity.EXTRA_PATTERN;
import static com.esrlabs.simonsays.NewGameActivity.EXTRA_LEVEL;
import static com.esrlabs.simonsays.PatternColor.GREEN;
import static com.esrlabs.simonsays.PatternColor.RED;
import static com.esrlabs.simonsays.helpers.Activites.nextActivity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

@Config(manifest = "src/main/AndroidManifest.xml", resourceDir = "res", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ColorInputActivityTest {

    private ColorInputActivity colorInputActivity;
    private int currentLevel = 3;

    @Before
    public void setUp() throws Exception {
        colorInputActivity = new ColorInputActivity();
    }

    @Test
    public void showsCurrentLevel() throws Exception {
        startActivityWith(Pattern.of(RED));
        TextView levelView = (TextView) colorInputActivity.findViewById(R.id.currentLevelLabel);
        assertThat(levelView.getText(), CoreMatchers.<CharSequence>is(String.valueOf(currentLevel)));
    }

    @Test
    public void correctPatternTriggersSuccessActivity() throws Exception {
        startActivityWith(Pattern.of(RED, GREEN));
        pressRed();
        assertNoActivitTriggered();
        pressGreen();
        assertNextActivityIs(SuccessViewActivity.class);
    }

    @Test
    public void invalidPatternTriggersFailActivity() throws Exception {
        startActivityWith(Pattern.of(RED));
        pressGreen();
        assertNextActivityIs(FailViewActivity.class);
    }

    private void assertNextActivityIs(Class<? extends Activity> activityClass) {
        Intent expectedIntent = new Intent(colorInputActivity, activityClass);
        expectedIntent.putExtra(EXTRA_LEVEL, currentLevel);
        assertThat(nextActivity(colorInputActivity), is(expectedIntent));
    }

    private void startActivityWith(Pattern pattern) {
        Intent onlyRedPattern = new Intent();
        onlyRedPattern.putExtra(EXTRA_PATTERN, pattern.toArray());
        onlyRedPattern.putExtra(EXTRA_LEVEL, currentLevel);
        ActivityController.of(colorInputActivity).withIntent(onlyRedPattern).create().start().visible();
    }

    private void assertNoActivitTriggered() {
        assertThat(shadowOf(colorInputActivity).getNextStartedActivity(), is(nullValue()));
    }

    private void pressGreen() {
        colorInputActivity.findViewById(R.id.button_green).callOnClick();
    }

    private void pressRed() {
        colorInputActivity.findViewById(R.id.button_red).callOnClick();
    }

}

package com.esrlabs.simonsays;

import android.content.Intent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ColorInputActivityTest {

    private ColorInputActivity colorInputActivity;

    @Test
    public void correctPatternTriggersSuccessActivity() throws Exception {
        colorInputActivity = new ColorInputActivity();
        Intent onlyRedPattern = new Intent();
        onlyRedPattern.putExtra(ColorInputActivity.EXTRA_PATTERN, Pattern.of(PatternColor.RED, PatternColor.GREEN).toArray());
        ActivityController.of(colorInputActivity).withIntent(onlyRedPattern).create().start().visible();
        pressRed();
        assertNoActivitTriggered();
        pressGreen();
        Intent expectedIntent = new Intent(colorInputActivity, SuccessViewActivity.class);
        assertThat(shadowOf(colorInputActivity).getNextStartedActivity(), is(expectedIntent));
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

    @Test
    public void invalidPatternTriggersFailActivity() throws Exception {
        colorInputActivity = new ColorInputActivity();
        Intent onlyRedPattern = new Intent();
        onlyRedPattern.putExtra(ColorInputActivity.EXTRA_PATTERN, Pattern.of(PatternColor.RED).toArray());
        ActivityController.of(colorInputActivity).withIntent(onlyRedPattern).create().start().visible();
        pressGreen();
        Intent expectedIntent = new Intent(colorInputActivity, FailViewActivity.class);
        assertThat(shadowOf(colorInputActivity).getNextStartedActivity(), is(expectedIntent));
    }
}
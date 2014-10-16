package com.esrlabs.simonsays;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.robolectric.Robolectric.shadowOf;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class PatternPlaybackActivityTest {

  private PatternGenerator patternGenerator = mock(PatternGenerator.class);
  private PatternPlaybackActivity patternPlaybackActivity;
  private ActivityController<PatternPlaybackActivity> controller;
  private ManualScheduler scheduler = new ManualScheduler();

  @Before
  public void setUp() throws Exception {
    patternPlaybackActivity = new PatternPlaybackActivity(patternGenerator, scheduler);
    controller = ActivityController.of(patternPlaybackActivity);
  }

  @Test
  public void generatePatternOfSpecifiedLevel() throws Exception {
    when(patternGenerator.generatePattern(anyInt())).thenReturn(Pattern.of(PatternColor.BLUE));
    int aGameLevel = 3;
    Intent levelThree = new Intent();
    levelThree.putExtra("level", aGameLevel);
    controller.withIntent(levelThree).create().start().visible();
    verify(patternGenerator).generatePattern(aGameLevel);
  }

  @Test
  public void patternPlaybackShowsANewColorEverySecond() throws Exception {
    when(patternGenerator.generatePattern(anyInt())).thenReturn(Pattern.of(PatternColor.BLUE, PatternColor.GREEN));
    controller.create().start().visible();
    assertThat(scheduler.getFrequencyInMs(), is(1000));
    assertBackgroundIs(WHITE);
    scheduler.runNext();
    assertBackgroundIs(BLUE);
    scheduler.runNext();
    assertBackgroundIs(GREEN);
  }

  @Test
  public void patternPlaybackSwitchesToColorInput() throws Exception {
    Pattern pattern = Pattern.of(PatternColor.BLUE);
    when(patternGenerator.generatePattern(anyInt())).thenReturn(pattern);
    controller.create().start().visible();
    scheduler.runNext();
    scheduler.runNext();
    Intent expectedIntent = new Intent(patternPlaybackActivity, ColorInputActivity.class);
    expectedIntent.putExtra("pattern", pattern.toArray());
    assertThat(shadowOf(patternPlaybackActivity).getNextStartedActivity(), is(expectedIntent));
  }

  private void assertBackgroundIs(int expectedColor) {
    View view = patternPlaybackActivity.findViewById(R.id.patternPlayback);
    ColorDrawable background = (ColorDrawable) view.getBackground();
    assertThat(background.getColor(), is(expectedColor));
  }

}

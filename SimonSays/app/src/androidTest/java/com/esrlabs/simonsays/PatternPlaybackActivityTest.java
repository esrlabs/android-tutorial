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
import static com.esrlabs.simonsays.Activites.nextActivity;
import static com.esrlabs.simonsays.ColorInputActivity.EXTRA_PATTERN;
import static com.esrlabs.simonsays.NewGameActivity.EXTRA_LEVEL;
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
  private int currentLevel = 3;

  @Before
  public void setUp() throws Exception {
    patternPlaybackActivity = new PatternPlaybackActivity(patternGenerator, scheduler);
    controller = ActivityController.of(patternPlaybackActivity);
    Intent levelThree = new Intent();
    levelThree.putExtra(EXTRA_LEVEL, currentLevel);
    controller.withIntent(levelThree).create();
  }

  @Test
  public void generatePatternOfSpecifiedLevel() throws Exception {
    when(patternGenerator.generatePattern(anyInt())).thenReturn(Pattern.of(PatternColor.BLUE));
    controller.start().visible();
    verify(patternGenerator).generatePattern(currentLevel);
  }

  @Test
  public void patternPlaybackShowsANewColorEverySecond() throws Exception {
    when(patternGenerator.generatePattern(anyInt())).thenReturn(Pattern.of(PatternColor.BLUE, PatternColor.GREEN));
    controller.start().visible();
    assertThat(scheduler.getFrequencyInMs(), is(1000));
    assertBackgroundIs(WHITE);
    scheduler.runNext();
    assertBackgroundIs(BLUE);
    scheduler.runNext();
    assertBackgroundIs(WHITE);
    scheduler.runNext();
    assertBackgroundIs(GREEN);
  }

  @Test
  public void patternPlaybackSwitchesToColorInput() throws Exception {
    Pattern pattern = Pattern.of(PatternColor.BLUE);
    when(patternGenerator.generatePattern(anyInt())).thenReturn(pattern);
    controller.start().visible();
    scheduler.runNext();
    scheduler.runNext();
    scheduler.runNext();
    Intent expectedIntent = new Intent(patternPlaybackActivity, ColorInputActivity.class);
    expectedIntent.putExtra(EXTRA_PATTERN, pattern.toArray());
    expectedIntent.putExtra(EXTRA_LEVEL, currentLevel);
    assertThat(nextActivity(patternPlaybackActivity), is(expectedIntent));
  }

  private void assertBackgroundIs(int expectedColor) {
    View view = patternPlaybackActivity.findViewById(R.id.patternPlayback);
    ColorDrawable background = (ColorDrawable) view.getBackground();
    assertThat(background.getColor(), is(expectedColor));
  }

}

package com.esrlabs.simonsays;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.util.ActivityController;

import static android.graphics.Color.BLUE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class PatternPlaybackActivityTest {

  private PatternGenerator patternGenerator = mock(PatternGenerator.class);
  private PatternPlaybackActivity patternPlaybackActivity;
  private ActivityController<PatternPlaybackActivity> controller;

  @Before
  public void setUp() throws Exception {
    ShadowLog.stream = System.out;
    patternPlaybackActivity = new PatternPlaybackActivity(patternGenerator);
    controller = ActivityController.of(patternPlaybackActivity);
  }

  @Test
  public void generatePatternOfSpecifiedLevel() throws Exception {
    int aGameLevel = 3;
    Intent levelThree = new Intent();
    levelThree.putExtra("level", aGameLevel);
    controller.withIntent(levelThree).create().start().visible();
    verify(patternGenerator).generatePattern(aGameLevel);
  }

  @Test
  public void givenPatternOfLength1LabelShowsGivenColor() throws Exception {
    when(patternGenerator.generatePattern(anyInt())).thenReturn(Pattern.of(PatternColor.BLUE));
    controller.create().start().visible();
    View view = patternPlaybackActivity.findViewById(R.id.test);
    ColorDrawable background = (ColorDrawable) view.getBackground();
    assertThat(background.getColor(), is(BLUE));
  }

}

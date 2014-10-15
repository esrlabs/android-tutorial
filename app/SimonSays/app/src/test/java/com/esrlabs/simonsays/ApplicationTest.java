package com.esrlabs.simonsays;

import android.widget.Button;

import com.java.esrlabs.simonsays.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Config(manifest = "app/src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ApplicationTest {

  private SimonSaysGame game = mock(SimonSaysGame.class);
  private MyActivity myActivity;

  @Before
  public void setup() throws Exception {
    myActivity = new MyActivity(game);
    ActivityController<MyActivity> controller = ActivityController.of(myActivity);
    controller.create().start().visible();
  }

  @Test
  public void startButtonShouldGeneratePatternOfLength1() throws Exception {
    Button startButton = (Button) myActivity.findViewById(R.id.start);
    startButton.callOnClick();
    verify(game).newGame(myActivity, 2);
  }

}

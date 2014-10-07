package com.java.esrlabs.simonsays;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.view.View;
import android.widget.Button;

import com.esrlabs.simonsays.SimonSaysGame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        verify(game).newGame(myActivity, 1);
    }
}
package com.esrlabs.simonsays;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import com.esrlabs.simonsays.concurrent.HandlerBasedPeriodicScheduler;
import com.esrlabs.simonsays.concurrent.PeriodicScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.graphics.Color.WHITE;
import static com.esrlabs.simonsays.ColorInputActivity.EXTRA_PATTERN;
import static com.esrlabs.simonsays.NewGameActivity.EXTRA_LEVEL;


public class PatternPlaybackActivity extends ActivityWithLevel {

  private Map<PatternColor, Integer> colorMapping;
  public static final int FREQUENCY_IN_MS = 500;
  private static final String TAG = "SimonSays";

  private final PatternGenerator patternGenerator;
  private final PeriodicScheduler periodicScheduler;

  public PatternPlaybackActivity() {
    this(PatternGenerator.create(), new HandlerBasedPeriodicScheduler());
  }

  public PatternPlaybackActivity(PatternGenerator patternGenerator, PeriodicScheduler periodicScheduler) {
    this.patternGenerator = patternGenerator;
    this.periodicScheduler = periodicScheduler;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    colorMapping = new HashMap<PatternColor, Integer>() {{
      put(PatternColor.GREEN, getResources().getColor(R.color.green));
      put(PatternColor.RED, getResources().getColor(R.color.red));
      put(PatternColor.BLUE, getResources().getColor(R.color.blue));
    }};
    setContentView(R.layout.activity_pattern_playback);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_pattern_playback, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onStart() {
    super.onStart();
    setBackground(WHITE);
    List<Runnable> playback = new ArrayList<Runnable>();
    Pattern patternColors = patternGenerator.generatePattern(level());
    showEachColor(patternColors, playback);
    changeToColorInput(patternColors, playback);
    periodicScheduler.schedule(playback, FREQUENCY_IN_MS);
  }

  private void changeToColorInput(final Pattern patternColors, List<Runnable> playback) {
    playback.add(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(PatternPlaybackActivity.this, ColorInputActivity.class);
        intent.putExtra(EXTRA_PATTERN, patternColors.toArray());
        intent.putExtra(EXTRA_LEVEL, level());
        startActivity(intent);
      }
    });
  }

  private void showEachColor(Pattern patternColors, List<Runnable> playback) {
    for (final PatternColor patternColor : patternColors) {
      showColor(playback, colorMapping.get(patternColor));
      showColor(playback, WHITE);
    }
  }

  private void showColor(List<Runnable> playback, final int color) {
    playback.add(new Runnable() {
      @Override
      public void run() {
        Log.i(TAG, "Set color to " + color);
        setBackground(color);
      }
    });
  }

  private void setBackground(int color) {
    findViewById(R.id.patternPlayback).setBackgroundColor(color);
  }

}

package com.esrlabs.simonsays;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;


public class PatternPlaybackActivity extends Activity {

  private final PatternGenerator patternGenerator;
  private static final Map<PatternColor, Integer> COLOR_MAPPING = new HashMap<PatternColor, Integer>(){{
    put(PatternColor.BLUE, Color.BLUE);
    put(PatternColor.GREEN, Color.GREEN);
    put(PatternColor.RED, Color.RED);
  }};

  public PatternPlaybackActivity() {
    this(PatternGenerator.create());
  }

  public PatternPlaybackActivity(PatternGenerator patternGenerator) {
    this.patternGenerator = patternGenerator;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
    setBackground(Color.WHITE);
    Pattern patternColors = patternGenerator.generatePattern(level());
    for (PatternColor patternColor :patternColors) {
      Integer color = COLOR_MAPPING.get(patternColor);
      setBackground(color);
    }


  }

  private void setBackground(int color) {
    findViewById(R.id.test).setBackgroundColor(color);
  }

  private int level() {
    return getIntent().getIntExtra("level", 5);
  }
}

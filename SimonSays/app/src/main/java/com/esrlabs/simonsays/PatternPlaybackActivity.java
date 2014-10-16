package com.esrlabs.simonsays;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class PatternPlaybackActivity extends Activity {

  private final PatternGenerator patternGenerator;

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
    findViewById(R.id.test).setBackgroundColor(Color.WHITE);
    patternGenerator.generatePattern(level());
  }

  private int level() {
    return getIntent().getIntExtra("level", 1);
  }
}

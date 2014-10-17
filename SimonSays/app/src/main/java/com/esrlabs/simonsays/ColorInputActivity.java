package com.esrlabs.simonsays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import static com.esrlabs.simonsays.PatternColor.*;


public class ColorInputActivity extends Activity implements GameListener {

    public static final String EXTRA_PATTERN = "pattern";
    private ActivePatternMatching activePatternMatching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_color_input);

        registerOnClickListener(R.id.button_red, RED);
        registerOnClickListener(R.id.button_green, GREEN);
        registerOnClickListener(R.id.button_blue, BLUE);
    }

    private void registerOnClickListener(int button, final PatternColor patternColor) {
        findViewById(button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activePatternMatching.onInput(patternColor);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_input, menu);
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
        Pattern pattern = readPatternFromIntent();
        activePatternMatching = new ActivePatternMatching(pattern, this);
    }

    private Pattern readPatternFromIntent() {
        return Pattern.from(getIntent().getIntArrayExtra(EXTRA_PATTERN));
    }

    @Override
    public void onWin() {
        Intent intent = new Intent(ColorInputActivity.this, SuccessViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoose() {
        Intent intent = new Intent(ColorInputActivity.this, FailViewActivity.class);
        startActivity(intent);

    }
}

package com.esrlabs.simonsays;

import android.os.Handler;

import java.util.Iterator;

public class HandlerBasedSequencer {

  public static HandlerBasedSequencer EVERY_SECOND = new HandlerBasedSequencer(1000);

  private final int frequency;
  private Iterator<Runnable> iterator;

  public HandlerBasedSequencer(int frequency){
    this.frequency = frequency;
  }

  public void run(Iterable<Runnable> runnables){
    iterator = runnables.iterator();
    Handler handler = new Handler();
    start(handler);
  }

  private void start(final Handler handler) {
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        if(!iterator.hasNext()){
          return;
        }
        iterator.next().run();
        start(handler);
      }
    }, frequency);
  }
}

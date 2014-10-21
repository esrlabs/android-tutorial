package com.esrlabs.simonsays.concurrent;

import android.os.Handler;

import java.util.Iterator;

public class HandlerBasedPeriodicScheduler implements PeriodicScheduler {

  @Override
  public void schedule(final Iterable<Runnable> runnables, final int frequencyInMs){
    schedule(runnables.iterator(), frequencyInMs);
  }

  private void schedule(final Iterator<Runnable> runnables, final int frequencyInMs){
    if(!runnables.hasNext()){
      return;
    }
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        runnables.next().run();
        schedule(runnables, frequencyInMs);
      }
    }, frequencyInMs);
  }
}

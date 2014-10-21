package com.esrlabs.simonsays.helpers;

import com.esrlabs.simonsays.concurrent.PeriodicScheduler;

import java.util.Iterator;

public class ManualScheduler implements PeriodicScheduler {

  private Iterator<Runnable> runnables;
  private int frequencyInMs;

  @Override
  public void schedule(Iterable<Runnable> runnables, int frequencyInMs) {
    this.frequencyInMs = frequencyInMs;
    this.runnables = runnables.iterator();
  }

  public int getFrequencyInMs() {
    return frequencyInMs;
  }

  public void runNext() {
    runnables.next().run();
  }

}

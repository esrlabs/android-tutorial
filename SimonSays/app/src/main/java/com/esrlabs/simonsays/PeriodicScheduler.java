package com.esrlabs.simonsays;

public interface PeriodicScheduler {
  void schedule(Iterable<Runnable> runnables, int frequencyInMs);
}

package com.esrlabs.simonsays.concurrent;

public interface PeriodicScheduler {
  void schedule(Iterable<Runnable> runnables, int frequencyInMs);
}

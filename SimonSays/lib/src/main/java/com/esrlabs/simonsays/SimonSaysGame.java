package com.esrlabs.simonsays;

public class SimonSaysGame {

  public ActivePatternMatching newGame(GameListener listener, int patternLength) {
    PatternGenerator patternGenerator = new PatternGenerator(new JavaRandomElementPicker());
    Pattern pattern = patternGenerator.generatePattern(patternLength);
    return new ActivePatternMatching(pattern, listener);
  }

}

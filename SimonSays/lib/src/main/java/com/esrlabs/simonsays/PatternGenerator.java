package com.esrlabs.simonsays;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PatternGenerator {

  public static PatternGenerator create() {
    return new PatternGenerator(new JavaRandomElementPicker());
  }

  private final RandomElementPicker colorPicker;

  public PatternGenerator(RandomElementPicker colorPicker) {
    this.colorPicker = colorPicker;
  }

  public Pattern generatePattern(int length) {
    List<PatternColor> result = new ArrayList<PatternColor>(length);
    for (int i = 0; i < length; i++) {
      result.add(colorPicker.pickRandomElement(asList(PatternColor.values())));
    }
    return new Pattern(result);
  }
}

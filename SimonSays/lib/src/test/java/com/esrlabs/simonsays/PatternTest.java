package com.esrlabs.simonsays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PatternTest {

  @Test
  public void convertsfromAndToIds() throws Exception {
    Pattern expected = Pattern.of(PatternColor.BLUE, PatternColor.GREEN);
    assertThat(Pattern.from(expected.toArray()), is(expected));
  }

}
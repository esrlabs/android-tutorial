package com.esrlabs.simonsays;

import java.util.List;

/**
 * Created by andreibechet on 06/10/14.
 */
public interface RandomElementPicker {

  <T> T pickRandomElement(List<T> input);

}

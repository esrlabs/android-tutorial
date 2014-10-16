package com.esrlabs.simonsays;

import com.esrlabs.simonsays.JavaRandomElementPicker;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreibechet on 06/10/14.
 */
public class JavaRandomElementPickerTest {

    @Test
    public void picksRandomElementInList() throws Exception {
        JavaRandomElementPicker elementPicker = new JavaRandomElementPicker();
        List<Integer> input = asList(1, 3, 4, 5);
        int randomElement = elementPicker.pickRandomElement(input);
        assertTrue(input.contains(randomElement));
    }
}

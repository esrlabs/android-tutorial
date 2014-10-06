package com.esrlabs.simonsays;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by andreibechet on 06/10/14.
 */
public class JavaRandomElementPicker implements RandomElementPicker{

    private final Random random = new Random();

    @Override
    public <T> T pickRandomElement(List<T> input) {
        int index = random.nextInt(input.size());
        return input.get(index);
    }
}

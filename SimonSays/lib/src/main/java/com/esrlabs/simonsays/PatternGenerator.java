package com.esrlabs.simonsays;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PatternGenerator {

    private final RandomElementPicker colorPicker;

    public PatternGenerator(RandomElementPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public Pattern generatePattern(int length) {
        List<Color> result = new ArrayList<Color>(length );
        for(int i = 0; i < length; i++){
            result.add(colorPicker.pickRandomElement(asList(Color.values())));
        }
        return new Pattern(result);
    }
}

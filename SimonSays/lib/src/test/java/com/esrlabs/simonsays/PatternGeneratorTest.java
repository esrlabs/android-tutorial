package com.esrlabs.simonsays;

import com.esrlabs.simonsays.Color;
import com.esrlabs.simonsays.PatternGenerator;
import com.esrlabs.simonsays.RandomElementPicker;
import com.esrlabs.simonsays.Pattern;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.esrlabs.simonsays.Color.*;

/**
 * Created by andreibechet on 06/10/14.
 */
public class PatternGeneratorTest {



    @org.junit.Test
    public void generatesRandomColorSequencesOfGivenLength() throws Exception {
        Pattern expectedPattern = Pattern.of(RED, RED, BLUE, GREEN);
        PatternGenerator game = new PatternGenerator(new ColorPicker(expectedPattern));
        Pattern generatedPattern = game.generatePattern(expectedPattern.size());
        assertThat(generatedPattern, is(expectedPattern));
    }

    private class ColorPicker implements RandomElementPicker{
        private final Iterator<Color> iterator;

        public ColorPicker(Pattern expectedPattern) {
            iterator = expectedPattern.get().iterator();
        }

        @Override
        public <T> T pickRandomElement(List<T> input) {
            return (T) iterator.next();
        }
    }
}

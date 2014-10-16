package com.esrlabs.simonsays;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.esrlabs.simonsays.PatternColor.*;

public class PatternGeneratorTest {



    @org.junit.Test
    public void generatesRandomColorSequencesOfGivenLength() throws Exception {
        Pattern expectedPattern = Pattern.of(RED, RED, BLUE, GREEN);
        PatternGenerator game = new PatternGenerator(new ColorPicker(expectedPattern));
        Pattern generatedPattern = game.generatePattern(4);
        assertThat(generatedPattern, is(expectedPattern));
    }

    private class ColorPicker implements RandomElementPicker{
        private final Iterator<PatternColor> iterator;

        public ColorPicker(Pattern expectedPattern) {
            iterator = expectedPattern.get().iterator();
        }

        @Override
        public <T> T pickRandomElement(List<T> input) {
            return (T) iterator.next();
        }
    }
}

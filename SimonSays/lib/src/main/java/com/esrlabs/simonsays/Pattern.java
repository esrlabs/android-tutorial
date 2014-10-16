package com.esrlabs.simonsays;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by andreibechet on 06/10/14.
 */
public class Pattern implements Iterable<PatternColor> {

    private final List<PatternColor> colors;

    public Pattern(List<PatternColor> colors) {
        this.colors = colors;
    }

    public static Pattern of(PatternColor... colors) {
        return new Pattern(asList(colors));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pattern pattern = (Pattern) o;

        if (colors != null ? !colors.equals(pattern.colors) : pattern.colors != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return colors != null ? colors.hashCode() : 0;
    }

    public int size() {
        return colors.size();
    }

    public List<PatternColor> get() {
        return colors;
    }

    @Override
    public Iterator<PatternColor> iterator() {
        return colors.iterator();
    }
}

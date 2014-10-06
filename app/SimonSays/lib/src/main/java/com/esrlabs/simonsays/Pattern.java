package com.esrlabs.simonsays;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by andreibechet on 06/10/14.
 */
public class Pattern implements Iterable<Color> {

    private final List<Color> colors;

    public Pattern(List<Color> colors) {
        this.colors = colors;
    }

    public static Pattern of(Color... colors) {
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

    public List<Color> get() {
        return colors;
    }

    @Override
    public Iterator<Color> iterator() {
        return colors.iterator();
    }
}

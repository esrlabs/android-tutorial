package com.esrlabs.simonsays;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public class Pattern implements Iterable<PatternColor> {

    private final List<PatternColor> colors;
    private int[] colorIds;

    public Pattern(List<PatternColor> colors) {
        this.colors = colors;
    }

    public static Pattern of(PatternColor... colors) {
        return new Pattern(asList(colors));
    }

    public static Pattern from(int... colorIds) {
        List<PatternColor> colors = new ArrayList<PatternColor>(colorIds.length);
        for (int i = 0; i < colorIds.length; i++) {
            colors.add(PatternColor.values()[colorIds[i]]);
        }
        return new Pattern(colors);
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

    public List<PatternColor> get() {
        return colors;
    }

    public PatternColor get(int index) {
        return colors.get(index);
    }
    @Override
    public Iterator<PatternColor> iterator() {
        return colors.iterator();
    }

    public int[] toArray() {
        if(colorIds != null){
            return colorIds;
        }
        colorIds = new int[colors.size()];
        for(int i=0; i < colors.size(); i++){
            colorIds[i] = colors.get(i).ordinal();
        }
        return colorIds;
    }

}

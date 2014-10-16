package com.esrlabs.simonsays;

import java.util.Iterator;

public class ActivePatternMatching {

    private final Iterator<PatternColor> correctPattern;
    private final GameListener listener;

    public ActivePatternMatching(Pattern correctPattern, GameListener listener) {
        this.listener = listener;
        this.correctPattern = correctPattern.iterator();
    }

    public void onInput(PatternColor color) {
        if(color != correctPattern.next()){
           listener.onLoose();
           return;
        }
        if(!correctPattern.hasNext()){
            listener.onWin();
        }
    }

}

package com.esrlabs.simonsays;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ActivePatternMatchingTest {

    private GameListener listener = mock(GameListener.class);

    @Test
    public void onInputReturnsTrueWhenGivenElementMatchesPattern() throws Exception {
        ActivePatternMatching game = newGame(Color.BLUE, Color.BLUE, Color.RED);

        game.onInput(Color.BLUE);
        game.onInput(Color.BLUE);
        game.onInput(Color.RED);

        verify(listener).onWin();
    }

    @Test
    public void onInputReturnsFalseWhenGivenElementMatchesPattern() throws Exception {
        ActivePatternMatching patternChecker = newGame(Color.BLUE);
        patternChecker.onInput(Color.RED);
        verify(listener).onLoose();
    }


    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionWhenPatternIsAlreadyComplete() throws Exception {
        ActivePatternMatching patternChecker = newGame(Color.BLUE);
        patternChecker.onInput(Color.BLUE);
        patternChecker.onInput(Color.RED);
    }

    private ActivePatternMatching newGame(Color... colors) {
        return new ActivePatternMatching(Pattern.of(colors), listener);
    }
}
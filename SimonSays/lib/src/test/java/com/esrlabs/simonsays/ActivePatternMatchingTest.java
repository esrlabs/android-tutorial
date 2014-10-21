package com.esrlabs.simonsays;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ActivePatternMatchingTest {

  private GameListener listener = mock(GameListener.class);

  @Test
  public void onInputReturnsTrueWhenGivenElementMatchesPattern() throws Exception {
    ActivePatternMatching game = newGame(PatternColor.BLUE, PatternColor.BLUE, PatternColor.RED);

    game.onInput(PatternColor.BLUE);
    game.onInput(PatternColor.BLUE);
    game.onInput(PatternColor.RED);

    verify(listener).onWin();
  }

  @Test
  public void onInputReturnsFalseWhenGivenElementMatchesPattern() throws Exception {
    ActivePatternMatching patternChecker = newGame(PatternColor.BLUE);
    patternChecker.onInput(PatternColor.RED);
    verify(listener).onLoose();
  }


  @Test(expected = NoSuchElementException.class)
  public void throwsExceptionWhenPatternIsAlreadyComplete() throws Exception {
    ActivePatternMatching patternChecker = newGame(PatternColor.BLUE);
    patternChecker.onInput(PatternColor.BLUE);
    patternChecker.onInput(PatternColor.RED);
  }

  private ActivePatternMatching newGame(PatternColor... colors) {
    return new ActivePatternMatching(Pattern.of(colors), listener);
  }
}

package piperinnshall.glam.tokenizer;

import java.io.BufferedReader;

@FunctionalInterface
public interface TokenizerLazy {
  abstract BufferedReader r();

  default boolean hasNext() {
    return false; 
  }

  default Token peekNext() {
    return sel -> sel.apply("nil", TokenType.INVALID, 0, 0);
  }

  default Token popNext() {
    return sel -> sel.apply("nil", TokenType.INVALID, 0, 0);
  }
}

package piperinnshall.glam.tokenizer;

import java.io.BufferedReader;

@FunctionalInterface
public interface TokenFactory {
  abstract BufferedReader r();

  default boolean hasNext() {
    return false; 
  }

  default void test() {
    // Token t = 
  }
}

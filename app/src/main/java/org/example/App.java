package org.example;

import org.example.lexer.Tokenizer;
import org.example.resources.Resource;

public class App {
  public static void main(String[] args) {
    Tokenizer t = Tokenizer.of(
      ((Resource)() -> "test.txt").path()
    );

    System.out.println(t.tokenize());
  }
}

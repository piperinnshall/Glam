package piperinnshall.glam.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import piperinnshall.glam.collections.LinkedList;
import piperinnshall.glam.collections.LinkedListEmpty;
import piperinnshall.glam.tuple.Token;

public interface Tokenizer {
  abstract Path path();
  default LinkedList<Token> tokenize() {
    try (BufferedReader reader = Files.newBufferedReader(this.path())) {
      return ((TokenizeFile) () -> reader).tokenize();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return (LinkedListEmpty<Token>) x -> x;
  }
}

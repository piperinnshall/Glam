package piperinnshall.glam.tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import piperinnshall.glam.collections.LinkedList;
import piperinnshall.glam.collections.LinkedListEmpty;

public interface TokenizerEager {
  abstract Path path();
  default LinkedList<Token> tokenize() {
    try (BufferedReader reader = Files.newBufferedReader(this.path())) {
      LinkedListEmpty<Token> e = x -> x;
      return tokenizeHelper(() -> reader, e);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return (LinkedListEmpty<Token>) x -> x;
  }

  private LinkedList<Token> tokenizeHelper(TokenizerLazy t, LinkedList<Token> acc) {
    if (!t.hasNext()) {
      return acc.reverse();
    } else {
      Token token = t.popNext();
      return tokenizeHelper(t, acc.add(token));
    }
  }
}

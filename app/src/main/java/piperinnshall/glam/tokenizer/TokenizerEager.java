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
      InternalState empty = sel -> sel.apply(
          s -> s.apply("", TokenType.INVALID, 0, 0),
          "",
          0,
          0);

      return tokenizeHelper(() -> sel -> sel.apply(() -> reader.lines(), empty), e);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return (LinkedListEmpty<Token>) x -> x;
  }

  default LinkedList<Token> tokenizeHelper(TokenizerLazy t, LinkedList<Token> acc) {
    /* if (!t.hasNext()) {
      return acc.reverse();
    } else {
      Token token = t.popNext();
      return tokenizeHelper(t, acc.add(token));
    } */
    return acc;
  }
}

package piperinnshall.glam;

import piperinnshall.glam.lexer.OldTokenType;
import piperinnshall.glam.lexer.Tokenizer;
import piperinnshall.glam.resources.Resource;

public class App {
  public static void main(String[] args) {
    Tokenizer t = () -> ((Resource) () -> "test.txt").get();
    t.tokenize()
        .forEach(x -> System.out.print(x.lexeme()));
    t.tokenize()
        .filter(x -> !x.tokenType().equals(OldTokenType.WHITESPACE))
        .forEach(x -> System.out.print(x.tokenType() + " "));
  }
}

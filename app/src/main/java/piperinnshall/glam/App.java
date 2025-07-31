package piperinnshall.glam;

import piperinnshall.glam.lexer.Tokenizer;
import piperinnshall.glam.resources.Resource;
import piperinnshall.glam.lexer.TokenType;

public class App {
  public static void main(String[] args) {
    Tokenizer t= ()->((Resource)()->"test.txt").get();
    t.tokenize()
      .reverse()
      .forEach(x->System.out.print(x.tokenType()+" "));
    System.out.println();
    System.out.println();
    t.tokenize()
      .reverse()
      .filter(x -> !x.tokenType().equals(TokenType.WHITESPACE))
      .forEach(x->System.out.print(x.lexeme()));
  }
}


package piperinnshall.glam;

import piperinnshall.glam.lexer.Tokenizer;
import piperinnshall.glam.resources.Resource;

public class App {
  public static void main(String[] args) {
    Tokenizer t= ()->((Resource)()->"test.txt").get();
    t.tokenize()
      .reverse()
      .forEach(x->System.out.printf("%-40s %s%n",x.value(),x.tokenType()));
  }
}


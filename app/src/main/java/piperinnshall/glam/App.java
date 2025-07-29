package piperinnshall.glam;

import piperinnshall.glam.collections.LinkedListEmpty;
import piperinnshall.glam.lexer.Tokenizer;
import piperinnshall.glam.resources.Resource;

public class App {
  public static void main(String[] args) {
    Tokenizer t= ()->((Resource)()->"test.txt").get();
    t.tokenize()
      .forEach(x->System.out.println(x.value()));

    LinkedListEmpty<Integer> emptyList= x->x;
    System.out.println(emptyList.add(42).getHead(100));
    emptyList.add(1).add(2).add(3)
      .forEach(x->System.out.println(x));
  }
}

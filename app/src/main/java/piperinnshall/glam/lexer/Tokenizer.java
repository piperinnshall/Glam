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
    try(BufferedReader reader= Files.newBufferedReader(this.path())){
      return readAll(reader);
    }catch(IOException e){e.printStackTrace();return (LinkedListEmpty<Token>)x->x;}
  }
  private static LinkedList<Token> readAll(BufferedReader r) throws IOException{
    LinkedList<Token> ls= (LinkedListEmpty<Token>)x->x;
    String line;
    while ((line= r.readLine())!=null) {
      ls= ls.add(Token.of(TokenType.TRUE,line,0,0));
    }
    return ls;
  }
}

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
      return TokenizeFile.tokenize(reader);
    }catch(IOException e){e.printStackTrace();}
    return (LinkedListEmpty<Token>)x->x;
  }
}

interface TokenizeFile {
  static LinkedList<Token> tokenize(BufferedReader r) throws IOException {
    LinkedList<Token> ls = (LinkedListEmpty<Token>) x -> x;
    String line;
    int lineNum = 0;
    while ((line = r.readLine()) != null) {
      int curTokenLen = 0;

      for (int colNum = 0; colNum < line.length();) {
        Token token = null;

        if (colNum + 2 <= line.length()) {
          String sub = line.substring(colNum, colNum + 2);
          TokenType t = TokenType.fromString(sub);
          if (t!=null) {
            token= Token.of(t, sub, lineNum, colNum);
            colNum+=2;
            ls = ls.add(token);
            continue;
          }
        }

        if (colNum + 1 <= line.length()) {
          String sub = line.substring(colNum, colNum + 1);
          TokenType t = TokenType.fromString(sub);
          if (t!=null) {
            token = Token.of(t, sub, lineNum, colNum);
            colNum += 1;
            ls = ls.add(token);
            continue;
          }
        }

        colNum++;      }

      lineNum++;
    }
    return ls;
  }
}

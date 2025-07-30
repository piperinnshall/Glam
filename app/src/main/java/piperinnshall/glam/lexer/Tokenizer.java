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
    LinkedList<Token> ls= (LinkedListEmpty<Token>)x->x;
    String line;
    int lineNum= 0;
    while ((line = r.readLine())!=null){

      for (int colNum = 0; colNum < line.length();){
        Token token = null;

        for(int len= TokenType.longestTokenLength();len>0;len--){
          if (colNum + len > line.length()) continue;
          String sub = line.substring(colNum, colNum + len);
          TokenType t = TokenType.fromString(sub);
          if (t == null) continue;
          token = Token.of(t, sub, lineNum, colNum);
          colNum += len;
          break;        
        }

        if (token != null) ls = ls.add(token);
        else colNum++;
      }
      lineNum++;
    }
    return ls;
  }

}

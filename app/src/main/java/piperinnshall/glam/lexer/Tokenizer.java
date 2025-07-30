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

interface TokenizeFile{
  static LinkedList<Token> tokenize(BufferedReader r) throws IOException{
    LinkedList<Token> ls= (LinkedListEmpty<Token>)x->x;
    String line;
    int lineNum= 0;
    while ((line= r.readLine())!=null) {
      int curTokenLen = 0;

      for (int colNum= 0;colNum<line.length();colNum++) {
        int mark = colNum;

        char c = line.charAt(colNum);

        if (Character.isWhitespace(c)) continue;

        ls= nLengthToken(ls,""+c,lineNum,colNum);
      }

      lineNum++;
    }
    return ls;
  }

  private static LinkedList<Token> nLengthToken(LinkedList<Token> ls, String s, int lineNum, int colNum){
    TokenType t= TokenType.fromString(s);
    if(t!=null)return ls.add(Token.of(t,s,lineNum,colNum));
    return ls;
  }
}

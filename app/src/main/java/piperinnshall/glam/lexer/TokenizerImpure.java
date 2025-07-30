package piperinnshall.glam.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import piperinnshall.glam.collections.LinkedList;
import piperinnshall.glam.collections.LinkedListEmpty;
import piperinnshall.glam.tuple.Token;

/*
 * I wish tail recursion in Java was optimized.
 */
public interface TokenizerImpure {
  static LinkedList<Token> tokenize(BufferedReader r) throws IOException{
    LinkedList<Token> tokens= (LinkedListEmpty<Token>)x->x;
    String line;
    int lineNum= 0;
    while ((line = r.readLine()) != null){
      tokens= 
        tokenizeLine(line,lineNum,tokens)
        .add(Token.of(TokenType.NEWLINE,"\n",lineNum,line.length()));
      lineNum++;
    }
    return tokens;
  }
  private static LinkedList<Token> tokenizeLine(String line, int lineNum, LinkedList<Token> tokens){
    int colNum = 0;
    while (colNum < line.length()) {
      Token token = findAnyToken(line, colNum, lineNum);
      tokens= tokens.add(token);
      colNum += token.value().length();
    }
    return tokens;
  }
  private static Token findAnyToken(String line,int colNum,int lineNum){
    Optional<Token> t;
    t= findPresetToken(line,colNum,lineNum);
    if(t.isPresent())return t.get(); 
    t= findAbstractToken(line,colNum,lineNum);
    if(t.isPresent())return t.get();
    String invalidChar = line.substring(colNum, colNum + 1);
    return Token.of(TokenType.INVALID,invalidChar,lineNum,colNum);
  }
  private static Optional<Token> findPresetToken(String line,int colNum,int lineNum){
    for (int len= TokenType.longestTokenLength();len>0;len--) {
      if (colNum+len>line.length())continue;
      String sub= line.substring(colNum, colNum + len);
      TokenType t= TokenType.fromString(sub);
      if (t != null) {
        return Optional.of(Token.of(t, sub, lineNum, colNum));
      }
    }
    return Optional.empty();
  }
  private static Optional<Token> findAbstractToken(String line, int colNum, int lineNUm){
    return Optional.empty();
  }
}


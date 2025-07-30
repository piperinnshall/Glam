package piperinnshall.glam.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import piperinnshall.glam.collections.LinkedList;
import piperinnshall.glam.collections.LinkedListEmpty;
import piperinnshall.glam.tuple.Token;

public interface TokenizerImpure {
  static LinkedList<Token> tokenize(BufferedReader r) throws IOException {
    LinkedList<Token> tokens = (LinkedListEmpty<Token>) x -> x;
    String line;
    int lineNum = 0;
    while ((line = r.readLine()) != null) {
      tokens = tokenizeLine(line, lineNum, tokens)
        .add(Token.of(TokenType.NEWLINE, "\n", lineNum, line.length()));
      lineNum++;
    }
    return tokens;
  }
  private static LinkedList<Token> tokenizeLine(String line, int lineNum, LinkedList<Token> tokens) {
    int colNum = 0;
    while (colNum < line.length()) {
      Token token = findAnyToken(line, colNum, lineNum);
      tokens = tokens.add(token);
      colNum += token.value().length();
    }
    return tokens;
  }
  private static Token findAnyToken(String line, int colNum, int lineNum) {
    return findPresetToken(line, colNum, lineNum)
      .or(() -> findIdentifier(line, colNum, lineNum))
      .or(() -> findLitNum(line, colNum, lineNum))
      .or(() -> findLitStr(line, colNum, lineNum))
      .orElseGet(() -> {
        String invalidChar = line.substring(colNum, colNum + 1);
        return Token.of(TokenType.INVALID, invalidChar, lineNum, colNum);
      });
  }
  private static Optional<Token> findPresetToken(String line, int colNum, int lineNum) {
    for (int len = TokenType.longestTokenLength(); len > 0; len--) {
      if (colNum + len > line.length()) continue;
      String sub = line.substring(colNum, colNum + len);
      TokenType type = TokenType.fromString(sub);
      if (type != null) return Optional.of(Token.of(type, sub, lineNum, colNum)); 
    } return Optional.empty();
  }
  private static Optional<Token> findIdentifier(String line, int colNum, int lineNum) {
    if (!Character.isLetter(line.charAt(colNum))) return Optional.empty();
    int startCol = colNum;
    String s = "";
    while (colNum < line.length()) {
      char c = line.charAt(colNum);
      if (Character.isLetterOrDigit(c)) {
        s += c; colNum++;
      } 
      else break;
    }
    if (!s.isEmpty()) return Optional.of(Token.of(TokenType.IDENTIFIER, s, lineNum, startCol));
    return Optional.empty();
  }
  private static Optional<Token> findLitNum(String line, int colNum, int lineNum) {
    if (!Character.isDigit(line.charAt(colNum))) return Optional.empty();
    boolean seenDot = false;
    int startCol = colNum;
    String s = "";
    while (colNum < line.length()) {
      char c = line.charAt(colNum);
      if (Character.isDigit(c)) {
        s += c;
        colNum++;
        continue;
      }
      if (c == '.' && !seenDot) {
        seenDot = true;
        s += c;
        colNum++;
        continue;
      }
      break;
    }
    if (!s.isEmpty()) return Optional.of(Token.of(TokenType.LIT_NUM, s, lineNum, startCol));
    return Optional.empty();
  }
  private static Optional<Token> findLitStr(String line, int colNum, int lineNum) {
    int startCol = colNum;
    String s = "";
    if (line.charAt(colNum) != '"') return Optional.empty();
    s += '"';
    colNum++;
    int braceCount = 0;
    boolean escaped = false;
    boolean invalid = false;
    while (colNum < line.length()) {
      char c = line.charAt(colNum);
      if (escaped) {
        s += c; escaped = false;
        colNum++; continue;
      }
      if (c == '\\') {
        s += c;
        escaped = true;
        colNum++; continue;
      }
      if (c == '{') {
        s += c; braceCount++;
        colNum++; continue;
      }
      if (c == '}') {
        s += c;
        if (braceCount == 0)invalid= true;
        else braceCount--;
        colNum++; continue;
      }
      if (c == '"') {
        s += c; colNum++;
        if (braceCount == 0 && !invalid) return Optional.of(Token.of(TokenType.LIT_STR, s, lineNum, startCol));
        else return Optional.of(Token.of(TokenType.LIT_STR_INVALID, s, lineNum, startCol));
      }
      s += c; colNum++;
    }
    if (!s.isEmpty()) return Optional.of(Token.of(TokenType.LIT_STR_INVALID, s, lineNum, startCol));
    return Optional.empty();
  }
}

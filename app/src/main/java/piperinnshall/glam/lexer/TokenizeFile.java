package piperinnshall.glam.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import piperinnshall.glam.collections.LinkedList;
import piperinnshall.glam.collections.LinkedListEmpty;
import piperinnshall.glam.tuple.Token;

public interface TokenizeFile {
  abstract BufferedReader r();

  default LinkedList<Token> tokenize() throws IOException {
    int[] i = { 1 };
    return r().lines()
        .map(line -> {
          int y = i[0]++;
          return line(line, y);
        }).reduce((LinkedListEmpty<Token>) l -> l, LinkedList::concat);
  }

  private static LinkedList<Token> line(String line, int y) {
    return lineHelper(line, 0, y, (LinkedListEmpty<Token>) l -> l)
        .add(Token.of(TokenType.NEWLINE, "\n", 0, line.length()))
        .reverse();
  }

  private static LinkedList<Token> lineHelper(String line, int x, int y, LinkedList<Token> acc) {
    if (x >= line.length()) return acc;
    Token token = findAnyToken(line, x, y);
    return lineHelper(line, x + token.lexeme().length(), y, acc.add(token));
  }

  private static Token findAnyToken(String line, int x, int y) {
    return findPresetToken(line, x, y)
        .or(() -> findIdentf(line, x, y))
        .or(() -> findLitNum(line, x, y))
        .or(() -> findLitStr(line, x, y))
        .orElseGet(() -> Token.of(TokenType.INVALID, line.substring(x, x + 1), y, x));
  }

  private static Optional<Token> findPresetToken(String line, int x, int y) {
    return findPresetToken(line, x, y, TokenType.longestTokenLength());
  }

  private static Optional<Token> findPresetToken(String line, int x, int y, int len) {
    if (len == 0) return Optional.empty();
    if (x + len > line.length()) return findPresetToken(line, x, y, len - 1);
    String sub = line.substring(x, x + len);
    TokenType type = TokenType.fromString(sub);
    if (type != null) return Optional.of(Token.of(type, sub, y, x));
    return findPresetToken(line, x, y, len - 1);
  }

  private static Optional<Token> findIdentf(String line, int x, int y) {
    if (!Character.isLetter(line.charAt(x))) return Optional.empty();
    String lexeme = findIdentf(line, x);
    return Optional.of(Token.of(TokenType.IDENTIFIER, lexeme, y, x));
  }

  private static String findIdentf(String line, int pos) {
    if (pos < line.length() && Character.isLetterOrDigit(line.charAt(pos)))
      return line.charAt(pos) + findIdentf(line, pos + 1);
    return "";
  }

  private static Optional<Token> findLitNum(String line, int x, int y) {
    if (!Character.isDigit(line.charAt(x))) return Optional.empty();
    String lexeme = findLitNum(line, x, false);
    return Optional.of(Token.of(TokenType.LIT_NUM, lexeme, y, x));
  }

  private static String findLitNum(String line, int pos, boolean dot) {
    if (pos < line.length()) {
      char c = line.charAt(pos);
      if (Character.isDigit(c)) return c + findLitNum(line, pos + 1, dot);
      if (c == '.' && !dot) return c + findLitNum(line, pos + 1, true);
    }
    return "";
  }

  private static Optional<Token> findLitStr(String s, int start, int y) {
    if (s.charAt(start) != '"') return Optional.empty();
    int x = start + 1, br = 0;
    boolean esc = false, invalid = false;
    while (x < s.length()) {
      char c = s.charAt(x++);
      if (esc) { esc = false; continue; }
      if (c == '\\') esc = true;
      else if (c == '{') br++;
      else if (c == '}') invalid |= (br-- <= 0);
      else if (c == '"') break;
    }
    String lex = s.substring(start, x);
    TokenType t = (!invalid && br == 0) 
      ? TokenType.LIT_STR : TokenType.LIT_STR_INVALID;
    return Optional.of(Token.of(t, lex, y, start));
  }
}

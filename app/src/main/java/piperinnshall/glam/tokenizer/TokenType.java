package piperinnshall.glam.tokenizer;

import piperinnshall.glam.collections.LinkedList;
import piperinnshall.glam.collections.LinkedListEmpty;

public interface TokenType {
  abstract String text();

  TokenType IDENTIFIER = () -> null;
  TokenType INVALID = () -> null;
  // Literals
  TokenType LIT_NUM = () -> null;
  TokenType LIT_STR = () -> null;
  TokenType LIT_STR_INVALID = () -> null;
  // Types
  TokenType INT = () -> "int";
  TokenType FLOAT = () -> "float";
  TokenType STR = () -> "str";
  // Keywords
  TokenType TRUE = () -> "true";
  TokenType FALSE = () -> "false";
  TokenType OUT = () -> "out";
  TokenType MAIN = () -> "main";
  TokenType SELF = () -> "self";
  TokenType TYPE = () -> "type";
  TokenType PLACEHOLDER = () -> "$";
  // Operators
  TokenType PLUS = () -> "+";
  TokenType MINUS = () -> "-";
  TokenType STAR = () -> "*";
  TokenType SLASH = () -> "/";
  TokenType MODULO = () -> "%";
  TokenType ASSIGN = () -> "=";
  TokenType EQEQ = () -> "==";
  TokenType BANG = () -> "!";
  TokenType BANGEQ = () -> "!=";
  TokenType LT = () -> "<";
  TokenType LTE = () -> "<=";
  TokenType GT = () -> ">";
  TokenType GTE = () -> ">=";
  TokenType AND = () -> "&";
  TokenType OR = () -> "|";
  // Delimiters
  TokenType LPAREN = () -> "(";
  TokenType RPAREN = () -> ")";
  TokenType LBRACE = () -> "{";
  TokenType RBRACE = () -> "}";
  TokenType LBRACKET = () -> "[";
  TokenType RBRACKET = () -> "]";
  TokenType COMMA = () -> ",";
  TokenType COLON = () -> ":";
  TokenType ARROW = () -> "->";
  // Pattern Matching
  TokenType USCORE = () -> "_";
  TokenType GUARD = () -> "?";
  // Comments and white space
  TokenType COMMENT = () -> "--";
  TokenType WHITESPACE = () -> " ";
  TokenType NEWLINE = () -> null;

  private static LinkedList<TokenType> all() {
    return ((LinkedListEmpty<TokenType>) l -> l)
        .add(IDENTIFIER)
        .add(INVALID)
        .add(LIT_NUM)
        .add(LIT_STR)
        .add(LIT_STR_INVALID)
        .add(INT)
        .add(FLOAT)
        .add(STR)
        .add(TRUE)
        .add(FALSE)
        .add(OUT)
        .add(MAIN)
        .add(SELF)
        .add(TYPE)
        .add(PLACEHOLDER)
        .add(PLUS)
        .add(MINUS)
        .add(STAR)
        .add(SLASH)
        .add(MODULO)
        .add(ASSIGN)
        .add(EQEQ)
        .add(BANG)
        .add(BANGEQ)
        .add(LT)
        .add(LTE)
        .add(GT)
        .add(GTE)
        .add(AND)
        .add(OR)
        .add(LPAREN)
        .add(RPAREN)
        .add(LBRACE)
        .add(RBRACE)
        .add(LBRACKET)
        .add(RBRACKET)
        .add(COMMA)
        .add(COLON)
        .add(ARROW)
        .add(USCORE)
        .add(GUARD)
        .add(COMMENT)
        .add(WHITESPACE)
        .add(NEWLINE);
  }

  /**
   * Finds largest matching token
   */
  public static TokenType contains(String s) {
    return containsHelper(s, all(), null);
  }

  private static TokenType containsHelper(String s, LinkedList<TokenType> list, TokenType best) {
    return list.match(
        () -> best,
        (head, tail) -> containsHelper(s, tail, isBest(s, head, best) ? head : best));
  }

  private static boolean isBest(String s, TokenType head, TokenType best) {
    if (head.text() == null) return false;
    if (!s.startsWith(head.text())) return false;
    if (best == null) return true;
    return head.text().length() > best.text().length();
  }
}

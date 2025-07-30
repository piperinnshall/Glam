package piperinnshall.glam.lexer;

import java.util.Arrays;

public enum TokenType {
  IDENTIFIER(null),
  INVALID(null),
  // Literals
  LIT_NUM(null),
  LIT_STR(null),
  LIT_CHAR(null),
  //Types
  INT("int"),
  FLOAT("float"),
  STR("str"),
  CHAR("char"),
  // Keywords
  TRUE("true"),
  FALSE("false"),
  WHEN("when"),
  OUT("out"),
  MAIN("main"),
  SELF("self"),
  TYPE("type"),
  PLACEHOLDER("$"),
  // Operators
  PLUS("+"),
  MINUS("-"),
  STAR("*"),
  SLASH("/"),
  MODULO("%"),
  ASSIGN("="),
  EQEQ("=="),
  BANG("!"),
  BANGEQ("!="),
  LT("<"),
  LTE("<="),
  GT(">"),
  GTE(">="),
  AND("&"),
  OR("|"),
  // Delimiters
  LPAREN("("),
  RPAREN(")"),
  LBRACE("{"),
  RBRACE("}"),
  LBRACKET("["),
  RBRACKET("]"),
  COMMA(","),
  COLON(":"),
  ARROW("->"),
  // Pattern Matching
  USCORE("_"),
  GUARD("?"),
  // Comments and white space
  COMMENT("--"),
  WHITESPACE(" "),
  NEWLINE(null);
  private final String text;
  TokenType(String text){this.text= text;}
  public static int longestTokenLength() {
    return Arrays.stream(values())
      .filter(t -> t.text != null)
      .mapToInt(t -> t.text.length())
      .max()
      .orElse(0);
  }
  public static TokenType fromString(String text){
    return Arrays.stream(values())
      .filter(t -> t.text!=null && t.text.equals(text))
      .findFirst()
      .orElse(null);
  }
}

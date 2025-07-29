package piperinnshall.glam.lexer;

public enum TokenType {
  IDENTIFIER(null),
  // Literals
  NUMBER(null),
  STRING(null),
  CHAR(null),
  BOOL(null),
  UNIT(null),
  // Keywords with their string representations
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
  BLKCOMMENT("-[-]"),
  WHITESPACE(null),
  NEWLINE(null);

  private final String text;
  TokenType(String text){this.text= text;}
  public String text(){return text;}
  public static TokenType fromString(String text){
    return java.util.Arrays.stream(values())
      .filter(t -> t.text != null && t.text.equals(text))
      .findFirst()
      .orElse(IDENTIFIER);
  }
}

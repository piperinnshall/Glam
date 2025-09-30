package piperinnshall.glam.tokenizer;

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
}

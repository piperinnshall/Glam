package piperinnshall.glam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import piperinnshall.glam.lexer.OldTokenType;
import piperinnshall.glam.tuple.OldToken;
import org.junit.jupiter.api.Test;

class AppTest {
  @Test
  void testCreateToken() {
    OldTokenType t = OldTokenType.LIT_NUM;
    String v = "42";
    int l = 15;
    int c = 17;
    OldToken token = OldToken.of(t, v, l, c);
    assertEquals(t, token.tokenType(), "Token type should be: " + t);
    assertEquals(v, token.lexeme(), "Token value should be: " + v);
    assertEquals(l, token.line(), "Token line should be: " + l);
    assertEquals(c, token.column(), "Token column should be: " + c);
  }
}

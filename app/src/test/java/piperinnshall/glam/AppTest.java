package piperinnshall.glam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import piperinnshall.glam.lexer.TokenType;
import piperinnshall.glam.tuple.Token;
import org.junit.jupiter.api.Test;

class AppTest {
  @Test
  void testCreateToken() {
    TokenType t= TokenType.NUMBER;String v= "42";int l= 15;int c= 17;
    Token token= Token.of(t,v,l,c);
    assertEquals(t,token.tokenType(),"Token type should be: "+t);
    assertEquals(v,token.value(),"Token value should be: "+v);
    assertEquals(l,token.line(),"Token line should be: "+l);
    assertEquals(c,token.column(),"Token column should be: "+c);
  }
}

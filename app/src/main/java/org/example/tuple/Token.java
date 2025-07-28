package org.example.tuple;

import org.example.lexer.TokenType;

public interface Token{
  public static Token of(TokenType tokenType, String value, int line, int column){
    return sel -> sel.apply(tokenType,value,line,column);
  }
  TokenUnion apply(TokenSelector sel);
  default TokenType tokenType() {
    final TokenSelector sel=(t,_,_,_)->(FromTokenType)()->t;
    return this.apply(sel).toTokenType();
  }
  default String value() {
    final TokenSelector sel=(_,v,_,_)->(FromValue)()->v;
    return this.apply(sel).toValue();
  }
  default int line() {
    final TokenSelector sel=(_,_,l,_)->(FromLine)()->l;
    return this.apply(sel).toLine();
  }
  default int column() {
    final TokenSelector sel=(_,_,_,c)->(FromColumn)()->c;
    return this.apply(sel).toColumn();
  }
}
interface TokenUnion{
  default TokenType toTokenType() {return this.toTokenType();}
  default String    toValue()     {return this.toValue();}
  default int       toLine()      {return this.toLine();}
  default int       toColumn()    {return this.toColumn();}
}
interface FromTokenType extends TokenUnion{TokenType toTokenType();}
interface FromValue     extends TokenUnion{String toValue();}
interface FromLine      extends TokenUnion{int toLine();}
interface FromColumn    extends TokenUnion{int toColumn();}
interface TokenSelector{TokenUnion apply(TokenType t, String v, int l, int c);}


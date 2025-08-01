# Grammar

---
<!---->
<!-- ``` -->
<!-- Program         ::= Statement* -->
<!---->
<!-- Statement       ::= VariableDecl -->
<!--                   | Expression -->
<!--                   | Comment -->
<!---->
<!-- Comment         ::= "--" .*  -- single line comment -->
<!---->
<!-- VariableDecl    ::= Identifier TypeAnnotation? "=" Expression -->
<!---->
<!-- TypeAnnotation  ::= Type -->
<!---->
<!-- Type            ::= BaseType -->
<!--                     | "[" Type "]" -->
<!---->
<!-- BaseType        ::= "int"  -->
<!--                     | "float"    -->
<!--                     | "bool"  -->
<!--                     | "str"  -->
<!--                     | "fn" -->
<!---->
<!-- ParamList       ::= Param ("," Param)* -->
<!---->
<!-- Expression      ::= LambdaExpr -->
<!--                     | PatternMatchExpr -->
<!--                     | FunctionCall -->
<!--                     | BinaryExpr -->
<!--                     | UnaryExpr -->
<!--                     | Literal -->
<!--                     | Identifier -->
<!--                     | ParenExpr -->
<!--                     | OutputExpr -->
<!---->
<!-- LambdaExpr      ::= "(" ParamList? ")" "->" Expression -->
<!---->
<!-- Param           ::= Identifier TypeAnnotation -->
<!---->
<!-- FunctionCall    ::= Identifier "(" ArgumentList? ")" -->
<!---->
<!-- ArgumentList    ::= Expression ("," Expression)* -->
<!---->
<!-- PatternMatchExpr ::= Expression "{" MatchCase+ "}" -->
<!---->
<!-- MatchCase       ::= Pattern "->" Expression -->
<!---->
<!-- Pattern         ::= Literal -->
<!--                   | Identifier -->
<!--                   | Wildcard -->
<!--                   | ListPattern -->
<!--                   | TuplePattern -->
<!---->
<!-- Wildcard        ::= "_" -->
<!---->
<!-- ListPattern     ::= "[" PatternList? "]" -->
<!---->
<!-- PatternList     ::= Pattern ("," Pattern)* -->
<!---->
<!-- TuplePattern    ::= Pattern ("," Pattern)+  -- comma separated patterns -->
<!---->
<!-- OutputExpr      ::= "out" (StringLiteral | "{" Expression "}") -->
<!---->
<!-- BinaryExpr      ::= Expression BinaryOp Expression -->
<!---->
<!-- UnaryExpr       ::= UnaryOp Expression -->
<!---->
<!-- BinaryOp        ::= "+" | "-" | "*" | "/" | "%" | "=" | "==" | "!=" | "<" | "<=" | ">" | ">=" | "&" | "|" -->
<!---->
<!-- UnaryOp         ::= "!" | "-" -->
<!---->
<!-- Literal         ::= IntegerLiteral -->
<!--                   | FloatLiteral -->
<!--                   | StringLiteral -->
<!--                   | BoolLiteral -->
<!--                   | ListLiteral -->
<!---->
<!-- ListLiteral     ::= "[" ExpressionList? "]" -->
<!---->
<!-- ExpressionList  ::= Expression ("," Expression)* -->
<!---->
<!-- IntegerLiteral  ::= [0-9]+ -->
<!---->
<!-- FloatLiteral    ::= [0-9]+ "." [0-9]+ -->
<!---->
<!-- StringLiteral   ::= "\"" (Char | EscapeSequence | Interpolation)* "\"" -->
<!---->
<!-- Interpolation   ::= "{" Expression "}" -->
<!---->
<!-- BoolLiteral     ::= "true" | "false" -->
<!---->
<!-- Identifier      ::= [a-zA-Z_][a-zA-Z0-9_]* -->
<!---->
<!-- ParenExpr       ::= "(" Expression ")" -->
<!-- ``` -->

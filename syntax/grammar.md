# Grammar

```
E  ::= lit | (x T)->Ss E | ()->Ss E 
   | E1(E2) | E1() | E{ Cases _->E'} | E1 op E2
S  ::= x = E
T  ::= int | float | bool | str | Fn T1->T2 | Fn T
lit::=...
x   ::= ...
op  ::= ...
Case ::= E1 -> E2 | ?E1 -> E2
v   ::= lit | (x T)->Ss E | ()->Ss E
Ctx ::= Ctx(E2) | ((x T)->Ss E)(Ctx) | Ctx() 
  | Ctx{ Cases _->E'} 
  | Ctx op E2 | v op Ctx
  | v{ Ctx->E2 Cases _->E'}
  | v{ ?Ctx->E2 Cases _->E'}
  | (() x=Ctx Ss E)()

Reduction
E==>E'


  E ==> E'
---------------------(ctx)
Ctx[E] ==> Ctx[E']

------------------------------------------- (done)
  (()-> E)() ==>  E
   
-------------------------------------------   (eat argument)
  ((x T)-> Ss E)(v) ==>  (()=>Ss[x=v] E[x=v])()

---------------------------------------------------- (replace stm)
(()-> x2=v Ss E2)()==> (()-> Ss[x2=v] E2[x2=v])()

 
---------------------------- (match)
 v{v -> E Cases _->E'} ==> E

  v != v'
---------------------------- (more)
 v{v' -> E Cases _->E'} ==> v{Cases _->E'}


 
---------------------------- (true)
 v{?true -> E Cases _->E'} ==> E

---------------------------- (false)
 v{?false -> E Cases _->E'} ==> v{Cases _->E'}

---------------------------- (default)
 v{_->E'} ==> E'
  
--------------------------------- (primitive)
  v1 op v2 ==> op[v1,v2]


Define E[x=v]
Define Ss[x=v]
Define S[x=v]

Type system
```
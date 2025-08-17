# Grammar

```
E       ::= x | lit 
        | E(E') | E()        
        | !E | E op E'
        | (x T | T)->Ss E | ()->Ss E
        | E{Cases _->E'}    
S       ::= x = E
T       ::= int | float | bool | str | [T] 
        | fn:T                          -- fn returns T
lit     ::= ... | true | false
x       ::= ... | self | $
op      ::= + | - | * | / | % 
        | == | != 
        | < | <= | > | >= 
        | and | or
Case    ::= E1->E2 | ?E1->E2
v       ::= lit
Ctx     ::= Ctx(E2) 
        | ((x T | T)->Ss E)(Ctx) 
        | Ctx() 
        | Ctx{ Cases _->E'} 
        | Ctx op E2 
        | v op Ctx
        | v{ Ctx->E2 Cases _->E'}
        | v{ ?Ctx->E2 Cases _->E'}
        | (() x = Ctx Ss E)()
```

# Reduction

```glam

E ==> E'
---(ctx)
Ctx[E] ==> Ctx[E']

(done)
(()->E)() ==> E[self = (()->E)()]

(expr-eat-arg)
((x T | T)->E)(v) ==> (()->E[x:=v, $:=v, self:=((x T | T)->E)])()

(stm-eat-arg)
((x T | T)->Ss E)(v) ==> (()->Ss[x:=v, $:=v, self:=((x T | T)->Ss E)] E[x:=v?, $:=v, self:=((x T | T)->Ss E)])()

(stm-replace)
(()->x:=E Ss E')() ==> (()->Ss[x:=E, self:=(()-> x:=E Ss E'] E'[x:=E self:=(()->x:=E Ss E')])()

(match)
v{v->E Cases _->E'} ==> E

v != v'
---(match-fail)
v{v'->E Cases _->E'} ==> v{Cases _->E'}

(match-guard)
v{?true->E Cases _->E'} ==> E
v{?false->E Cases _->E'} ==> v{Cases _->E'}

(default)
v{_->E'} ==> E'

(primitive)
v1 op v2 ==> op[v1,v2]

(neg)
!true  ==> false
!false ==> true

```

```
Define E[x:=v]
Define Ss[x:=v]
Define S[x:=v]
```

# Grammar

```
E       ::= x | lit 
        | (x T)->Ss E     
        | ()->Ss E           
        | C(E2) | C()        
        | !E | E1 op E2        
        | E{Cases _->E'}    
C       ::= (x T)->Ss E | ()->Ss E      --Callable
S       ::= x = E
T       ::= int | float | bool | str 
        | [T] | fn:T                        -- fn returns T
lit     ::= ... | true | false
x       ::= ... | self
op      ::= + | - | * | / | % 
        | == | != 
        | < | <= | > | >= 
        | and | or
Case    ::= E1->E2 | ?E1->E2
v       ::= lit | C
Ctx     ::= Ctx(E2) 
        | ((x T)->Ss E)(Ctx) 
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

(stm-apply)
((x T)->Ss E)(v) ==> (()->Ss[x=v, self=((x T)->Ss E)] Ss E[x=v, self=((x T)->Ss E)])()

(nullary-apply)
(()->x=E Ss E')() ==> (()->Ss[x=E, self=(()-> x=E Ss E'] E'[x=E self=(()->x=E Ss E')])()

(expr-apply)
((x T)->E)(v) ==> (()->E[x=v, self = ((x T)->E)])()

(match)
v{v->E Cases _->E'} ==> E

v != v'
---(match-fail)
v{v'->E Cases _->E'} ==> v{Cases _->E'}

(true)
v{?true->E Cases _->E'} ==> E

(false)
v{?false->E Cases _->E'} ==> v{Cases _->E'}

(default)
v{ _->E'} ==> E'

(primitive)
v1 op v2 ==> op[v1,v2]

(neg)
!true  ==> false
!false ==> true

```

```
Define E[x=v]
Define Ss[x=v]
Define S[x=v]
```

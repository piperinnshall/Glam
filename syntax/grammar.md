# Grammar

```
E  ::= x 
     | lit 
     | (x : T) -> Ss E
     | () -> Ss E
     | E1(E2)
     | E1()
     | E { Cases _ -> E' }
     | E1 op E2

S  ::= x = E 
     | x : T = E

V  ::= x = v 
     | x : T = v

T  ::= int 
     | float 
     | bool 
     | str 
     | Fn T1 -> T2
     | Fn T

lit ::= ...
x   ::= ...
op  ::= ...
Case ::= E1 -> E2 
       | ?E1 -> E2

v   ::= lit 
     | (x : T) -> Ss E 
     | () -> Ss E

Ctx ::= Ctx(E2) 
      | ((x : T) -> Ss E)(Ctx) 
      | Ctx() 
      | Ctx { Cases _ -> E' } 
      | Ctx op E2 
      | v op Ctx
      | v { Ctx -> E2 Cases _ -> E' } 
      | v { ?Ctx -> E2 Cases _ -> E' } 
      | (() Vs x = Ctx Ss E)()

Γ ::= x1 : T1, ..., xn : Tn
```

# Well-formedness

**No repeated variables, well-guarded, no free variables.**

```
Vars(Vs | E) = xs            
where xs has no repetitions

Vars(Vs | E) = Vars(Vs), Vars(E)
Vars(x) = ∅
Vars(lit) = ∅
Vars((x : T) -> Ss E) = x, Vars(Ss), Vars(E)
Vars(() -> Ss E) = Vars(Ss), Vars(E)
Vars(E1(E2)) = Vars(E1), Vars(E2)
Vars(E1()) = Vars(E1)
Vars(E { Cases _ -> E' }) = Vars(E), Vars(Cases), Vars(E')
Vars(E1 op E2) = Vars(E1), Vars(E2)
Vars(S1 .. Sn) = Vars(S1), ..., Vars(Sn)
Vars(Case1 .. Casen) = Vars(Case1), ..., Vars(Casen)
Vars(x = E) = x, Vars(E)

Vars(E1 -> E2) = Vars(E1), Vars(E2)
Vars(?E1 -> E2) = Vars(E1), Vars(E2)
```

# Reduction

```
Vs|E ==> Vs'|E'
-------------------------------------------------- (ctx)
Vs|Ctx[E] ==> Vs'|Ctx[E']

x = v in Vs    
v ~= v'
-------------------------------------------------- (x)
Vs|x ==> Vs|v'

-------------------------------------------------- (done)
Vs|(()-> Vs' E)() ==> Vs Vs' | E
   
x not in dom(Vs)
-------------------------------------------------- (eat argument)
Vs|((x T)-> Ss E)(v) ==> Vs x=v | (()=>Ss E)()

-------------------------------------------------- (match)
Vs|v{v -> E Cases _->E'} ==> Vs|E

v != v'
-------------------------------------------------- (more)
Vs|v{v' -> E Cases _->E'} ==> Vs|v{Cases _->E'}

-------------------------------------------------- (true)
Vs|v{?true -> E Cases _->E'} ==> Vs|E

-------------------------------------------------- (false)
Vs|v{?false -> E Cases _->E'} ==> Vs|v{Cases _->E'}

-------------------------------------------------- (default)
Vs|v{_->E'} ==> Vs|E'
  
-------------------------------------------------- (primitive)
Vs|v1 op v2 ==> Vs|op[v1,v2]
```

# Type system

```
-------------------------------------------------- (t-x)
Γ | Γ' ⊢ x : Γ(x)

-------------------------------------------------- (t-lit)
Γ | Γ' ⊢ lit : typeOf(lit)

-------------------------------------------------- (t-abs)
Γ, x:T1 | Γ' ⊢ Ss : ·
Γ, x:T1 | Γ' ⊢ E : T2
-------------------------------------------------- 
Γ | Γ' ⊢ (x : T1) -> Ss E : Fn T1 -> T2

-------------------------------------------------- (t-abs-untyped-param)
Γ, x:T1 | Γ' ⊢ Ss : ·
Γ, x:T1 | Γ' ⊢ E : T2
-------------------------------------------------- 
Γ | Γ' ⊢ (x) -> Ss E : Fn T1 -> T2

-------------------------------------------------- (t-abs-nullary)
Γ | Γ' ⊢ () -> Ss E : Fn T

-------------------------------------------------- (t-call)
Γ | Γ' ⊢ E1 : Fn T2 -> T
Γ | Γ' ⊢ E2 : T2
-------------------------------------------------- 
Γ | Γ' ⊢ E1(E2) : T

-------------------------------------------------- (t-call-nullary)
Γ | Γ' ⊢ E1 : Fn T
-------------------------------------------------- 
Γ | Γ' ⊢ E1() : T

-------------------------------------------------- (t-op)
Γ | Γ' ⊢ E1 : T1
Γ | Γ' ⊢ E2 : T2
-------------------------------------------------- 
Γ | Γ' ⊢ E1 op E2 : typeOf(op, T1, T2)


Γ | Γ' ⊢ E1 : TLHS
Γ | Γ' ⊢ E2 : TRHS
-------------------------------------------------- (normal-case-ok)
Γ | Γ' ⊢ (E1 -> E2) : Ok TLHS TRHS

Γ | Γ' ⊢ E1 : Bool
Γ | Γ' ⊢ E2 : TRHS
-------------------------------------------------- (question-case-ok)
Γ | Γ' ⊢ (?E1 -> E2) : Ok T TRHS

Γ | Γ' ⊢ E  : TM
Γ | Γ' ⊢ E' : T
For all i. Ci : Ok TM T
-------------------------------------------------- (t-match)
Γ | Γ' ⊢ E { C1, C2, ..., Cn _-> E' } : T

```

# Glam Language Specification

This specification provides a detailed guide to the syntax and semantics of the Glam programming language.

---

## Main Entry Point

The `main` function serves as the entry point of every Glam program. It is a special function that can be written simply as an expression.

```glam
main = 0
```

This desugars into an immediately-invoked lambda:

```glam
main = (() -> 0)()
```

---

## Comments

* Single-line comments use `--`
* Block comments use `-[` and `]-`

```glam
-- This is a comment

-[ This is a block comment ]-
```

---

## Variables

Variables are declared with the format:

```glam
varname type = value
```

---
## Types

```glam
myInt int = 1
myFloat float = 0.1
myBool bool = true
myChar char = 'A'
myStr str = "Hello
```

---

## Operators

```glam
+-*/%
=   ==
!   !=
<   <=
>   >=
&   |
```

---

## Lambda Functions

### Anonymous Lambda

A lambda function with no name, often used inline.

```glam
() -> print("Hello World!")
```

### Named Lambda

A lambda assigned to a variable:

```glam
mult fn:int = (m int) -> m * 2
print(mult(2))
```

### Placeholder Lambda

Use `$` as an implicit parameter placeholder:

```glam
mult fn:int = (int) -> $ * 2
mult(1)
```

### Curried Lambda

Breaks down multi-argument functions into chained single-argument functions:

```glam
mult fn:fn:int = (x int) -> (y int) -> x * y
partial fn:int = mult(1)
result1 int = partial(1)
result2 int = mult(1)(2)
```

---

## Tuples

Return and destructure multiple values:

```glam
num fn:int*str*bool = (int) -> $, -[ $ to string ]-, true
nums fn:fn:int*int = (x int) -> (y int) -> x, y
```

Destructuring:

```glam
x,y,z int*str*bool = num(1)
x, y = int*int nums(1)(2)
tup int*int*int = x, y, z
```

Pass tuple to a function:

```glam
pass fn:int = (int*int*int) -> {
  a, b, c = $
  a + b + c
}

-- or explicitly:

pass fn:int = (a,b,c int*int*int) -> {
  a + b + c
}
```

---

## Pattern Matching

### Anonymous Pattern Match

```glam
() -> when day {
  1 -> print("Monday")
  2 -> print("Tuesday")
  3 -> print("Wednesday")
  4 -> print("Thursday")
  5 -> print("Friday")
  6 -> print("Saturday")
  7 -> print("Sunday")
  _ -> print("Unknown")
}()
```

### Named Pattern Match

```glam
day int = 4
matched str = when day {
  1 -> "Monday"
  2 -> "Tuesday"
  3 -> "Wednesday"
  4 -> "Thursday"
  5 -> "Friday"
  6 -> "Saturday"
  7 -> "Sunday"
  _ -> "Unknown"
}

print(matched)
```

### Lambda Pattern Match

```glam
describe fn:str = (x int) -> when x {
  1 -> "One"
  2 -> "Two"
  _ -> "Other"
}

-- Using placeholders:

describe fn:str = (int) -> when $ {
  1 -> "One"
  2 -> "Two"
  _ -> "Other"
}
```

### Curried Pattern Match

```glam
describe fn:fn:str = (x int) -> (y int) -> when x, y {
  1, 1 -> "One and One"
  1, _ -> "One and something else"
  _, 2 -> "Something and Two"
  _, _ -> "Other"
}

-- Or nested:

describe fn:fn:str = (x int) -> when x {
  1 -> (int) -> when $ {
    1 -> "One and One"
    _ -> "One and something else"
  }
  _ -> (int) -> when $ {
    2 -> "Something and Two"
    _ -> "Other"
  }
}
```

---

## Recursion

```glam
factorial fn:int = (int) -> when $ {
  0 -> 1
  _ -> $ * self($ - 1)
}
```

---

## Example: Two Sum

```glam
nums [int] = 2, 7, 11, 15
target int = 9

find = (i, j int*int) -> when i, j {
  ? j >= length(nums) -> -1, -1
  ? nums(i) + nums(j) == target -> i, j
  _ -> self(i), j + 1
}

twoSum = () -> when {
  length(nums) < 2 -> -1, -1
  _ -> find(0,1)
}

main = twoSum()
```

---

## Error Types

Glam supports algebraic error types:

```glam
ok*int | err*str
-- shorthand
!int 
```

---

## Error Handling

Glam allows multiple ways to define functions with error handling via pattern matching:

```glam
safeDiv fn:!int = (int*int) -> when {
  _, 0 -> err, "divide by zero"
  x, y -> ok, x / y
}
```

Desugarings and equivalent versions:

```glam
safeDiv fn:!int = (int*int) -> when $ { ... }
safeDiv fn:ok*int|err*str = (tup int*int) -> when tup { ... }
safeDiv fn:ok*int|err*str = (x, y int*int) -> when x, y { ... }
```

---

## Notes

* All functions are expressions.
* Pattern matching is exhaustive unless a fallback `_` is defined.
* Currying and placeholders make Glam concise and expressive.
* Errors and option types are built into the type system.

---

*End of Spec.*


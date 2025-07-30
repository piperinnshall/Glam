# Gλ Language Specification

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

## Output

```glam
x int = 5

() -> out "Hello World! {x}"()

() -> out {
    \\My number: 
    \\{x} 
}()
```

---

## Comments

* Single-line comments use `--`

```glam
-- This is a comment
```

---

## Variables

Variables are declared with the format:

```glam
variableName = value
```

---

## Types

```glam
int
float
bool
str
fn
```

---

## Operators

```glam
+ - * / %    -- Arithmetic
=   ==       -- Assignment and equality
!   !=       -- Negation and inequality
<   <=       -- Less than and less than or equal
>   >=       -- Greater than and greater than or equal
&   |        -- Logical AND and OR
```

---

## Lambda Functions

### Parameters

Lambda functions must have type parameters.

```glam
(x int) -> x
```

### Anonymous Lambda

A lambda function with no name, often used inline.

```glam
() -> print("Hello World!")
```

### Named Lambda

A lambda assigned to a variable:

```glam
mult = (m int) -> m * 2
print(mult(2))
```

### Placeholder Lambda

Use `$` as an implicit parameter placeholder:

```glam
mult = (int) -> $ * 2
mult(1)
```

### Curried Lambda

Breaks down multi-argument functions into chained single-argument functions:

```glam
mult = (x int) -> (y int) -> x * y
partial = mult(1)
result1 = partial(1)
result2 = mult(1)(2)
```

### Invoking Lambdas

In Glam you can only use () to call:

1. An anonymous arrow lambda, e.g.
```glam 
() -> out { "Hello" }()
```

2. A named function, e.g.
```glam 
sayHello = () -> out { "Hi!" }
sayHello()  
```

---

## Pattern Matching

Braces after an expression means pattern matching

### Anonymous Pattern Match

```glam
() -> day {
  1 -> out { "Monday" }
  2 -> out { "Tuesday" }
  3 -> out { "Wednesday" }
  4 -> out { "Thursday" }
  5 -> out { "Friday" }
  6 -> out { "Saturday" }
  7 -> out { "Sunday" }
  _ -> out { "Unknown" }
}()
```

### Named Pattern Match

```glam
day int = 4
matched = day {
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
describe = (x int) -> x {
  1 -> "One"
  2 -> "Two"
  _ -> "Other"
}

-- Using placeholders:

describe = (int) -> $ {
  1 -> "One"
  2 -> "Two"
  _ -> "Other"
}
```

### Curried Pattern Match

```glam
describe = (x int) -> (y int) -> x, y {
  1, 1 -> "One and One"
  1, _ -> "One and something else"
  _, 2 -> "Something and Two"
  _, _ -> "Other"
}

-- Or nested:

describe = (x int) -> x {
  1 -> (int) -> $ {
    1 -> "One and One"
    _ -> "One and something else"
  }
  _ -> (int) -> $ {
    2 -> "Something and Two"
    _ -> "Other"
  }
}
```

---

## Recursion

```glam
factorial = (int) -> $ {
  0 -> 1
  _ -> $ * self($ - 1)
}
```

---

## Lists

Lists are a fundamental data type in Glam. A list is denoted with square brackets around a type:

```glam
[int] -- a list of integers
[str] -- a list of strings
```

### List Literals

Lists are created using comma-separated values:

```glam
nums [int] = [1, 2, 3, 4]
words [str] = ["one", "two", "three"]
```

You can also nest lists:

```glam
matrix [[int]] = [[1, 2], [3, 4]]
```

### Accessing List Elements

Use parentheses for index access:

```glam
nums[0] -- gets the first element 1
nums[1] -- gets the second element 2
```

### Common List Functions

```glam
length(nums)        -- number of elements
head(nums)          -- first element
tail(nums)          -- all but the first element
last(nums)          -- last element
```

### List Destructuring

You can pattern match on lists:

```glam
describe = ([int]) -> $ {
  [] -> "Empty list"
  [x] -> "Single item: {x}"
  [x, y] -> "Two items"
  _ -> "Many items"
}
```

---

# *End of Specification*

---

`TODO:`

## Tuples

Return and destructure multiple values:

```glam
num = (int) -> $, "$ to string", true
nums = (x int) -> (y int) -> x, y
```

### Tuple Destructuring

```glam
x, y, z int*str*bool = num(1)
x, y int*int = nums(1)(2)
tup int*int*int = x, y, z
```

### Pass Tuple to a Function

```glam
pass = (int*int*int) -> {
    a, b, c = $
    a + b + c
}

-- or explicitly:

pass = (a, b, c int*int*int) -> {
    a + b + c
}
```

-----

`TODO:`

## Error Types

Glam supports algebraic error types:

```glam
ok*int | err*str
-- shorthand
!int 
```

### Error Handling

Glam allows multiple ways to define functions with error handling via pattern matching:

```glam
safeDiv = (int*int) -> $ {
    _, 0 -> err, "divide by zero"
    _ -> ok, {
      x, y int*int = $ 
      x / y
    }
}
```

Desugarings and equivalent versions:

```glam
safeDiv = (int*int) -> tup { ... }
safeDiv = (x, y int*int) -> x, y { ... }
```

---

## Example: Two Sum

```
nums [int] = 2, 7, 11, 15
target int = 9

find = (i, j int*int) -> i, j {
  ? j >= length(nums) -> -1, -1
  ? nums(i) + nums(j) == target -> i, j
  _ -> self(i), j + 1
}

twoSum = () -> {
  length(nums) < 2 -> -1, -1
  _ -> find(0, 1)
}

main = twoSum()
```

---

## Language Notes

* All functions are expressions.
* Pattern matching is exhaustive unless a fallback `_` is defined.
* Currying and placeholders make Glam concise and expressive.
* Errors and option types are built into the type system.

---

`TODO:`
- Finish tuple destructuring
    - Should tuples exist
- Add multi line string 
- Add generics
- Add higher order filter(pred)(t)


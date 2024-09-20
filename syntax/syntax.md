# Main

This is the entrance to your code

```
main () = {
    print("Hello World")
}
```

# Output

The `print()` and `println()` functions are used to output values/text.

# Comments

```
// This is a comment

/* This is a block comment */
```

# Variables

To create a variable, use `let` and assign it to a value with the equals sign.
By default, variables are immutable.

```
let variableName = value
```

Use the `mut` keyword to create a mutable variable.

```
let mut variableName = value
```

The type of a variable is decided by its value.

```
let myInt = 1               // int
let myFloat = 0.1           // float 
let myBool = true           // bool 
let myChar = 'C'            // char
let myStr = "Hello"         // str
```

But it is possible to specify a type.

```
let myInt int = 1           // int
let myFloat float = 0.1     // float 
let myBool bool = true      // bool 
let myChar char = 'C'       // char
let myStr str = "Hello"     // str
```

# Operators

```
+
-
*
/
%
++
--

=
+=
-=
*=
/=
%=

==
!=
>
<
>=
<=

!
and
or
```

# If..Else

If else statements.

```
if condition1 {
    expression1
} else if condition2 {
    expression2
} else {
    expression3
}
```

Ternary like operations.

```
let result = if condition expression1 else expression2 
```

# When

there are 2 different types of when expressions.

## Regular when expression.

```
let day = 4

let whichDay int = when day { 
    1 -> "Monday"
    2 -> "Tuesday"
    3 -> "Wednesday"
    4 -> "Thursday"
    5 -> "Friday"
    6 -> "Saturday"
    7 -> "Sunday"
    else -> "Not a Day"
}

main () = { 
    whichDay(1) 
}
```

## When expression in a lambda function.

When expressions in lambda functions are only allowed to have 1 parameter.

```
let whichDay (int) -> str = when $ {
    1 -> "Monday"
    2 -> "Tuesday"
    3 -> "Wednesday"
    4 -> "Thursday"
    5 -> "Friday"
    6 -> "Saturday"
    7 -> "Sunday"
    else -> "Not a Day"
}

main () = { 
    whichDay(1) 
}
```

# While

While do.

```
while condition 
do { 
    statements
}
```

Do while.

```
do {
    statements
}
while condition
```

# Break/Continue

Break exits out of a loop early.

```
let mut i = 0
while i < 10
do {
    i ++
    if i == 4 {
        break
    }
}
```

Continue breaks out of a loop for 1 iteration.
This example will skip 4.

```
let mut i = 0
while i < 10
do {
    if i == 4 {
        continue 
    }
    i ++
}
```

# List

Lists can be initialized without a type.

```
let myList = [0, true, "string"] 
```

Or with a defined type.

```
let myList int = [0, 1, 2, 3]
```

# Lambda functions

A lambda function is a block of code that can be reused.
Lambdas can be used in the local, top-level, and global scobes.
This means that you can define a lambda function inside of a lambda function.
Lambdas can be named or anonymous, but anonymous lambdas can only be used in the local scope.

## Named lambda

A named lambda is a variable where the type is specified as lambda.
This is denoted by the open and closed parentheses `()`
and the return type, a function arrow `->` followed by a type e.g. `int`.

Lambdas can be passed parameters inside of the parentheses, or the lambda type.
The code block, or body expression, that processes the lambdas is defined by the curly brackets.

This is a variable called myLambda with a type of `(int)` (a lambda that takes an integer as a parmater), 
and a return type of int, as well as an empty code block

```
let myLambda (int) -> int = {}
```

Lamdas have implicit returns. The result of the last evaluated expression is automatically returned.
This means that an explicit return statement is not needed.
Lambdas also have an implicit parameter placeholder `$` if there is only one parameter.

The code below will output `2`

```
let myLambda (int) -> int = {
    $ + 1
}

main () = {
    print(myLambda(1)) 
}
```

A lambda can have multiple parameters and multiple return types. 
If a lambda has multiple parameters, an explicit parameter placeholder must be defined.
This is done inside of tthe curly brackets at the start of the code block.

In the below code the parameter placeholders are defined as `a` and `b`.
The function arrow `->` seperates the parameters from the body of the lambda. 
indicates that the lambda takes `a` and `b` as inputs and processes them to the body expression.

The below code will output `3, 2`.

```
let myLamda (int, int) -> int, int = { a, b ->
    a + b, a + 1
}

main () = {
    print(myLambda(1, 2)) 
}
```

# Anonymous lambda function

An anonymous lambda is similar to a lambda, except it can only be used in the local scope.
Anonymous lambdas are lambda functions that do not have names. 
They can be used to implement one time usage code in the local scope.
Anonymous lambdas invoke themselves, by placing parentheses after the code block.

The below code will output `5`.

```
main () = {
    let a = 3
    print((int) -> int = { a + $ }(2))
}
```

# Unit lambda function

A unit lambda function is a special type of lambda that does not return a result.
This means that the defined output type is `unit`.

```
let printer (str) -> unit = { 
    print($)
}

let main () = {
    printer("Hello World")
}
```

By default, a lambda function has a return type of `unit`.
This means that you can define a lambda function with no return type.
You can also define a lambda with no parameters.

```
let helloWord () = {
    print("Hello World")
}
```

Main is the entrance to your code. it is also a special type of lambda.
Because it is already defined, the let keyword is not used.

```
main () = {

}
```

# Curried lambda function

Currying is the process of transforming a function that takes multiple arguments
into a series of functions, each taking a single argument.

A curried lambda function allows  a lambda to take arguments one at a time, 
returning a new lambda with each successive argument until all are provided, 
and the final result is computed. Each argument is applied in sequence, creating a chain of lambdas.

```
let curried (int) (int) (int) -> int = { a, b, c ->
    a + b + c
}
```

Each argument can be called all at once.

```
main () = {
    let curriedTotal = curried(1)(1)(1)

    print(curriedTotal) // Prints a + b + c = 3
}
```

Or seperately.

```
main () = {
    let curriedOne = curried(1)
    let curriedTwo = curriedOne(1)
    let curriedThree = curriedTwo(1)
    
    print(curriedOne) // Prints a = 1
    print(curriedTwo) // Prints a + b = 2
    print(curriedThree) // Prints a + b + c = 3
}
```

# Modifing lamda funcions

# Structs

```
let Player struct = {
    let health int
    let x double
    let y double
    let printName () -> unit
    let moveX (int) -> unit
    let moveY (int) -> unit
    let updateHealth (int) -> unit
}

player1 Player = {
    health = 100,
}

let createPlayer (int, int) -> Player = { xStart, yStart ->
    {
        x = xStart,
        y = yStart,
        printName = print("player1"),
        
        moveX = dx -> x + dx,
        moveX = dx -> x + dx,
        updateHealth = dmg -> this.health - dmg
    }
}

main () = {
    player1.health(100)
    print(player1.health)

    let player2 = createPlayer(5, 5)
    player2.moveX(3)
    player2.moveY(3)
    player2.updateHealth(10)
    player2.printName()
    print(player2.x)
    print(player2.y)
    print(player2.health)
}
```

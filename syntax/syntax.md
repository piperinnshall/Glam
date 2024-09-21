# Main

This is the entrance to your code.

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
By default, variables are implicitly immutable.

```
let variableName = value
```

Use the `immut` keyword to explicitly define an immutable variable.

```
let immut variableName = value
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

there are multiple different types of when expressions.

## When expression.

A when expression is a powerful control flow construct that allows you to handle multiple conditions.

```
main () = { 
    let day = 4

    let whichDay str = when day { 
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Not a Day"
    }

    print(whichDay)
}
```

## Anonymous when expression

Anonymous when expressions are when expressions that are not assigned to a variable. 

```
main () = { 
    when day { 
        1 -> print("Monday")
        2 -> print("Tuesday")
        3 -> print("Wednesday")
        4 -> print("Thursday")
        5 -> print("Friday")
        6 -> print("Saturday")
        7 -> print("Sunday")
        else -> print("Not a Day")
    }
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

A lambda function is a block of code that can be reused. Lambdas are immmutable,
which means the function logic inside a lambda cannot be changed.
Lambdas can be used in the local, top-level, and global scobes.
This means that you can define a lambda function inside of a lambda function.
Lambdas can be named or anonymous, but anonymous lambdas can only be used in the local scope.

## Named lambda function

A named lambda is a variable where the type is specified as lambda.
This is denoted by the open and closed parentheses `()`
and the return type, a function arrow `->` followed by a type e.g. `int`.

Lambdas can be passed parameters inside of the parentheses, or the lambda type.
The code block, or body expression, that processes the lambdas is defined by the curly brackets.

This is a variable called myLambda with a type of `(int)` (a lambda that takes an integer as a parmater), 
and a return type of int, as well as an empty code block.

```
let myLambda (int) -> int = {}
```

Lamdas have implicit returns. The result of the last evaluated expression is automatically returned.
This means that an explicit return statement is not needed.
Lambdas also have an implicit parameter placeholder `$` if there is only one parameter.

The code below will output `2`.

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

## Anonymous lambda function

Anonymous lambdas are lambda functions that are not assigned to a variable. 
They can be used to implement one time usage code in the local scope.
Anonymous lambdas invoke themselves, by placing parentheses after the code block.

The below code will output `5`.

```
main () = {
    let a = 3
    print((int) -> int = { a + $ }(2))
}
```

## Unit lambda function

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

## Curried lambda function

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

## Modifing lamda funcions

The mod keyword allows you to modify the code block of an existing lambda function without changing its type.
This is particularly useful for altering the behavior of a lambda without needing to redefine it completely.

```
let myLambda (int) -> int = {
    $ + 1
}

mod myLambda = {
    $ - 1
}

main () = {
    print(myLambda(2)) // This will output 1 (2 - 1)
}
```

# Structs

Structs, short for structures, are a composite data type that allows you to group variables. 
They can be immutable or mutable. You can create an instance of a struct and access its fields using dot notation. 
You can read the fields of any struct instance, but you can only modify them if the struct instance is mutable.
You may omit fields from instances of structs, but you may not define new variables in instances of structs.
Variables can only be defined in the top level struct, but can be given values in any instance of the struct.

## Immutable struct 

The fields are readable, but not modifiable.
The variables x and y are defined in the top level struct, and given values in the instance of the struct.
Variables in immutable structs can be marked as mutable using the `mut` keyword.

```
struct Point {
    let mut x double
    let y double
    let add (double, double) -> double = x, y -> x + y
}

let point1 Point = {
    x = 0
    y = 0
}

main () = {
    point1.x = 1
    print(point1.x)
    print(point1.y)
    print(add(point1.x, point1,y))
}
```

## Mutable struct 

The fields are readable, and modifiable. All fields are mutable by default.
Variables in mutable structs can be marked as immutable using the `immut` keyword.

```
mut struct Point {
    let x double
    let immut y double
    let add (double, double) -> double = x, y -> x + y
}

let point1 Point = {
    x = 0
    y = 0
}

main () = {
    point1.x = 1 
    point1.y = 1 // This will cause an error because y is immutable  
    print(add(point1.x, point1,y))
}
```

> [!important]
>
> Lambda functions are immutable, even when in a mutable struct, unless the mod keyword is used.

## Lambda function that returns a struct

Lambda functions can return an instance of a struct.

```
mut struct Point = {
    let x double
    let y double
    let add (double, double) -> double 
}

let newPoint (int) -> Point = {
    let point Point = {
        x = $
        y = 0
        add = x, y -> x + y
    }
    point
}

main () = {
    point = newPoint(1)

    print(point.x) // prints 1
    print(point.y) // prints 0
    point.y = 1
    print(point.y) // prints 1
    print(add(point.x, point.y)) // prints 2
}
```

# Enums 

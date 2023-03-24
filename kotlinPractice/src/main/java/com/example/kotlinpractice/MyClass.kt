package com.example.kotlinpractice


fun main() {

    var number1 = 1
    var number2 = 2
    var sumof12 = number1 + number2

    println(sumof12)

    val numbers = arrayOf(1, 2, 3, 4, 5, 6)
    numbers.forEach {
        print("$it ")
    }

    var colors = arrayOf("red", "Blue", "pink")
    println(colors)


    val num = 0
    val result = if (num > 0) {
        "positive number"
    } else if (num < 0) {
        "negative number"
    } else {
        "zero"
    }
    println(result)

    //when (switch case)
    var firstVal = 1123
    var secondval = 10
    val operator = "*"
    val resultOfOperation = when (operator) {
        "+" -> firstVal + secondval
        "-" -> firstVal - secondval
        "*" -> firstVal * secondval
        "/" -> firstVal / secondval
        else -> "$operator operator is invalid"
    }
    println(resultOfOperation)

    val age = 12
    when (age) {
        in 1..18 -> println("age is in the range")
        !in 18..100 -> println("age is outside the range")
        else -> println("None of the above")
    }

    println("please enter numbers like (1 2 3 4)")
    var numString = readLine()
    var arrayOfNums = numString?.split(" ")
    arrayOfNums?.forEach {
        print("$it ")
    }
    println()
    when (age) {
        is Int -> println("the age is $age")
        else -> println("It is not an int")
    }

    for (index in 1..10) {
        println(index)
    }

    for (index in 12 downTo 0 step 2) {
        print("$index ")
    }

    //referential equality and structural equality
    val names1 = setOf("ayush", "aniket", "atul")
    val names2 = setOf("ayush", "aniket", "atul")
    val name3From1 = names1

    println(names1 == names2)       //checks if contents are same
    println(names1 === names2)      //checks if refers to the same object in memory
    println(names1 === name3From1)  //gives true since name3From1 is initialized from name1

    println(names1.equals(names2))  //"==" jevu j

    //data types
    val a: Int = 100
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    val b: Int = 10000
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b

    println(boxedA === anotherBoxedA) // true   All nullable references to "a" are actually the same object because of the memory optimization that JVM applies to Integers between -128 and 127
    println(boxedB === anotherBoxedB) // false  //because b is not in -128 t 128 range so it does


    // Hypothetical code, does not actually compile:
//    val a: Int? = 1 // A boxed Int (java.lang.Integer)
//    val b: Long? = a // Implicit conversion yields a boxed Long (java.lang.Long)
//    print(b == a) // Surprise! This prints "false" as Long's equals() checks whether the other is Long as well
//    As a consequence, smaller types are NOT implicitly converted to bigger types. This means that assigning a value of type Byte to an Int variable requires an explicit conversion:


//    The operations on floating-point numbers discussed in this section are:
//
//    Equality checks: a == b and a != b
//
//            Comparison operators: a < b, a > b, a <= b, a >= b
//
//    Range instantiation and range checks: a..b, x in a..b, x !in a..b
//
//    When the operands a and b are statically known to be Float or Double or their nullable counterparts (the type is declared or inferred or is a result of a smart cast), the operations on the numbers and the range that they form follow the IEEE 754 Standard for Floating-Point Arithmetic.
//
//    However, to support generic use cases and provide total ordering, when the operands are not statically typed as floating point numbers (for example, Any, Comparable<...>, a type parameter), the operations use the equals and compareTo implementations for Float and Double, which disagree with the standard, so that:
//
//    NaN is considered equal to itself
//
//    NaN is considered greater than any other element including POSITIVE_INFINITY
//
//    -0.0 is considered less than 0.0


    //did not understand : Unsigned numbers are implemented as inline classes with the single storage property of the corresponding signed counterpart type of the same width. Nevertheless, changing type from unsigned type to signed counterpart (and vice versa) is a binary incompatible change.


//
//    Raw strings can contain newlines and arbitrary text. It is delimited by a triple quote ("""), contains no escaping and can contain newlines and any other characters:
//
//val text = """
//    for (c in "foo")
//        print(c)
//    """
//To remove leading whitespace from raw strings, use the trimMargin() function:
//
//val text = """
//    |Tell me and I forget.
//    |Teach me and I remember.
//    |Involve me and I learn.
//    |(Benjamin Franklin)
//    """.trimMargin()
//By default, a pipe symbol | is used as margin prefix, but you can choose another character and pass it as a parameter, like trimMargin(">").
//


//    Arrays in Kotlin are invariant.
//    This means that Kotlin does not let us assign an Array<String> to an Array<Any>,
//    which prevents a possible runtime failure (but you can use Array<out Any>, see Type Projections).


    //casts and smart cast
    //In most cases, you don't need to use explicit cast operators in Kotlin because the compiler tracks the is-checks and explicit casts for immutable values and inserts (safe) casts automatically when necessary:

    var x: Any = "hello"
    if (x is String) {
        println(x.length)
    }

    val someValue: Any = 123
    if (someValue is Int) {
        println(someValue)      //here someValue is not smartly casted
    }

    if (x !is String || x.length == 0)
    //loops
    //labels

        label1@ for (i in 1..20) {
            for (j in 1..100) {
                if (j == 10) break@label1
                else print("$j ")
            }
        }
    print("it got out!")

//    try is an expression
//    try is an expression, which means it can have a return value:
//
//        val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }
//        The returned value of a try expression is either the last expression in the try block or the last expression in the catch block (or blocks). The contents of the finally block don't affect the result of the expression.

//throw is an expression in kotlin and its type is of "NOTHING"
//Nothing is a type in kotlin that is used to mark location which cannot be reached

    //ex:
    fun fail(a: String): Nothing {
        throw java.lang.IllegalArgumentException("$a is not good argument")
    }


    //also,
    var nothingWithNull = null
    //this variable has type of "Nothing?"

}



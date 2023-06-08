package com.example.kotlinpractice.problemspractices

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains different examples to iterate over arrays.
*/

val positiveNumbers = arrayOf(1, 2, 3, 4, 5)

fun main() {
    for (i in 0..positiveNumbers.size - 1) {
        print("${positiveNumbers[i]} ") // prints: 1 2 3 4 5
    }

    println()

    for (i in 0..positiveNumbers.lastIndex) {
        print("${positiveNumbers[i]} ") // prints: 1 2 3 4 5
    }

    println()

    for (i in 0 until positiveNumbers.size) {
        print("${positiveNumbers[i]} ") // prints: 1 2 3 4 5
    }

    println()

    for (i in positiveNumbers.indices) {
        print("${positiveNumbers[i]} ") // prints: 1 2 3 4 5
    }

    println()

    for (elem in positiveNumbers) {
        print("$elem ") // prints: 1 2 3 4 5
    }

    println()

    positiveNumbers.forEach {
        print("$it ") // prints: 1 2 3 4 5
    }

    println()

    for ((ind, ele) in positiveNumbers.withIndex()) {
        println("$ind: $ele")
    }
    println()
    // prints:
    // 0: 1
    // 1: 2
    // 2: 3
    // 3: 4
    // 4: 5

    positiveNumbers.forEachIndexed { index, i ->
        println("$index -> $i")
    }
    // prints:
    // 0 -> 1
    // 1 -> 2
    // 2 -> 3
    // 3 -> 4
    // 4 -> 5
}

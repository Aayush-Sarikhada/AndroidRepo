package com.example.kotlinpractice.operatoroverloading

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains examples of operator overloading.
*/

data class Point(val x: Int, val y: Int) {
    operator fun unaryMinus() = Point(-x, -y)

    operator fun unaryPlus() = Point(+x, +y)

    operator fun plus(otherPoint: Point): Point {
        return Point(x + otherPoint.x, y + otherPoint.y)
    }
}

// Assignments are NOT expressions in Kotlin.
// === and !== (identity checks) are not overloadable, so no conventions exist for them.
// provideDelegate, getValue and setValue operator functions are described in Delegated properties.

fun main() {
    val point = Point(10, 11)
    println(point.unaryMinus()) // prints: Point(x=-10, y=-11)
    println(-point) // prints: Point(x=-10, y=-11)
    println(point) // prints: Point(x=10, y=11)
}


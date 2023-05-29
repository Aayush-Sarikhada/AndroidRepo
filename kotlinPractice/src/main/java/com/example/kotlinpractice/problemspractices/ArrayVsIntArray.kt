package com.example.kotlinpractice.problemspractices

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains example to check difference between iterable vs sequence.
*/

fun main() {
    val zeroes: Array<Int> = Array(10) { 0 } // explicitly zero is initialized
    // internally "Integer[]" type

    val emptySlots = IntArray(10) // by default 0 is initialized
    // internally "int[]" type

    zeroes.forEach {
        print("$it ")
    } // prints: 0 0 0 0 0 0 0 0 0 0
    println()

    emptySlots.forEach {
        print("$it ")
    } // prints: 0 0 0 0 0 0 0 0 0 0
    println()
}
package com.example.kotlinpractice.inlinefunctions

import com.example.kotlinpractice.aftersecondkt.User

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains example to see working of inline functions.
*/

inline fun print(stringToBePrinted: String, printingFunction: (String) -> Unit) {
    println("start printing...")
    printingFunction(stringToBePrinted) // after compiling the code of lambda function is inlined here
    println("end printing...")
}

inline fun executeAsync(crossinline block: () -> Unit, crossinline callback: (Int) -> Unit) {
    Thread {
        block()
        callback(100)
    }.start()
}

inline fun <reified T> findMax(list: List<T>, noinline selector: (T) -> Int): T? {
    return list.maxByOrNull(selector)
}

fun main() {
    print("Hello") {// this functions code will be inlined here after compile
        println("$it world!") // prints: "Hello world"
    }

    executeAsync(block = {
            println("This task is being executed in different thread")
        }
    ) {
        println("The response is $it") // prints: The response is 100
    }

    val websiteUsers = listOf(
        User("Aayush", "Sarikhada"),
        User("Aaditya", "Shah"),
        User("Aman", "Gameti"),
    )
    val maxLengthUserName = findMax(websiteUsers) {
        it.userName.length
    }
    println(maxLengthUserName) // prints: User(firstName=Aayush, lastName=Sarikhada)
}
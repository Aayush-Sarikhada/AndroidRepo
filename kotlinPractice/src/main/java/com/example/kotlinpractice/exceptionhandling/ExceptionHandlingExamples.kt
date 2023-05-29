package com.example.kotlinpractice.exceptionhandling

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains examples of exception handling in kotlin.
*/

// there is no throws keyword in kotlin and therefor there are not checked exceptions in kotlin
// this function will throw Exception since College_Notes.txt does not exists.
fun readCollegeNotes() {
    val collegeNotes = File("College_Notes.txt")
    try {
        collegeNotes.readBytes()
    } catch (e: FileNotFoundException) {
        println("This file does not exists")
        throw Exception("Trying to access a file which does not exist")
    }
}

fun main() {
    // handling a specific type of exception
    try {
        println(10 / 0)
    } catch (e: ArithmeticException) {
        println("Divide by zero is not allowed")
    } catch (e: IOException) {
        // handle the IOException here
        // control won't reach here since the exception has already been caught
    } finally {
        // code that will always execute, regardless of whether an exception was thrown or not
        println("the exception is handled")
    }

    // catch any type of exception
    try {
        val file = File("file.txt")
        val outputStream = file.outputStream()
        outputStream.write("Hello this file is used for Exception handling demo.".toByteArray())
        outputStream.close() // stream has been closed

        // attempting to write in the file after closing the stream
        outputStream.write("This will throw exception".toByteArray())
    } catch (e: Exception) {
        println("Trying to write on stream after it is close!")
    }

    try {
        readCollegeNotes()
    } catch (e: Exception) {
        println("Exception thrown from custom function")
        println("Message: ${e.message}") // prints: Message: Trying to access a file which does not exist
    }
}
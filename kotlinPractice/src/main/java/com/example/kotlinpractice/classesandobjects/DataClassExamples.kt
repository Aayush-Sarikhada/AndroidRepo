package com.example.kotlinpractice.classesandobjects

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains notes and examples of data classes in kotlin.
*/

// It is not unusual to create classes whose main purpose is to hold data. In such classes,
// some standard functionality and some utility functions are often mechanically derivable from the data.
// In Kotlin, these are called data classes and are marked with `data` keyword

// The compiler automatically derives the following members from all properties declared in the primary constructor:
//      - equals()/hashCode() pair
//      - toString() of the form "User(name=John, age=42)"
//      - componentN() functions corresponding to the properties in their order of declaration.
//      - copy() function (see below).
// To ensure consistency and meaningful behavior of the generated code, data classes have to fulfill the following requirements:
//       - The primary constructor needs to have at least one parameter.
//       - All primary constructor parameters need to be marked as val or var.
//       - Data classes cannot be abstract, open, sealed, or inner.
//       - Additionally, the generation of data class members follows these rules with regard to the members' inheritance:
// If there are explicit implementations of equals(), hashCode(), or toString() in the data class body or final implementations in a superclass,
// then these functions are not generated, and the existing implementations are used.
//
// If a supertype has componentN() functions that are open and return compatible types, the corresponding functions are generated for the data class
// and override those of the supertype. If the functions of the supertype cannot be overridden due to incompatible signatures or due to their being
// final, an error is reported.
// Providing explicit implementations for the componentN() and copy() functions is not allowed.
// Data classes may extend other classes (see Sealed classes for examples).
// On the JVM, if the generated class needs to have a parameterless constructor, default values for the properties have to be specified (see Constructors).
// data class User(val name: String = "", val age: Int = 0)

// The compiler only uses the properties defined inside the primary constructor for the automatically generated functions.
// To exclude a property from the generated implementations, declare it inside the class body:

data class PersonData(val name: String, val age: Int) {
    var money: Int = 0
}

data class HttpResponse(val responseCode: Int, val msg: String, val errorMsg: String? = null)

fun main() {
    val personData = PersonData(
        name = "ajay",
        age = 26
    ).also {
        it.money = 1000
    }

    println(personData.age) // prints: 26
    println(personData.name) // prints: ajay
    println(personData.money) // prints: 1000

    val response = HttpResponse(responseCode = 404, "Page not found")
    println(response) // prints: HttpResponse(requestCode=404, msg=Page not found, errorMsg=null)
}
package com.example.kotlinpractice.problemspractices

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains example to check difference between iterable vs sequence.
*/

data class Person(val id: Int, val name: String)

val personList = listOf(
    Person(1, "Eren"),
    Person(2, "Bertold"),
    Person(3, "Mikasa"),
    Person(4, "Armin"),
    Person(5, "Reiner")
)

fun main() {
    println(personList) // prints: [Person(id=1, name=Eren), Person(id=2, name=Bertold), Person(id=3, name=Mikasa), Person(id=4, name=Armin), Person(id=5, name=Reiner)]

    //iterable
    val peopleWithNameLongerThanFourChars = personList.filter {
        println("filter called for iterable")
        it.name.length > 4
    }.map {
        println("map called for iterable")
        it.name
    }
    println(peopleWithNameLongerThanFourChars) // prints: [Bertold, Mikasa, Armin, Reiner]

    //Sequence
    val peopleWithNameLongerThanTwoChars = personList.asSequence().filter {
        println("filter called for sequence")
        it.name.length > 2
    }.map {
        println("map called for sequence")
        it.name
    }.toList()
    println(peopleWithNameLongerThanTwoChars) // prints: [Eren, Bertold, Mikasa, Armin, Reiner]
}
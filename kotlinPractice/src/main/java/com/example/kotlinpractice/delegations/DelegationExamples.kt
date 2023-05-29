package com.example.kotlinpractice.delegations

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains examples for delegation in kotlin.
 */

class ArihantClassSubmarineDelegate {

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "Arihant Class"
    }

}

class KalvariClassSubmarineDelegate {

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "Kalvari Class"
    }

}

fun main() {
    val insArihant by ArihantClassSubmarineDelegate()
    val insArighat by ArihantClassSubmarineDelegate()
    val insKalvari by KalvariClassSubmarineDelegate()
    val insKhanderi by KalvariClassSubmarineDelegate()


    println(insArighat) // Arihant Class
    println(insArihant) // Arihant Class

    println(insKalvari) // Kalvari Class
    println(insKhanderi) // Kalvari Class

    // if the initialization of a value throws an exception, it will attempt to reinitialize the value at next access.
    val morningGreeting: String by lazy { "hello good morning" }
    println(morningGreeting) // prints: hello good morning

    // will change the value only if the returned true from lambda else it will ignore it.
    var planetName: String by Delegates.vetoable("earth") { _, old, new ->
        new.length < old.length
    }

    var starName: String by Delegates.observable("sun") { _, old, new ->
        println("change observed: $old -> $new")
    }

    planetName = "jupiter"
    starName = "dhruv"

    println(planetName) // prints: earth
    println(starName) // prints: dhruv
}
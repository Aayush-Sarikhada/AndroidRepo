package com.example.kotlinpractice.classesandobjects

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains examples of enums in kotlin.
*/

interface WeekDays {
    fun shortName(): String
}

enum class Days(val gujaratiName: String) : WeekDays {
    MONDAY("somvar") {
        override fun shortName(): String {
            return "mon"
        }
    },
    TUESDAY("mangalvar") {
        override fun shortName(): String {
            return "tue"
        }
    },
    WEDNESDAY("budhvar") {
        override fun shortName(): String {
            return "wed"
        }
    },
    THURSDAY("guruvar") {
        override fun shortName(): String {
            return "thur"
        }
    },
    FRIDAY("sukravar") {
        override fun shortName(): String {
            return "fri"
        }
    },
    SATURDAY("sanivar") {
        override fun shortName(): String {
            return "sat"
        }
    },
    SUNDAY("ravivar") {
        override fun shortName(): String {
            return "sun"
        }
    };

    init {
        println("init is called")
    }
}

enum class Months {
    JAN, FAB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
}

enum class RGB(private val hexValue: String) {
    RED("FF0000"),
    GREEN("FF0000"),
    BLUE("FF0000");

    fun printColorName() {
        println(this.name)
    }

    fun printHexValue() {
        println(this.hexValue)
    }
}

fun main() {
    val firstDayOfTheWeek = Days.MONDAY
    println(firstDayOfTheWeek.gujaratiName) // prints: somvar
    println(firstDayOfTheWeek.shortName()) // prints: mon

    val january = Months.valueOf("JAN") //throws illegalArgumentException if value doesn't match

    println("${january.name} = ${january.ordinal}") //JAN = 0

    print("name: ")
    println(january.declaringJavaClass.name) // prints: com.example.kotlinpractice.enums.Months

    print("canonicalName: ")
    println(january.declaringJavaClass.canonicalName) // prints: canonicalName: com.example.kotlinpractice.enums.Months

    print("simpleName: ")
    println(january.declaringJavaClass.simpleName) // prints: simpleName: Months

    print("typeName: ")
    println(january.declaringJavaClass.typeName) // prints: typeName: com.example.kotlinpractice.enums.Months

    val monthsConstants = Months.values()
    monthsConstants.forEach {
        print("${it.name} ")
    } // prints: JAN FAB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC
    println()

    val ordinalOfRed = RGB.RED.ordinal
    val blue = RGB.BLUE
    println(ordinalOfRed) // prints: 0
    blue.printColorName() // prints: BLUE
    blue.printHexValue() // prints: FF0000
}
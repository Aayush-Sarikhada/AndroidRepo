package com.example.kotlinpractice.aftersecondkt

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains practices i did after my SecondKT of kotlin language.
*/

interface Months {
    fun getDays(): Int
}

enum class FirstTwoMonths : Months {
    JAN {
        override fun getDays(): Int {
            return 31
        }
    },
    FAB {
        override fun getDays(): Int {
            return 29
        }
    }
}

@JvmInline
value class Password(private val pass: String) {
    init {
        println("init ran in inline class")
    }

    init {
        println("another init ran in inline class")
    }
}

fun main() {
    val teamAScore = Array(10) { 0 } // prints: Score of team 'A' for all 10 rounds
    val teamBScore = Array(10) { 0 } // prints: Score of team 'A' for all 10 rounds
    println(teamAScore === teamBScore)
    println(teamAScore.contentEquals(teamBScore))

    val patientATemperatureReadings = arrayListOf(36,37,104) // prints: morning, noon and evening temperature readings of patient 'A'
    val patientBTemperatureReadings = arrayListOf(35,64,36) // prints: morning, noon and evening temperature readings of patient 'B'
    println(patientATemperatureReadings)
    println(patientATemperatureReadings === patientBTemperatureReadings)
    println(patientATemperatureReadings == patientBTemperatureReadings)
}
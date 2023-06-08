package com.example.kotlinpractice.classesandobjects

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains one example of difference between Nested class and Inner class.
*/

class Laptop {
    val brandName = "Dell"

    inner class CPU {
        private val cpuManufacturerName = "intel"

        fun manufacturer(): String {
            return cpuManufacturerName
        }

        fun printWhereCpuIsFitted() {
            println("$cpuManufacturerName cpu is fitted in $brandName laptop")
        }
    }

    class OS {
        private val osName = "Windows"

        fun osName(): String {
            return osName
        }

        fun printWhereOSIsInstalled() {
            println("$osName OS is installed in ${Laptop().brandName} laptop")
        }
    }
}

fun main() {
    val osOfLaptop = Laptop.OS().osName()
    println(osOfLaptop) // prints: Windows

    // we have to create an object of outer class to actually access the inner nested class
    println(Laptop().CPU().manufacturer()) // prints: intel
}
package com.example.kotlinpractice.classesandobjects

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains example and notes of Interfaces in kotlin.
*/

// Note: interfaces in kotlin can have:
// 1. abstract methods or normal methods
// 2. abstract properties or computed properties(properties with custom getter and setters)

// NOTE: Properties declared in interfaces don't have backing fields

// Ex:1
interface Vehicle {
    fun run()
    fun stop() {
        println("stopping the vehicle")
    }

    val maxGear: Int // abstract property
    // initializers are not allowed in interface
}

// interface inheritance
// can provide implementation to abstract members and can add new methods and properties
interface ElectricVehicle : Vehicle {
    var batteryCapacityInMW: Int
    override val maxGear: Int get() = 0
}

class ElectricBike(override var batteryCapacityInMW: Int) : ElectricVehicle {
    var averageOfElectricBike: Int = 50
    override fun run() {
        println("electric bike is running...")
    }
}

// Resolving overriding conflicts
// When you declare many types in your supertype list, you may inherit more than one implementation of the same method:
interface LeftEngine {
    fun startEngine() {
        println("Left engine starting")
    }

    fun checkEngineCondition()
}

interface RightEngine {
    fun startEngine() {
        println("Right engine starting")
    }

    fun checkEngineCondition() {
        println("Right engine condition is ok")
    }
}

open class LeftEngineImpl : LeftEngine {
    override fun checkEngineCondition() {
        println("Left engine condition is ok")
    }
}

class EngineSystem : LeftEngine, RightEngine, LeftEngineImpl() {
    override fun startEngine() {
        super<LeftEngine>.startEngine()
        super<RightEngine>.startEngine()
    }

    override fun checkEngineCondition() {
        super<RightEngine>.checkEngineCondition()
        super<LeftEngineImpl>.checkEngineCondition()
    }
}

fun main() {
    val aircraftEngineSystem = EngineSystem()
    aircraftEngineSystem.startEngine()
    // prints:  Left engine starting
    // right engine starting
}
package com.example.kotlinpractice.classes_and_objects

//Note: interfaces in kotlin can have:
    //1. abstract methods or normal methods
    //2. abstract properties or computed properties(properties with custom getter and setters)

//NOTE: Properties declared in interfaces don't have backing fields

//ex:1
interface Vehicle{
    fun Run()
    fun Stop(){
        println("stopping the vehicle")
    }
    val maxGear:Int        //abstract property
                        //initializers are not allowed
}
//interface inheritance
//can provide implementation to abstract members and can add new methods and properties
interface ElectricVehicle: Vehicle{
    var batteryCapacityInMW:Int
    override val maxGear: Int get() = 0
}

class ElectricBike(override var batteryCapacityInMW: Int):ElectricVehicle{
    var a:Int = 19
    override fun Run() {

    }

}


//resolving conflicts

/*
Resolving overriding conflicts
When you declare many types in your supertype list, you may inherit more than one implementation of the same method:
*/

interface A {
    fun foo() { print("A") }
    fun bar()
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}

fun main(){

}

private var s:Int = 10
package com.example.kotlinpractice.classesandobjects

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains examples of Inheritance in kotlin.
*/

// by default interface's members are open
interface Shape {
    val vertices: Int

    fun draw() {
        println("drawing with shape interface")
    }
}

class Rectangle(override val vertices: Int = 4) : Shape {
    override fun draw() {
        println("drawing rectangle")
    }
}

class Polygon(override var vertices: Int) : Shape {
    override fun draw() {
        println("drawing polygon with $vertices vertices")
    }
}

// overriding rules in kotlin
// In Kotlin, implementation inheritance is regulated by the following rule: if a class inherits multiple implementations of the same member from its immediate superclasses, it must override this member and provide its own implementation (perhaps, using one of the inherited ones).
// ex:
open class GeometricShape {
    open fun draw() {
        println("drawing a geometric shape")
    }
}

// overriding rules ( if inheriting from multiple parents with same method name than we have to provide a new implementation in child class
class Circle(override val vertices: Int = 0) : GeometricShape(), Shape {
    override fun draw() {
        super<Shape>.draw()
        super<Shape>.draw()
        println("drawing a circle")
    }
}

fun main() {
    // You can also override a val property with a var property, but not vice versa. This is allowed because a val property essentially declares a get method, and overriding it as a var additionally declares a set method in the derived class.
    // Note that you can use the override keyword as part of the property declaration in a primary constructor:

    Circle().draw() // prints: "drawing with ShapeWithDraw"
    // "drawing with shapeInterface

    println(Rectangle().vertices) // prints: 4
    println(Polygon(5).vertices) // prints: 5
}
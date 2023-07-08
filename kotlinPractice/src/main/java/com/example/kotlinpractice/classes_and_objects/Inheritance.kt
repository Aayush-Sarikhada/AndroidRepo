package com.example.kotlinpractice.classes_and_objects

interface Shape{
    val vertices:Int
}

class Rectangle(override val vertices: Int = 4): Shape

class Polygon(override var vertices: Int = 0): Shape

open class BaseClass(var name:String){
    init {
        println("primary of base class")
    }
    open val size: Int =
        name.length.also { println("initializing size property in base class....") }
}

class ChildClass(
    name:String,
    val lastName:String
): BaseClass(name){
    init {
        println("primary of child class")
    }

    override val size: Int = (super.size + lastName.length).also { println("initializing child class") }
}
//This means that when the base class constructor is executed, the properties declared or overridden in the derived class have not yet been initialized. Using any of those properties in the base class initialization logic (either directly or indirectly through another overridden open member implementation) may lead to incorrect behavior or a runtime failure. When designing a base class, you should therefore avoid using open members in the constructors, property initializers, or init blocks.
//


//overriding rules in kotlin------------------------>
//In Kotlin, implementation inheritance is regulated by the following rule: if a class inherits multiple implementations of the same member from its immediate superclasses, it must override this member and provide its own implementation (perhaps, using one of the inherited ones).
//ex:
open class ShapeWithDraw(){
    open fun draw(){}
}

//bydefaykt interface's members are open
interface ShapeInterface{
    fun draw(){

    }
}

//overriding rules ( if inheriting from multiple parents with same method name than we have to provide a new implementation in child class
class InheritedClass: ShapeWithDraw(), ShapeInterface {
    override fun draw() {
        super<ShapeInterface>.draw()
        super<ShapeWithDraw>.draw()
    }
}

//custom getters and setters and backing fields
class DemoForGettersandSetters{
    var num:Int = 0        //initializer assigns a backing field (called "field" directly so if we want to use backing field we have to initialize the property first)
        set(value) {
            field = value * 10      //here using "num" instead of "field" will cause a recursive situation
        }
    //field identifier can only be used in the accessor of the property

}
//A backing field will be generated for a property if it uses the default implementation of at least one of the accessors, or if a custom accessor references it through the field identifier.
//
//For example, there would be no backing field in the following case:
//
//val isEmpty: Boolean
//    get() = this.size == 0

//backing properties
class DemoForBackingProperties{
    private var _age:Int = 0
    public var age:Int
    get() = _age
    set(value) {
        _age = value
    }
}


//
//To check whether a lateinit var has already been initialized, use .isInitialized on the reference to that property:
//
//if (foo::bar.isInitialized) {
//    println(foo.bar)
//}



fun main(){
//    You can also override a val property with a var property, but not vice versa. This is allowed because a val property essentially declares a get method, and overriding it as a var additionally declares a set method in the derived class.
//    Note that you can use the override keyword as part of the property declaration in a primary constructor:

}
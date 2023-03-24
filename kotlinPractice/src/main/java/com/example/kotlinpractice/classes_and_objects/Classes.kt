package com.example.kotlinpractice

    fun main(){
        //23/03/2023

        //classes & objects
        class Animal(val name: String) {
            val sound = "no-sound-1".also { println(it) }

            init {
                println("first init ran")
            }

            val sadSound = "no-sound-2".also { println(it) }

            init {
                println("second init ran")
            }
        }

        var animalObject = Animal(name = "dog")
        animalObject.name         //we can only have a constructor parameter as property if we have prefixed it in constructor as val/var
        // or if we assign it in a property in init block other wise they are only available in init block


        //If the constructor has annotations or visibility modifiers, the constructor keyword is required and the modifiers go before it:
        //
        //class Customer public @Inject constructor(name: String) { /*...*/ }


        //secondary constructors

        //If the class has a primary constructor, each secondary constructor needs to delegate to the primary constructor, either directly or indirectly through another secondary constructor(s). Delegation to another constructor of the same class is done using the this keyword:
        class Person(val name:String){
            var age:Int = 0

            init{       //just like the body of primary constructor
                println("primary constructor ran")
            }
            constructor(name:String,age:Int):this(name){
                this.age = age
                println("secondary constructor ran")
            }
        }

        //init blocks and primary constructor runs before secondary constructors
        var somePerson = Person("aayush",25)

//    If a non-abstract class does not declare any constructors (primary or secondary), it will have a generated primary constructor with no    arguments. The visibility of the constructor will be public.
//
//    If you don't want your class to have a public constructor, declare an empty primary constructor with non-default visibility:

        //no need to add "open" to inherit this class since it's an abstract class
        abstract class Polygon{
            abstract fun draw()
            init {
                println("polygons constructor called")
            }
        }

        class implOfPolygon: Polygon() {
            init {
                println("implementations constructor called")
            }
            override fun draw() {
                println("Drawing...")
            }
        }

        var impl = implOfPolygon()
        impl.draw()

        //we can override an "non-abstract" "open" member of a class to make it abstract
        open class normalClass {
            open fun someFun(){     //open over here is also mandatory to override this fun
                println("somefun ran...")
            }
        }

        abstract class nowMakingNormalClass: normalClass(){
            abstract override fun someFun()
        }


        //Companion objects
        //If you need to write a function that can be called without having a class instance but that needs access to the internals of a class (such as a factory method), you can write it as a member of an object declaration inside that class.
        //
        //Even more specifically, if you declare a companion object inside your class, you can access its members using only the class name as a qualifier.
    }



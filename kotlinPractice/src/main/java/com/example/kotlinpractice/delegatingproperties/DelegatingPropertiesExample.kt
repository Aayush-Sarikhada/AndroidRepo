package com.example.kotlinpractice.delegatingproperties

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains example for Delegating Properties.
*/

class CustomLazyProperty<T>(initializer: () -> T) : Lazy<T> {

    private var initializer: (() -> T)? = initializer
    private var _value: Any? = null
    override val value: T
        get() {
            if (_value == null) {
                _value = initializer!!()
                initializer = null
            }
            return _value as T
        }

    override fun isInitialized() = (_value != null)

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        return value
    }
}

class ObservableProperty<T>(
    initialValue: T,
    val afterChangeInProperty: (KProperty<*>, T, T) -> Unit,
    val beforeChangeInProperty: (KProperty<*>, T, T) -> Boolean
) : ObservableProperty<T>(initialValue) {
    private var value = initialValue

    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) =
        afterChangeInProperty(property, oldValue, newValue)

    override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T): Boolean {
        return beforeChangeInProperty(property, oldValue, newValue)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (beforeChange(property, this.value, value)) {
            this.value = value
        }
    }
}

fun main() {
    val iphonePrice by CustomLazyProperty { 100000 }
    println(iphonePrice) // prints: 100000

    var workLaptop: String by ObservableProperty(
        "Macbook air m2",
        afterChangeInProperty = { _, old, new ->
            println("$old -> $new")
        },
        beforeChangeInProperty = { prop, old, new ->
            println("before change")
            old.length < new.length
        })

    println(workLaptop) // Macbook air m2
    workLaptop = "Macbook pro m2"
    println(workLaptop) // Macbook pro m2

}
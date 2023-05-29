package com.example.kotlinpractice.exercieseGivenByMentor.paymentProcessingUsingDelegation

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class PaymentProcessor(private val paymentMethod: PaymentMethod): ReadOnlyProperty<Any?, PaymentProcessor> {

    fun processPayment(amount: Double) {
        println("processing the payment of $amount rupees using ${paymentMethod.paymentMethodName}")
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>) = PaymentProcessor(paymentMethod)

}
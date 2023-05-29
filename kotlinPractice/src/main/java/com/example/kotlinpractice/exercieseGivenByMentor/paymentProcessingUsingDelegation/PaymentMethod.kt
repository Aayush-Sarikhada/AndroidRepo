package com.example.kotlinpractice.exercieseGivenByMentor.paymentProcessingUsingDelegation

import com.example.kotlinpractice.exercieseGivenByMentor.ExerciseConstants

sealed class PaymentMethod {

    abstract fun pay()
    abstract val paymentMethodName: String

    object CreditCard: PaymentMethod() {
        override fun pay() {
            println("paying using credit card!")
        }

        override val paymentMethodName = ExerciseConstants.PAYMENT_METHOD_CREDITCARD
    }

    object UPI: PaymentMethod() {
        override fun pay() {
            println("paying using UPI!")
        }

        override val paymentMethodName = ExerciseConstants.PAYMENT_METHOD_UPI
    }

}
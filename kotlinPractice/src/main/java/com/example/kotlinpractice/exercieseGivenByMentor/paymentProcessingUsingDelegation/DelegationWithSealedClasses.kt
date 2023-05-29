package com.example.kotlinpractice.exercieseGivenByMentor.paymentProcessingUsingDelegation

import com.example.kotlinpractice.exercieseGivenByMentor.ExerciseConstants

/*
Created By: Aayush Sarikhada
Updated on: 22 may 2023

This file contains example of Delegation with sealed class.
*/

fun main() {

    val paymentMethod = PaymentMethod.CreditCard
    val paymentProcessor by PaymentProcessor(paymentMethod)

    paymentProcessor.processPayment(ExerciseConstants.MONEY_IN_RUPEES) // prints: processing the payment of 100.0 rupees using CreditCard

}
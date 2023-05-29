package com.example.kotlinpractice.problemspractices

import java.util.Collections
import kotlin.Comparator

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains example for different implementation of comparator and comparable interfaces.
*/

// Comparable is meant for objects with natural ordering which means the object itself must know how it is to be ordered.
// For example Roll Numbers of students.
// Whereas, Comparator interface sorting is done through a separate class.
// Logically, Comparable interface compares “this” reference with the object specified and Comparator in Java compares two different class objects provided.

data class Student(val id: Int, val name: String)

class CompareById : Comparator<Student> {
    override fun compare(p0: Student?, p1: Student?): Int {
        if (p0 == null || p1 == null) {
            throw IllegalArgumentException()
        }
        return if (p0.id == p1.id) 0 else if (p0.id > p1.id) 1 else -1
    }
}

data class Employee(val id: Int, val name: String, val salary: Int) : Comparable<Employee> {
    //naturally sortable using id
    override fun compareTo(other: Employee): Int {
        return if (id == other.id) 0 else if (id > other.id) 1 else -1
    }
}

class CompareBySalary : Comparator<Employee> {
    override fun compare(p0: Employee?, p1: Employee?): Int {
        if (p0 == null || p1 == null) {
            throw IllegalArgumentException()
        }
        return if (p0.salary == p1.salary) 0 else if (p0.salary > p1.salary) 1 else -1
    }
}

fun main() {
    val listOfStudents = listOf(
        Student(2, "Atul"),
        Student(3, "Aniket"),
        Student(1, "Aayush")
    )

    Collections.sort(listOfStudents, CompareById())

    listOfStudents.forEach {
        println(it)
    }
    // prints:
    // Student(id=1, name=Aayush)
    // Student(id=2, name=Atul)
    // Student(id=3, name=Aniket)

    val listOfOfficeEmployees = listOf(
        Employee(1, "Jack", 25000),
        Employee(2, "Lisa", 20000),
        Employee(3, "John", 15000),
        Employee(4, "Ajay", 10000)
    )

    Collections.sort(listOfOfficeEmployees, CompareBySalary())
    listOfOfficeEmployees.forEach {
        println(it)
    }
    // prints:
    // Employee(id=4, name=Ajay, salary=10000)
    // Employee(id=3, name=John, salary=15000)
    // Employee(id=2, name=Lisa, salary=20000)
    // Employee(id=1, name=Jack, salary=25000)
}
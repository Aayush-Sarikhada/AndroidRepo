package com.example.kotlinpractice.problemspractices;

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains code for diamond problem given by soham bhai.
*/

interface Animal {
    String typeOfAnimal(String nameOfAnimal);
}

interface Carnivorous {
    String getCarnivorous();
}

interface Herbivorous {
    String getHerbivorous();
}

abstract class AnimalType implements Animal, Carnivorous, Herbivorous {
    @Override
    public String typeOfAnimal(String nameOfAnimal) {
        if (nameOfAnimal == "TIGER") {
            return getCarnivorous();
        } else if (nameOfAnimal == "SHEEP") {
            return getHerbivorous();
        }
        return "Enter valid name of animal (try TIGER or SHEEP)";
    }

    @Override
    public String getCarnivorous() {
        return "Carnivorous";
    }

    @Override
    public String getHerbivorous() {
        return "Herbivorous";
    }
}

class AnimalTypeImpl extends AnimalType {
}

class DiamondProblem {
    public static void main(String[] args) {
        AnimalTypeImpl animalTypeGetter = new AnimalTypeImpl();
        System.out.println(animalTypeGetter.typeOfAnimal("TIGER")); // prints: Carnivorous
    }
}

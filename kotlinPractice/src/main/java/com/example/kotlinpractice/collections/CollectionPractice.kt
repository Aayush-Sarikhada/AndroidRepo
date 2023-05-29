package com.example.kotlinpractice.collections

import java.util.*
import kotlin.random.Random

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains notes and examples of collections in kotlin
*/

// types of collections : list, map and set
fun main() {
    // list
    // mutable
    var numbers = mutableListOf(11, 12, 13)
    numbers.add(14)
    println(numbers) // prints: 11 12 13 14

    //immutable
    val friends = listOf("Ajay, Mehsur, Rick")
    println(friends) // prints: Ajay Mehsur Rick

    //set
    //immutable
    val uniqueNumbers = setOf(1, 2, 3, 4, 6, 66, 6, 6, 6)
    println(uniqueNumbers) // prints: 1 2 3 4 6 66     //insertion order is preserved

    //mutable
    val productIdSet = mutableSetOf(0, 2, 4, 6, 8)
    println(productIdSet) // prints: 0 2 4 6 8
    productIdSet.add(36)
    println(productIdSet) // prints: 0 2 4 6 8 36

    //map
    //immutable
    val phonesPrice = mapOf(
        "iphone 13" to 57_000,
        "samsung s21" to 69_790,
        "oneplus 9 pro" to 64_999
    )
    println("All keys: ${phonesPrice.keys}") // All keys: [iphone 13, samsung s21, oneplus 9 pro]
    println("All values: ${phonesPrice.values}") // All values: [57000, 69790, 64999]

    //mutable
    val capitalOfCountries = mutableMapOf(
        "India" to "delhi",
        "USA" to "Washington DC",
        "UK" to "London",
        "Canada" to "Ottawa"
    )

    capitalOfCountries["South Korea"] = "Seoul"
    println(capitalOfCountries) // prints: {India=delhi, USA=Washington DC, UK=London, Canada=Ottawa, South Korea=Seoul}

    //construct of collections
    //used functions to create collections in above examples

    //using builder
    val alphabetsToPosition = buildMap {
        put("a", 1)
        put("b", 2)
        put("c", 3)
    }
    println(alphabetsToPosition) // prints: {a=1, b=2, c=3}

    val evenNumbers = List(3) { it * 2 } // or MutableList(){}
    println(evenNumbers) // prints: [0, 2, 4]

    val linkedListImplOfDoubledValuesList = LinkedList(evenNumbers)
    println(linkedListImplOfDoubledValuesList) // prints: [0, 2, 4]

    //Iterators
    val numbersIterator = numbers.iterator()
    while (numbersIterator.hasNext())
        print("${numbersIterator.next()} ") // prints: 11 12 13 14

    println()

    while (numbersIterator.hasNext()) {
        println("this won't be printed! because iterator can only iterate once")
        println(numbersIterator.next()) // not printed
    }

    for (item in numbers)
        print("$item ") // prints: 11 12 13 14

    println()

    numbers.forEach {
        print("$it ") // prints: 11 12 13 14
    }

    println()

    val numbersListIterator = numbers.listIterator()
    while (numbersListIterator.hasNext()) {
        print(numbersListIterator.next())   // prints: 111213
    }

    println()

    while (numbersListIterator.hasPrevious()) {
        print("${numbersListIterator.previous()} ") // prints: 14 13 12 11
    }

    println()

    // mutable iterators
    val numbersMutableIterator = numbers.listIterator()

    println("before: $numbers") // prints: "before: [11, 12, 13, 14]"
    numbersMutableIterator.next()
    numbersMutableIterator.remove()
    println("after removal $numbers") // prints: after removal [12, 13, 14]

    numbersMutableIterator.add(600)
    println("after adding $numbers") // prints: after adding [600, 12, 13, 14]

    numbersMutableIterator.next()
    numbersMutableIterator.set(900)
    println("after updating $numbers") // prints: after updating [600, 900, 13, 14]

    // ranges and progressions
    // allowed age range
    val ageRange = 1..12
    for (i in ageRange) {
        print("$i ") // prints: 1 2 3 4 5 6 7 8 9 10 11 12
    }

    println()

    for (i in 4 downTo 1)
        print("$i ") //prints: 4 3 2 1

    println()

    for (i in 1..100 step 10) print("$i ") // prints: 1 11 21 31 41 51 61 71 81 91
    println()

    for (i in 10 downTo 1 step 2) print("$i ") // prints: 10 8 6 4 2
    println()

    // from a function
    // creates infinite sequence but lazily
    val oddNumbers = generateSequence(0) { it * 2 + 1 }
    println(oddNumbers.take(5).toList()) // prints: [0, 1, 3, 7, 15]

    // to create finite return null
    val positionsOfSatellites = generateSequence(1) { if (it < 8) it + 2 else null }
    println(positionsOfSatellites.toList()) // prints: [1, 3, 5, 7, 9]

    //from chunks
    val oddNumbersFromChunks = sequence {
        yield(1)
        yieldAll(listOf(3, 5))
        yieldAll(generateSequence(7) { it + 2 })
    }
    println(oddNumbersFromChunks.take(6).toList())  // prints: [1, 3, 5, 7, 9, 11]

    // extension functions
    // 1. Transformations
    // 2. Filtering
    // 3. Plus and Minus
    // 4. Grouping
    // 5. Retrieving collection parts
    // 6. Retrieving single elements
    // 7. Ordering
    // 8. Aggregate operations
    // doesn't change the original collection

    // filter extension function
    var carBrands = listOf("BMW", "Honda", "Hyundai", "TATA", "Mahindra")
    carBrands.filter {
        it.length > 3
    }
    println("original: $carBrands") // prints: original: [BMW, Honda, Hyundai, TATA, MAHINDRA]

    val carBrandsFilteredByLength = carBrands.filter {
        it.length > 3
    }
    println("Car brand names longer than 3 characters: $carBrandsFilteredByLength") // prints: Car brand names longer than 3 characters: [Honda, Hyundai, TATA, MAHINDRA]

    //Operations with destinations (they have "To" postfix)
    val destinationFilteredCarBrands = mutableListOf<String>() // destination collection
    carBrands.filterTo(destinationFilteredCarBrands) {
        it.length > 3
    }

    // they append the result so above and below both are stored in destination here
    carBrands.filterIndexedTo(destinationFilteredCarBrands) { index, _ ->
        index == 0 || index == 2
    }
    println(destinationFilteredCarBrands) // prints: [Honda, Hyundai, TATA, Mahindra, BMW, Hyundai]

    // sort and sorted
    val sortedCarBrands = carBrands.sorted()
    println(carBrands == sortedCarBrands) // prints: false

    carBrands = carBrands.toMutableList()
    carBrands.sort()    // in-place sorting can happen because the list is now mutable
    println(carBrands == sortedCarBrands)  // prints: true

    // Collection transformation operations
    // 1. Mapping transformation
    val temperatures = setOf(1, 2, 3, 4, 5, 6, 7)
    println(temperatures.map { it * 4 }) // prints: [4, 8, 12, 16, 20, 24, 28]
    println(temperatures.mapIndexed { ind, i ->
        ind * i
    }) // prints: [0, 2, 6, 12, 20, 30, 42]

    println(temperatures.mapNotNull {
        if (it == 2) null else it * 4
    }) // prints: [4, 12, 16, 20, 24, 28]

    println(temperatures.mapIndexedNotNull { ind, i ->
        if (ind == 0) null else i
    }) // prints: [2, 3, 4, 5, 6, 7]

    //mapping maps
    val passwords = mapOf(
        "aniket" to "123Aniket",
        "aayush" to "aayush9001",
        "atul" to "atul0101"
    )

    println(passwords.mapKeys {
        it.key.uppercase()
    }) // prints: {ANIKET=123Aniket, AAYUSH=aayush9001, ATUL=atul0101}

    println(passwords.mapValues {
        it.value + it.key.length
    }) // prints: {aniket=123Aniket6, aayush=aayush90016, atul=atul01014}

    // zipping transformation function
    val colors = listOf("red", "brown", "gray")
    var animals = listOf("fox", "bear", "wolf")
    println(colors zip animals) // prints: [(red, fox), (brown, bear), (gray, wolf)]

    val carnivorousAnimals = listOf("Tiger", "Lion")
    println(colors.zip(carnivorousAnimals)) // prints: [(red, Tiger), (brown, Lion)]

    println(colors.zip(animals) { color, animal ->
        "The ${animal.replaceFirstChar { it.uppercase() }} is $color"
    }) // prints: [The Fox is red, The Bear is brown, The Wolf is gray]

    val mobileBrandsWithModelNames = listOf("Apple" to "Iphone", "Samsung" to "Galaxy", "Nokia" to "Lumia")
    println(mobileBrandsWithModelNames.unzip()) // prints: ([Apple, Samsung, Nokia], [Iphone, Galaxy, Lumia])

    // Association transformations
    // associateWith()
    println(carBrands.associateWith { it.length })  // prints: {BMW=3, Honda=5, Hyundai=7, Mahindra=8, TATA=4}

    // associateBy()
    println(carBrands.associateBy {
        it.first().uppercaseChar()
    }) // prints: {B=BMW, H=Hyundai, M=Mahindra, T=TATA}
    println(carBrands.associateBy(keySelector = {
        it.first().uppercaseChar()
    }, valueTransform = {
            it.length
        }
    )) // prints: {B=3, H=7, M=8, T=4}

    // Flattening transformation
    // Readings of some experiment per day
    val experimentReadings = listOf(setOf(11, 2, 36, 456), setOf(4, 56, 63, 7), setOf(1, 2))
    println(experimentReadings.flatten())   // prints: [11, 2, 36, 456, 4, 56, 63, 7, 1, 2]

    data class StringsContainer(val values: List<String>)

    val containers = listOf(
        StringsContainer(listOf("one", "two", "three")),
        StringsContainer(listOf("four", "five", "six")),
        StringsContainer(listOf("seven", "eight"))
    )

    // here in this lambda (internally called transform function) we provide a way to reach to internal collection
    // because this flatMap() is normally used when we have some custom structure in the collection and that
    // structure contains the collection object so we can't do it directly like flatten(). so we have to provide
    // a transform lambda with flatMap()
    // flatten() won't work with `containers`
    println(containers.flatMap {
        it.values
    }) // prints: [one, two, three, four, five, six, seven, eight]

    println(
        carBrands.joinToString(
            separator = " - ",
            prefix = "Start: ",
            postfix = " :End"
        )
    ) // prints: Start: BMW - Honda - Hyundai - Mahindra - TATA :End

    println(carBrands.joinToString(limit = 3, truncated = "<...>"))  // prints: BMW, Honda, Hyundai, <...>

    // 2. Filtering
    println(animals.filter {
        it.length > 3
    }) // prints: [bear, wolf]

    println(carBrands.filterIndexed { ind, i ->
        (ind != 0) && (i.length < 5)
    }) // prints: [TATA]

    println(phonesPrice.filterNot {
        it.value <= 60_000
    }) // prints: {samsung s21=69790, oneplus 9 pro=64999}

    val mixedTypesValues = listOf(null, 1, "two", 3.0, "four", null)
    println("All String elements in upper case: ")
    mixedTypesValues.filterIsInstance<String>().forEach {
        println(it.uppercase())
    } // TWO FOUR

    listOf(null, "one", "two", "three", null)
        .filterNotNull()
        .forEach {
            print("${it.length} ") // prints: 3 3 5
        }

    val studentsNames = listOf("Aniket", "Aayush", "Atul", "Rahul", "Divyang")
    val (studentsWithPrefixA, restOfStudents) = studentsNames.partition {
        it.first().uppercaseChar() == 'A'
    }
    println("Students whose names start with 'A': $studentsWithPrefixA") // prints: Students whose names start with 'A': [Aniket, Aayush, Atul]
    println("rest of the students: $restOfStudents")    // prints: rest of the students: [Rahul, Divyang]

    // Test predicates : any(), none() and all()

    println(studentsNames.any { it.endsWith("h") }) // prints: true
    println(studentsNames.none { it.endsWith("h") }) // prints: false
    println(studentsNames.all { it.endsWith("h") }) // prints: false

    // any() and none() can also be used to check the collection emptiness.
    val emptyCollections = emptyList<String>()
    println(emptyCollections.any()) // prints: false
    println(emptyCollections.none()) // prints: true

    // 3. plus and minus operators
    animals = animals.toMutableList()
    animals = animals + "Monkey"
    println(animals) // prints: [fox, bear, wolf, Monkey]
    animals = animals - "fox"
    println(animals) // prints: [bear, wolf, Monkey]

    // 4. Grouping
    // using groupBy()
    val booksTitles = listOf("Atomic habits", "Harry potter pt1", "Harry potter pt2", "12 rules for life")
    println(booksTitles.groupBy {
        it.first()
            .uppercase()
    }) // prints: {A=[Atomic habits], H=[Harry potter pt1, Harry potter pt2], 1=[12 rules for life]}

    println(booksTitles.groupBy(keySelector = {
        it.first()
    }, valueTransform = {
        it.uppercase()
    })) // prints: {A=[ATOMIC HABITS], H=[HARRY POTTER PT1, HARRY POTTER PT2], 1=[12 RULES FOR LIFE]}

    // groupingBy() and eachCount(),fold()
    println(booksTitles.groupingBy {
        it.first()
    }.eachCount()) // prints: {A=1, H=2, 1=1}

    // 5. Retrieving collection parts
    // slice()
    val ageList = (1..100).toList()
    println(ageList) // prints: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13...99, 100]
    println(ageList.slice(1..10)) // prints: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
    println(ageList.slice(0..40 step 4)) // prints: [1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41]
    println(ageList.slice(setOf(3, 5, 0))) // prints: [4, 6, 1]

    // take and drop
    println(ageList.take(10))   // prints: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    println(ageList.takeLast(10))  // prints: [91, 92, 93, 94, 95, 96, 97, 98, 99, 100]
    println(ageList.drop(10))   // prints: [11, 12, 13, 14, 15, 16, 17..., 99, 100]
    println(ageList.dropLast(10))   // prints: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10...90]

    println(ageList.takeWhile {
        it < 10
    }) // prints: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    println(ageList.takeLastWhile {
        it < 10
    }) // prints: []
    println(ageList.dropWhile {
        it < 50
    }) // prints: [50, 51, 52, 53..., 100]
    println(ageList.dropLastWhile {
        it > 50
    }) // prints: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10..., 49, 50]

    // chunked
    println(ageList.chunked(10)) // prints: [[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]..., [81, 82, 83, 84, 85, 86, 87, 88, 89, 90], [91, 92, 93, 94, 95, 96, 97, 98, 99, 100]]

    ageList.chunked(10).forEach {
        print("${it.average()}")
    } // prints: 5.5 15.5 25.5 35.5 45.5 55.5 65.5 75.5 85.5 95.5

    println("direct avg: ${ageList.average()}") // prints: direct avg: 50.5
    println("indirect avg: ${ageList.chunked(10) { it.average() }.average()}") // prints: indirect avg: 50.5

    // Windowed
    println(ageList.windowed(10))   // prints: [[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [2, 3, 4, 5, 6, 7, 8, 9, 10, 11]..., [90, 91, 92, 93, 94, 95, 96, 97, 98, 99], [91, 92, 93, 94, 95, 96, 97, 98, 99, 100]]
    val scoreList = (1..10).toList()
    println(
        scoreList.windowed(
            3,
            step = 2,
            partialWindows = true
        )
    ) // prints: [[1, 2, 3], [3, 4, 5], [5, 6, 7], [7, 8, 9], [9, 10]]
    println(scoreList.windowed(3) { it.sum() }) // prints: [6, 9, 12, 15, 18, 21, 24, 27]

    // zipWIthNext()
    println(scoreList.zipWithNext())   // prints: [(1, 2), (2, 3), (3, 4), (4, 5), (5, 6), (6, 7), (7, 8), (8, 9), (9, 10)]
    println(scoreList.zipWithNext { first, second ->   // prints: [false, false, false, false, false, false, false, false, false]
        first > second
    })

    // 6. Retrieving single elements
    // retrieve by position
    println(ageList.elementAt(10)) // prints: 11
    println(ageList.first()) // prints: 1
    println(ageList.last()) // prints: 100

    println(ageList.elementAtOrNull(101)) // null
    println(ageList.elementAtOrElse(101) {
        "The value for index $it is not available"
    }) // prints: The value for index 101 is not available

    // retrieve by condition
    // throws no such element exception if not found in the collection
    println(animals.first {
        it.startsWith('w')
    }) // prints: wolf

    println(animals.last {
        it.length > 3
    }) // prints: monkey

    println(animals.lastOrNull { it.length > 10 })  // prints: null
    // or firstOrNull

    // alias for firstOrNull() ==> find()
    // alias for lastOrNull() ==> findLast()

    // check element existence
    // contains() or `in`
    println(animals.contains("Tiger"))  // false
    println("panther" in animals)   // false

    // if there are elements at all
    // isEmpty() or isNotEmpty()

    // 7. Ordering
    // natural order : `Comparable` interface(compareTo function)

    class Version(val major: Int, val minor: Int) : Comparable<Version> {
        override fun compareTo(other: Version): Int {
            return when {
                this.major != other.major -> this.major compareTo other.major
                this.minor != other.minor -> this.minor compareTo other.minor
                else -> 0
            }
        }
    }

    println(Version(1, 2) > Version(1, 3)) // prints: false

    // custom orders: `Comparator` interface( compare() function)
    val lengthComparator = Comparator { str1: String, str2: String ->
        str1.length - str2.length
    }
    println(listOf("Fan", "AC", "Cooler").sortedWith(lengthComparator)) // prints: [AC, Fan, Cooler]

    // shorter way to define a Comparator is CompareBy()
    println(listOf("Fan", "AC", "Cooler").sortedWith(compareBy {
        it.length
    })) // prints: [AC, Fan, Cooler]

    // reverse order( reversed()-> creates a copy of collection and asReversed()->Does not create copy but create a view(means the change in original will be reflected on the copy
    println(animals.reversed()) // prints: [Monkey, wolf, bear]

    // 8. Aggregate functions
    val randomNumbers = mutableListOf<Int>()
    for (i in 1..10) randomNumbers.add(Random(1000).nextInt())

    println(randomNumbers)  // prints: [1811705957, 1811705957, 1811705957, 1811705957, 1811705957, 1811705957, 1811705957, 1811705957, 1811705957, 1811705957]
    println("Count: ${randomNumbers.count()}") // prints: Count: 10
    println("Max: ${numbers.maxOrNull()}") // prints: Max: 14
    println("Min: ${numbers.minOrNull()}") // prints: Min: 11
    println("Average: ${numbers.average()}") // prints: Average: 12.5
    println("Sum: ${numbers.sum()}") // prints: Sum: 50

    // fold and reduce
    println(randomNumbers.fold(0) { sum, i ->
        sum + i
    }) // prints: 937190386

    println(randomNumbers.reduce { sum, el ->
        sum + el
    }) // prints: 937190386

    println(randomNumbers.foldRight(0) { element, sum ->
        sum + element
    }) // prints: 937190386

    println(randomNumbers.reduceRight { el, sum ->
        sum + el
    }) // prints: 937190386

    println(randomNumbers.foldIndexed(0) { ind, sum, i ->
        sum + i + ind
    }) // prints: 937190431

    println(randomNumbers.reduceIndexed { ind, sum, el ->
        sum + el + ind
    }) // prints: 937190431

    println(randomNumbers.foldRightIndexed(0) { ind, element, sum ->
        sum + element + ind
    }) // prints: 937190431

    println(randomNumbers.reduceRightIndexed { ind, el, sum ->
        sum + el + ind
    }) // prints: 937190422

    // collection write operations
    // add
    numbers = (1..30).toMutableList()
    numbers.add(31)
    numbers.addAll(setOf(32, 33))
    println(numbers)  // prints: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33]

    numbers.addAll(5, listOf(0, 0, 0))
    println(numbers)  // prints: [1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33]

    numbers += 50
    numbers += listOf(51, 52, 53)
    println(numbers)  // prints: [1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 50, 51, 52, 53]

    // remove
    numbers.remove(53)
    println(numbers)  // prints: [1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 50, 51, 52]
    numbers.removeAll(setOf(50, 51, 52))
    numbers.retainAll((1..30).toList())
    println(numbers)  // prints: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30]

    numbers -= listOf(24, 25, 26)
    println(numbers)  // prints: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 27, 28, 29, 30]

    numbers.clear()
    println(numbers)  // prints: []

    // list-specific operations
    val cityNames = listOf("mumbai", "ahmedabad", "pune", "delhi")
    println(cityNames)    // prints: [mumbai, ahmedabad, pune, delhi]

    // retrieve element by index
    // different get()
    println(cityNames.get(3)) // prints: delhi
    println(cityNames[2]) // prints: pune
    println(cityNames.getOrNull(5)) // prints: null
    println("This index is out of bound") // prints: This index is out of bound

    // retrieving list parts
    val computerParts = mutableListOf("monitor", "CPU", "RAM", "GPU", "COOLING KIT")
    println(computerParts) // prints: [monitor, CPU, RAM, GPU, COOLING KIT]
    println(computerParts.subList(1, 3)) // prints: [CPU, RAM]

    // find element position in the list
    // linear search
    println("the position of monitor in list is: ${computerParts.indexOf("monitor")}") // prints: the position of monitor in list is: 0
    println("the position of CPU in list is: ${computerParts.lastIndexOf("CPU")}") // prints: the position of CPU in list is: 1

    println(computerParts.indexOfFirst { it.length > 3 }) // prints: 0
    println(computerParts.indexOfLast { it.length > 3 })  // prints: 4

    // binary search
    computerParts.sort()
    computerParts.binarySearch("CPU")

    // set specific operations
    println(productIdSet union setOf(2, 5, 6, 7)) // prints: [0, 2, 4, 6, 8, 36, 5, 7]
    println(productIdSet intersect setOf(2, 5, 6, 7)) // prints: [2, 6]
    println(productIdSet subtract setOf(2, 5, 6, 7)) // prints: [0, 4, 8, 36]

    // map Specific operations
    val idToEmailMap = mapOf(
        1 to "alex.tata@gmail.com",
        2 to "john.tata@gmail.com",
        3 to "drax.tata@gmail.com"
    )

    println(idToEmailMap.get(1)) // prints: alex.tata@gmail.com
    println(idToEmailMap[2]) // prints: john.tata@gmail.com
    println(idToEmailMap[3]) // prints: drax.tata@gmail.com
    println(idToEmailMap.getOrDefault(5, "No such key")) // prints: No such key

    println("All keys are: ${idToEmailMap.keys}") // prints: All keys are: [1, 2, 3]
    println("All values are: ${idToEmailMap.values}") // prints: All values are: [alex.tata@gmail.com, john.tata@gmail.com, drax.tata@gmail.com]

    // filter map
    val newMap = idToEmailMap.filter {
        it.value.length > 3
    }
    println(newMap) // prints: {1=alex.tata@gmail.com, 2=john.tata@gmail.com, 3=drax.tata@gmail.com}

    // filterKeys and filterValues
    println(idToEmailMap.filterKeys {
        it > 1
    }) // prints: {2=john.tata@gmail.com, 3=drax.tata@gmail.com}

    println(idToEmailMap.filterValues {
        it.length <= 3
    }) // prints: {}
}
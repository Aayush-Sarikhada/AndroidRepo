package com.example.kotlinpractice.problemspractices

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains code for scope function example with data classes and scope functions asked by mentor as Homework.
*/

// 1
data class Report(val title: String, val sections: List<Section>) {
    data class Section(val title: String, val paragraphs: List<Paragraph>) {
        data class Paragraph(val text: String)
    }
}

class ReportBuilder {
    var title: String = ""
    private val sections = mutableListOf<Report.Section>()

    fun withTitle(title: String) = apply { this.title = title }

    fun section(title: String, block: SectionBuilder.() -> Unit) {
        val section = SectionBuilder(title)
        block.invoke(section)
        sections.add(section.buildSection())
    }

    fun buildSection(): Report {
        return Report(title, sections)
    }

    inner class SectionBuilder(private val title: String) {
        private val paragraphs = mutableListOf<Report.Section.Paragraph>()

        fun addParagraphToSection(text: String) {
            paragraphs.add(Report.Section.Paragraph(text))
        }

        fun buildSection(): Report.Section {
            return Report.Section(title, paragraphs)
        }
    }
}

fun printReport(report: Report) {
    print("<-------${report.title}------->").also {
        report.sections.forEach { section ->
            println()
            println(section.title).also {
                println()
                section.paragraphs.forEach { paragraph ->
                    println(paragraph.text)
                }
            }
        }
    }
}

fun main() {
    val report = ReportBuilder().apply {
        withTitle("Kotlin Learning")
        section("Classes and Objects") {
            addParagraphToSection("The class declaration consists of the class name, the class header (specifying its type parameters," +
                    " the primary constructor, and some other things)," +
                    " and the class body surrounded by curly braces. Both the header and the body are optional;\n if the class has no body," +
                    " the curly braces can be omitted.")
            addParagraphToSection("A class in Kotlin can have a primary constructor and one or more secondary constructors." +
                    " The primary constructor is a part of the class header, and it goes after the class name and optional type parameters.")
        }
        section("Object expressions and declarations") {
            addParagraphToSection("Sometimes you need to create an object that is a slight modification of some class, without explicitly declaring a new subclass for it." +
                    " Kotlin can handle this with object expressions and object declarations.")
            addParagraphToSection("Object expressions create objects of anonymous classes, that is, classes that aren't explicitly declared with the class declaration.\n" +
                    "Such classes are useful for one-time use. You can define them from scratch, inherit from existing classes, or implement interfaces." +
                    " Instances of anonymous classes are also called anonymous objects because they are defined by an expression, not a name.")
            addParagraphToSection(
                "Using anonymous objects as return and value types\n" +
                        "When an anonymous object is used as a type of a local or private but not inline declaration (function or property)," +
                        " all its members are accessible via this function or property:"
            )
        }
    }.buildSection()
    printReport(report)
    // prints:
    // <-------Kotlin Learning------->
    // Classes and Objects
    //
    // The class declaration consists of the class name, the class header (specifying its type parameters, the primary constructor, and some other things), and the class body surrounded by curly braces. Both the header and the body are optional;
    // if the class has no body, the curly braces can be omitted.
    // A class in Kotlin can have a primary constructor and one or more secondary constructors. The primary constructor is a part of the class header, and it goes after the class name and optional type parameters.
    //
    // Object expressions and declarations
    //
    // Sometimes you need to create an object that is a slight modification of some class, without explicitly declaring a new subclass for it. Kotlin can handle this with object expressions and object declarations.
    // Object expressions create objects of anonymous classes, that is, classes that aren't explicitly declared with the class declaration.
    // Such classes are useful for one-time use. You can define them from scratch, inherit from existing classes, or implement interfaces. Instances of anonymous classes are also called anonymous objects because they are defined by an expression, not a name.
    // Using anonymous objects as return and value types
    // When an anonymous object is used as a type of a local or private but not inline declaration (function or property), all its members are accessible via this function or property:
}
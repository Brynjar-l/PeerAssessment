package isl.priv.brynj

import parser.RatingParser


fun main() {
    val dict = setOf(
        "Brynjar",
        "Ignas",
    )

    val myParser = RatingParser(dict)

    val (students, errors) = myParser.parse(
        "Ignas+-",
        "Ignas++++-",
        "Brynjar++",
        "Gibberish",
        "Gibberish+++",
        "Brynjar+++f",
        "Brynjar++d",
        "Brynjar+++",
    )

    students.forEach(::println)
    println()

    errors.forEach {
        println("text: '${it.text}',\t --> msg: '${it.errMsg}'")
    }
}













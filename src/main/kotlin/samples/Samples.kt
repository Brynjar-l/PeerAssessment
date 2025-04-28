package isl.priv.brynj.samples

import parser.RatingParser

fun sampleUsageOfRatingParser() {
    val myDictionary = setOf("Name1", "Name2", "Name3") // dictionary used to validate legitimate student names.
    val myParser = RatingParser(myDictionary) // new parser instances with above-defined dictionary as arg.

    // ok: List of ParseResult objects.
    // err: List of ParseError objects.
    val (ok, err) = myParser.parse(
        "Name1+++",
        "Name3--",
    )

    ok.forEach(::println)   // Successfully parsed students
    err.forEach(::println)  // Errors for unmatched or invalid inputs
}
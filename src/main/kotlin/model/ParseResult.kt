package model


/**
 *  class representing the results from a parsing.
 *
 *  @property students is a list of successfully parsed textRatings, contained in a [StudentRating] object.
 *  @property errors is a list of failed parsings, contained in a [ParseError] object.
 */
data class ParseResult(
    val students: List<StudentRating>,
    val errors: List<ParseError>
)
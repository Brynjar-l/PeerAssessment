package parser

import extensions.toRating
import model.ParseError
import model.ParseResult
import model.StudentRating
import java.util.Locale

/**
 *  Class used to parse plain-text student performance ratings into [StudentRating].
 *
 *  Takes a dictionary of valid student names to validate input textRatings.
 *  Once the dictionary has been set, use the [parse] method to input an arbitrary number of textRatings to be parsed and converted to [StudentRating]
 *
 *  All failed parsings will be saved as a [ParseError] object and returned alongside the results, contained within a [ParseResult].
 *
 *  @sample isl.priv.brynj.samples.sampleUsageOfRatingParser
 *
 *  @property dictionary Set of names considered legitimate, to ensure that no textRating is assigned to a non-existing student.
 *
 *  @see ParseError
 *  @see ParseResult
 *  @see parse
 */
class RatingParser(val dictionary: Set<String>) {

    /** transformed text within dictionary: Set<String> to lower_snake_case format */
    private val normalizedDictionary = dictionary.map { it.normalized }.toSet()

    /**
     *  Parses an arbitrary number of textRatings, validates by matching names to the instance variable [dictionary].
     *
     *  @param textRatings any number of Strings with the format "<student_name><'+' to '+++' || '-' to '---' || 'o'>"
     *  @return a [ParseResult] containing lists of successful parses (students), and accumulated error from failed parsings (errors).
     */
    fun parse(vararg textRatings: String): ParseResult {

        val parseErrors = mutableListOf<ParseError>()
        val students = buildList {

            for (textRating in textRatings) {
                require(textRating.isNotBlank()) { "Input must not be blank" }

                try {
                    add(convertToStudentRating(textRating))
                } catch (e: IllegalArgumentException) {
                    parseErrors.add(ParseError(textRating, e.message, e::class))
                } catch (e: NoSuchElementException) {
                    parseErrors.add(ParseError(textRating, e.message, e::class))
                }
            }
        }

        return ParseResult(students, parseErrors.toList())
    }


    /**
     *  Returns a [StudentRating], if name exists in dictionary and rating is valid, else throw exception.
     *
     *  @throws IllegalArgumentException if the rating section includes more than one type of rating-character.
     *  @throws NoSuchElementException if the name does not exist in the dictionary.
     */
    private fun convertToStudentRating(textRating: String): StudentRating {
        val name = isolateName(textRating)
        val rating = isolateRating(textRating)

        if (name.normalized !in normalizedDictionary) throw NoSuchElementException("{$name} not in Dictionary")
        return StudentRating(name, rating.toRating())
    }

    /** transforms Text to lower_snake_case format */
    internal val String.normalized: String
        get() = lowercase(Locale.ROOT)
            .replace(" ", "_")
            .replace("-", "_")
}
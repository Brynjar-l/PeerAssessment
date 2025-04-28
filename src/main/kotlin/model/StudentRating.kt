package model

/**
 *  Represents a student and their peer-assigned rating.
 *
 *  @property name The name of the student.
 *  @property rating The student's current rating.
 */
data class StudentRating(
    val name: String,
    val rating: Rating
) {

    /** Returns a new StudentRating instance with the [rating] increased by 1. */
    operator fun inc() = StudentRating(name, rating.inc())

    /** Returns a new StudentRating instance with the [rating] decreased by 1. */
    operator fun dec() = StudentRating(name, rating.dec())

    override fun toString(): String {
        return "$name: ${rating.value}"
    }
}
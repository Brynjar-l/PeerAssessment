package model

import constants.Constants.MAX_RATING
import constants.Constants.MIN_RATING
import constants.Constants.RATING_REG


/**
 *  Represents a rating score.
 *
 *  Ratings range from -3 (worst) to +3 (best), inclusive.
 *  Ratings can be incremented (++) or decremented (--) safely within these bounds.
 *
 *  @property value Number representing the rating value.
 *  @throws IllegalArgumentException if [value] is outside range [-3, 3].
 */
@JvmInline
value class Rating private constructor(val value: Int) {

    companion object {
        fun from(seq: String): Rating {
            require(RATING_REG.matches(seq)) { "Invalid rating sequence '$seq'" }

            val score = when (seq[0]) {
                '+' -> seq.length
                '-' -> -seq.length
                else -> 0
            }
            return Rating(score)
        }
    }

    /** Returns a new Rating instance with the [value] increased by 1 or equal to its upper bound */
    operator fun inc() = Rating((value + 1).coerceAtMost(MAX_RATING))

    /** Returns a new Rating instance with the [value] decreased by 1 or equal to its lower bound */
    operator fun dec() = Rating((value - 1).coerceAtLeast(MIN_RATING))
}


package constants

object Constants {
    val RATING_REG = Regex("^([+-]{1,3}|[oO0])$")
    const val MAX_RATING = 3
    const val MIN_RATING = -3
}
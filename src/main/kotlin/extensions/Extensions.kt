package extensions

import model.Rating


/**
 *  shorthand method to convert String to [Rating]
 *
 *  The string should be a 1-3 character long sequence of either '+' or '-', but never a mix of the two.
 */
fun String.toRating(): Rating = Rating.from(this)
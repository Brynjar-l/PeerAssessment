package parser

import constants.Constants.MAX_RATING


/**
 *  Returns the 'name' section of a textRating.
 *
 *  A section is considered to be in the 'name' section as long as no rating characters show up, {'+'. '-'}.
 *  Once a rating character is first encountered, return all previous characters.
 */
internal fun isolateName(text: String): String {
    val whileName: (Char) -> Boolean = { it != '+' && it != '-' }

    return text.takeWhile(whileName)
}

/**
 *  Returns only the rating section of a textRating.
 *
 *  The rating section is identified by a 0-to-3 character long sequence of either '+' or '-', at the end of a string.
 *  The function will only look at the first 3 rating characters, and dismiss the rest, i.e. '+++++' is equivalent to '+++'.
 *  Returns 'o', which represents neutral rating; in case no rating-sequence was found.
 *
 *  @throws IllegalArgumentException if the rating section includes both the rating characters, '+' and '-', mixed.
 */
internal fun isolateRating(text: String): String {

    val isNotRatingChar: (Char) -> Boolean = { it != '+' && it != '-' }
    val isRatingChar: (Char) -> Boolean = { it == '+' || it == '-' }
    val trimmedText = text.trim()

    // results in something like "+++", or "--"
    val rating = trimmedText
        .dropWhile(isNotRatingChar)  // remove chars while they are not a part of the rating-sequence.
        .takeWhile(isRatingChar)     // take chars while they are a part of the rating-sequence.
        .take(MAX_RATING)       // take only the first [sequenceLength] chars; discard the rest.

    // invariant: no mixing rating-characters
    require(!(rating.contains('+') && rating.contains('-'))) { "Mixing '+' and '-' is not permitted." }


    val afterRaw = trimmedText
        .dropWhile { it != '+' && it != '-' } // skip to rating
        .drop(rating.length)                  // skip the rating itself


    val isTrailingCharacters = !(afterRaw.all { it.isWhitespace() })
    // invariant: no trailing characters after a rating-sequence
    if (isTrailingCharacters) { require(afterRaw.all { it == rating[0] }) }


    return rating.ifEmpty { "o" }
}
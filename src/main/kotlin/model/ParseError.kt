package model

import kotlin.reflect.KClass

/**
 *  Class representing a parse error.
 *
 *  @property text is the raw text that was attempted to be parsed, but failed.
 *  @property errMsg is the error message returned from the exception thrown.
 *  @property errType is the class of the error emitted.
 */
data class ParseError(
    val text: String,
    val errMsg: String?,
    val errType: KClass<out Throwable>,
)
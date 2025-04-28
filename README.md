# Student-Rating Parser

Small Kotlin project to parse plain-text student peer ratings into structured data. <br>
Made mostly for fun and practice.
<br><br>

## Structure

`RatingParser` — main class for parsing a list of text ratings.

`StudentRating` & `Rating` — small immutable models.

`ParseResult` & `ParseError` — returned from parsing.

*Helper functions* to isolate names and rating sequences from raw text.
<br><br>


## Example

`RatingParser` requires a 'dictionary' to validate plain-text ratings against. Dictionaries are of the type *Set<String>*.
 
```Kotlin
val myParser = RatingParser(setOf("Name1", "Name2"))

val (ok, err) = parser.parse(
    "Name1++",
    "Name2---",
    "UnknownName++"      // <--- will result in a new `ParseError` object in 'err'. 
)
```

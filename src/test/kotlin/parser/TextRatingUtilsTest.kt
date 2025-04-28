package parser

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Isolate Utility Functions")
class TextRatingUtilsTest {

    @Nested
    @DisplayName("isolate(text: String): String")
    inner class IsolateName {

        @Test
        fun `space-separated name`() {
            assertEquals("Spaced Name", isolateName("Spaced Name+++"))
        }

        @Test
        fun `extended rating-sequence`() {
            assertEquals("PlusThree", isolateName("PlusThree+++++++"))
        }

        @Test
        fun `space-separated rating`() {
            assertEquals("NameWithRatingSeparated", isolateName("NameWithRatingSeparated +++")) // leaves white-space
        }

        @Test
        fun `ideal rawText format`() {

            assertEquals("PlusThree", isolateName("PlusThree+++"))
            assertEquals("PlusTwo", isolateName("PlusTwo++"))
            assertEquals("PlusOne", isolateName("PlusOne+"))

            assertEquals("PlusNone", isolateName("PlusNone"))

            assertEquals("MinusOne", isolateName("MinusOne-"))
            assertEquals("MinusTwo", isolateName("MinusTwo--"))
            assertEquals("MinusThree", isolateName("MinusThree---"))
        }

        @Test
        fun `'o' indicating neutral rating`() {
            assertEquals("PlusO", isolateName("PlusO o"))
        }
    }

    @Nested
    @DisplayName("isolateRating(text: String): String")
    inner class IsolateRating {

        /*
         *  characters after rating seq
         *  rating at the start
         *  mixed rating seq
         *
         */

        @Test
        fun `space-separated name`() {
            assertEquals("+++", isolateRating("Spaced Name+++"))
        }

        @Test
        fun `extended rating-sequence`() {
            assertEquals("+++", isolateRating("PlusThree+++++++"))
        }

        @Test
        fun `space-separated rating`() {
            assertEquals("+++", isolateRating("NameWithRatingSeparated +++")) // leaves white-space
        }

        @Test
        fun `ideal rawText format`() {

            assertEquals("+++", isolateRating("PlusThree+++"))
            assertEquals("++", isolateRating("PlusTwo++"))
            assertEquals("+", isolateRating("PlusOne+"))

            assertEquals("o", isolateRating("PlusNone"))

            assertEquals("-", isolateRating("MinusOne-"))
            assertEquals("--", isolateRating("MinusTwo--"))
            assertEquals("---", isolateRating("MinusThree---"))
        }

        @Test
        fun `'o' indicating neutral rating`() {
            assertEquals("o", isolateRating("PlusO o"))
        }
    }

}
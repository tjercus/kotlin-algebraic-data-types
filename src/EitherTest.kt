import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EitherTest {

  @Test
  fun map() {
    val uppercasedWrapped = Either.of("blah")
      .map { value -> (value as String).toUpperCase() }
      .inspect()
    assertEquals("Right(BLAH)", uppercasedWrapped);

    val nullWrapped = Left.of("blah")
      .map { value -> (value as String).toUpperCase() }
      .inspect()
    assertEquals("Left(blah)", nullWrapped);
  }

  @Test
  fun inspect() {
    // Either is Right biased, so it uses Right when not Left or Right
    assertEquals("Right(blah)", Either.of("blah").inspect());
    assertEquals("Left(blah)", Left.of("blah").inspect());
    assertEquals("Right(blah)", Right.of("blah").inspect());
  }

  @Test
  fun unwrap() {
    val extractedRight = Right.of("blah")
      .map { value -> (value as String).toUpperCase() }
      .unwrap(
        {value -> "No map on a Left: $value"},
        {value -> "mapped a Right: $value"})
    assertEquals("mapped a Right: BLAH", extractedRight)

    val extractedLeft = Left.of("blah")
      .map { value -> (value as String).toUpperCase() }
      .unwrap(
        {value -> "No map on a Left: $value"},
        {value -> "mapped a Right: $value"})
    assertEquals("No map on a Left: blah", extractedLeft)
  }

  @Test
  fun chain() {
    val uppercasedExtracted = Either.of("blah")
      .chain { value -> (value as String).toUpperCase() }
    assertEquals("BLAH", uppercasedExtracted);

    val nullWrapped = Left.of("blah")
      .chain { value -> (value as String).toUpperCase() }
    assertTrue(nullWrapped.toString().contains("Left@"));
  }
}

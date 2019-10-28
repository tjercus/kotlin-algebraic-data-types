import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class JustTest {

  @Test
  fun unwrap() {
    val extractedFromJust = maybeOf("Tjerk").unwrap({}, { value -> "$value"})
    assertEquals("Tjerk", extractedFromJust)
    val extractedFromNothing = maybeOf(null).unwrap({ value -> "$value"}, {})
    assertEquals("nothing", extractedFromNothing)
  }

  @Test
  fun inspect() {
    assertEquals("Just(Tjerk)", maybeOf("Tjerk").inspect());
    assertEquals("Nothing", maybeOf(null).inspect());
  }

  @Test
  fun map() {
    val uppercasedWrapped = maybeOf("Tjerk").map { value -> (value as String).toUpperCase() }.inspect()
    assertEquals("Just(TJERK)", uppercasedWrapped);
  }
}

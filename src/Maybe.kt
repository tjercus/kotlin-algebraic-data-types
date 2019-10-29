
// Just is the 'right' side of the Maybe and holds a non-null mappable value
class Just(private val value: Any) : Monad {
  override fun unwrap(whenLeft: (Any?) -> Any, whenRight: (Any) -> Any): Any = whenRight(value)

  override fun inspect(): String = "Just(${value})"

  override fun map(fn: (Any?) -> Any): Just = Just(fn(value))

  override fun chain(fn: (Any?) -> Any): Any = fn(value)
}

// Nothing is the 'left' side of the Maybe and holds nothing but still can be mapped over safely
class Nothing() : Monad {
  override fun unwrap(whenLeft: (Any?) -> Any, whenRight: (Any) -> Any): Any = whenLeft("nothing")

  override fun inspect(): String = "Nothing"

  override fun map(fn: (Any?) -> Any): Nothing = this

  override fun chain(fn: (Any?) -> Any): Any = this
}

// Helper function to create a 'Maybe' from a nullable value so you can safely map at it until you weigh an ounce
fun maybeOf(value: Any?): Monad = if (value == null) Nothing() else Just(value)

// TODO port some JS usage examples to Kotlin

fun main(args: Array<String>) {
  println(
    "out: " + maybeOf("Tjerk")
      .chain { value -> (value as String).toUpperCase() }
  )
}

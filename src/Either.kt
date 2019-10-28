
// Either is Right biased, so it uses Right when not specified a Left or Right
open class Either(open val value: Any) : Functor {
  override fun map(fn: (Any?) -> Any): Functor = Right(fn(value))

  override fun inspect(): String = "Either(${value})"

  override fun unwrap(whenLeft: (Any?) -> Any, whenRight: (Any) -> Any): Any = whenRight(value)

  companion object {
    fun of(value: Any): Right = Right(value)
  }
}

// Left is the not-mapping passive container
class Left(override val value: Any) : Either(value) {
  override fun inspect(): String = "Left(${value})"

  override fun unwrap(whenLeft: (Any?) -> Any, whenRight: (Any) -> Any): Any = whenLeft(value)

  override fun map(fn: (Any?) -> Any): Either = this

  companion object {
    fun of(value: Any): Left = Left(value)
  }
}

// Right is the mapping, active, happy-flow, container
class Right(override val value: Any) : Either(value) {
  override fun inspect(): String = "Right(${value})"

  companion object {
    fun of(value: Any): Right = Right(value)
  }
}

//fun eitherOf(whenLeft: (Any?) -> Any, whenRight: (Any) -> Any, func: Functor): Any {
//  when(func) {
//    is Left -> whenLeft(func.cata({value -> "" + value}, {}))
//    is Right -> whenRight({}, func.cata({value -> "" + value})
//  }
//}

fun main() {
  println(Either.of("blah")
    .map { value -> (value as String).toUpperCase() }
    .inspect())

  println(Left.of("Something not ok, not mapping on a Left")
    .map { value -> (value as String).toUpperCase() }
    .inspect())

  println(Left.of("blah")
    .map { value -> (value as String).toUpperCase() }
    .unwrap(
      {value -> "No map on a Left: $value"},
      {value -> "map on a Right: $value"}))
}

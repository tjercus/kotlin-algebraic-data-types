interface Functor {
  fun map(fn: (Any?) -> Any): Functor
  fun inspect(): String
  // 'unwrap' is sometimes formally named 'cata' for 'catamorphism'
  fun unwrap(whenLeft: (Any?) -> Any, whenRight: (Any) -> Any): Any
}

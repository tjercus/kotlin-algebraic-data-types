interface Functor {
  fun map(fn: (Any?) -> Any): Functor
  fun inspect(): String
  // TODO use a better name for 'cata'
  fun unwrap(whenLeft: (Any?) -> Any, whenRight: (Any) -> Any): Any
}

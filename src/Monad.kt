interface Monad : Functor {
  // apply a function and return the unwrapped result
  // 'chain' is sometimes called 'flatMap'
  fun chain(fn: (Any?) -> Any): Any
}

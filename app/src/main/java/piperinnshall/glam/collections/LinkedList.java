package piperinnshall.glam.collections;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import piperinnshall.glam.tuple.Tuple0;
import piperinnshall.glam.tuple.Tuple2;

public interface LinkedList<T> {
  <R> R match(Supplier<R> onEmpty, BiFunction<T, LinkedList<T>, R> onCons);
  default LinkedList<T> add(T elem) {
    return (Cons<T>) s -> s.apply(elem, this);
  }
  default T getHead(T orElse) {
    return match(() -> orElse, (head, _) -> head);
  }
  default LinkedList<T> getTail(LinkedList<T> orElse) {
    return match(() -> orElse, (_, tail) -> tail);
  }
  default <R> R fold(R acc, BiFunction<R, T, R> f) {
    return match(
        () -> acc,
        (head, tail) -> tail.fold(f.apply(acc, head), f));
  }
  default Tuple0 forEach(Consumer<T> c) {
    return match(
        () -> (Tuple0) this,
        (head, tail) -> {
          c.accept(head);
          return tail.forEach(c);
        });
  }
  default LinkedList<T> reverse() {
    return reverseHelper(this, (LinkedListEmpty<T>) s -> s);
  }
  private static <T> LinkedList<T> reverseHelper(LinkedList<T> list, LinkedList<T> acc) {
    return list.match(
        () -> acc,
        (head, tail) -> reverseHelper(tail, acc.add(head)));
  }
  default LinkedList<T> filter(Predicate<T> p) {
    return filterHelper(p, this, (LinkedListEmpty<T>) s -> s).reverse();
  }
  private static <T> LinkedList<T> filterHelper(Predicate<T> p, LinkedList<T> list, LinkedList<T> acc) {
    return list.match(
        () -> acc,
        (head, tail) -> filterHelper(p, tail, p.test(head) ? acc.add(head) : acc));
  }
  /**
   * Returns the concatenation of this list with {@code other}.
   * The implementation builds the result in reverse during recursion,
   * so the returned {@code LinkedList<T>} is in reverse order.
   */
  default LinkedList<T> concat(LinkedList<T> other) {
    return match(
        () -> other,
        (head, tail) -> tail.concat(other).add(head)
    );
  }
}

interface Cons<T> extends Tuple2<T, LinkedList<T>>, LinkedList<T> {
  default <R> R match(Supplier<R> onEmpty, BiFunction<T, LinkedList<T>, R> onCons) {
    return onCons.apply(this.a(), this.b());
  }
}

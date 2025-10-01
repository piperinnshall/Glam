package piperinnshall.glam.collections;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import piperinnshall.glam.tuple.Tuple0;

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

    /** Fold left: processes from head to tail */
    default <R> R foldL(R orElse, BiFunction<R, T, R> f) {
        return match(
            () -> orElse,
            (head, tail) -> tail.foldL(f.apply(orElse, head), f)
        );
    }

    /** Fold right: processes from tail to head */
    default <R> R foldR(R orElse, BiFunction<T, R, R> f) {
        return match(
            () -> orElse,
            (head, tail) -> f.apply(head, tail.foldR(orElse, f))
        );
    }

    /** forEach using foldL and functional Tuple0. */
    default Tuple0 forEach(Consumer<T> c) {
        return foldL(v -> v, (acc, t) -> { c.accept(t); return acc; });
    }

    /** reverse using foldL */
    default LinkedList<T> reverse() {
        LinkedList<T> empty = (LinkedListEmpty<T>) s -> s;
        return foldL(empty, (acc, t) -> acc.add(t));
    }

    /** filter using foldR */
    default LinkedList<T> filter(Predicate<T> p) {
        LinkedList<T> empty = (LinkedListEmpty<T>) s -> s;
        return foldR(empty, (head, acc) -> p.test(head) ? acc.add(head) : acc);
    }

    /** concat using foldR */
    default LinkedList<T> concat(LinkedList<T> other) {
        return foldR(other, (head, acc) -> acc.add(head));
    }

    /** max using foldL */
    default T max(java.util.Comparator<T> cmp, T orElse) {
        return foldL(orElse, (acc, next) -> cmp.compare(next, acc) > 0 ? next : acc);
    }

}

interface Cons<T> extends LinkedList<T>, piperinnshall.glam.tuple.Tuple2<T, LinkedList<T>> {
    default <R> R match(Supplier<R> onEmpty, BiFunction<T, LinkedList<T>, R> onCons) {
        return onCons.apply(this.a(), this.b());
    }
}


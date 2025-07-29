package piperinnshall.glam.collections;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import piperinnshall.glam.tuple.Tuple0;

public interface LinkedListEmpty<T> extends Tuple0,LinkedList<T>{
  default <R> R match(Supplier<R>onEmpty, BiFunction<T,LinkedList<T>,R>onCons){
    return onEmpty.get();
  }
}

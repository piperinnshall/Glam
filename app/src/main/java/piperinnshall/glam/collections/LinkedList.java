package piperinnshall.glam.collections;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

import piperinnshall.glam.tuple.Tuple0;
import piperinnshall.glam.tuple.Tuple2;

public interface LinkedList<T> {
  <R> R match(Supplier<R>onEmpty, BiFunction<T,LinkedList<T>,R>onCons);
  default LinkedList<T> add(T elem){
    return (Cons<T>)s->s.apply(elem,this);
  }
  default T getHead(T orElse){
    return match(()->orElse,(head,_)->head);
  }
  default LinkedList<T> getTail(LinkedList<T> orElse){
    return match(()->orElse,(_,tail)->tail);
  }
  default Tuple0 forEach(Consumer<T> c) {
    return match(
      () -> (Tuple0)this,
      (head, tail) -> {c.accept(head);return tail.forEach(c);} 
    );
  }
}

interface Cons<T> extends Tuple2<T,LinkedList<T>>, LinkedList<T>{
  default <R> R match(Supplier<R>onEmpty, BiFunction<T,LinkedList<T>,R>onCons){
    return onCons.apply(this.a(),this.b());
  }
}


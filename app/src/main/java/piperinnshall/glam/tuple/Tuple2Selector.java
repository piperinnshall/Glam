package piperinnshall.glam.tuple;

public interface Tuple2Selector<A, B> {
  Tuple2Union<A, B> apply(A a, B b);
}

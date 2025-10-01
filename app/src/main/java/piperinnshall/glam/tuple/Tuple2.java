package piperinnshall.glam.tuple;

public interface Tuple2<A, B> {
  Tuple2Union<A, B> apply(Tuple2Selector<A, B> sel);
  default A a() {
    Tuple2Selector<A, B> sel = (a, _) -> (Tuple2FromA<A, B>) () -> a;
    return this.apply(sel).toA();
  }
  default B b() {
    Tuple2Selector<A, B> sel = (_, b) -> (Tuple2FromB<A, B>) () -> b;
    return this.apply(sel).toB();
  }
}
interface Tuple2FromA<A, B> extends Tuple2Union<A, B> { A toA(); }
interface Tuple2FromB<A, B> extends Tuple2Union<A, B> { B toB(); }

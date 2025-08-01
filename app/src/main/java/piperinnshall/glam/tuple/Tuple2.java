package piperinnshall.glam.tuple;

public interface Tuple2<A, B> {
  public static <A, B> Tuple2<A, B> of(A a, B b) {
    return sel -> sel.apply(a, b);
  }
  Tuple2Union<A, B> apply(Tuple2Selector<A, B> sel);
  default A a() {
    Tuple2Selector<A, B> sel = (a, _) -> (FromA<A, B>) () -> a;
    return this.apply(sel).toA();
  }
  default B b() {
    Tuple2Selector<A, B> sel = (_, b) -> (FromB<A, B>) () -> b;
    return this.apply(sel).toB();
  }
}
interface FromA<A, B> extends Tuple2Union<A, B> { A toA(); }
interface FromB<A, B> extends Tuple2Union<A, B> { B toB(); }

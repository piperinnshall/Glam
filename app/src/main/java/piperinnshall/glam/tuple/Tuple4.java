package piperinnshall.glam.tuple;

public interface Tuple4<A, B, C, D> {
    Tuple4Union<A, B, C, D> apply(Tuple4Selector<A, B, C, D> sel);
    default A a() {
        Tuple4Selector<A, B, C, D> sel = (a, _, _, _) -> (Tuple4FromA<A, B, C, D>) () -> a;
        return this.apply(sel).toA();
    }
    default B b() {
        Tuple4Selector<A, B, C, D> sel = (_, b, _, _) -> (Tuple4FromB<A, B, C, D>) () -> b;
        return this.apply(sel).toB();
    }
    default C c() {
        Tuple4Selector<A, B, C, D> sel = (_, _, c, _) -> (Tuple4FromC<A, B, C, D>) () -> c;
        return this.apply(sel).toC();
    }
    default D d() {
        Tuple4Selector<A, B, C, D> sel = (_, _, _, d) -> (Tuple4FromD<A, B, C, D>) () -> d;
        return this.apply(sel).toD();
    }
}
interface Tuple4FromA<A, B, C, D> extends Tuple4Union<A, B, C, D> { A toA(); }
interface Tuple4FromB<A, B, C, D> extends Tuple4Union<A, B, C, D> { B toB(); }
interface Tuple4FromC<A, B, C, D> extends Tuple4Union<A, B, C, D> { C toC(); }
interface Tuple4FromD<A, B, C, D> extends Tuple4Union<A, B, C, D> { D toD(); }

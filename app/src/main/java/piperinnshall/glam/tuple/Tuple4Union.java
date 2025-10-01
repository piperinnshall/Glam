package piperinnshall.glam.tuple;

public interface Tuple4Union<A, B, C, D> {
  default A toA() { return this.toA(); }
  default B toB() { return this.toB(); }
  default C toC() { return this.toC(); }
  default D toD() { return this.toD(); }
}


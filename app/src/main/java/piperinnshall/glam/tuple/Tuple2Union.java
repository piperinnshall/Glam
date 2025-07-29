package piperinnshall.glam.tuple;

public interface Tuple2Union<A,B>{
  default A toA(){return this.toA();}
  default B toB(){return this.toB();}
}

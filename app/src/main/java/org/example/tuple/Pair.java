package org.example.tuple;

public interface Pair<A,B>{
  public static <A,B> Pair<A,B> of(final A a,final B b){
    return sel -> sel.apply(a,b);
  }
  AorB<A,B> apply(Selector<A,B> sel);
  default A a(){
    final Selector<A,B> sel=(a,_)->(FromA<A,B>)()->a;
    return this.apply(sel).toA();
  }
  default B b(){
    final Selector<A,B> sel=(_,b)->(FromB<A,B>)()->b;
    return this.apply(sel).toB();
  }
}
interface AorB<A,B>{
  default A toA(){return this.toA();}
  default B toB(){return this.toB();}
}
interface FromA<A,B> extends AorB<A,B>{A toA();}
interface FromB<A,B> extends AorB<A,B>{B toB();}
interface Selector<A,B>{AorB<A,B> apply(A a, B b);}

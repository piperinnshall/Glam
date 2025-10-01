package piperinnshall.glam.tokenizer;

import java.util.function.Supplier;
import java.util.stream.Stream;

import piperinnshall.glam.tuple.Tuple2;
import piperinnshall.glam.tuple.Tuple4;

interface LazyState extends Tuple2<Supplier<Stream<String>>, InternalState> {
  default Supplier<Stream<String>> lines() { return a(); }
  default InternalState state() { return b(); }
}

interface InternalState extends Tuple4<Token, String, Integer, Integer> {
  default Token token() { return a(); }
  default String line() { return b(); }
  default int lineNum() { return c(); }
  default int colNum() { return d(); }
}

@FunctionalInterface
public interface TokenizerLazy {

  abstract LazyState current();

  default TokenizerLazy hasNext() {
    return this::current;
  }

  default TokenizerLazy peekNext() {
    return this::current;
  }

  default TokenizerLazy popNext() {
    int lineNum = current().state().lineNum();
    int colNum = current().state().colNum();

    current().lines()
      .get()
      .findFirst();
    // .map(this::findPreset);
    // .map(this::evaluate)
    // .map();

    return this::current;
  }

  default int evaluate(String line) {
    return line.indexOf(' ');
  }

  // default Token findPreset(){};

  /*default TokenizerLazy popNext() throws IOException {
    Stream<String> lines = current().lines();
    BufferedReader reader = current().reader();

    String line = current().state().line().isEmpty()
    ? reader.readLine();
    :

    String line = lines
    .findFirst()
    .orElse(null);

    int lineNum = current().state().lineNum();
    int colNum = current().state().colNum();

    int tokenLength = 0;
    int lineNumNext = lineNum;
    int colNumNext = colNum;

    String token = line.substring(0, tokenLength);
    String consumed = line.substring(tokenLength, line.length());

    Token t = sel -> sel.apply(token, TokenType.INVALID, colNum, lineNum + tokenLength);

    InternalState state = sel -> sel.apply(t, consumed, lineNumNext, colNumNext);
    return () -> sel -> sel.apply(lines, state);
    } */
}


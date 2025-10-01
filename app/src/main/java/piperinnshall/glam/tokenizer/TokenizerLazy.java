package piperinnshall.glam.tokenizer;

import java.io.BufferedReader;
import java.io.IOException;

import piperinnshall.glam.tuple.Tuple2;
import piperinnshall.glam.tuple.Tuple4;

@FunctionalInterface
public interface TokenizerLazy {

    abstract LazyState current();

    static interface LazyState extends Tuple2<BufferedReader, InternalState> {
        default BufferedReader reader() { return a(); }
        default InternalState state() { return b(); }
    }

    static interface InternalState extends Tuple4<Token, String, Integer, Integer> {
        default Token token() { return a(); }
        default String line() { return b(); }
        default int lineNum() { return c(); }
        default int colNum() { return d(); }
    }
    
    static InternalState empty = sel -> sel.apply(
        s -> s.apply("", TokenType.INVALID, 0, 0),
        "",
        0,
        0);

    default TokenizerLazy hasNext() {
        return this::current;
    }

    default TokenizerLazy peekNext() throws IOException {
        return this::current;
    }

    default TokenizerLazy popNext() throws IOException {
        BufferedReader reader = current().reader();
        String line = reader.readLine();
        int lineNum = current().state().lineNum();
        int colNum = current().state().colNum();

        int tokenLength = 0; /* some method that gets the length of the next token */
        int lineNumNext = lineNum;
        int colNumNext = colNum;

        String token = line.substring(0, tokenLength);
        String consumed = line.substring(tokenLength, line.length());

        Token t = sel -> sel.apply(token, TokenType.INVALID, colNum, lineNum + tokenLength);

        InternalState state = sel -> sel.apply(t, consumed, lineNumNext, colNumNext);
        return () -> sel -> sel.apply(reader, state);
    }
}


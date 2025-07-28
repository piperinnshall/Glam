package org.example.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public interface Tokenizer {
  public static Tokenizer of(Path p) {
    return ()->switch(0){default ->{
      try{yield Files.newBufferedReader(p,Charset.forName("UTF-8"));}
      catch(IOException e){e.printStackTrace();yield null;}}
    };
  }

  abstract BufferedReader reader();

  default String tokenize() {
    return reader().lines()
      .collect(Collectors.joining(System.lineSeparator()));
  }
}

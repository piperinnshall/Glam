package org.example.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public interface Resource {
  abstract String file();
  default Path path(){return Path.of(uri(file()));}
  private URI uri(String file){
     return switch(0){default ->{
      try{yield Resource.class.getClassLoader().getResource(file).toURI();}
      catch(URISyntaxException e){yield null;}}
    };
  }
}

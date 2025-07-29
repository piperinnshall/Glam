package piperinnshall.glam.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public interface Resource {
  abstract String file();
  default Path get(){return Path.of(uri(file()));}
  private URI uri(final String file){
     return switch(0){
       default->{
        try{yield Resource.class.getClassLoader().getResource(file).toURI();}
        catch(final URISyntaxException _){yield null;}
       }
    };
  }
}

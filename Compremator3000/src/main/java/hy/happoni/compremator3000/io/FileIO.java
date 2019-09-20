// pakkaus
package hy.happoni.compremator3000.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Luokka, joka huolehtii tekstitiedoston lukemisesta ja luomisesta.
 */
public class FileIO {
    
    /**
     * Metodi, jolla luetaan tekstitiedosto merkkijonoksi.
     * 
     * @param filepath - tekstitiedoston polku
     * @return - tekstitiedostosta haettu merkkijonoesitys
     */
    public String readFile(String filepath) {
        
        StringBuilder builder = new StringBuilder();
        
        try (Stream<String> stream = Files.lines( Paths.get(filepath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> builder.append(s));
        }
        catch (IOException e) {
            e.getMessage();
            System.out.println(e);
        }
        return builder.toString();
    }
    
}

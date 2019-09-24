// pakkaus
package hy.happoni.compremator3000.io;

// tarvittavat importit
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Luokka, joka huolehtii tekstitiedoston lukemisesta ja luomisesta.
 */
public class FileIO {

    /**
     * Metodi, jolla luetaan tekstitiedosto tavulistaksi.
     *
     * @param filePath - tekstitiedoston polku/nimi
     * @return - tekstitiedostosta haettu tavulista
     */
    public static byte[] readFile(String filePath) {
        byte[] input = null;
        Path path = Paths.get(filePath);

        try {
            input = Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return input;
    }

    /**
     * Metodi kirjoittaa annetut tavut tiedostoon. Voidaan käyttää sekä pakatun tekstin kirjoittamiseen tiedostoksi että
     * puretun tiedoston kirjoittamiseksi tekstitiedostoksi. Pakatessa metodi lisää sen nimen perään päätteen sen algoritmin
     * mukaan, jolla se on pakattu (.lz, .lzw, .lzss).
     * 
     * @param encoded - tavulista, joka sisältää pakatun tiedoston "koodin"
     * @param filepath - tiedoston polku/nimi
     * @param algoType - algoritmin, jolla pakkaus tehty, tiedoston pääte
     * @return - palauttaa true, jos kirjoitus onnistui, muulloin false
     * @throws java.io.IOException
     */
    public static boolean writeFile(byte[] encoded, String filepath, String algoType) throws IOException {
        File file = new File(filepath + algoType);

        try {
            FileOutputStream os = new FileOutputStream(file);
            os.write(encoded);
            System.out.println("File wrote succesfully.");
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Something has gone wrong.");
        }
        return false;
    }
}

// pakkaus
package hy.happoni.compremator3000.io;

// tarvittavat importit
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Luokka, joka huolehtii tekstitiedoston lukemisesta ja luomisesta.
 */
public class FileIO {

    /**
     * Metodi, jolla luetaan pakattava tekstitiedosto.
     *
     * @param filePath - tekstitiedoston nimi
     * @return - tekstitiedosto merkkijonona
     */
    public String readFile(String filePath) {
        String input = "";
        try {
            input = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Something went wrong, maybe file is not found.");
        }
        return input;
    }

    public char[] readFileToCharArray(String filePath) {
        String s = "";
        try {
            s = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Something went wrong, maybe file is not found.");
        }
        char[] input = new char[s.length()];
        for (int i = 0; i < input.length; i++) {
            input[i] = s.charAt(i);
        }
        return input;
    }

    /**
     * Pakatun tiedoston lukeminen. Lukee tektitiedoston byte arrayksi. Heittää
     * IOExceptionin, jos lukeminen ei onnistu.
     *
     * @param filePath - tiedoston nimi
     * @return - tekstitiedosto byte arrayna, jos lukeminen ei onnistu,
     * palauttaa null
     */
    public byte[] readFileToByteArray(String filePath) {
        byte[] input = null;

        try {
            input = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Something went wrong, maybe file is not found.");
        }
        return input;
    }

    /**
     * Pakatun tiedoston kirjoittaminen tiedostoon. Heittää IOExceptionin, jos
     * kirjoittaminen ei onnistu.
     *
     * @param compressedData - byte arrayna pakattu tiedosto
     * @param filePath - tiedoston nimi
     * @param algoType - algoritmista riippuva pääte pakattavan tiedoston nimeen
     * @return - true, jos kirjoittaminen onnistui, muuten false
     */
    public boolean writeCompressedFile(byte[] compressedData, String filePath, String algoType) {
        try {
            Files.write(Paths.get(filePath + algoType), compressedData);
            return true;
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
        return false;
    }

    /**
     * Puretun tiedoston kirjoittaminen tiedostoon. Kirjoittaa merkkijonon
     * puretun tiedoston mukaan nimettyyn tiedostoon. Heittää IOExceptionin, jos
     * kirjoittaminen ei onnistu.
     *
     * @param uncompressed - merkkijonona purettu tiedosto
     * @param filepath - tiedoston nimi
     * @return - true, jos kirjoittaminen onnistuu, muuten false
     */
    public boolean writeFile(String uncompressed, String filepath) {
        try {
            Files.write(Paths.get(filepath), uncompressed.getBytes());
            return true;
        } catch (IOException e) {
            System.out.println("Something has gone wrong.");
        }
        return false;
    }

    public boolean writeBytesToFile(byte[] uncompressed, String filepath) {
        try {
            Files.write(Paths.get(filepath), uncompressed);
            return true;
        } catch (IOException e) {
            System.out.println("Something has gone wrong.");
        }
        return false;
    }
}

package hy.happoni.compremator3000.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Luokka, joka huolehtii tekstitiedoston lukemisesta ja luomisesta.
 */
public class FileIO {

    /**
     * Pakatun tiedoston lukeminen. Lukee tekstitiedoston byte arrayksi. Heittää
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
     * Metodilla kirjoitetaan byte array tiedostoon. Käytetään puretun tiedoston
     * kirjoittamiseen.
     *
     * @param uncompressed - purettu tiedosto byte arrayna
     * @param filepath - tiedoston nimi, johon purettu data tallennetaan
     * @return - true, jos tallennus onnistuu, muuten false
     */
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

package hy.happoni.compremator3000.ui;

import java.util.Scanner;                                                       // Scannerin import käyttäjän syötteiden lukua varten.

/**
 * Tekstikäyttöliittymästä huolehtiva luokka.
 */
public class UI {

    private final Scanner reader;                                               // tarvittavia muuttujia
    private final AppLogic logic;
    private String algoType;

    public UI(AppLogic logic, Scanner reader) {
        this.reader = reader;
        this.logic = logic;
        algoType = "";
    }

    /**
     * Ohjelman käyttöliittymän toiminnan käynnistävä metodi. Käytännössä
     * tulostaa otsikon ja kutsuu käyttöliittymän main menu -metodia.
     */
    public void startApp() {
        System.out.println("COMPREMATOR3000");                                  // Ohjelman otsikko
        System.out.println("Text compression program using LZ77, LZW and/or LZSS.");
        mainMenu();
    }

    /**
     * Metodi tulostaa main menun ohjetekstin ja huolehtii valinnan syötöstä
     * eteenpäin. Käyttäjän valinnoilla 1-2 kutsutaan ao. metodia ja valinnalla
     * x katkaistaan silmukan suoritus, eli käytännössä lopetetaan ohjelman
     * suoritus.
     */
    private void mainMenu() {
        while (true) {                                                          // Main menun ohjeet.
            System.out.println();                                               // Epäkelvolla syötteellä silmukka jatkaa pyörimistään,
            System.out.println("What would you like to do?");                   // kunnes annetaan kelvollinen syöte.
            System.out.println("[1] -- Compress text file.");
            System.out.println("[2] -- Uncompress compressed file.");
            System.out.println("[x] -- Exit program");

            System.out.print("> ");
            String choice = reader.nextLine();

            if (choice.equals("1")) {
                compressFile();
            } else if (choice.equals("2")) {
                uncompressFile();
            } else if (choice.equals("x")) {
                break;
            }
        }
    }

    /**
     * Tiedoston pakkaaminen. Valitaan ensin algoritmi ja annetaan tiedoston
     * nimi. Kutsutaan sovelluslogiikan metodia compress.
     */
    public void compressFile() {
        algoType = chooseAlgorithm();
        System.out.print("Insert name of the text file to be compressed: ");
        String fileName = reader.nextLine();

        logic.compress(fileName, algoType);
    }

    /**
     * Tiedoston purkaminen. Annetaan tiedoston nimi ja kutsutaan
     * sovelluslogiikan metodia uncompress.
     */
    public void uncompressFile() {
        System.out.print("Insert name of the file to be uncompressed: ");
        String fileName = reader.nextLine();
        logic.uncompress(fileName);
    }

    /**
     * Apumetodi, jolla valitaan pakkaamiseen käytettävä algoritmi. Käytännössä
     * muuttaa merkkijonomuuttujaa käyttäjän syötteen mukaan (1 = lz, 2 = lzw).
     *
     * @return - käyttäjän syötteen mukaan joko "lz" tai "lzw".
     */
    public String chooseAlgorithm() {
        while (true) {
            System.out.println();
            System.out.println("What algorithm shall we use?");
            System.out.println("[1] -- LZ77");
            System.out.println("[2] -- LZW");

            System.out.print("> ");
            String choice = reader.nextLine();

            if (choice.equals("1")) {
                return "lz77";
            } else if (choice.equals("2")) {
                return "lzw";
            }
        }
    }
}

package hy.happoni.compremator3000.ui;

// Scannerin import käyttäjän syötteiden lukua varten.
import java.util.Scanner;

/**
 * Tekstikäyttöliittymästä huolehtiva luokka.
 */
public class UI {

    // tarvittavia muuttujia
    private Scanner reader;
    private AppLogic logic;
    private String algoType;

    // konstruktori
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
        // Ohjelman otsikko
        System.out.println("COMPREMATOR3000");
        System.out.println("Text compression program using LZ77, LZW and/or LZSS.");
        mainMenu();
    }

    /**
     * Metodi tulostaa main menun ohjetekstin ja huolehtii valinnan syötöstä
     * eteenpäin. Käyttäjän valinnoilla 1-3 kutsutaan ao. metodia ja valinnalla
     * x katkaistaan silmukan suoritus, eli käytännössä lopetetaan ohjelman
     * suoritus.
     */
    private void mainMenu() {
        // Main menun ohjeet. Epäkelvolla syötteellä silmukka jatkaa pyörimistään, kunnes annetaan kelvollinen syöte.
        while (true) {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("[1] -- Insert text to demonstrate compression results.");
            System.out.println("[2] -- Compress text file.");
            System.out.println("[3] -- Uncompress compressed file.");
            System.out.println("[x] -- Exit program");

            System.out.print("> ");
            String choice = reader.nextLine();

            if (choice.equals("1")) {
                compressOwnText();
            } else if (choice.equals("2")) {
                compressFile();
            } else if (choice.equals("3")) {
                uncompressFile();
            } else if (choice.equals("x")) {
                break;
            }
        }
    }

    /**
     * Oman tekstin demopakkaaminen. Kutsutaan ensin apumetodia, jolla valitaan
     * pakkaamiseen käytettävä algoritmi. Kysytään käyttäjältä syötettä ja
     * algoritmivalinnan mukaan kutsutaan sovelluslogiikan metodia.
     */
    public void compressOwnText() {
        algoType = chooseAlgorithm();
        System.out.println("Insert text to be compressed: ");
        String userText = reader.nextLine();

        if (algoType.equals("lz")) {
            logic.demoLZ(userText);
        } else if (algoType.equals("lzw")) {
            logic.demoLZW(userText);
        } else if (algoType.equals("lzss")) {
            logic.demoLZSS(userText);
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
     * muuttaa merkkijonomuuttujaa käyttäjän syötteen mukaan (1 = lz, 2 = lzw, 3
     * = lzss).
     *
     * @return - käyttäjän syötteen mukaan joko "lz", "lzw" tai "lzss".
     */
    public String chooseAlgorithm() {
        while (true) {
            System.out.println();
            System.out.println("What algorithm shall we use?");
            System.out.println("[1] -- LZ77");
            System.out.println("[2] -- LZW");
            System.out.println("[3] -- LZSS");

            System.out.print("> ");
            String choice = reader.nextLine();

            if (choice.equals("1")) {
                return "lz";
            } else if (choice.equals("2")) {
                return "lzw";
            } else if (choice.equals("3")) {
                return "lzss";
            }
        }
    }
}

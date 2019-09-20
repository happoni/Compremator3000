// Pakkaus
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

    // konstruktori
    public UI(AppLogic logic, Scanner reader) {
        this.reader = reader;
        this.logic = logic;
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
     * eteenpäin.
     *
     * @return choice - käyttäjän antama syöte. 1:llä ja 2:lla kutsutaan ao.
     * metodeja, x:llä lopetetaan ohjelman suoritus ja muilla syötteillä
     * annetaan ohjeet ja kysytään syötettä uudestaan.
     */
    private void mainMenu() {
        // Main menun ohjeet. Epäkelvolla syötteellä silmukka jatkaa pyörimistään, kunnes annetaan kelvollinen syöte.
        while (true) {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("[1] -- Use test text \"TOBEORNOTTOBEORTOBEORNOT\"");
            System.out.println("[2] -- Insert your own text to be compressed");
            System.out.println("[3] -- Read text file and compress it.");
            System.out.println("[x] -- Exit program");

            System.out.print("> ");
            String choice = reader.nextLine();

            if (choice.equals("1")) {
                useTestText();
            } else if (choice.equals("2")) {
                insertOwnText();
            } else if (choice.equals("3")) {
                readFile();
            } else if (choice.equals("x")) {
                break;
            }
        }
    }

    /**
     * Metodin avulla luetaan tekstitiedosto ja saadaan siitä merkkijono.
     */
    private void readFile() {
        // Pyydetään tiedoston polku käyttäjältä.
        System.out.println("Please insert name of text file:");
        String filePath = reader.nextLine();
        // tähän tarvittaessa syötteen validointi!

        String fileText = logic.readFile(filePath);
        
        // Jatketaan käyttöliittymässä eteenpäin.
        choiceCompressMethod(fileText);
    }

    /**
     * Asettaa pakattavaksi tekstiksi testitekstin "TOBEORNOTTOBEORTOBEORNOT".
     */
    private void useTestText() {
        choiceCompressMethod("TOBEORNOTTOBEORTOBEORNOT");
    }

    /**
     * Käyttöliittymän osa, jossa kysytään, mitä algoritmia valitun tekstin
     * pakkaamiseen käytetään. Tulostaa alkuun pakattavan tekstin ja antaa
     * valita LZ77:n (1), LZW:n(2), LZSS:n(3) tai kaikki nämä (4). x:llä metodin
     * suoritus loppuu. Metodi kutsuu valinnan mukaan sovelluslogiikan ao.
     * metodia. Epäkelpo syöte johtaa ohjetekstin uudelleentulostukseen.
     *
     * @param text - Teksti, joka pakataan. Joko käyttäjän syöttämä tai valmis
     * testiteksti.
     */
    private void choiceCompressMethod(String text) {
        // Valintaikkunan ohjeteksti.
        while (true) {
            System.out.println("Text is \"" + text + "\"");
            System.out.println("What algorithm shall we use to compress it?");
            System.out.println("[1] -- LZ77");
            System.out.println("[2] -- LZW");
            System.out.println("[3] -- LZSS");
            System.out.println("[4] -- Compress with all.");
            System.out.println("[x] -- Back to main menu.");

            System.out.print("> ");
            String choice = reader.nextLine();

            // Valinnan mukaan kutsutaan ao. metodeja.
            if (choice.equals("1")) {
                logic.runLZ(text);
                break;
            } else if (choice.equals("2")) {
                logic.runLZW(text);
                break;
            } else if (choice.equals("3")) {
                logic.runLZSS(text);
                break;
            } else if (choice.equals("4")) {
                logic.runAll(text);
            } else if (choice.equals("x")) {
                break;
            }
        }
    }

    /**
     * Metodi, jolla asetetaan käyttäjän oma syöte pakattavaksi tekstiksi.
     */
    private void insertOwnText() {
        // Pyydetään syöte käyttäjältä.
        System.out.println("Please insert your own text to compress:");
        String userText = reader.nextLine();
        // tähän tarvittaessa syötteen validointi!

        // Jatketaan käyttöliittymässä eteenpäin.
        choiceCompressMethod(userText);
    }

}

// Pakkaus
package hy.happoni.compremator3000.ui;

import java.util.Scanner;

/**
 * Tekstikäyttöliittymästä huolehtiva luokka.
 */
public class UI {

    private Scanner reader;
    private AppLogic logic;

    public UI(AppLogic logic, Scanner reader) {
        this.reader = reader;
        this.logic = logic;
    }

    public void startApp() {
        System.out.println("COMPREMATOR3000");
        System.out.println("Text compression program using LZ77, LZW and/or LZSS.");

        mainMenu();
        System.out.println();

    }

    /**
     * Metodi tulostaa main menun ohjetekstin ja huolehtii valinnan syötöstä
     * eteenpäin.
     *
     * @return choice - käyttäjän antama syöte. Muut kuin 0, 1 tai 2 hylätään.
     */
    private void mainMenu() {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("[1] -- Use test text \"TOBEORNOTTOBEORTOBEORNOT\"");
            System.out.println("[2] -- Insert your own text to be compressed");
            System.out.println("[x] -- Exit program");

            System.out.print("> ");
            String choice = reader.nextLine();

            if (choice.equals("1")) {
                useTestText();
            } else if (choice.equals("2")) {
                insertOwnText();
            } else if (choice.equals("x")) {
                break;
            }
        }
    }

    /**
     * Metodi...
     */
    private void useTestText() {
        while (true) {
            System.out.println("Text is \"TOBEORNOTTOBEORTOBEORNOT\"");
            System.out.println("What algorithm shall we use to compress it?");
            System.out.println("[1] -- LZ77");
            System.out.println("[2] -- LZW");
            System.out.println("[3] -- LZSS");
            System.out.println("[4] -- Compress with all.");
            System.out.println("[x] -- Back to main menu.");

            System.out.println("> ");
            String choice = reader.nextLine();
            String testText = "TOBEORNOTTOBEORTOBEORNOT";
            
            if (choice.equals("1")) {
                useLZ(testText);
            } else if (choice.equals("2")) {
                useLZW(testText);
            } else if (choice.equals("3")) {
                useLZSS(testText);
            } else if (choice.equals("4")) {
                useAll(testText);
            } else if (choice.equals("x")) {
                mainMenu();
            }
        }
    }


    
    /**
     * Metodi...
     */
    private void insertOwnText() {

    }

}

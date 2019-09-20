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
            System.out.println();
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

    private void useTestText() {
        choiceCompressMethod("TOBEORNOTTOBEORTOBEORNOT");
    }
    
    /**
     * Metodi...
     */
    private void choiceCompressMethod(String text) {
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
            
            if (choice.equals("1")) {                
                useLZ(text);
                break;
            } else if (choice.equals("2")) {
                useLZW(text);
                break;
            } else if (choice.equals("3")) {
                useLZSS(text);
                break;
            } else if (choice.equals("4")) {
                useAll(text);
            } else if (choice.equals("x")) {
                break;
            }
        }
    }

    /**
     * Metodi...
     * @param text 
     */
    private void useLZ(String text) {
        logic.runLZ(text);
    }
    
    /**
     * Metodi...
     * @param text 
     */
    private void useLZW(String text) {
        logic.runLZW(text);
    }
    
    /**
     * Metodi...
     * @param text 
     */
    private void useLZSS(String text) {
        logic.runLZSS(text);
    }
    
    /**
     * Metodi...
     * @param text 
     */
    private void useAll(String text) {
        useLZ(text);
        useLZW(text);
        useLZSS(text);
    }
    
    /**
     * Metodi...
     */
    private void insertOwnText() {
        System.out.println("Please insert your own text to compress:");
        String userText = reader.nextLine();
        // tähän tarvittaessa syötteen validointi!
        
        choiceCompressMethod(userText);                
    }

}

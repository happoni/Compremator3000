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
        
    }
    
    /**
     * Metodi...
     */
    private void insertOwnText() {
        
    }
    
}

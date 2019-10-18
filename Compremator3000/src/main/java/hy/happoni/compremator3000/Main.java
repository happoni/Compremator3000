package hy.happoni.compremator3000;

import hy.happoni.compremator3000.ui.AppLogic;                                  // tuodaan tarvittavat palaset
import hy.happoni.compremator3000.ui.UI;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AppLogic logic = new AppLogic();
        Scanner reader = new Scanner(System.in);
        UI ui = new UI(logic, reader);

        ui.startApp();
        
    }
}

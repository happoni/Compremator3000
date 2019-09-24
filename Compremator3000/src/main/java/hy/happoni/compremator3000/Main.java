// pakkaus
package hy.happoni.compremator3000;

// tuodaan tarvittavat palaset
import hy.happoni.compremator3000.io.FileIO;
import hy.happoni.compremator3000.ui.AppLogic;
import hy.happoni.compremator3000.ui.UI;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AppLogic logic = new AppLogic();
        Scanner reader = new Scanner(System.in);

        UI ui = new UI(logic, reader);

        ui.startApp();

//        Byte a = 127;
//        System.out.println(a.byteValue());
//        System.out.println(a);
//        String apua = "0X7F";
//        Byte decoded = a.decode(apua);
//        System.out.println(decoded);
//        
//        String s = "SOH";
//        byte[] bytes = s.getBytes();
//        System.out.println(bytes[0]);
//        System.out.println(bytes[1]);
//        
//        char ab = (char) 21;
//        System.out.println(ab);
        
    }
}

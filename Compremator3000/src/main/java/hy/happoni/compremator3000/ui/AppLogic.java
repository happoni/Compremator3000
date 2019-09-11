// pakkaus
package hy.happoni.compremator3000.ui;

import hy.happoni.compremator3000.domain.LZW;
import java.util.List;

/**
 * Yleisestä sovelluslogiikasta huolehtiva luokka.
 */
public class AppLogic {

    private LZW lzw;

    public AppLogic() {
        this.lzw = new LZW();
    }

    public void runLZW() {
        List<Integer> compressed = lzw.compress("TOBEORNOTTOBEORTOBEORNOT");
        System.out.println(compressed);
        String decompressed = lzw.decompress(compressed);
        System.out.println(decompressed);
    }

}

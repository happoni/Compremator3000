// pakkaus
package hy.happoni.compremator3000.ui;

import hy.happoni.compremator3000.domain.LZW;
import static hy.happoni.compremator3000.domain.LZW.compress;
import static hy.happoni.compremator3000.domain.LZW.decompress;
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
        List<Integer> compressed = compress("TOBEORNOTTOBEORTOBEORNOT");
        System.out.println(compressed);
        String decompressed = decompress(compressed);
        System.out.println(decompressed);
    }

}

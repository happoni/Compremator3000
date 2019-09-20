// pakkaus
package hy.happoni.compremator3000.ui;

import hy.happoni.compremator3000.domain.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Yleisestä sovelluslogiikasta huolehtiva luokka.
 */
public class AppLogic {

    private LZW lzw;
    private LZ lz;
    private LZSS lzss;

    public AppLogic() {
        this.lzw = new LZW();
        this.lz = new LZ();
        this.lzss = new LZSS();
    }

    public void runLZW(String text) {
        List<Integer> compressed = lzw.compress(text);
        System.out.println(compressed);
        String decompressed = lzw.decompress(compressed);
        System.out.println(decompressed);
    }

    public void runLZ(String text) {
        List<Tuple> compressed = lz.compress(text);
        System.out.println(compressed);
        String decompressed = lz.decompress(compressed);
        System.out.println(decompressed);
    }

    public void runLZSS(String text) {
        List<LZSSTuple> compressed = lzss.compress(text);
        System.out.println(compressed);
        String decompressed = lzss.decompress(compressed);
        System.out.println(decompressed);
    }

}

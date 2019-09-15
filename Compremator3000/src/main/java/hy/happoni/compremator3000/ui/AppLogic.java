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
    private LZMA lzma;

    public AppLogic() {
        this.lzw = new LZW();
        this.lz = new LZ();
        this.lzss = new LZSS();
        this.lzma = new LZMA();
    }

    public void runLZW() {
        List<Integer> compressed = lzw.compress("TOBEORNOTTOBEORTOBEORNOT");
        System.out.println(compressed);
        String decompressed = lzw.decompress(compressed);
        System.out.println(decompressed);
    }

    public void runLZ() {
        List<Tuple> compressed = lz.compress("TOBEORNOTTOBEORTOBEORNOT");
        System.out.println(compressed);
        String decompressed = lz.decompress(compressed);
        System.out.println(decompressed);
    }

    public void runLZSS() {
        List<LZSSTuple> compressed = lzss.compress("TOBEORNOTTOBEORTOBEORNOT");
        System.out.println(compressed);
        String decompressed = lzss.decompress(compressed);
        System.out.println(decompressed);
    }

    public void runLZMA() {

    }
}

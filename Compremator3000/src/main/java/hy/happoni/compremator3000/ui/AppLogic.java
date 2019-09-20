// pakkaus
package hy.happoni.compremator3000.ui;

// tällä hetkellä tarvittavat importit
import hy.happoni.compremator3000.domain.*;
import hy.happoni.compremator3000.io.FileIO;
import java.util.List;

/**
 * Yleisestä sovelluslogiikasta huolehtiva luokka.
 */
public class AppLogic {

    // tarvittavat muuttujat, käytännössä algoritmit suorittavat luokat
    private FileIO fileIo;
    private LZW lzw;
    private LZ lz;
    private LZSS lzss;

    // konstruktori
    public AppLogic() {
        this.fileIo = new FileIO();
        this.lzw = new LZW();
        this.lz = new LZ();
        this.lzss = new LZSS();
    }

    /**
     * Luetaan fileIO:n avulla annetun polun päässä oleva tekstitiedosto merkkijonoksi.
     * 
     * @param filePath - tiedoston polku 
     * @return  - tiedoston teksti merkkijonona
     */
    public String readFile(String filePath) {                
        return fileIo.readFile(filePath);
    }
    
    /**
     * LZW-algoritmin ajava metodi. Tällä hetkellä pakkaa ja purkaa annetun tekstin ja tulostaa "pakkauskoodin", ja tekstin purkamisen jälkeen.
     * 
     * @param text - Pakattava teksti.
     */
    public void runLZW(String text) {
        // pakataan annettu teksti ja tulostetaan "pakkauskoodi"
        List<Integer> compressed = lzw.compress(text);
        System.out.println(compressed);
        
        // puretaan äsken pakattu teksti ja tulostetaan se
        String decompressed = lzw.decompress(compressed);
        System.out.println(decompressed);
    }

    /**
     * LZ77-algoritmin ajava metodi. Tällä hetkellä pakkaa ja purkaa annetun tekstin ja tulostaa "pakkauskoodin", ja tekstin purkamisen jälkeen.
     * 
     * @param text - Pakattava teksti.
     */
    public void runLZ(String text) {
        // pakataan annettu teksti ja tulostetaan "pakkauskoodi"
        List<Tuple> compressed = lz.compress(text);
        System.out.println(compressed);
        
        // puretaan äsken pakattu teksti ja tulostetaan se
        String decompressed = lz.decompress(compressed);
        System.out.println(decompressed);
    }

    /**
     * LZSS-algoritmin ajava metodi. Tällä hetkellä pakkaa ja purkaa annetun tekstin ja tulostaa "pakkauskoodin", ja tekstin purkamisen jälkeen.
     * 
     * @param text - Pakattava teksti.
     */
    public void runLZSS(String text) {
        // pakataan annettu teksti ja tulostetaan "pakkauskoodi"
        List<LZSSTuple> compressed = lzss.compress(text);
        System.out.println(compressed);
        
        // puretaan äsken pakattu teksti ja tulostetaan se
        String decompressed = lzss.decompress(compressed);
        System.out.println(decompressed);
    }

    /**
     * Metodi, joka ajaa kaikki kolme algoritmia (LZ77, LZW ja LZSS). Tällä hetkellä pakkaa ja purkaa annetun tekstin ja tulostaa "pakkauskoodin"
     * , ja tekstin purkamisen jälkeen. Käytännössä kutsuu jokaista algoritmin ajavaa metodia.
     * 
     * @param text - Pakattava teksti
     */
    public void runAll(String text) {
        runLZ(text);
        runLZW(text);
        runLZSS(text);
    }
    
}

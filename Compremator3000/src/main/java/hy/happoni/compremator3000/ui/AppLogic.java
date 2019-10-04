package hy.happoni.compremator3000.ui;

// tällä hetkellä tarvittavat importit
import hy.happoni.compremator3000.domain.*;
import hy.happoni.compremator3000.io.FileIO;
import java.util.ArrayList;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Yleisestä sovelluslogiikasta huolehtiva luokka.
 */
public class AppLogic {

    // tarvittavat muuttujat, käytännössä algoritmit suorittavat luokat
    private final FileIO fileIo;
    private final LZW lzw;
    private final LZ lz;
    private final LZSS lzss;

    // konstruktori
    public AppLogic() {
        this.fileIo = new FileIO();
        this.lzw = new LZW();
        this.lz = new LZ();
        this.lzss = new LZSS();
    }
    
    public void compressLZW(String filepath) {
        fileIo.writeCompressedFile((lzw.encode(fileIo.readFileToCharArray(filepath))), filepath, ".lzw");
        System.out.println("pakkauskoodi jes");
       
    }
}

//    /**
//     * Metodi, joka demonstroi LZ-algoritmin toimintaa. Kutsuu lz-luokan metodia
//     * compress, ja tulostaa pakatun "koodin".
//     *
//     * @param text - käyttäjän syöttämä, pakattava teksti
//     */
//    public void demoLZ(String text) {
//        // pakataan annettu teksti ja tulostetaan "pakkauskoodi"
//        System.out.println("Text to be compressed with LZ77: " + text);
//        byte[] compressed = lz.compress(text);
//        ArrayList<Tuple> demo = SerializationUtils.deserialize(compressed);
//        System.out.println("Compressed code: " + demo);
//    }
//
//    /**
//     * Metodi, joka demonstroi LZW-algoritmin toimintaa. Kutsuu lzw-luokan
//     * metodia compress, ja tulostaa pakatun "koodin".
//     *
//     * @param text - käyttäjän syöttämä, pakattava teksti
//     */
//    public void demoLZW(String text) {
//        // pakataan annettu teksti ja tulostetaan "pakkauskoodi"
//        System.out.println("Text to be compressed with LZW: " + text);
//        byte[] compressed = lzw.compress(text);
//        ArrayList<Integer> demo = SerializationUtils.deserialize(compressed);
//        System.out.println("Compressed code: " + demo);
//    }
//
//    /**
//     * Metodi, joka demonstroi LZSS-algoritmin toimintaa. Kutsuu lzss-luokan
//     * metodia compress, ja tulostaa pakatun "koodin".
//     *
//     * @param text - käyttäjän syöttämä, pakattava teksti
//     */
//    public void demoLZSS(String text) {
//        // pakataan annettu teksti ja tulostetaan "pakkauskoodi"
//        System.out.println("Text to be compressed with LZSS: " + text);
//        byte[] compressed = lzss.compress(text);
//        ArrayList<LZSSTuple> demo = SerializationUtils.deserialize(compressed);
//        System.out.println("Compressed code: " + demo);
//    }
//
//    /**
//     * Tiedoston pakkaamisesta huolehtiva metodi. Pakkaa syötetyn tiedoston
//     * annetulla algoritmilla. Jos tiedostoa ei ole (tai se on tyhjä), ei pakkaa
//     * mitään, vaan tulostaa virheviestin. Kutsuu ao. algoritmin suorittavan
//     * luokan metodia ja fileIO-luokan metodeja readFile ja writeCompressedFile.
//     *
//     * @param fileName - pakattavan tiedoston nimi
//     * @param algoType - algoritmi, jolla pakkaus suoritetaan
//     */
//    public void compress(String fileName, String algoType) {
//        String data = fileIo.readFile(fileName);
//        boolean success = false;
//
//        if (data != "") {
//            if (algoType.equals("lz")) {
//                success = fileIo.writeCompressedFile(lz.compress(data), fileName, ".lz");
//            } else if (algoType.equals("lzw")) {
//                success = fileIo.writeCompressedFile(lzw.compress(data), fileName, ".lzw");
//            } else if (algoType.equals("lzss")) {
//                success = fileIo.writeCompressedFile(lzss.compress(data), fileName, ".lzss");
//            }
//        }
//        if (success) {
//            System.out.println("File compressed succesfully: " + fileName + "." + algoType);
//        } else {
//            System.out.println("Compression failed.");
//        }
//    }
//
//    /**
//     * Pakatun tiedoston purkamisesta huolehtiva metodi. Lukee ensin tiedoston
//     * fileIO:n avulla ja katsoo tiedoston päätteen perusteella, minkä
//     * algoritmin suorittavan luokan metodilla tiedosto puretaan. Kirjoittaa
//     * sitten puretun tekstin tiedostoksi ilman pakkauspäätettä. Jos purkaminen
//     * ei onnistu, antaa virheviestin.
//     *
//     * @param fileName - purettavan tiedoston nimi
//     */
//    public void uncompress(String fileName) {
//
//        byte[] data = fileIo.readCompressedFile(fileName);
//        boolean success = false;
//
//        if (data != null) {
//            if (fileName.contains(".lz")) {
//                success = fileIo.writeFile((lz.decompress(data)), (fileName.substring(0, fileName.length() - 3)));
//            } else if (fileName.contains(".lzw")) {
//                success = fileIo.writeFile((lzw.decompress(data)), (fileName.substring(0, fileName.length() - 4)));
//            } else if (fileName.contains(".lzss")) {
//                success = fileIo.writeFile((lzss.decompress(data)), (fileName.substring(0, fileName.length() - 5)));
//            }
//        }
//        if (success) {
//            System.out.println("File uncompressed succesfully.");
//        } else {
//            System.out.println("Uncompression failed.");
//        }
//    }
//
//}

package hy.happoni.compremator3000.ui;

// tällä hetkellä tarvittavat importit
import hy.happoni.compremator3000.domain.LZSS.LZSS;
import hy.happoni.compremator3000.domain.LZW.LZW;
import hy.happoni.compremator3000.domain.LZ77.LZ;
import hy.happoni.compremator3000.io.FileIO;

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
    
    /**
     * Tiedoston pakkaamisesta huolehtiva metodi. Pakkaa syötetyn tiedoston
     * annetulla algoritmilla. Jos tiedostoa ei ole (tai se on tyhjä), ei pakkaa
     * mitään, vaan tulostaa virheviestin. Kutsuu ao. algoritmin suorittavan
     * luokan metodia ja fileIO-luokan metodeja readFileToByteArray ja writeCompressedFile.
     *
     * @param fileName - pakattavan tiedoston nimi
     * @param algoType - algoritmi, jolla pakkaus suoritetaan
     */
    public void compress(String fileName, String algoType) {
        byte[] data = fileIo.readFileToByteArray(fileName);
        boolean success = false;

        if (data != null) {
            if (algoType.equals("lz")) {
                success = fileIo.writeCompressedFile(lz.compress(data), fileName, ".lz");
            } else if (algoType.equals("lzw")) {
                success = fileIo.writeCompressedFile(lzw.compress(data), fileName, ".lzw");
            } else if (algoType.equals("lzss")) {
                success = fileIo.writeCompressedFile(lzss.compress(data), fileName, ".lzss");
            }
        }
        if (success) {
            System.out.println("File compressed succesfully: " + fileName + "." + algoType);
        } else {
            System.out.println("Compression failed.");
        }
    }

    /**
     * Pakatun tiedoston purkamisesta huolehtiva metodi. Lukee ensin tiedoston
     * fileIO:n avulla ja katsoo tiedoston päätteen perusteella, minkä
     * algoritmin suorittavan luokan metodilla tiedosto puretaan. Kirjoittaa
     * sitten puretun tekstin tiedostoksi ilman pakkauspäätettä. Jos purkaminen
     * ei onnistu, antaa virheviestin.
     *
     * @param fileName - purettavan tiedoston nimi
     */
    public void uncompress(String fileName) {

        byte[] data = fileIo.readFileToByteArray(fileName);
        boolean success = false;

        if (data != null) {
            if (fileName.contains(".lz")) {
                success = fileIo.writeBytesToFile((lz.decompress(data)), (fileName.substring(0, fileName.length() - 3)));
            } else if (fileName.contains(".lzw")) {
                success = fileIo.writeBytesToFile((lzw.decompress(data)), (fileName.substring(0, fileName.length() - 4)));
            } else if (fileName.contains(".lzss")) {
                success = fileIo.writeBytesToFile((lzss.decompress(data)), (fileName.substring(0, fileName.length() - 5)));
            }
        }
        if (success) {
            System.out.println("File uncompressed succesfully.");
        } else {
            System.out.println("Uncompression failed.");
        }
    }
}

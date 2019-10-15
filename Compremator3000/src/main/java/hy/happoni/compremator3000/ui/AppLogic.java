package hy.happoni.compremator3000.ui;

// tarvittavat importit
import hy.happoni.compremator3000.domain.LZW.LZW;
import hy.happoni.compremator3000.domain.LZ77.LZ77;
import hy.happoni.compremator3000.domain.LZ77.LZ77New;
import hy.happoni.compremator3000.io.FileIO;

/**
 * Yleisestä sovelluslogiikasta huolehtiva luokka.
 */
public class AppLogic {

    // tarvittavat muuttujat, käytännössä algoritmit suorittavat luokat
    private final FileIO fileIo;
    private final LZW lzw;
    private final LZ77New lz77;

    // konstruktori
    public AppLogic() {
        this.fileIo = new FileIO();
        this.lzw = new LZW();
        this.lz77 = new LZ77New();
    }

    /**
     * Tiedoston pakkaamisesta huolehtiva metodi. Pakkaa syötetyn tiedoston
     * annetulla algoritmilla. Jos tiedostoa ei ole (tai se on tyhjä), ei pakkaa
     * mitään, vaan tulostaa virheviestin. Kutsuu ao. algoritmin suorittavan
     * luokan metodia ja fileIO-luokan metodeja readFileToByteArray,
     * readFileToString ja writeCompressedFile.
     *
     * @param fileName - pakattavan tiedoston nimi
     * @param algoType - algoritmi, jolla pakkaus suoritetaan
     */
    public void compress(String fileName, String algoType) {
        byte[] data = fileIo.readFileToByteArray(fileName);
        String stringData = fileIo.readFileToString(fileName);
        boolean success = false;

        if (data != null || !stringData.equals("")) {
            if (algoType.equals("lz77")) {
                success = fileIo.writeCompressedFile(lz77.compress(data), fileName, ".lz77");
            } else if (algoType.equals("lzw")) {
                success = fileIo.writeCompressedFile(lzw.compress(data), fileName, ".lzw");
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
            if (fileName.contains(".lz77")) {
                //success = fileIo.writeBytesToFile((lz77.decompress(data)), (fileName.substring(0, fileName.length() - 5)));
            } else if (fileName.contains(".lzw")) {
                success = fileIo.writeBytesToFile((lzw.decompress(data)), (fileName.substring(0, fileName.length() - 4)));
            }
        }
        if (success) {
            System.out.println("File uncompressed succesfully.");
        } else {
            System.out.println("Uncompression failed.");
        }
    }
}

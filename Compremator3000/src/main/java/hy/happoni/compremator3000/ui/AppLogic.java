package hy.happoni.compremator3000.ui;                                        

import hy.happoni.compremator3000.domain.LZW.LZW;                               // tarvittavat importit
import hy.happoni.compremator3000.domain.LZ77.LZ77;
import hy.happoni.compremator3000.io.FileIO;

/**
 * Yleisestä sovelluslogiikasta huolehtiva luokka.
 */
public class AppLogic {

    private final FileIO fileIo;                                                // tarvittavat muuttujat, käytännössä algoritmit suorittavat luokat
    private final LZW lzw;
    private final LZ77 lz77;
    private int originalSize;                                                   // muuttujat havainnollistamaan algoritmien tehokkuutta                                            
    private int newSize;

    public AppLogic() {
        this.fileIo = new FileIO();
        this.lzw = new LZW();
        this.lz77 = new LZ77();
    }

    /**
     * Tiedoston pakkaamisesta huolehtiva metodi. Pakkaa syötetyn tiedoston
     * annetulla algoritmilla. Jos tiedostoa ei ole (tai se on tyhjä), ei pakkaa
     * mitään, vaan tulostaa virheviestin. Kutsuu ao. algoritmin suorittavan
     * luokan metodia ja fileIO-luokan metodeja readFileToByteArray ja
     * writeCompressedFile.
     *
     * @param fileName - pakattavan tiedoston nimi
     * @param algoType - algoritmi, jolla pakkaus suoritetaan
     */
    public void compress(String fileName, String algoType) {
        byte[] data = fileIo.readFileToByteArray(fileName);                     // luetaan data
        boolean success = false;

        long timeAtBegin = System.currentTimeMillis();                          // aika alussa

        if (data != null) {                                                     // kunhan meillä on jotain dataa...
            originalSize = data.length;
            if (algoType.equals("lz77")) {                                      // pakataan lz77:lla
                byte[] toBeCompressed = lz77.compress(data);
                newSize = toBeCompressed.length;
                success = fileIo.writeCompressedFile(toBeCompressed, fileName, ".lz77"); // kirjoitetaan pakattu setti tiedostoon
            } else if (algoType.equals("lzw")) {                                // pakataan lzw:llä
                byte[] toBeCompressed = lzw.compress(data);
                newSize = toBeCompressed.length;
                success = fileIo.writeCompressedFile(toBeCompressed, fileName, ".lzw");
            }
        }
        if (success) {                                                          // jos pakkaaminen onnistui, tulostetaan tietoja
            System.out.println();
            System.out.println("File compressed succesfully: " + fileName + "." + algoType);
            long timeAtEnd = System.currentTimeMillis();
            System.out.println("Original size: " + originalSize + " bytes");
            System.out.println("Compressed size: " + newSize + " bytes");
            System.out.println("Compressed size is " + Math.ceil((1.0 * newSize / originalSize) * 100) + " % of original size.");
            System.out.println("Time elapsed: " + (timeAtEnd - timeAtBegin) + " ms");
        } else {                                                                // muuten tulostetaan virheviesti
            System.out.println();
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

        byte[] data = fileIo.readFileToByteArray(fileName);                     // luetaan pakattu tiedosto
        boolean success = false;
        long timeAtBegin = System.currentTimeMillis();

        if (data != null) {
            if (fileName.contains(".lz77")) {                                   // pakkaus on tehty lz77:lla, puretaankin siis sillä
                success = fileIo.writeBytesToFile((lz77.decompress(data)), (fileName.substring(0, fileName.length() - 5)));
            } else if (fileName.contains(".lzw")) {                             // pakkaus on tehty lzw:llä, puretaan siis sillä
                success = fileIo.writeBytesToFile((lzw.decompress(data)), (fileName.substring(0, fileName.length() - 4)));
            }
        }
        if (success) {
            System.out.println("File uncompressed succesfully.");
            long timeAtEnd = System.currentTimeMillis();
            System.out.println("Time elapsed: " + (timeAtEnd - timeAtBegin) + " ms");
        } else {
            System.out.println("Uncompression failed.");
        }
    }
}

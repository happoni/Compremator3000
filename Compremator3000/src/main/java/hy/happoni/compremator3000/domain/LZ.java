// Pakkaus.
package hy.happoni.compremator3000.domain;

// Tuodaan tarvittavia importeja, poistetaan myöhemmässä vaiheessa tarpeettomiksi käyneet.
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Luokka, joka huolehtii Lempel-Ziv -algoritmin (LZ77) toteutuksesta. Luokan
 * toteutuksessa on otettu mallia lähteissä mainitussa artikkelista
 * "Understanding the Lempel-Ziv Data Compression Algorithm in Java".
 */
public class LZ {

    // Tuple on luokka, joka pitää kirjaa sanakirjan pisimmän osuman sijainnista, pisimmän osuman pituudesta ja seuraavasta merkistä pisimmän osuman jälkeen bufferissa.
    Tuple thisTuple;

    // Lista, johon tuplet säilötään.
    ArrayList<Tuple> compressedData;

    // Etsintäikkuna
    String searchSubstring;

    // Apumuuttujia, joita tarvitaan algoritmin toiminnassa.
    int matchLength;
    int matchLocation;
    int charCount;
    int searchWindowStart;
    int lookAheadWindowEnd;

    // Sanakirja ja bufferi.
    int dictionaryLength;
    int bufferLength;

    // Konstruktori, jolla voidaan asettaa halutut arvot sanakirjan ja bufferin pituudelle.
    public LZ(int dictionaryLength, int bufferLength) {
        this.compressedData = new ArrayList<>();
        this.dictionaryLength = dictionaryLength;
        this.bufferLength = bufferLength;
    }

    // Parametriton konstruktori.
    public LZ() {
        this.compressedData = new ArrayList<>();
        this.dictionaryLength = 31;
        this.bufferLength = 7;
    }

    /**
     * Metodi, joka pakkaa annetun merkkijonon tupleiksi, eli ns. pakatun koodin
     * palasiksi.
     *
     * @param uncompressed - merkkijono, joka halutaan pakata.
     * @return compressedData - lista muuttujia "tuple", jotka kertovat pakatun
     * merkkijonon koodipalat.
     */
    public List<Tuple> compress(String uncompressed) {

        // Käydään merkkijono läpi.
        charCount = 0;
        while (charCount < uncompressed.length()) {
            // Etsintäikkunan alku.
            searchWindowStart = (charCount - dictionaryLength >= 0) ? charCount - dictionaryLength : 0;
            // Bufferin loppu.
            lookAheadWindowEnd = (charCount + bufferLength < uncompressed.length()) ? charCount + bufferLength : uncompressed.length();
            // Otetaan pala etsintäikkunasta. Jos charCount on nolla, etsintäikkuna on tyhjä.
            if (charCount == 0) {
                searchSubstring = "";
            } else {
                searchSubstring = uncompressed.substring(searchWindowStart, charCount);
            }
            // Haetaan etsintäikkunasta osumaa bufferin seuraavaan merkkiin.
            matchLength = 1;
            String searchTarget = uncompressed.substring(charCount, charCount + matchLength);

            if (searchSubstring.indexOf(searchTarget) != -1) {
                // Tällöin on saatu osuma yhden merkin pituiseen merkkijonoon. Tutkitaan, jatkuuko osuma pidemmälle. Ei kuitenkaan ylitetä bufferin pituutta.
                matchLength++;
                while (matchLength <= bufferLength) {
                    // Tutkitaan, miten pitkälle päästään.
                    searchTarget = uncompressed.substring(charCount, charCount + matchLength);
                    matchLocation = searchSubstring.indexOf(searchTarget);

                    if ((matchLocation != -1) && (charCount + matchLength) < uncompressed.length()) {
                        matchLength++;
                    } else {
                        break;
                    }
                }
                // Asetetaan osuman pituus.
                matchLength--;

                // Haetaan etsintäikkunasta viimeisimmän osuman sijainti.
                matchLocation = searchSubstring.indexOf(uncompressed.substring(charCount, charCount + matchLength));

                // Kasvatetaan merkkilaskuria.
                charCount += matchLength;

                // Laitetaan koodipala tietoon tupleen.
                int offset = (charCount < (dictionaryLength + matchLength)) ? charCount - matchLocation - matchLength : dictionaryLength - matchLocation;

                String nextChar = uncompressed.substring(charCount, charCount + 1);

                thisTuple = new Tuple(offset, matchLength, nextChar);
                compressedData.add(thisTuple);
            } else {
                String nextChar = uncompressed.substring(charCount, charCount + 1);
                thisTuple = new Tuple(0, 0, nextChar);
                compressedData.add(thisTuple);
            }
            // Kasvatetaan merkkilaskuria.
            charCount++;
        }
        return compressedData;
    }

    /**
     * Metodi purkaa syötteenä annetun pakatun tiedoston.
     *
     * @param compressed
     * @return
     */
    public String decompress(List<Tuple> compressed) {
        // Luodaan dekoodattu merkkijono tähän talteen.
        StringBuilder reconData = new StringBuilder();
        // Haetaan tietoja tuple-listasta.
        Iterator<Tuple> iterator = compressed.iterator();
        while (iterator.hasNext()) {
            Tuple nextTuple = iterator.next();
            if (nextTuple.stringLength == 0) {
                // Ei osumia tälle merkille.
                reconData.append(nextTuple.nextChar);
            } else {
                // Tuple tietää osumista, jes.
                for (int i = 0; i < nextTuple.stringLength; i++) {
                    // iteroidaan, ja otetaan offsetin ja pituuden mukaan osumat jo rakennetuista paloista.
                    char workingChar = reconData.charAt(reconData.length() - nextTuple.offset);
                    reconData.append(workingChar);
                }
                // Lisätään osumattomat merkit.
                reconData.append(nextTuple.nextChar);
            }
        }
        return reconData.toString();
    }
}

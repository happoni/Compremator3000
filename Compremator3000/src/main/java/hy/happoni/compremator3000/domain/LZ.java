// pakkaus
package hy.happoni.compremator3000.domain;

// tuodaan tässä vaiheessa kaikki importit
import java.util.*;

/**
 * Luokka, joka huolehtii Lempel-Ziv -algoritmin (LZ77) toteutuksesta. Luokan
 * toteutuksessa on otettu mallia lähteissä mainitussa artikkelista
 * "Understanding the Lempel-Ziv Data Compression Algorithm in Java".
 */
public class LZ {

    // Tuple on luokka, joka pitää kirjaa sanakirjan pisimmän osuman sijainnista,
    // pisimmän osuman pituudesta ja seuraavasta merkistä pisimmän osuman jälkeen
    // etsintäikkunassa.
    Tuple thisTuple;

    // Lista, johon tuplet säilötään.
    ArrayList<Tuple> compressedData = new ArrayList<>();

    // Etsintäikkuna
    String searchSubstring;

    // Apumuuttujia, joita tarvitaan algoritmin toiminnassa.
    int matchLength;
    int matchLocation;
    int charCount;
    int searchWindowStart;
    int lookAheadWindowEnd;

    // Etsintäikkunan ja "kurkistusikkunan" pituudet.
    int searchWindowLength = 31;
    int lookAheadWindowLength = 7;

    /**
     * Metodi, joka pakkaa annetun merkkijonon tupleiksi, eli ns. pakatun koodin
     * palasiksi.
     *
     * @param uncompressed - merkkijono, joka halutaan pakata.
     * @return compressedData - lista muuttujia "tuple", jotka kertovat pakatun
     * merkkijonon kodekit.
     */
    public List<Tuple> compress(String uncompressed) {

        // Käydään merkkijono läpi.
        charCount = 0;
        while (charCount < uncompressed.length()) {
            // Etsintäikkunan alku. Lause on käytännössä else-if.
            searchWindowStart = (charCount - searchWindowLength >= 0)
                    ? charCount - searchWindowLength : 0;
            // Kurkistusikkunan loppu.
            lookAheadWindowEnd = (charCount + lookAheadWindowLength < uncompressed.length())
                    ? charCount + lookAheadWindowLength : uncompressed.length();
            // Otetaan pala etsintäikkunasta.
            if (charCount == 0) {
                // Aloitetaan tällöin tyhjällä etsintäikkunalla.
                searchSubstring = "";
            } else {
                searchSubstring = uncompressed.substring(searchWindowStart, charCount);
            }
            // Haetaan etsintäikkunasta osumaa kurkistusikkunan seuraavaan merkkiin.
            matchLength = 1;
            String searchTarget = uncompressed.substring(charCount, charCount + matchLength);

            if (searchSubstring.indexOf(searchTarget) != -1) {
                // Tällöin on saatu osuma yhden merkin pituiseen merkkijonoon. Tutkitaan,
                // jatkuuko osuma pidemmälle. Ei kuitenkaan ylitetä kurkistusikkunan pituutta.
                matchLength++;
                while (matchLength <= lookAheadWindowLength) {
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

                // Laitetaan kodekki tietoon tupleen.
                int offset = (charCount < (searchWindowLength + matchLength))
                        ? charCount - matchLocation - matchLength
                        : searchWindowLength - matchLocation;

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
        StringBuffer reconData = new StringBuffer();
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
                    // iteroidaan, ja otetaan offsetin ja pituuden mukaan
                    // osumat jo rakennetuista paloista.
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

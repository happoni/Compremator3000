package hy.happoni.compremator3000.domain;

// Tuodaan tarvittavia importeja, poistetaan myöhemmässä vaiheessa tarpeettomiksi käyneet.
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Luokka, joka huolehtii Lempel-Ziv -algoritmin (LZ77) toteutuksesta.
 */
public class LZ {

    // Lista, johon tuplet säilötään.
    ArrayList<Tuple> compressedData;

    // Etsintäikkuna
    byte[] searchSubstring;

    // Apumuuttujia, joita tarvitaan algoritmin toiminnassa.
    int matchLength;
    int matchLocation;
    int byteCount;
    int searchWindowStart;
    int bufferEnd;

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
     * Metodilla asetetaan LZ77-algoritmin etsintäikkunan alkupiste.
     *
     * @param charCount - tieto siitä, kuinka mones merkki on käsittelyssä
     * @param dictionaryLength - tieto sanakirjan pituudesta
     * @return charCount - dictionaryLength, jos se ei ole negatiivinen, muutoin
     * palautetaan nolla
     */
    public int setSearchWindowStart(int charCount, int dictionaryLength) {
        if (charCount - dictionaryLength >= 0) {
            return charCount - dictionaryLength;
        }
        return 0;
    }

    /**
     * Metodilla asetetaan LZ77-algoritmin bufferin loppupiste.
     *
     * @param charCount - tieto siitä, mones merkki on käsittelyssä
     * @param bufferLength - tieto bufferin pituudesta
     * @param inputLength - tieto pakattavana olevan syötteen pituudesta
     * @return charCount + bufferLength, jos ne yhdessä ovat lyhyempi kuin
     * syötteen pituus, muutoin syötteen pituus
     */
    public int setBufferEnd(int charCount, int bufferLength, int inputLength) {
        if (charCount + bufferLength < inputLength) {
            return charCount + bufferLength;
        }
        return inputLength;
    }

    /**
     * Metodilla asetetaan ikkuna, josta etsitään osumia.
     *
     * @param input - pakattava merkkijono
     * @param byteCount - tieto siitä, mones merkki on käsittelyssä
     * @param searchWindowStart - tieto etsintäikkunan alkupisteestä
     * @return jos charCount on nolla, palautetaan tyhjä merkkijono, muulloin se
     * osamerkkijono, joka on searchWindowStartin ja charCountin välissä
     * syötteessä
     */
    public byte[] setSearchSubstring(byte[] input, int byteCount, int searchWindowStart) {
        if (byteCount == 0) {
            return null;
        }
        byte[] searchSubbytes = new byte[byteCount - searchWindowStart];
        for (int i = 0; i < searchSubbytes.length; i++) {
            searchSubbytes[i] = input[searchWindowStart + i];
        }
        return searchSubbytes;
    }

    /**
     * Metodi, joka pakkaa annetun merkkijonon tupleiksi, eli ns. pakatun koodin
     * palasiksi.
     *
     * @param input - merkkijono, joka halutaan pakata.
     * @return compressedData - byte array listasta tupleja, jotka kertovat
     * "pakkauskoodin"
     */
    public byte[] compress(byte[] input) {

        // Käydään merkkijono läpi.
        byteCount = 0;
        while (byteCount < input.length) {
            // Asetetaan etsintäikkunan alku.
            searchWindowStart = setSearchWindowStart(byteCount, dictionaryLength);
            // Bufferin loppu.
            bufferEnd = setBufferEnd(byteCount, bufferLength, input.length);
            // Otetaan pala etsintäikkunasta. Jos charCount on nolla, etsintäikkuna on tyhjä.
            searchSubstring = setSearchSubstring(input, byteCount, searchWindowStart);
            // Haetaan etsintäikkunasta osumaa bufferin seuraavaan merkkiin.
            matchLength = 1;
            String searchTarget = input.substring(byteCount, byteCount + matchLength);

            if (searchSubstring.contains(searchTarget)) {
                // Tällöin on saatu osuma yhden merkin pituiseen merkkijonoon. Tutkitaan, jatkuuko osuma pidemmälle. Ei kuitenkaan ylitetä bufferin pituutta.
                while (matchLength <= bufferLength) {
                    // Tutkitaan, miten pitkälle päästään.
                    searchTarget = input.substring(byteCount, byteCount + matchLength);
                    matchLocation = searchSubstring.indexOf(searchTarget);

                    if ((matchLocation != -1) && (byteCount + matchLength) < input.length()) {
                        matchLength++;
                    } else {
                        break;
                    }
                }
                // Asetetaan osuman pituus.
                matchLength--;
                // Haetaan etsintäikkunasta viimeisimmän osuman sijainti.
                matchLocation = searchSubstring.indexOf(input.substring(byteCount, byteCount + matchLength));
                // Kasvatetaan merkkilaskuria.
                byteCount += matchLength;

                // Laitetaan koodipala tietoon tupleen.
                int offset = (byteCount < (dictionaryLength + matchLength)) ? byteCount - matchLocation - matchLength : dictionaryLength - matchLocation;
                String nextChar = input.substring(byteCount, byteCount + 1);
                compressedData.add(new Tuple(offset, matchLength, nextChar));
            } else {
                String nextChar = input.substring(byteCount, byteCount + 1);
                compressedData.add(new Tuple(0, 0, nextChar));
            }
            // Kasvatetaan merkkilaskuria.
            byteCount++;
        }
        return SerializationUtils.serialize(compressedData);
    }

    /**
     * Metodi purkaa syötteenä annetun pakatun tiedoston, eli käytännössä listan
     * muuttujia tuple.
     *
     * @param compressedData - byte array listasta tupleja
     * @return reconData - toString()-metodin avulla muodostettu merkkijono.
     */
    public String decompress(byte[] compressedData) {
        List<Tuple> compressed = SerializationUtils.deserialize(compressedData);

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

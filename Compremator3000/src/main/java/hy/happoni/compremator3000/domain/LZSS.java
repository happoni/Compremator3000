// pakkaus
package hy.happoni.compremator3000.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Luokka, joka huolehtii Lempel-Ziv-Storer-Szymanski -algoritmin (LZSS)
 * toteutuksesta.
 */
public class LZSS {

    // Lista, johon tuplet säilötään.
    ArrayList<LZSSTuple> compressedData;

    // Etsintäikkuna
    String searchSubstring;

    // Apumuuttujia, joita tarvitaan algoritmin toiminnassa.
    int matchLength;
    int matchLocation;
    int charCount;
    int searchWindowStart;
    int bufferEnd;

    // Sanakirja ja bufferi.
    int dictionaryLength;
    int bufferLength;

    // Konstruktori, jolla voidaan asettaa halutut arvot sanakirjan ja bufferin pituudelle.
    public LZSS(int dictionaryLength, int bufferLength) {
        this.compressedData = new ArrayList<>();
        this.dictionaryLength = dictionaryLength;
        this.bufferLength = bufferLength;
    }

    // Parametriton konstruktori.
    public LZSS() {
        this.compressedData = new ArrayList<>();
        this.dictionaryLength = 31;
        this.bufferLength = 7;
    }

    /**
     * Metodilla asetetaan LZSS-algoritmin etsintäikkunan alkupiste.
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
     * Metodilla asetetaan LZSS-algoritmin bufferin loppupiste.
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
     * Metodilla asetetaan merkkijono, josta osumia haetaan.
     *
     * @param input - pakattava merkkijono
     * @param charCount - tieto siitä, mones merkki on käsittelyssä
     * @param searchWindowStart - tieto etsintäikkunan alkupisteestä
     * @return jos charCount on nolla, palautetaan tyhjä merkkijono, muulloin se
     * osamerkkijono, joka on searchWindowStartin ja charCountin välissä
     * syötteessä
     */
    public String setSearchSubstring(String input, int charCount, int searchWindowStart) {
        if (charCount == 0) {
            return "";
        }
        return input.substring(searchWindowStart, charCount);
    }

    /**
     * Metodi, joka pakkaa annetun merkkijonon tupleiksi, eli ns. pakatun koodin
     * palasiksi.
     *
     * @param input - merkkijono, joka halutaan pakata.
     * @return compressedData - lista muuttujia "tuple", jotka kertovat pakatun
     * merkkijonon koodipalat.
     */
    public List<LZSSTuple> compress(String input) {

        // Käydään merkkijono läpi.
        charCount = 0;
        while (charCount < input.length()) {
            // Asetetaan etsintäikkunan alku.
            searchWindowStart = setSearchWindowStart(charCount, dictionaryLength);
            // Bufferin loppu.
            bufferEnd = setBufferEnd(charCount, bufferLength, input.length());
            // Otetaan pala etsintäikkunasta. Jos charCount on nolla, etsintäikkuna on tyhjä.
            searchSubstring = setSearchSubstring(input, charCount, searchWindowStart);
            // Haetaan etsintäikkunasta osumaa bufferin seuraavaan merkkiin.
            matchLength = 3;
            String searchTarget = input.substring(charCount, charCount + matchLength);

            if (searchSubstring.contains(searchTarget)) {
                // Tällöin on saatu osuma kolmen merkin pituiseen merkkijonoon. Tutkitaan, jatkuuko osuma pidemmälle. Ei kuitenkaan ylitetä bufferin pituutta.
                matchLength++;
                while (matchLength <= bufferLength) {
                    // Tutkitaan, miten pitkälle päästään.
                    searchTarget = input.substring(charCount, charCount + matchLength);
                    matchLocation = searchSubstring.indexOf(searchTarget);

                    if ((matchLocation != -1) && (charCount + matchLength) < input.length()) {
                        matchLength++;
                    } else {
                        break;
                    }
                }
                // Asetetaan osuman pituus.
                matchLength--;
                // Haetaan etsintäikkunasta viimeisimmän osuman sijainti.
                matchLocation = searchSubstring.indexOf(input.substring(charCount, charCount + matchLength));
                // Kasvatetaan merkkilaskuria.
                charCount += matchLength;

                // Laitetaan koodipala tietoon tupleen.
 
                compressedData.add(new LZSSTuple(false, matchLocation, matchLength));
            } else {
                String nextChar = input.substring(charCount, charCount + 1);
                compressedData.add(new LZSSTuple(true, nextChar));
            }
            // Kasvatetaan merkkilaskuria.
            charCount++;
            System.out.println(charCount);
        }
        return compressedData;
    }

    /**
     * Metodi purkaa syötteenä annetun pakatun tiedoston, eli käytännössä listan
     * muuttujia tuple.
     *
     * @param compressed -
     * @return reconData - toString()-metodin avulla muodostettu merkkijono.
     */
    public String decompress(List<LZSSTuple> compressed) {
        // Luodaan dekoodattu merkkijono tähän talteen.
        StringBuilder reconData = new StringBuilder();
        // Haetaan tietoja tuple-listasta.
        Iterator<LZSSTuple> iterator = compressed.iterator();
        while (iterator.hasNext()) {
            LZSSTuple nextTuple = iterator.next();
            if (nextTuple.singleChar) {
                // Ei osumia tälle merkille.
                reconData.append(nextTuple.character);
            } else {
                
                String workingStr = reconData.substring(nextTuple.position, nextTuple.length);
                reconData.append(workingStr);

            }
        }
        return reconData.toString();
    }

}

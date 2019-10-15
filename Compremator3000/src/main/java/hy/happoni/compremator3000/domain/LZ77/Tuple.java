package hy.happoni.compremator3000.domain.LZ77;

/**
 * Apuluokka, jota käytetään LZ77-algoritmissa. Tuple säilöö tiedot pisimmän
 * sanakirjasta löytyvän osuman sijainnista (offset), pisimmän osuman pituudesta
 * (stringlength) ja osumaa seuraavasta merkistä (nextchar).
 */
public class Tuple {

    int offset;         // pisimmän sanakirjasta löytyvän osuman sijainti
    int stringLength;   // pisimmän osuman pituus
    char nextChar;      // pisintä osumaa seuraava merkki

    /**
     * Konstruktorilla tupleen heti tietoon kaikki oleellinen.
     *
     * @param offset - pisimmän sanakirjasta löytyvän osuman sijainti
     * @param stringLength - pisimmän osuman pituus
     * @param nextChar - pisintä osumaa seuraava merkki
     */
    public Tuple(int offset, int stringLength, String nextChar) {
        this.offset = offset;
        this.stringLength = stringLength;
        this.nextChar = nextChar.charAt(0);
    }

    /**
     * Palauttaa offsetin arvon.
     *
     * @return - offsetin arvo
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Palauttaa pisimmän osuman pituuden.
     *
     * @return - pisimmän osuman pituus
     */
    public int getStringLength() {
        return stringLength;
    }

    /**
     * Palauttaa pisintä osumaa seuraavan merkin.
     *
     * @return - pisintä osumaa seuraava merkki
     */
    public char getNextChar() {
        return nextChar;
    }
}

// pakkaus
package hy.happoni.compremator3000.domain;

/**
 * Luokka, joka kuvaa LZSS-algoritmin koodipalasia.
 */
public class LZSSTuple {

    // Totuusarvo kertoo, onko palanen yksittäinen merkki (true) vai isompi pätkä (false).
    boolean singleChar;
    // merkki
    String character;
    // sijainti sanakirjassa
    int position;
    // pituus sanakirjasta
    int length;

    public LZSSTuple(boolean singleChar, String character) {
        this.singleChar = singleChar;
        this.character = character;
    }

    public LZSSTuple(boolean singleChar, int position, int length) {
        this.singleChar = singleChar;
        this.position = position;
        this.length = length;
    }

    @Override
    public String toString() {
        return singleChar + "," + character + "," + position + "," + length;
    }
}

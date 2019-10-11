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
    byte nextByte;      // pisintä osumaa seuraava tavu

    public Tuple(int offset, int stringLength, String nextChar) {
        this.offset = offset;
        this.stringLength = stringLength;
        this.nextChar = nextChar.charAt(0);
    }

    public Tuple(int offset, int byteLength, byte nextByte) {
        this.offset = offset;
        this.stringLength = byteLength;
        this.nextByte = nextByte;
    }

    public int getOffset() {
        return offset;
    }

    public int getStringLength() {
        return stringLength;
    }

    public char getNextChar() {
        return nextChar;
    }

//    @Override
//    public String toString() {
//        return offset + "," + stringLength + "," + nextChar;
//    }
    @Override
    public String toString() {
        return offset + "," + stringLength + "," + nextByte;
    }

}

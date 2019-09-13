// pakkaus
package hy.happoni.compremator3000.domain;

/**
 * Apuluokka, jota käytetään LZ77-algoritmissa.
 */
public class Tuple {

    // Offset pitää kirjaa sen pisimmän osuman sijainnista, joka sijaitsee
    // sanakirjassa. StringLength on pisimmän osuman pituus. NextChar on
    // seuraava merkki bufferissa pisimmän osuman jälkeen.
    int offset;
    int stringLength;
    String nextChar;

    public Tuple(int offset, int stringLength, String nextChar) {
        this.offset = offset;
        this.stringLength = stringLength;
        this.nextChar = nextChar;
    }

    @Override
    public String toString() {
        return offset + "," + stringLength + "," + nextChar;
    }

}
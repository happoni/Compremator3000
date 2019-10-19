package hy.happoni.compremator3000.domain.LZ77;

/**
 * Apuluokka, jota käytetään LZ77-algoritmissa. Tuple säilöö tiedot pisimmän
 * sanakirjasta löytyvän osuman sijainnista (offset), pisimmän osuman pituudesta
 * (stringlength) ja osumaa seuraavasta merkistä (nextchar).
 */
public class Tuple {

    short offset;
    short byteLength;
    byte nextByte;
    
    /**
     * Konstruktorilla tupleen heti tietoon kaikki oleellinen.
     *
     * @param offset - pisimmän sanakirjasta löytyvän osuman sijainti
     * @param byteLength - pisimmän osuman pituus
     * @param nextByte - seuraava tavu
     */
    public Tuple(int offset, int byteLength, byte nextByte) {
    this.offset = (short) offset;
    this.byteLength = (short) byteLength;
    this.nextByte = nextByte;

    }

    /**
     * Palauttaa offsetin arvon.
     *
     * @return - offsetin arvo
     */
    public short getOffset() {
        return offset;
    }

    /**
     * Palauttaa pisimmän osuman pituuden.
     *
     * @return - pisimmän osuman pituus
     */
    public short getByteLength() {
        return byteLength;
    }

    /**
     * Palauttaa pisintä osumaa seuraavan merkin.
     *
     * @return - pisintä osumaa seuraava merkki
     */
    public byte getNextByte() {
        return nextByte;
    }
}

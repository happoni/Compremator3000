package hy.happoni.compremator3000.domain.LZW;

/**
 * Luokka toteuttaa LZW-algoritmin sanakirjan koodisanan alkuosan. Käytännössä
 * kyseessä on tieto siitä, missä indeksissä alkuosa sijaitsee.
 */
public class Prefix {

    private ByteArray byteArray;

    /**
     * Alkuosaa kuvataan ByteArray-apuluokan avulla.
     */
    public Prefix() {
        this.byteArray = new ByteArray();
    }

    /**
     * Metodilla palautetaan alkuosan koko.
     *
     * @return - kokonaislukuna alkuosan koko.
     */
    public int size() {
        return byteArray.size();
    }

    /**
     * Metodilla lisäätän alkuosaan tavu.
     *
     * @param b - tavu, joka lisätään
     * @return - tosi
     */
    public boolean add(byte b) {
        byteArray.add(b);
        return true;
    }

    /**
     * Metodilla voi hakea tietyn tavun alkuosasta, kun tietää tavun indeksin
     * alkuosassa.
     *
     * @param index - kokonaislukuna indeksi
     * @return - se tavu, joka alkuosasta löytyy
     */
    public byte get(int index) {
        return byteArray.get(index);
    }

    /**
     * Metodilla voi hakea alkuosan tavulistana.
     *
     * @return - byte array (byte[]), joka on alkuosa
     */
    public byte[] getBytes() {
        return byteArray.getBytes();
    }

    /**
     * Metodi tyhjentää alkuosan.
     */
    public void clear() {
        byteArray.clear();
    }

    /**
     * Metodilla alkuosaan lisätään seuraava tavu. Ei kuitenkaan muuteta
     * alkuosaa vaan muodostetaan uusi alkuosa. Hyödynnetään add-metodia.
     *
     * @param b - seuraava tavu
     * @return - alkuosa, johon on lisätty seuraava tavu (alkuosa + seuraava
     * tavu)
     */
    public Prefix merge(byte b) {
        Prefix merged = clone();
        merged.add(b);
        return merged;
    }

    /**
     * Metodi muodostaa uuden alkuosan tästä alkuosasta. Hyödynnetään metodissa
     * merge.
     *
     * @return - alkuosa
     */
    @Override
    public Prefix clone() {
        Prefix clone = new Prefix();
        clone.byteArray = this.byteArray.duplicate();
        return clone;
    }
}

package hy.happoni.compremator3000.domain.LZW;

/**
 * Luokka kuvaa yhtä LZW-algoritmin sanakirjaan talletettua sanaa. Käytännössä
 * alkuosana on indeksi alkuosaan. Tavu muodostaa loppupään sanasta.
 */
public class Word {

    private final int prefix;
    private final byte value;
    private Word first;
    private Word child;

    /**
     * Konstruktori, asetetaan sanan alkuosa ja tavu.
     *
     * @param prefix - alkuosa kokonaislukuna
     * @param value - tavu
     */
    public Word(int prefix, byte value) {
        this.prefix = prefix;
        this.value = value;
    }

    /**
     * Metodilla saa sanan alkuosan.
     *
     * @return - kokonaisulukuna alkuosa
     */
    public int getPrefix() {
        return prefix;
    }

    /**
     * Metodilla saa sanan tavun.
     *
     * @return - tavu
     */
    public byte getValue() {
        return value;
    }

    /**
     * Metodilla lisätään lapsi sanalle. Jos on jo yksi lapsi, laitetaan se
     * tietoon toiseksi. Muutoin ensimmäiseksi lapseksi.
     *
     * @param prefix - kokonaisulukuna alkuosa
     * @param value - tavu
     */
    public void addChild(int prefix, byte value) {
        Word child = new Word(prefix, value);
        if (first != null) {
            child.child = first;
        }
        first = child;
    }

    /**
     * Metodilla haetaan sanan lapsi tavun perusteella. Katsotaan ensin sanan
     * lasta, jos sitä ei ole, palautetaan heti null. Jos lapsi löytyy,
     * katsotaan sen tavua, jos se on sama kuin annettu, palautetaan lapsi,
     * muulloin katsotaan lapsen lasta jne.
     *
     * @param value - tavu, jonka avulla etsitään lapsi
     * @return - null, jos ei löydy, muuten löydetty lapsi
     */
    public Word getChild(byte value) {
        Word child = first;
        while (child != null) {
            if (child.getValue() == value) {
                return child;
            }
            child = child.child;
        }
        return null;
    }
}

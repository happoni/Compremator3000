package hy.happoni.compremator3000.domain.LZW;

/**
 * Luokka kuvaa yhtä LZW-algoritmin sanakirjaan talletettua sanaa. Käytännössä
 * alkuosana on indeksi alkuosaan. Tavu muodostaa loppupään sanasta.
 */
public class Word {

    private final int prefix;
    private final byte value;
    private Word first;
    private Word left; // yritetään toteuttaa binäärihakupuuna, siksi vasen ja oikea
//    private Word right;

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
     * Metodiööa saa sanan alkuosan.
     *
     * @return - kokonaisulukuna alkuosa
     */
    public int getPrefix() {
        return prefix;
    }

    /**
     * Metodilla saa sanana tavun
     *
     * @return - tavu
     */
    public byte getValue() {
        return value;
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
//            } else if (child.getValue() < value) {
//                child = child.left;
//            } else {
//                child = child.right;
//            }            
            }
            child = child.left;
        }
        return null;
    }

    /**
     * Metodilla lisätään lapsi sanalle. Jos on jo lapsi, laitetaan se tietoon
     * "serkuksi". Muutoin varsinaiseksi lapseksi.
     *
     * @param prefix - kokonaisulukna alkuosa
     * @param value - tavu
     */
    public void addChild(int prefix, byte value) {
        Word child = new Word(prefix, value);
        if (first != null) {
            child.left = first;
        }
        first = child;
    }

}

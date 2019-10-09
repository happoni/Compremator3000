package hy.happoni.compremator3000.domain.LZW;

/**
 * Luokka, jota käytetään pakatun tiedoston purkamisessa. Samantyyppinen kuin
 * sanakirja Dictionary. Ei kuitenkaan pidetä sanoja, vaan alkuosia
 * sanakirjassa.
 */
public class PrefixDictionary {

    private static final int INITIAL_SIZE = 1024;
    private Prefix[] array;
    private int size;

    /**
     * Konstruktori, kutsutaan myös populateDictionary-metodia, jolla
     * käytännössä alustetaan sanakirja.
     */
    public PrefixDictionary() {
        this.array = new Prefix[INITIAL_SIZE];
        this.size = 0;
        populateDictionary();
    }

    /**
     * Metodi lisää alkuosan sanakirjaan.
     *
     * @param prefix - alkuosa
     */
    public void add(Prefix prefix) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = prefix;
    }

    /**
     * Metodilla saa alkuosan indeksin perusteella.
     *
     * @param index - indeksi
     * @return - palauttaa sen alkuosan, joka indeksistä löytyy
     */
    public Prefix get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Metodilla saa sanakirjan koon.
     *
     * @return - koko
     */
    public int size() {
        return size;
    }

    /**
     * Asettaa sanakirjan koon 256:een, eli alustettuun sanakirjaan. Käytännössä
     * siis resetoi sanakirjan.
     */
    public void reset() {
        this.size = 256;
    }

    /**
     * Tarvittaessa sanakirjaa (taulukkoa) täytyy kasvattaa (emmehän tiedä paljon purettavaa on).
     */
    private void resizeArray() {
        Prefix[] newArray = new Prefix[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    /**
     * Sanakirjan alustaminen.
     */
    private void populateDictionary() {
        byte b = -128;
        for (int i = 0; i < 256; i++) {
            Prefix prefix = new Prefix();
            prefix.add(b);
            array[size++] = prefix;
            b++;
        }
    }
}
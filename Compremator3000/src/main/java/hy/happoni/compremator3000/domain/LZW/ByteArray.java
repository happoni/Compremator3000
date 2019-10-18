package hy.happoni.compremator3000.domain.LZW;

/**
 * Apuluokka, jonka avulla pidetään dynaamista tavulistaa.
 */
public class ByteArray {

    private static final int INITIAL_SIZE = 1024;
    private byte[] array;
    private int size;

    /**
     * Konstruktorilla lista isoksi ja kooksi nolla.
     */
    public ByteArray() {
        this.array = new byte[INITIAL_SIZE];
        this.size = 0;
    }
        
    /**
     * Metodi, jolla saadaan kokonaisluku suoraan ByteArrayhyn.
     *
     * @param i - luku, joka muutetaan
     */
    public void add(short i) {
        byte[] number = new byte[2];
        number[0] = (byte) (i >> 8);
        number[1] = (byte) (i);
        add(number);
    }

//    /**
//     * Metodi, jolla saadaan kokonaisluku suoraan ByteArrayhyn.
//     *
//     * @param i - luku, joka muutetaan
//     */
//    public void add(int i) {
//        byte[] number = new byte[4];
//        number[0] = (byte) (i >> 24);
//        number[1] = (byte) (i >> 16);
//        number[2] = (byte) (i >> 8);
//        number[3] = (byte) (i);
//        add(number);
//    }
    /**
     * Metodilla lisätään tavu tavulistaan. Tarvittaessa kasvatetaan listaa.
     *
     * @param b - lisättävä tavu
     */
    public void add(byte b) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = b;
    }

    /**
     * Voidaan lisätä myös tavulista tavulistalle. Hyödynnetään metodia add.
     *
     * @param array - lisättävä tavulista
     */
    public void add(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    /**
     * Metodilla voi hakea tavulistan koon.
     *
     * @return - koko
     */
    public int size() {
        return size;
    }

    /**
     * Metodilla saa koko tavulistan.
     *
     * @return - uusi byte[], joka on tämä tavulista
     */
    public byte[] getBytes() {
        byte[] output = new byte[size];
        for (int i = 0; i < size; i++) {
            output[i] = array[i];
        }
        return output;
    }

    /**
     * Metodilla saa tavun tietystä kohdasta tavulistaa.
     *
     * @param index - kohta, mistä haetaan
     * @return - tavu siitä kohtaa
     */
    public byte get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Metodilla voi kloonata tämän tavulistan.
     *
     * @return - samainen tavulista
     */
    public ByteArray duplicate() {
        byte[] newArr = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            newArr[i] = array[i];
        }
        ByteArray duplicate = new ByteArray();
        duplicate.array = newArr;
        duplicate.size = this.size;
        return duplicate;
    }

    /**
     * Metodi tyhjentää tavulistan (tai oikeastaan asettaa sen kooksi nolla,
     * jolloin se täytetään alusta vanhan päälle.
     */
    public void clear() {
        size = 0;
    }

    /**
     * Apumetodi, joka kasvattaa listaa, jos se täyttyy.
     */
    private void resizeArray() {
        byte[] newArray = new byte[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}

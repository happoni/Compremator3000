package hy.happoni.compremator3000.domain.LZ77;

import hy.happoni.compremator3000.domain.LZW.ByteArray;

/**
 * Luokka, joka huolehtii LZ77-algoritmin tuplejen listaamisesta. Hyödynnetään
 * bittien käsittelyyn LZW-algoritmin apuluokkaa ByteArrays.
 */
public class LZList {

    private Tuple[] values;
    private int size;

    /**
     * Konstruktorilla taulukon kooksi 16, varsinainen lista on tyhjä (koko on
     * nolla).
     */
    public LZList() {
        this.values = new Tuple[16];
        this.size = 0;
    }

    public int size() {
        return size;
    }
    
    /**
     * Metodilla lisätään tuple listalle. Tarvittaessa kasvatetaan taulukon
     * kokoa.
     *
     * @param value - lisättävä tuple-muuttuja
     */
    public void add(Tuple value) {
        if (size == values.length) {
            increase();
        }
        values[size] = value;
        size++;
    }

    /**
     * Metodi palauttaa tuplen halutusta kohtaa listaa.
     *
     * @param index - tuplen indeksi listalla
     * @return - tuple, joka löytyy indeksistä. Jos indeksi on alle tai yli
     * listan koon, heitetään poikkeus
     */
    public Tuple get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return values[index];
    }

    /**
     * Apumetodi, joka kasvattaa taulukon kokoa.
     */
    private void increase() {
        Tuple[] newList = new Tuple[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            newList[i] = values[i];
        }
        values = newList;
    }

    /**
     * Metodi muuntaa tuple-listan byte arrayksi hyödyntäen
     * ByteArray-apuluokkaa.
     *
     * @return - lista byte arrayna
     */
    public byte[] toByteArray() {
        ByteArray bytes = new ByteArray();

        for (int i = 0; i < size; i++) {
            bytes.add(values[i].getOffset());
            bytes.add(values[i].getByteLength());
            bytes.add(values[i].getNextByte());
        }

        return bytes.getBytes();
    }
}

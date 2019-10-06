package hy.happoni.compremator3000.domain.LZ77;

import hy.happoni.compremator3000.domain.LZW.ByteArray;

/**
 * Luokka, joka huolehtii LZ77-algoritmin tuplejen listaamisesta.
 */
public class LZList {

    private Tuple[] values;
    private int size;

    public LZList() {
        this.values = new Tuple[16];
        this.size = 0;
    }

    public void add(Tuple value) {
        if (size == values.length) {
            increase();
        }
        values[size] = value;
        size++;
    }

    public Tuple get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return values[index];
    }

    private void increase() {
        Tuple[] newList = new Tuple[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            newList[i] = values[i];
        }
        values = newList;
    }

    public byte[] toByteArray() {
        ByteArray bytes = new ByteArray();

        for (int i = 0; i < size; i++) {
            bytes.add(values[i].getOffset());
            bytes.add(values[i].getStringLength());
            bytes.add((byte) values[i].getNextChar());
        }

        return bytes.getBytes();
    }
}

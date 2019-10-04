package hy.happoni.compremator3000.domain;

/**
 * Luokka toteuttaa LZW-algoritmin sanakirjan koodisanan alkuosan.
 */
public class LZWPrefix {

    private static final int INITSIZE = 1024;
    private byte[] array;
    private int size;

    public LZWPrefix() {
        this.array = new byte[INITSIZE];
        this.size = 0;
    }

    public void add(byte b) {
        if (size == array.length) {
            increaseArrayLength();
        }
        array[size++] = b;
    }

    private void increaseArrayLength() {
        byte[] newArray = new byte[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void add(byte[] byteArray) {
        for (int i = 0; i < byteArray.length; i++) {
            add(array[i]);
        }
    }

    public void set(int index, byte b) {
        array[index] = b;
    }

    public int size() {
        return size;
    }

    public byte[] getBytes() {
        byte[] output = new byte[size];
        for (int i = 0; i < size; i++) {
            output[i] = array[i];
        }
        return output;
    }

    public byte get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    public void clear() {
        size = 0;
    }

    public LZWPrefix duplicate() {
        byte[] newArr = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            newArr[i] = array[i];
        }
        LZWPrefix duplicate = new LZWPrefix();
        duplicate.array = newArr;
        duplicate.size = this.size;
        return duplicate;
    }
}

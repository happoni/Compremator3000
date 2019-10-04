package hy.happoni.compremator3000.domain;

/**
 * Luokka muuttaa listatyyppisen rakenteen integereja byte arrayksi
 */
public class LZWEncode {

    private byte[] array;
    private int size;

    public LZWEncode() {
        this.array = new byte[1024];
        this.size = 0;
    }

    public void add(int i) {
        byte[] bytes = intToByteArray(i);
        for (int j = 0; j < bytes.length; j++) {
            add(array[j]);
        }
    }

    public void add(byte b) {
        if (size == array.length) {
            increaseSize();
        }
        array[size++] = b;
    }

    private void increaseSize() {
        byte[] newArray = new byte[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public byte[] intToByteArray(int i) {
        byte[] result = new byte[4];

        result[0] = (byte) (i >> 24);
        result[1] = (byte) (i >> 16);
        result[2] = (byte) (i >> 8);
        result[3] = (byte) (i /*>> 0*/);

        return result;
    }

    public byte[] toBytes() {
        byte[] output = new byte[size];
        for (int i = 0; i < size; i++) {
            output[i] = array[i];
        }
        return output;
    }
}

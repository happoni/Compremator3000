package hy.happoni.compremator3000.domain.LZW;

/**
 *
 */
public class PrefixDictionary {

    private static final int INITIAL_SIZE = 1024;
    private Prefix[] array;
    private int size;

    public PrefixDictionary() {
        this.array = new Prefix[INITIAL_SIZE];
        this.size = 0;
        populateDictionary();
    }

    public void add(Prefix element) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = element;
    }

    public Prefix get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    public void reset() {
        this.size = 256;
    }

    private void resizeArray() {
        Prefix[] newArray = new Prefix[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

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

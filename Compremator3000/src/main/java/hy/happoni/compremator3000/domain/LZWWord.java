package hy.happoni.compremator3000.domain;

/**
 * Luokka kuvaa yhtä LZW-algoritmin sanakirjaan talletettua sanaa.
 */
public class LZWWord {

    private int prefix;
    private byte value;
    private int first;
    private int left;
    private int right;
    private int next;

    public LZWWord(int prefix, byte value) {
        this.prefix = prefix;
        this.value = value;
        this.first = -1;
        this.left = -1;
        this.right = -1;
        this.next = -1;
    }

    public int getPrefix() {
        return prefix;
    }

    public byte getValue() {
        return value;
    }

    public int getFirst() {
        return first;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getNext() {
        return next;
    }
}

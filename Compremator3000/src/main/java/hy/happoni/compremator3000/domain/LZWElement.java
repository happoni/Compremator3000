
package hy.happoni.compremator3000.domain;

/**
 * Luokka kuvaa yhtä LZW-algoritmin sanakirjaan talletettua elementtiä.
 */
public class LZWElement {
    
    private final int prefixIndex;
    private final byte value;
    private int first;
    private int left;
    private int right;
    
    public LZWElement(int prefixIndex, byte value) {
        this.prefixIndex = prefixIndex;
        this.value = value;
    }
    
    public LZWElement(int prefixIndex, byte value, int first, int left, int right) {
        this.prefixIndex = prefixIndex;
        this.value = value;
        this.first = first;
        this.left = left;
        this.right = right;
    }
    
    public int getPrefixIndex() {
        return prefixIndex;
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
    

    
}

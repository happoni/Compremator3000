package hy.happoni.compremator3000.domain;

/**
 * Luokka kuvaa yhtä LZW-algoritmin sanakirjaan talletettua elementtiä.
 */
public class LZWcode {
    
    private final int prefixIndex;
    private final byte value;
    
    public LZWcode(int prefixIndex, byte value) {
        this.prefixIndex = prefixIndex;
        this.value = value;
    }    
    
    public byte getValue() {
        return value;
    }
    
    public int getPrefixIndex() {
        return prefixIndex;
    }       
    
}

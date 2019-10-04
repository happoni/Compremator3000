
package hy.happoni.compremator3000.domain;

/**
 * Luokka kuvaa yhtä LZW-algoritmin sanakirjaan talletettua elementtiä.
 */
public class LZWElement {
    
    private final int prefixIndex;
    private final byte postByte;
    private LZWElement parent;
    private LZWElement left;
    private LZWElement right;
    
    public LZWElement(int prefixIndex, byte value) {
        this.prefixIndex = prefixIndex;
        this.postByte = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
            
    public void setParent(LZWElement parent) {
        this.parent = parent;
    }
    
    public void setLeft(LZWElement left) {
        this.left = left;
    }
    
    public void setRight(LZWElement right) {
        this.right = right;
    }
        
    public int getPrefixIndex() {
        return prefixIndex;
    }
    
    public byte getPostByte() {
        return postByte;
    }

    public LZWElement getParent() {
        return parent;
    }

    public LZWElement getLeft() {
        return left;
    }

    public LZWElement getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Alkuosa löytyy tästä indeksistä: " + prefixIndex + "; tavun arvo: " + postByte;
    }

    
}

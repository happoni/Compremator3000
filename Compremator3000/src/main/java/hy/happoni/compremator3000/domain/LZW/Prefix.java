package hy.happoni.compremator3000.domain.LZW;

/**
 * Luokka toteuttaa LZW-algoritmin sanakirjan koodisanan alkuosan.
 */
public class Prefix {

    private ByteArray byteArray;

    public Prefix() {
        this.byteArray = new ByteArray();
    }

    public Prefix merge(byte b) {
        Prefix merged = clone();
        merged.add(b);
        return merged;
    }
    
    public boolean add(byte b) {
        byteArray.add(b);
        return true;
    }
    
    public int size() {
        return byteArray.size();
    }
    
    public byte get(int index) {
        return byteArray.get(index);
    }
    
    public byte[] getBytes() {
        return byteArray.getBytes();
    }
    
    public void clear() {
        byteArray.clear();
    }    
    
    @Override
    public Prefix clone() {
        Prefix clone = new Prefix();
        clone.byteArray = this.byteArray.duplicate();
        return clone;
    }
}

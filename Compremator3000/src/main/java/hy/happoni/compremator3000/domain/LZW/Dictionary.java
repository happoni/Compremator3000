package hy.happoni.compremator3000.domain.LZW;

import hy.happoni.compremator3000.domain.LZW.Prefix;
import hy.happoni.compremator3000.domain.LZW.Word;

/**
 * Luokka, joka toimii LZW-algoritmin sanakirjana.
 *
 */
public class Dictionary {

    private Word[] dictionary;
    private int size;

    public Dictionary() {
        this.dictionary = new Word[256];
        this.size = 256;
        
        byte b = -128;
        for (int i = 0; i < size; i++) {
            this.dictionary[i] = new Word(i, b);
            b++;
        }                        
    }
    
    public int size() {
        return size;
    }

    private Word searchWord(Prefix bytes) {
        Word w = dictionary[128 + bytes.get(0)];
        for (int i = 1; i < bytes.size(); i++) {
            w = w.getChild(bytes.get(i));
            if (w == null) {
                return null;
            }
        }
        return w;
    }
    
    public int getPrefix(Prefix bytes) {
        if (bytes.size() == 0) {
            throw new IllegalArgumentException("Bytes can't be empty.");
        }
        if (bytes.size() == 1) {
            int i = 128 + bytes.get(0);
            return dictionary[i].getPrefix();
        }
        Word w = searchWord(bytes);
        // voiko w olla null?
        return w.getPrefix();        
    }
    
    public boolean contains(Prefix bytes, byte nextByte) {
        if (bytes.size() == 0) {
            return true;
        }
        Word w = searchWord(bytes);
        if (w == null) {
            return false;
        }
        if (w.getChild(nextByte) == null) {
            return false;
        }
        return true;
    }
    
    public boolean add(Prefix bytes, byte nextByte) {
        Word w = searchWord(bytes);
        w.addChild(size++, nextByte);
        return true;
    }
}
package hy.happoni.compremator3000.domain.LZ77;

/**
 * Luokka, jonka avulla rakennetaan LZ77-algoritmin sanakirja. Idea on, että
 * sana on käytännössä yksi tietty tavu, joka tietää kaikki sitä seuraavat tavut
 * sanakirjassa.
 *
 * @author hanihani
 */
public class LZ77Word {

    int index;
    byte b;
    boolean[] followers;
    
    
    
    public LZ77Word(int index, byte b) {
        this.index = index;
        this.b = b;
        followers = new boolean[256]; // jokainen kohta taulukossa vastaa yhtä tavua -128...127 ja on nyt false, koska yhtään tavua ei seuraa tätä tavua
    }
    
    public boolean addFollower(byte b) {
        int i = (int) b;
        followers[i] = true;
        return true;
    }
    
    public int getIndex() {
        return index;
    }
    
    public byte getByte() {
        return b;
    }
    
    public boolean isFollowed(byte b) {
        int i = (int) b + 128;
        return followers[i];
    }
}

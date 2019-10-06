package hy.happoni.compremator3000.domain.LZW;

import java.util.Objects;

/**
 * Luokka kuvaa yhtä LZW-algoritmin sanakirjaan talletettua sanaa.
 */
public class Word {

    private final int prefix;
    private final byte value;
    private Word first;
    private Word left; // yritetään toteuttaa binäärihakupuuna, siksi vasen ja oikea
//    private Word right;

    public Word(int prefix, byte value) {
        this.prefix = prefix;
        this.value = value;
    }

    public int getPrefix() {
        return prefix;
    }

    public byte getValue() {
        return value;
    }

    public Word getChild(byte value) {
        Word child = first;
        while (child != null) {
            if (child.getValue() == value) {
                return child;
//            } else if (child.getValue() < value) {
//                child = child.left;
//            } else {
//                child = child.right;
//            }            
            }
            child = child.left;
        }
        return null;
    }

    public void addChild(int prefix, byte value) {
        Word child = new Word(prefix, value);
        if (first != null) {
            child.left = first;
        }
        first = child;
    }

}

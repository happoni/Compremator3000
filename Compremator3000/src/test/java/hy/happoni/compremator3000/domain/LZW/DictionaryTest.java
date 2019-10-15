package hy.happoni.compremator3000.domain.LZW;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka Dictionary-luokalle.
 */
public class DictionaryTest {

    Dictionary d;
    Prefix p;
    Prefix pt = new Prefix();

    public DictionaryTest() {
    }

    @Before
    public void setUp() {
        d = new Dictionary();
        p = new Prefix();
        p.add((byte) 88);
        pt.add((byte) 10);
        pt.add((byte) 5);
    }

    /**
     * Testi, jolla tarkistetaan, että size-metodi palauttaa oikean arvon.
     */
    @Test
    public void sizeReturnsCorrectValue() {
        byte b = 43;
        d.add(p, b);
        assertEquals(257, d.size());
    }

    /**
     * Testillä tarkistetaan, että getPrefix-metodi palauttaa oikean prefixin.
     */
    @Test
    public void getPrefixReturnsCorrectPrefix() {
        int result = d.getPrefix(p);
        assertEquals(216, result);        
    }

    /**
     * Testillä tarkistetaan, että contains-metodi toimii oikein.
     */
    @Test
    public void containsReturnsCorrectValue() {
        byte c = 44;
        assertFalse(d.contains(p, c));
        byte e = 12;
        d.add(p, e);
        assertTrue(d.contains(p, e));
        assertFalse(d.contains(pt, e));
    }

}

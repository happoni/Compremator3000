package hy.happoni.compremator3000.domain.LZW;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka, jolla testataan Prefix-luokan toimintaa.
 */
public class PrefixTest {

    Prefix p;
    Prefix testP;
    byte b;
    byte c;

    public PrefixTest() {
    }

    @Before
    public void setUp() {
        p = new Prefix();
        testP = new Prefix();
        b = 100;
        c = -12;
    }

    /**
     * Prefixin koon palauttavan metodin testi.
     */
    @Test
    public void sizeReturnsCorrectSize() {
        assertEquals(0, p.size());
        testP.add(b);
        testP.add(c);
        assertEquals(2, testP.size());
    }

    /**
     * Testillä testataan, että tavun lisääminen prefixiin toimii.
     */
    @Test
    public void addWorksCorrectly() {
        assertTrue(testP.add(b));
        assertTrue(testP.add(c));
    }

    /**
     * Testi tarkistaa, että get-metodi palauttaa oikean tavun.
     */
    @Test
    public void getReturnsCorrectByte() {
        testP.add(b);
        testP.add(c);
        assertEquals(b, testP.get(0));
    }

    /**
     * Testi tarkistaa, että getBytes-metodi palauttaa oikeanlaisen bytearrayn.
     */
    @Test
    public void getBytesReturnsCorrectByteArray() {
        ByteArray ba = new ByteArray();
        ba.add(b);
        ba.add(c);
        p.add(b);
        p.add(c);
        assertEquals(ba.getBytes()[0], p.getBytes()[0]);
        assertEquals(ba.getBytes()[1], p.getBytes()[1]);
    }

    /**
     * Testi tarkistaa, että clear-metodi "tyhjentää" prefixin eli käytännössä
     * nollaa sen koon.
     */
    @Test
    public void clearSetsSizeToZero() {
        p.add(b);
        p.clear();
        assertEquals(0, p.size());
    }

    /**
     * Testi tarkistaa, että merge-metodi liittää prefixiin tavun. Testaa
     * samalla clone-metodia.
     */
    @Test
    public void mergeAddsByteToPrefix() {
        p.add(b);
        Prefix m = p.merge(c);
        assertEquals(m.getBytes()[0], b);
        assertEquals(m.getBytes()[1], c);
    }
}

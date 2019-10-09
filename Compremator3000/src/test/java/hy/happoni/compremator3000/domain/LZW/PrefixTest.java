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
     * Testillä testataan, että tavun lisääminen prefixiin toimii.
     */
    @Test
    public void addWorksCorrectly() {
        assertTrue(testP.add(b));
        assertTrue(testP.add(c));
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
     * Testi tarkistaa, että get-metodi palauttaa oikean tavun.
     */
    @Test
    public void getReturnsCorrectByte() {
        testP.add(b);
        testP.add(c);
        assertEquals(b, testP.get(0));        
    }

}

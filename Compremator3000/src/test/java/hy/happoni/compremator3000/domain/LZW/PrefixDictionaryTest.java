package hy.happoni.compremator3000.domain.LZW;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testiluokka PrefixDictionary-luokalle.
 */
public class PrefixDictionaryTest {

    PrefixDictionary pd;

    public PrefixDictionaryTest() {
    }

    @Before
    public void setUp() {
        pd = new PrefixDictionary();
    }

    /**
     * Testillä tarkistetaan, että size-metodi palauttaa oikean koon.
     */
    @Test
    public void sizeReturnsCorrectValue() {
        assertEquals(256, pd.size());
        pd.add(new Prefix());
        assertEquals(257, pd.size());
        for (int i = 0; i < 1000; i++) {
            pd.add(new Prefix());
        }
        assertEquals(1257, pd.size());
    }

    /**
     * Testillä tarkistetaan, että oikea prefix lisätään add-metodilla.
     */
    @Test
    public void addAddsCorrectPrefix() {
        Prefix p = new Prefix();
        p.add((byte) 10);
        pd.add(p);
        assertEquals(pd.get(256), p);
    }

    /**
     * Testillä tarkistetaan, että reset-metodi toimii oikein.
     */
    @Test
    public void resetSetsSizeCorrect() {
        pd.add(new Prefix());
        pd.reset();
        assertEquals(256, pd.size());
    }        
}

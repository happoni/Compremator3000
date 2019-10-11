package hy.happoni.compremator3000.domain.LZW;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka Word-luokalle.
 */
public class WordTest {

    Word w;
    Byte b;

    public WordTest() {
    }

    @Before
    public void setUp() {
        b = 22;
        w = new Word(3, b);
    }

    /**
     * Testi tarkistaa, että getPrefix palauttaa oikean arvon.
     */
    @Test
    public void getPrefixReturnsCorrectValue() {
        assertEquals(3, w.getPrefix());
    }

    /**
     * Testi tarkistaa, että getValue palauttaa oikean arvon.
     */
    @Test
    public void getValueReturnsCorrectValue() {
        Byte c = 22;
        assertEquals(c, b);
    }

    /**
     * Testillä tarkistetaan, että addChild-metodi lisää sanalle lapsen oikein.
     * Samalla tarkistetaan, että metodi getChild palauttaa oikeanlaisen lapsen.
     */
    @Test
    public void addChildAddsCorrectChild() {
        Byte d = -12;
        Word c = new Word(10, d);
        w.addChild(10, d);
        assertEquals(c.getPrefix(), w.getChild(d).getPrefix());
        w.addChild(12, b);
        assertEquals(12, w.getChild(b).getPrefix());
    }
}

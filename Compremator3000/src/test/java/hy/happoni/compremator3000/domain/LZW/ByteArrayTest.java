package hy.happoni.compremator3000.domain.LZW;

import java.nio.ByteBuffer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka ByteArray-luokalle.
 */
public class ByteArrayTest {

    ByteArray ba;
    Byte b;

    public ByteArrayTest() {
    }

    @Before
    public void setUp() {
        ba = new ByteArray();
        b = 55;
    }

    /**
     * Testillä tarkistetaan, että kokonaisluvun lisääminen onnistuu oikein.
     */
    @Test
    public void addIntAddsCorrectValue() {
        int i = 69;
        ba.add(i);
        assertEquals(4, ba.size());
        assertEquals(i, ByteBuffer.wrap(ba.getBytes()).getInt());
    }

    /**
     * Testillä tarkistetaan, että useiden tavujen lisääminen kasvattaa lopulta
     * taulukkoa.
     */
    @Test
    public void addingManyBytesResizesArray() {
        for (int i = 0; i < 5000; i++) {
            ba.add(b);
        }
        assertEquals(5000, ba.size());
    }

    /**
     * Testillä tarkistetaan, että set-metodi asettaa oikean tavun oikeaan
     * paikkaan.
     */
    @Test
    public void setSetsCorrectByteToCorrectIndex() {
        ba.add(30);
        ba.set(0, b);
        assertTrue(ba.get(0) == b);
        assertFalse(ba.get(1) == 1);
    }
}

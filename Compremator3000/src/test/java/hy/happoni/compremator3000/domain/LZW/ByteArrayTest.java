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
    public void addShortAddsCorrectValue() {
        short i = 69;
        ba.add(i);
        assertEquals(2, ba.size());
        assertEquals(i, ByteBuffer.wrap(ba.getBytes()).getShort());
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
}

package hy.happoni.compremator3000.domain.LZ77;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka LZList-luokalle.
 */
public class LZListTest {

    LZList list;
    
    public LZListTest() {
    }

    @Before
    public void setUp() {
        list = new LZList();
        byte b = 15;
        list.add(new Tuple(1, 2, b));
    }

    /**
     * Testillä tarkistetaan, että size-metodi antaa oikean arvon.
     */
    @Test
    public void sizeReturnsCorrectValue() {
        assertEquals(1, list.size());
    }
    
    /**
     * Testillä tarkistetaan, että listan sisäisen taulukon kokoa kasvatetaan, kun listalle lisätään paljon tupleja.
     */
    @Test
    public void addingManyValuesIncreasesArraySize() {
        byte a = 77;
        for (int i = 0; i < 200; i++) {
            list.add(new Tuple(1, i, a));
        }
        assertEquals(201, list.size());
    }
    
    /**
     * Testillä tarkistetaan, että listan get-metodi palauttaa oikean tuplen.
     */
    @Test
    public void getReturnsCorrectTuple() {
        assertEquals(1, list.get(0).offset);
        assertEquals(2, list.get(0).byteLength);       
    }
    
    /**
     * Testillä tarkistetaan, että toByteArray-metodi toimii oikein.
     */
    @Test
    public void toByteArrayReturnsCorrectValue() {
        byte[] ba = list.toByteArray();
        assertEquals(5, ba.length);
    }
    
}

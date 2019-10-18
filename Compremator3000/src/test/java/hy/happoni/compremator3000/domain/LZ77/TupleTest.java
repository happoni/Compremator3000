package hy.happoni.compremator3000.domain.LZ77;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka Tuple-luokalle.
 */
public class TupleTest {

    Tuple t;
    
    public TupleTest() {
    }        
    
    @Before
    public void setUp() {
        byte b = 100;
        t = new Tuple(5, 10, b);
    }    

    @Test
    public void getOffsetReturnsCorrectValue() {
        assertEquals(5, t.getOffset());        
    }
    
    @Test
    public void getStringLengthReturnsCorrectValue() {
        assertEquals(10, t.getByteLength());
    }
    
    @Test
    public void getNextCharReturnsCorrectValue() {
        byte a = 100;
        assertEquals(a, t.getNextByte());
    }

}

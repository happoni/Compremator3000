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
        t = new Tuple(5, 10, "l");
    }    

    @Test
    public void getOffsetReturnsCorrectValue() {
        assertEquals(5, t.getOffset());        
    }
    
    @Test
    public void getStringLengthReturnsCorrectValue() {
        assertEquals(10, t.getStringLength());
    }
    
    @Test
    public void getNextCharReturnsCorrectValue() {
        String a = "l";
        char c = a.charAt(0);
        assertEquals(c, t.getNextChar());
    }

}

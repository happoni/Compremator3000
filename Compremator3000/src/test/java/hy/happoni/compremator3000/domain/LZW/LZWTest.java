package hy.happoni.compremator3000.domain.LZW;

import org.apache.commons.lang3.SerializationUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Luokka, joka huolehtii LZW-algoritmin testauksesta.
 */
public class LZWTest {

    LZW lzw;
    byte[] testInput;
    byte[] emptyInput;
    
    public LZWTest() {
    }

    @Before
    public void setUp() {
        lzw = new LZW();
        // hyödynnetään SerializationUtilsia varmemman ja helpomman testauksen saavuttamiseksi
        testInput = SerializationUtils.serialize("TOBEORNOTTOBEORTOBEORNOT");
        emptyInput = new byte[1];
        emptyInput[0] = (byte) 255;
    }

    /**
     * Testillä tutkitaan LZW:n pakkausmetodin toimintaa.
     */
    @Test
    public void compressTest() {
        byte[] emptyOutput = lzw.compress(emptyInput);
        assertEquals(2, emptyOutput.length);        
        byte[] testOutput = lzw.compress(testInput);
        assertEquals(46, testOutput.length);
    }
    
    @Test
    public void decompressTest() {
        byte[] output = lzw.compress(testInput);
        byte[] returned = lzw.decompress(output);
        String result = SerializationUtils.deserialize(returned);
        assertEquals(result, "TOBEORNOTTOBEORTOBEORNOT");
    }
    
    
    
}

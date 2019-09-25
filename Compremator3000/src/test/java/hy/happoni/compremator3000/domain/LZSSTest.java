// pakkaus
package hy.happoni.compremator3000.domain;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka, joka huolehtii LZSS-luokan testaamisesta.
 */
public class LZSSTest {

    LZSSTuple tupleIsChar;
    LZSSTuple tupleIsNotChar;
    LZSS lzss1;
    LZSS lzss2;
    ArrayList<LZSSTuple> codedToBe;
    
    public LZSSTest() {
    }

    @Before
    public void setUp() {
        tupleIsChar = new LZSSTuple(true, "k");
        tupleIsNotChar = new LZSSTuple(false, 2, 3);
        lzss1 = new LZSS(27, 9);
        lzss2 = new LZSS();
    }

    @Test
    public void creatingTupleWorks() {
        assertEquals(tupleIsChar.toString(), "true,k,0,0");
        assertEquals(tupleIsNotChar.toString(), "false,null,2,3");
        assertFalse(tupleIsChar.toString().equals(tupleIsNotChar.toString()));
    }

    @Test
    public void constructorWithoutParametresWorks() {
        assertEquals(lzss2.dictionaryLength, 31);
        assertEquals(lzss2.bufferLength, 7);
    }

    @Test
    public void constructorWithParametresWorks() {
        assertEquals(lzss1.dictionaryLength, 27);
        assertEquals(lzss1.bufferLength, 9);
    }
    
    @Test
    public void setSearchWindowSetsCorrectNumber() {
        assertEquals(lzss1.setSearchWindowStart(0, 27), 0);
        assertEquals(lzss1.setSearchWindowStart(29, 27), 2);
    }

    @Test
    public void setBufferEndSetsCorrectNumber() {
        assertEquals(lzss1.setBufferEnd(0, 9, 11), 9);
        assertEquals(lzss1.setBufferEnd(6, 9, 10), 10);
    }

    @Test
    public void setSearchSubstringSetsCorrectString() {
        assertEquals(lzss1.setSearchSubstring("maitojuna", 0, 0), "");
        assertEquals(lzss1.setSearchSubstring("maitojuna", 3, 0), "mai");
    }

    @Test
    public void compressWorksCorrectly() {
        LZSSTuple testiTuple1;
        LZSSTuple testiTuple2;
        LZSSTuple testiTuple3;
        LZSSTuple testiTuple4;
        
        byte[] tested = lzss2.compress("MISSISSIPPI");
        ArrayList<LZSSTuple> testiTuplet = SerializationUtils.deserialize(tested);
        
        testiTuple1 = testiTuplet.get(0);
        testiTuple2 = testiTuplet.get(4);
        testiTuple3 = new LZSSTuple(false, "L");
        testiTuple4 = testiTuplet.get(1);

        assertEquals(testiTuple1.character, "M");
        assertEquals(testiTuple1.singleChar, true);
        assertEquals(testiTuple1.length, 0);
        assertEquals(testiTuple1.position, 0);
        assertEquals(testiTuple2.singleChar, false);
        assertEquals(testiTuple2.position, 1);
        assertEquals(testiTuple2.length, 3);
        assertFalse(testiTuple4.equals(testiTuple3));
    }

    @Test
    public void decompressWorksCorrectly() {
        byte[] tested = lzss2.compress("TOBEORNOTTOBEORTOBEORNOT");
        assertEquals(lzss2.decompress(tested), "TOBEORNOTTOBEORTOBEORNOT");
    }

}

// pakkaus
package hy.happoni.compremator3000.domain.LZ77;

// tarvittavat importit testeihin
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka, joka huolehtii LZ77-algoritmin testauksesta.
 */
public class LZTest {

    LZ77 lz1;
    LZ77 lz2;
    ArrayList<Tuple> codedToBe;

    public LZTest() {
    }

//    @Before
//    public void setUp() {
//        lz1 = new LZ77(15, 5);
//        lz2 = new LZ77();
//    }
//
//    @Test
//    public void constructorWithoutParametresWorks() {
//        assertEquals(lz2.dictionaryLength, 1024);
//        assertEquals(lz2.bufferLength, 20);
//    }
//
//    @Test
//    public void setSearchWindowSetsCorrectNumber() {
//        assertEquals(lz1.setSearchWindowStart(0, 15), 0);
//        assertEquals(lz1.setSearchWindowStart(16, 15), 1);
//    }
//
//    @Test
//    public void setBufferEndSetsCorrectNumber() {
//        assertEquals(lz1.setBufferEnd(0, 5, 10), 5);
//        assertEquals(lz1.setBufferEnd(9, 5, 10), 10);
//    }

//    @Test
//    public void setSearchSubstringSetsCorrectString() {
//        assertEquals(lz1.setSearchSubstring("maitojuna", 0, 0), "");
//        assertEquals(lz1.setSearchSubstring("maitojuna", 3, 0), "mai");
//    }
//
//    @Test
//    public void compressWorksCorrectly() {
//        byte[] testiTuplet = lz2.compress("MISSISSIPPI");
//        ArrayList<Tuple> testiTupleLista = SerializationUtils.deserialize(testiTuplet);
//        Tuple testiTuple1 = testiTupleLista.get(0);
//        Tuple testiTuple2 = testiTupleLista.get(1);        
//        Tuple testiTuple3 = new Tuple(99, 5, "L");
//
//        assertEquals(testiTuple1.offset, 0);
//        assertEquals(testiTuple1.stringLength, 0);
//        assertEquals(testiTuple1.nextChar, "M");
//        assertFalse(testiTuple2.equals(testiTuple3));
//    }
//
//    @Test
//    public void decompressWorksCorrectly() {
//        byte[] tested = lz2.compress("TOBEORNOTTOBEORTOBEORNOT");
//        codedToBe = SerializationUtils.deserialize(tested);
//         
//         assertEquals(lz2.decompress(tested), "TOBEORNOTTOBEORTOBEORNOT");
//    }

}

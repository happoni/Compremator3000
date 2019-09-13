// pakkaus
package hy.happoni.compremator3000.domain;

// tarvittavat importit testeihin
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka, joka huolehtii LZ77-algoritmin testauksesta.
 */
public class LZTest {

    LZ lz1;
    LZ lz2;
    List<Tuple> codedToBe;

    public LZTest() {
    }

    @Before
    public void setUp() {
        lz1 = new LZ(15, 5);
        lz2 = new LZ();
    }

    @Test
    public void constructorWithoutParametresWorks() {
        assertEquals(lz2.dictionaryLength, 31);
        assertEquals(lz2.bufferLength, 7);
    }

    @Test
    public void setSearchWindowSetsCorrectNumber() {
        assertEquals(lz1.setSearchWindowStart(0, 15), 0);
        assertEquals(lz1.setSearchWindowStart(16, 15), 1);
    }

    @Test
    public void setBufferEndSetsCorrectNumber() {
        assertEquals(lz1.setBufferEnd(0, 5, 10), 5);
        assertEquals(lz1.setBufferEnd(9, 5, 10), 10);
    }

    @Test
    public void setSearchSubstringSetsCorrectString() {
        assertEquals(lz1.setSearchSubstring("maitojuna", 0, 0), "");
        assertEquals(lz1.setSearchSubstring("maitojuna", 3, 0), "mai");
    }

    @Test
    public void compressWorksCorrectly() {
        Tuple testiTuple1;
        Tuple testiTuple2;
        Tuple testiTuple3;
        List<Tuple> testiTuplet = lz2.compress("MISSISSIPPI");
        testiTuple1 = testiTuplet.get(0);
        testiTuple2 = testiTuplet.get(1);
        testiTuple3 = new Tuple(99, 5, "L");

        assertEquals(testiTuple1.offset, 0);
        assertEquals(testiTuple1.stringLength, 0);
        assertEquals(testiTuple1.nextChar, "M");
        assertFalse(testiTuple2.equals(testiTuple3));
    }

    @Test
    public void decompressWorksCorrectly() {
        codedToBe = lz2.compress("TOBEORNOTTOBEORTOBEORNOT");
        assertEquals(lz2.decompress(codedToBe), "TOBEORNOTTOBEORTOBEORNOT");
    }

}

package hy.happoni.compremator3000.domain.LZ77;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka, joka huolehtii LZ77-algoritmin testauksesta.
 */
public class LZ77Test {

    LZ77 lz1;
    LZ77 lz2;
    byte[] input;

    public LZ77Test() {
    }

    @Before
    public void setUp() {
        lz1 = new LZ77();
        lz2 = new LZ77();
        String testInput = "TOBEORNOTTOBEORTOBEORNOT";
        input = new byte[24];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) testInput.charAt(i);
        }
        
        
        //input = SerializationUtils.serialize(testInput);
    }

    /**
     * Testi tarkistaa, että apumetodi setDictionaryBegin asettaa oikean
     * aloituskohdan sanakirjalle.
     */
    @Test
    public void setDictionaryBeginReturnsCorrectValue() {
        assertEquals(0, lz1.setDictionaryBegin(10, 25));
        assertEquals(5, lz1.setDictionaryBegin(15, 10));
    }

    /**
     * Testi tarkistaa, että apumetodi setBufferEnd asettaa oikean päätöskohdan
     * bufferille.
     */
    @Test
    public void setBufferEndReturnsCorrectValue() {
        assertEquals(35, lz1.setBufferEnd(3, 50));
        assertEquals(50, lz1.setBufferEnd(30, 50));
    }

    /**
     * Testillä tarkistetaan, että apumetodi setSearchArray asettaa oikeanlaisen
     * tavulistan.
     */
    @Test
    public void setSearchArrayReturnsCorrectByteArray() {
        Assert.assertArrayEquals(null, lz1.setSearchArray(input, 0, 5));
        byte[] array = lz1.setSearchArray(input, 4, 0);
        assertEquals(4, array.length);
        String correct = "TOBE";
        byte[] ba = new byte[4];
        for (int j = 0; j < ba.length; j++) {
            ba[j] = (byte) correct.charAt(j);
        }
        
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], ba[i]);
        }
    }

    /**
     * Testillä tarkistetaan, että apumetodi setSearchTarget asettaa oikean
     * hakukohteen.
     */
    @Test
    public void setSearchTargetReturnsCorrectByteArray() {
        byte[] array = lz1.setSearchTarget(input, 0, 3);
        assertEquals(3, array.length);
        String correct = "TOB";
        byte[] ba = new byte[3];
        for (int j = 0; j < ba.length; j++) {
            ba[j] = (byte) correct.charAt(j);
        }
        
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], ba[i]);
        }
    }

    /**
     * Testillä tarkistetaan, että apumetodi isSubArray löytää osataulukon
     * isommasta taulukosta.
     */
    @Test
    public void isSubArrayRecognizeSameArrays() {
        String s1 = "klubi";
        byte[] ba1 = new byte[5];
        for (int j = 0; j < ba1.length; j++) {
            ba1[j] = (byte) s1.charAt(j);
        }
        
        byte[] empty = new byte[0];
        assertFalse(lz1.isSubArray(ba1, empty));
        assertFalse(lz1.isSubArray(input, ba1));
        assertTrue(lz1.isSubArray(ba1, ba1));
    }

    /**
     * Testillä tarkkaillaan compress-metodin toimintaa, ja että se pakkaa
     * tiedostoja ongelmitta.
     */
    @Test
    public void compressWorksCorrectly() {
        byte[] output = lz1.compress(input);
        assertEquals((9 * 5), output.length);
    }

}

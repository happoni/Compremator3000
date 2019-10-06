package hy.happoni.compremator3000.domain.LZW;

import hy.happoni.compremator3000.domain.LZW.LZW;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka, joka huolehtii LZW-algoritmin testauksesta.
 */
public class LZWTest {

    LZW lzw1;

    public LZWTest() {
    }

    @Before
    public void setUp() {
        lzw1 = new LZW();
    }

//    @Test
//    public void initDictionaryWorksCorrectly() {
//        Map<String, Integer> testDict = lzw1.initDictionary();
//        int test1 = testDict.get("" + (char) 1);
//        int test2 = testDict.get("" + (char) 200);
//        assertEquals(test1, 1);
//        assertEquals(test2, 200);
//    }

//    @Test
//    public void compressWorksCorrectly() {
//        byte[] testCompress = lzw1.compress("TOBEORNOTTOBE");
//        ArrayList<Integer> testList = SerializationUtils.deserialize(testCompress);
//        int test3 = testList.get(0);
//        int test4 = testList.get(1);
//        assertEquals(test3, 84);
//        assertFalse(test4 == 1);
//    }

//    @Test
//    public void decompressWorksCorrectly() {
//        ArrayList<Integer> testDecompress = new ArrayList<>();
//        testDecompress.add(72);
//        testDecompress.add(73);
//        testDecompress.add(83);
//        testDecompress.add(83);
//        testDecompress.add(257);
//        testDecompress.add(83);
//        testDecompress.add(196);
//
//        String testDecompressed = lzw1.decompress(SerializationUtils.serialize(testDecompress));
//        assertEquals(testDecompressed, "HISSISSÄ");
    }



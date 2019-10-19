// pakkaus
package hy.happoni.compremator3000.ui;

import hy.happoni.compremator3000.io.FileIO;
import java.io.File;
import java.io.FileWriter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * Testiluokka sovelluslogiikka-luokalle.
 */
public class AppLogicTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File testReadFile;
    File testWriteFile;
    String testReadPath;
    String testWritePath;
    String testText;
    byte[] testBytes;
    FileIO fileIO;
    AppLogic logic;

    public AppLogicTest() {
    }

    /**
     * Metodi luo tarvittavia väliaikaistiedostoja yms. testeille.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testReadFile = testFolder.newFile("readfile.txt");
        testWriteFile = testFolder.newFile("writefile.txt");

        testText = "Mississippi on iso joki jossa isi soutaa.";
        testBytes = testText.getBytes();

        try (FileWriter file = new FileWriter(testReadFile.getAbsolutePath())) {
            file.write(testText);
        }
        testReadPath = testReadFile.getAbsolutePath();
        testWritePath = testWriteFile.getAbsolutePath();

        fileIO = new FileIO();
        logic = new AppLogic();
    }

    /**
     * Testi LZ77-algoritmilla pakkaamiselle.
     */
    @Test
    public void compressLZTest() {
        assertTrue(logic.compress(testReadPath, "lz77"));
    }

    /**
     * Testi LZW-algoritmilla pakkaamiselle.
     */
    @Test
    public void compressLZWTest() {
        assertTrue(logic.compress(testReadPath, "lzw"));
    }

    /**
     * Testillä tarkistetaan, että pakkaaminen epäonnistuu oikein.
     */
    @Test
    public void compressFailsCorrectly() {
        assertFalse(logic.compress("lol", "lz77"));
    }
}

// pakkaus
package hy.happoni.compremator3000.io;

import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

/**
 * Testiluokka tiedostonlukijalle.
 */
public class FileIOTest {

    // luodaan väliaikainen testikansio ja poikkeuksien testaaminen
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    public ExpectedException expectedEx = ExpectedException.none();

    File testReadFile;
    File testWriteFile;
    String testReadPath;
    String testWritePath;
    String testText;
    byte[] testBytes;
    FileIO fileIO;

    // ennen testejä asetetaan sopiva testiteksti ja kirjoitetaan se testitiedostoon
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
    }

    @Test
    public void readFileReturnsCorrectText() {
        String returnedString = fileIO.readFile(testReadPath);
        assertEquals(returnedString, testText);
        assertFalse(returnedString.contains("666"));
    }

    @Test
    public void readCompressedFileReturnsCorrectByteArray() {
        byte[] returnedBytes = fileIO.readFileToByteArray(testReadPath);
        Assert.assertArrayEquals(returnedBytes, testBytes);
    }

    @Test
    public void writeFileWritesCorrectText() {
        String correct = "Heippalappu rapussa.";
        fileIO.writeFile(correct, testWritePath);
        String tested = fileIO.readFile(testWritePath);
        assertEquals(correct, tested);
        assertFalse(tested.equals("ei ole oikein"));
    }

    @Test
    public void writeCompressedFileWritesCorrectText() {
        byte[] correct = testBytes;
        fileIO.writeCompressedFile(testBytes, testWritePath, ".lz");
        byte[] tested = fileIO.readFileToByteArray(testWritePath + ".lz");
        Assert.assertArrayEquals(correct, tested);
    }

    @After
    public void tearDown() {
        testReadFile.delete();
        testWriteFile.delete();
        testFolder.delete();
    }
}

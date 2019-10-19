package hy.happoni.compremator3000.io;

import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import org.junit.Assert;
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

    File testReadFile;
    File testWriteFile;
    String testReadPath;
    String testWritePath;
    String testText;
    byte[] testBytes;
    FileIO fileIO;

    /**
     * Ennen testejä asetetaan sopiva testiteksti ja kirjoitetaan se
     * testitiedostoon.
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
    }

    /**
     * Testillä tarkistetaan, että tiedoston lukeminen byte arrayksi palauttaa
     * oikeat tavut.
     */
    @Test
    public void readFileToByteArrayReturnsCorrectBytesWhenFileExists() {
        byte[] returnedBytes = fileIO.readFileToByteArray(testReadPath);
        Assert.assertArrayEquals(returnedBytes, testBytes);
    }

    /**
     * Testillä tarkistetaan, että pakattu tiedosto kirjoitetaan oikein tiedostoon.
     */
    @Test
    public void writeCompressedFileWritesCorrectText() {
        byte[] correct = testBytes;
        fileIO.writeCompressedFile(testBytes, testWritePath, ".lz");
        byte[] tested = fileIO.readFileToByteArray(testWritePath + ".lz");
        Assert.assertArrayEquals(correct, tested);
    }

    /**
     * Tetillä tarkistetaan, että purettu tiedosto kirjoitetaan oikein.
     */
    @Test
    public void writeBytesToFileWritesCorrect() {
        byte[] correct = testBytes;
        fileIO.writeBytesToFile(testBytes, testWritePath);
        byte[] tested = fileIO.readFileToByteArray(testWritePath);
        Assert.assertArrayEquals(correct, tested);
    }
    
    @After
    public void tearDown() {
        testReadFile.delete();
        testWriteFile.delete();
        testFolder.delete();
    }
}

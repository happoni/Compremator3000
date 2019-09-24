// pakkaus
package hy.happoni.compremator3000.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

/**
 * Testiluokka tiedostonlukijalle.
 */
public class FileIOTest {

    // luodaan väliaikainen testikansio
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    public ExpectedException expectedEx = ExpectedException.none();

    File testReadFile;
    File testWriteFile;
    String testReadPath;
    String testWritePath;
    String testText;
    byte[] testBytes;

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
    }

    /**
     * Testillä tarkistetaan, että tiedoston lukeminen toimii. Tutkitaan sekä tavulista että merkkijono.
     */
    @Test
    public void readFileReturnsCorrectText() {
        byte[] returnedBytes = FileIO.readFile(testReadPath);
        String returnedString = new String(returnedBytes);

        Assert.assertArrayEquals(returnedBytes, testBytes);
        assertEquals(returnedString, testText);
        assertFalse(returnedString.contains("666"));        
    }
    
    /**
     * Testillä tarkistetaan, että tiedoston lukemisen epäonnistuminen aiheuttaa poikkeuksen.
     */
    @Test
    public void failingReadFileThrowsException() {
        expectedEx.expect(IOException.class);
        FileIO.readFile("eiloydy.txt");
    }

    /**
     * Testillä tarkistetaan, että tekstin kirjoittaminen tiedostoon toimii.
     * @throws java.io.IOException
     */
    @Test
    public void writeFileWritesCorrectText() throws IOException {
        assertTrue(FileIO.writeFile(testBytes, testWritePath, ""));
        Assert.assertArrayEquals(FileIO.readFile(testWritePath), testBytes);      
    }
    
    /**
     * Testillä tarkistetaan, että tekstin kirjoittamisen epäonnistuessa tulee asianmukainen virhe.
     * @throws Exception 
     */
    @Test
    public void writeFileThrowsErrorWhenFileNotFound() throws Exception {
        expectedEx.expect(FileNotFoundException.class);
        expectedEx.expectMessage("Something has gone wrong.");
        FileIO.writeFile(testBytes, "eiloydy.txt", "");
    }
    
    @After
    public void tearDown() {
        testReadFile.delete();
        testWriteFile.delete();
    }
}

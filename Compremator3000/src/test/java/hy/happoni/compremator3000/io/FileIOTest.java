// pakkaus
package hy.happoni.compremator3000.io;

import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Testiluokka tiedostonlukijalle.
 */
public class FileIOTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File testFile;
    FileIO fileIO;
    String testPath;
    
    @Before
    public void setUp() throws Exception {
        fileIO = new FileIO();
        testFile = testFolder.newFile("testfile.txt");
        
        try (FileWriter file = new FileWriter(testFile.getAbsolutePath())) {
            file.write("Mississippi on iso joki jossa isi soutaa.");
        }   
        testPath = testFile.getAbsolutePath();
    }

    @Test
    public void readFileReturnsCorrectText() {
        String correct = "Mississippi on iso joki jossa isi soutaa.";
    
        String test = fileIO.readFile(testPath);        
        assertTrue(correct.equals(test));
        assertFalse(test.contains("666"));
    }
    
    @After
    public void tearDown() {
        testFile.delete();
    }
}

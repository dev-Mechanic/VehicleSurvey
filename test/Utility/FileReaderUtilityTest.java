/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harryp
 */
public class FileReaderUtilityTest {
    
    public FileReaderUtilityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFileName method, of class FileReaderUtility.
     */
    @Test
    public void testGetFileName() {
        System.out.println("getFileName");
        FileReaderUtility instance = null;
        String expResult = "";
        String result = instance.getFileName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ReadAll method, of class FileReaderUtility.
     */
    @Test
    public void testReadAll() {
        System.out.println("ReadAll");
        FileReaderUtility instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.ReadAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

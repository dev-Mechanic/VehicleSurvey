/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleProgram;

import java.util.Calendar;
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
public class ProgramTest {
    
    public ProgramTest() {
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
     * Test of Run method, of class Program.
     */
    @Test
    public void testRun() {
        System.out.println("Run");
        String fileAddress = "";
        Calendar startFrom = null;
        int GroupByMinutes = 0;
        Program instance = new Program();
        String expResult = "";
        String result = instance.Run(fileAddress, startFrom, GroupByMinutes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Program.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Program.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

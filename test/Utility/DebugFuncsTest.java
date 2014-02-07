/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

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
public class DebugFuncsTest {
    
    public DebugFuncsTest() {
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
     * Test of M2C method, of class DebugFuncs.
     */
    @Test
    public void testM2C() {
        System.out.println("M2C");
        String msg = "";
        DebugFuncs.M2C(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of M2S method, of class DebugFuncs.
     */
    @Test
    public void testM2S() {
        System.out.println("M2S");
        String msg = "";
        DebugFuncs.M2S(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

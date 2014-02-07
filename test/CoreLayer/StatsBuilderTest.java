/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreLayer;

import Entity.StatsSet;
import Entity.VehicleRecord;
import java.util.ArrayList;
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
public class StatsBuilderTest {
    
    public StatsBuilderTest() {
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
     * Test of Builder method, of class StatsBuilder.
     */
    @Test
    public void testBuilder() {
        System.out.println("Builder");
        ArrayList<VehicleRecord> list = null;
        StatsSet expResult = null;
        StatsSet result = StatsBuilder.Builder(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GroupStats method, of class StatsBuilder.
     */
    @Test
    public void testGroupStats() {
        System.out.println("GroupStats");
        ArrayList<VehicleRecord> list = null;
        Calendar startAt = null;
        int groupMinutes = 0;
        ArrayList expResult = null;
        ArrayList result = StatsBuilder.GroupStats(list, startAt, groupMinutes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

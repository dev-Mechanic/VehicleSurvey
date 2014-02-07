/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreLayer;

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
public class SurveyParserTest {
    
    public SurveyParserTest() {
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
     * Test of parseSurvey method, of class SurveyParser.
     */
    @Test
    public void testParseSurvey() {
        System.out.println("parseSurvey");
        ArrayList<String> survey = null;
        Calendar startFrom = null;
        ArrayList expResult = null;
        ArrayList result = SurveyParser.parseSurvey(survey, startFrom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

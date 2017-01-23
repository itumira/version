/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.sql.Date;
import java.sql.Time;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ethan Mirado
 */
public class GestionDateTest {
    
    public GestionDateTest() {
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
     * Test of getAnneeAuj method, of class GestionDate.
     */
    @Test
    public void testGetAnneeAuj() {
        System.out.println("getAnneeAuj");
        GestionDate instance = new GestionDate();
        int expResult = 2017;
        int result = instance.getAnneeAuj();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }

    /**
     * Test of getDateAuj method, of class GestionDate.
     */
    @Test
    public void testGetDateAuj() {
        System.out.println("getDateAuj");
        GestionDate instance = new GestionDate();
        String expResult = "18/01/17";
        String result = instance.getDateAuj();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    //    fail("The test case is a prototype.");
    }

    /**
     * Test of dateSouple method, of class GestionDate.
     */
    @Test
    public void testDateSouple() throws Exception {
        System.out.println("dateSouple");
        String date = "11/01/17";
        GestionDate instance = new GestionDate();
        String expResult = "2017-01-11";
        String result = instance.dateSouple(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    //   fail("The test case is a prototype.");
    }

    /**
     * Test of heureSouple method, of class GestionDate.
     */
    @Test
    public void testHeureSouple() {
        System.out.println("heureSouple");
        String heure_entrer = "01:00 PM";
        GestionDate instance = new GestionDate();
        Time expResult = Time.valueOf("13:00:00");
        Time result = instance.heureSouple(heure_entrer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }

    
    /**
     * Test of isWeekEnd method, of class GestionDate.
     */
    @Test
    public void testIsWeekEnd() {
        System.out.println("isWeekEnd");
        Date date = Date.valueOf("2017-01-11");
        GestionDate instance = new GestionDate();
        boolean expResult = false;
        boolean result = instance.isWeekEnd(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    //    fail("The test case is a prototype.");
    }
    
}

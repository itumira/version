/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.sql.Time;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import panel.PanelJourFerie;

/**
 *
 * @author Ethan Mirado
 */
public class MetierJourFerieTest {

    public MetierJourFerieTest() {
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
     * Test of isJourFerier method, of class MetierJourFerie.
     */
    @Test
    public void testIsJourFerier() throws Exception {
        System.out.println("isJourFerier");
        Time heure = Time.valueOf("11:00:00");
        String date = "2016-06-26";
        MetierJourFerie instance = new MetierJourFerie();
        boolean expResult = true;
        boolean result = instance.isJourFerier(heure, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

}

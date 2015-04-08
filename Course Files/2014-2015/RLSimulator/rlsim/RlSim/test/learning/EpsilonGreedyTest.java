/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.HashMap;
import javax.swing.JTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alessandrobisiani
 */
public class EpsilonGreedyTest {
    
    public EpsilonGreedyTest() {
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
     * Test of setEpsilon method, of class EpsilonGreedy.
     */
    @Test
    public void testSetEpsilon() {
        System.out.println("setEpsilon");
        double e = 0.0;
        EpsilonGreedy instance = null;
        instance.setEpsilon(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of next method, of class EpsilonGreedy.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        HashMap m = new HashMap();
        m.put("one","1");
        m.put("two","2");
        m.put("three","3");
        EpsilonGreedy instance = new EpsilonGreedy(new QLearner(new JTable(), new JTable(), new Agent()));
        String expResult = "one";
        String result = instance.next(m);
        assertEquals(expResult, result);
    }

    /**
     * Test of setStateSpace method, of class EpsilonGreedy.
     */
    @Test
    public void testSetStateSpace() {
        System.out.println("setStateSpace");
        String[] ss = null;
        EpsilonGreedy instance = null;
        instance.setStateSpace(ss);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

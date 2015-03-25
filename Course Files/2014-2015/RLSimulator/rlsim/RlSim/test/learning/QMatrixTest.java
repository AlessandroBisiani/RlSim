/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

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
public class QMatrixTest {
    
    public QMatrixTest() {
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
     * Test of getValueAt method, of class QMatrix.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int x = 0;
        int y = 0;
        QMatrix instance = new QMatrix();
        Object expResult = null;
        Object result = instance.getValueAt(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueAt method, of class QMatrix.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        int x = 0;
        int y = 0;
        int v = 0;
        QMatrix instance = new QMatrix();
        instance.setValueAt(x, y, v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tdError method, of class QMatrix.
     */
    @Test
    public void testTdError() {
        System.out.println("tdError");
        QMatrix instance = new QMatrix();
        instance.tdError();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRowCount method, of class QMatrix.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        QMatrix instance = new QMatrix();
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnCount method, of class QMatrix.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        QMatrix instance = new QMatrix();
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of resetMatrix method, of class QMatrix.
     */
    @Test
    public void testResetMatrix() {
        System.out.println("resetMatrix");
        String[] states = {"s1","s2"};
        QMatrix instance = new QMatrix();
        boolean expResult = true;
        boolean result = instance.resetMatrix(states);
        assertEquals(expResult, result);
    }   
}

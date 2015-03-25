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
public class RMatrixTest {
    
    public RMatrixTest() {
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
     * Test of getRowCount method, of class RMatrix.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        RMatrix instance = new RMatrix();
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnCount method, of class RMatrix.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        RMatrix instance = new RMatrix();
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class RMatrix.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int rowIndex = 0;
        int columnIndex = 0;
        RMatrix instance = new RMatrix();
        Object expResult = null;
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetMatrix method, of class RMatrix.
     */
    @Test
    public void testResetMatrix() {
        System.out.println("resetMatrix");
        String[] states = {""};
        RMatrix instance = new RMatrix();
        boolean expResult = true;
        boolean result = instance.resetMatrix(states);
        assertEquals(expResult, result);
    }
    @Test
    public void testResetMatrix2() {
        System.out.println("resetMatrix");
        String[] states = {"1","2","3"};
        RMatrix instance = new RMatrix();
        boolean expResult = true;
        boolean result = instance.resetMatrix(states);
        assertEquals(expResult, result);
    }
    @Test (expected=RuntimeException.class)
    public void testResetMatrix3() {
        System.out.println("resetMatrix");
        String[] states = {"1",2,"3"};
        RMatrix instance = new RMatrix();
        boolean expResult = true;
        boolean result = instance.resetMatrix(states);
        assertEquals(expResult, result);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
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
public class MainFrameTest {
    
    public MainFrameTest() {
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
     * Test of closeLabelFrame method, of class MainFrame.
     */
    @Test
    public void testCloseLabelFrame() {
        System.out.println("closeLabelFrame");
        MainFrame.closeLabelFrame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMatrices method, of class MainFrame.
     */
    @Test
    public void testCreateMatrices() {
        System.out.println("createMatrices");
        ArrayList<String> statesList = null;
        MainFrame instance = new MainFrame();
        instance.createMatrices(statesList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetMatrices method, of class MainFrame.
     */
    @Test
    public void testResetMatricesTemplate() {
        System.out.println("resetMatrices");
        JTable qMatrix = null;
        JTable rMatrix = null;
        String[] states = null;
        MainFrame instance = new MainFrame();
        boolean expResult = false;
        boolean result = instance.resetMatrices(qMatrix, rMatrix, states);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testResetMatrices() {
        System.out.println("resetMatrix");
        String[] states = {""};
        JTable qMatrix = null;
        JTable rMatrix = null;
        MainFrame instance = new MainFrame();
        boolean expResult = true;
        boolean result = instance.resetMatrices(qMatrix, rMatrix, states);
        assertEquals(expResult, result);
    }
    @Test
    public void testResetMatricesx2() {
        System.out.println("resetMatrix");
        String[] states = {"1","2","3"};
        JTable qMatrix = null;
        JTable rMatrix = null;
        MainFrame instance = new MainFrame();
        boolean expResult = true;
        boolean result = instance.resetMatrices(qMatrix, rMatrix, states);
        assertEquals(expResult, result);
    }
    @Test (expected=RuntimeException.class)
    public void testResetMatricesx3() {
        System.out.println("resetMatrix");
        String[] states = {"1","2","3"};
        JTable qMatrix = null;
        JTable rMatrix = null;
        MainFrame instance = new MainFrame();
        boolean expResult = true;
        boolean result = instance.resetMatrices(qMatrix, rMatrix, states);
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class MainFrame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MainFrame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

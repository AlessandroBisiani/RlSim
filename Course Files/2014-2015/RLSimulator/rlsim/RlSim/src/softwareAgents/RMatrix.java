/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareAgents;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alessandrobisiani
 */
public class RMatrix {
    
    
    private final double[][] matrix = {{1,2,3,4,5,6,7,8,9,10}, 
                                    {11,12,13,0,0,0,0,0,0,0}, 
                                    {14,15,16,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0}};
    
    public RMatrix(){
        System.out.println(matrix[0][2] + "this test");
        System.out.println(matrix[1][1]);
        System.out.println(matrix[2][2]);
        System.out.println(getValueAt(0,0));
        System.out.println(getValueAt(1,1));
        System.out.println(getValueAt(2,2));
        System.out.println(getValueAt(2,0) + " - Rmatrix");
        System.out.println(matrix);
    }
    
    public double getValueAt(int x, int y){
        double v = 0;
        try {
            v = matrix[y][x];
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "RMatrix. Trying to get values out of bounds.");
            throw ex;
        }
        return v;
    }
    
}

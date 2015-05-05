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
    
    
    private final double[][] matrix = {{0,0,0,0,0,0,0,0,0,0}, 
                                    {0,0,0,0,0,0,0,0,0,0}, 
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0}};
    
    public RMatrix(){
        
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

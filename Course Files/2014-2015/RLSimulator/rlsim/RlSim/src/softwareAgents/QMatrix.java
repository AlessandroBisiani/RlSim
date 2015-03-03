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
public class QMatrix {
    
    //initialise 10x10 Q value matrix
    private final double[][] matrix = {{1,2,3,0,0,0,0,0,0,0}, 
                                    {4,5,6,0,0,0,0,0,0,0}, 
                                    {7,8,9,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0}};
    
    public QMatrix(){
        System.out.println(getValueAt(2,0) + " - Qmatrix");
    }
    
    public double getValueAt(int x, int y){
        try {
            double v = matrix[y][x];
            return v;
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "QMatrix. Trying to get value out of bounds.");
            throw ex;
        }
    }
    
    public void setValueAt(int x, int y, int v){
        try{
            matrix[y][x] = v;
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "QMatrix. Trying to set value out of bounds.");
            throw ex;
        }
    }
 
    public void tdError(){
        
    }
    
}

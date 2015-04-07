/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alessandrobisiani
 */
public class RMatrix extends AbstractTableModel{
    
    private String[] states = {"state1","state2","state3","state4","state5","state6","state7","state8","state9","state10"};
    private String[][] matrix =         {{"state1","0","0","0","0","0","0","0","0","0"}, 
                                                            {"state2","0","0","0","0","0","0","0","0","0"}, 
                                                            {"state3","0","0","0","0","0","0","0","0","0"},
                                                            {"state4","0","0","0","0","0","0","0","0","0"},
                                                            {"state5","0","0","0","0","0","0","0","0","0"},
                                                            {"state6","0","0","0","0","0","0","0","0","0"},
                                                            {"state7","0","0","0","0","0","0","0","0","0"},
                                                            {"state8","0","0","0","0","0","0","0","0","0"},
                                                            {"state9","0","0","0","0","0","0","0","0","0"},
                                                            {"state10","0","0","0","0","0","0","0","0","0"}};
    
    
    
    public RMatrix(){
        System.out.println("Rmatrix created");
        
        
    }
    
    /*
    public double getValueAt(int x, int y){
        double v = 0;
        try {
            v = matrix[y][x];
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "RMatrix. getValueAt() trying to get values out of bounds.");
            throw ex;
        }
        return v;
    
    }
    */

    @Override
    public int getRowCount() {
        return matrix.length;
    }

    @Override
    public int getColumnCount() {
        return matrix[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex];
    }
    
    public double getDoubleAt(int rowIndex, int columnIndex){
        return Double.parseDouble(matrix[rowIndex][columnIndex]);
    }
    
}

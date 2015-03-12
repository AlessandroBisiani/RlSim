/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alessandrobisiani
 */
public class RMatrix extends AbstractTableModel{
    
    private String[] states;
    private final Object[][] matrix =  {{1,2,3,4,5,6,7,8,9,10}, 
                                        {11,12,13,0,0,0,0,0,0,0}, 
                                        {14,15,16,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0}};
    public RMatrix(String[] states){
        this.states = states;
        System.out.println("Rmatrix reset");
    }
    public RMatrix(){
        System.out.println("Rmatrix created");
        /**
        System.out.println(matrix[0][0]);
        System.out.println(matrix[1][1]);
        System.out.println(matrix[2][2]);
        System.out.println(getValueAt(0,0));
        System.out.println(getValueAt(1,1));
        System.out.println(getValueAt(2,2));
        System.out.println(getValueAt(2,0) + " - Rmatrix");
        System.out.println(matrix);
        * 
    }
    public double getValueAt(int x, int y){
        double v = 0;
        try {
            v = matrix[y][x];
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "RMatrix. getValueAt() trying to get values out of bounds.");
            throw ex;
        }
        return v;
    * */
    }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

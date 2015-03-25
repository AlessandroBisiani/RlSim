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
    
    private String[] states = {"state1","state2","state3","state4","state5","state6","state7","state8","state9","state10"};
    private Object[][] matrix =         {{1,2,3,4,5,6,7,8,9,10}, 
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
        return matrix[rowIndex][columnIndex];
    }
    
    public boolean resetMatrix(String[] states){
        int c = 0;
        int r = 0;
        boolean b = false;
        //creates String[] beginning with null value followed by states input to the method.
        int l = states.length;
        this.states = new String[l+1];
        this.states[0] = "";
        for(int i=1 ; i<=l ; i++){
            this.states[i] = states[i-1];
        }
        //populates the matrix.
        matrix = new Object[l+1][l+1];
        for(int i=0 ; i<l ; i++){
            for(int j=0 ; j<=l ; j++){
                if(j==0){
                    matrix[i][0] = states[i];
                    r++;
                } else {
                    matrix[i][j] = 0;
                    c++;
                } 
            }
        }
        
        System.out.println("Qmatrix reset");
        if((c/l)==r){
            b=true;
        }
        return b;
    }
    
}

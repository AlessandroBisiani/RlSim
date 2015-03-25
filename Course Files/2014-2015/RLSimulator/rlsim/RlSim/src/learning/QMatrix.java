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
public class QMatrix extends AbstractTableModel{
    
    //initialise 10x10 Q value matrix
    private String[] states = {""};
    private Object[][] matrix =        {{1,2,3,0,0,0,0,0,0,0}, 
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
        System.out.println(getValueAt(2,0) + " - Qmatrix created");
    }
    
    public Object getValueAt(int rowIndex, int columnIndex){
        return matrix[rowIndex][columnIndex];
        /*
        try {
            Object v = matrix[y][x];
            return v;
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "QMatrix. Trying to get value out of bounds.");
            throw ex;
        }
                */
    }
    
    public void setValueAt(Object o, int rowIndex, int columnIndex){
        matrix[rowIndex][columnIndex] = o;
        /*
        try{
            matrix[y][x] = v;
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "QMatrix. Trying to set value out of bounds.");
            throw ex;
        }
            */
    }
 
    public void tdError(){
        
    }
    
    
    //Alt constructor takes an array of string containing state names and instantiates a matrix of that size populated by zeros, 
    //where the first cell of every row contains the state name.
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

    @Override
    public int getRowCount() {
        return matrix.length;
    }

    @Override
    public int getColumnCount() {
        return states.length;
    }
    
}

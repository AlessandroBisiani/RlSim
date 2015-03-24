/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import softwareAgents.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alessandrobisiani
 */
public class QMatrix {
    
    //initialise 10x10 Q value matrix
    private String[] states;
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
    //Alt constructor takes an array of string containing state names and instantiates a matrix of that size populated by zeros.
    public QMatrix(String[] states){
        int l = states.length;
        this.states = new String[l+1];
        this.states[0] = null;
        for(int i=1 ; i<=l ; i++){
            this.states[i] = states[i];
        }
        matrix = new Object[l+1][l+1];
        for(int i=0 ; i<l ; i++){
            for(int j=0 ; j<l ; j++){
                if(j==0){
                    matrix[i][0] = states[i];
                } else {
                    matrix[i][j] = 0;
                } 
            }
        }
        System.out.println("Qmatrix reset");
    }
    
    
    public QMatrix(){
        System.out.println(getValueAt(2,0) + " - Qmatrix created");
    }
    
    public Object getValueAt(int x, int y){
        try {
            Object v = matrix[y][x];
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public class Matrix extends AbstractTableModel{
    
    private String[] states = {"state1","state2","state3","state4","state5","state6","state7","state8","state9"};
    private String[][] matrix =                            {{"state1","0","0","0","0","0","0","0","0","0"}, 
                                                            {"state2","0","0","0","0","0","0","0","0","0"}, 
                                                            {"state3","0","0","0","0","0","0","0","0","0"},
                                                            {"state4","0","0","0","0","0","0","0","0","0"},
                                                            {"state5","0","0","0","0","0","0","0","0","0"},
                                                            {"state6","0","0","0","0","0","0","0","0","0"},
                                                            {"state7","0","0","0","0","0","0","0","0","0"},
                                                            {"state8","0","0","0","0","0","0","0","0","0"},
                                                            {"state9","0","0","0","0","0","0","0","0","0"}};
    
    
    
    public Matrix(String[][] data, String[] states){
        matrix = data;
        this.states = states;
        System.out.println("Custom model created");
    }
    
    /*
    public double getValueAt(int x, int y){
        double v = 0;
        try {
            v = matrix[y][x];
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "Matrix. getValueAt() trying to get values out of bounds.");
            throw ex;
        }
        return v;
    
    }
    */
    /**
     * Get an internal representation of the state space.
     * @return  A representation of the state space.
     */
    public String[] getStates(){
        return states;
    }
    /**
     * Get current model data, equivalent to the state of the matrix.
     * @return  All matrix data excluding state names.
     */
    public String[][] getData(){
        return matrix;
    }
    /**
     * Update the state names
     * @param states    An array of states matching that of the displayed columns in content and order.
     */
    public void setStates(String[] states){
        this.states = states;
    }
    /**
     * Update the model data.
     * @param data  The matrix content.
     */
    public void setData(String[][] data){
        matrix = data;
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
    public String getColumnName(int columnIndex){
        return states[columnIndex];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        if(columnIndex==0){
            return false;
        } else{
            return true;
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex];
    }
    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex){
            String s = String.valueOf(o);
            matrix[rowIndex][columnIndex] = s;
    }
    @Override
    public int findColumn(String columnName){
        int count = getColumnCount();
        for(int i=0;i<count;i++){
            String name = getColumnName(i);
            if(columnName.equals(name)){
                return i;
            }
        }
        return -1;
    }
    public int findRow(String rowName){
        for(int i=0;i<getRowCount();i++){
            String name = (String)getValueAt(i,0);
            if(rowName.equals(name)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Get the value at coordinates in the matrix as a double.
     * @param rowIndex      Index of the matrix row.
     * @param columnIndex   Index of the matrix column.
     * @return              The value of the matrix at the indeces passed.
     */
    public double getDoubleAt(int rowIndex, int columnIndex){
        return Double.parseDouble(matrix[rowIndex][columnIndex]);
    }
    
}

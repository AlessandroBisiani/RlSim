/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alessandrobisiani
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
    public String[] getStates(){
        return states;
    }
    public String[][] getData(){
        return matrix;
    }
    public void setStates(String[] states){
        this.states = states;
    }
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
    public int getStatesLength(){
       return states.length;
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
    
    public double getDoubleAt(int rowIndex, int columnIndex){
        return Double.parseDouble(matrix[rowIndex][columnIndex]);
    }
    
}

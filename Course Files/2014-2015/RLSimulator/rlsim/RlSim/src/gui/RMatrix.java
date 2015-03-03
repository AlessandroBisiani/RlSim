/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alessandrobisiani
 */
public class RMatrix extends AbstractTableModel{
    
    private Object[][] data = null;
    private String[] columns = null;
    
    
    public RMatrix(String[] columns){
        this.columns = columns;
    }
    
    public RMatrix(String[] columns, Object[][] data){
        this.columns = columns;
        this.data = data; 
   }

    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

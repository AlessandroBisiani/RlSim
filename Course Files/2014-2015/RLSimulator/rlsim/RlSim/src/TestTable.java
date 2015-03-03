
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alessandrobisiani
 */
public class TestTable extends AbstractTableModel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}




mport java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


/**
 * Steps to reproduce issue.
 * 1. Run this program.
 * 2. There will be two rows in table. Delete all rows one by one.
 * 3. Now click on Add button and see the exception.
 * 
 * I come to know that this exception is because of table.setAutoCreateRowSorter(true); line, which is there in TestCustTableModel's constructor
 * If you comment this line, then issue got resolved.
 * But I want to apply sorting on columns so this line is required.
 */

public class TestCustTableModel extends JPanel{
    JTable table = new JTable();
    public TestCustTableModel() {

        Object[][] data = new Object[2][3];
        data[0][0] = "1";
        data[0][1] = "User1";
        data[0][2] = "Delete";

        data[1][0] = "2";
        data[1][1] = "User2";
        data[1][2] = "Delete";

        JButton addButton = new JButton("Add");
        addButton.addMouseListener(new AddListener());
        table.setModel(new CustModel(data));
        table.addMouseListener(new TableListener());

        /**#################################
         * Following line throws ArrayIndexOutOfBoundsException. Please comment or Uncomment following line and see the difference.
         */
        table.setAutoCreateRowSorter(true);


        table.getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(addButton);
        this.add(scrollPane);
    }


    class TableListener extends MouseAdapter {
        public void mouseClicked(MouseEvent evnt) {
            Point p = evnt.getPoint();
            if(table.columnAtPoint(p) == 2) {
                Object[][] data = null;
                if(table.getModel().getRowCount() == 2 && table.rowAtPoint(p) == 0) {
                    data = new Object[1][3];
                    data[0][0] = "2";
                    data[0][1] = "User2";
                    data[0][2] = "Delete";
                }else if(table.getModel().getRowCount() == 2 && table.rowAtPoint(p) == 1) {
                    data = new Object[1][3];
                    data[0][0] = "1";
                    data[0][1] = "User1";
                    data[0][2] = "Delete";
                }else {
                    data = new Object[0][];
                }
                table.setModel(new CustModel(data));
            }
        }
    }


    class AddListener extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent evnt) {
            if(table.getModel().getRowCount() == 2) {
                return;
            }
            Object[][] data = new Object[table.getModel().getRowCount() + 1][3];
            for(int i = 0; i <= table.getModel().getRowCount(); i++) {
                data[i][0] = i;
                data[i][1] = "User" + i;
                data[i][2] = "Delete";
            }
            table.setModel(new CustModel(data));
        }

    }

    class CustModel extends AbstractTableModel {
        private String[] columnNames = {"ID", "NAME", "DELETE"};
        private Object[][] data = null;

        public CustModel(Object[][] data) {
            this.data = data;
        }
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }
    }

    private void display() {
        JFrame f = new JFrame("SwapTableModel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        TestCustTableModel obj = new TestCustTableModel();
        obj.display();
    }
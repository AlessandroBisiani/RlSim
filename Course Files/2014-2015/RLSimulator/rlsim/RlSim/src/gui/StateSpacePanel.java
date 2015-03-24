/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author alessandrobisiani
 */
public class StateSpacePanel extends javax.swing.JPanel{

    private ArrayList<String> listOfStates = new ArrayList<String>();
    //static String stringListOfStates;
    private MainFrame parentCreator;
    
    /**
     * Creates new form NewJPanel
     */
    public StateSpacePanel(MainFrame parentFrame) {
        initComponents();
        parentCreator = parentFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        stateSpace = new javax.swing.JTextArea();
        createJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        stateSpace.setColumns(20);
        stateSpace.setRows(5);
        jScrollPane1.setViewportView(stateSpace);

        createJButton.setText("Create");
        createJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createJButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Enter the desired states below, separated by a space.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(createJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    //Harvest JTextArea input and enter each state name in an ArrayList<String> for use.
    //The states entered should be divided by a space.
    private void createJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createJButtonActionPerformed
        listOfStates.clear();
        String states = stateSpace.getText();
        String word = "";
        int lengthOfStates = states.length();
        //CharSequence sepChar = System.getProperty("line.separator");
        
        for(int i=0; i<lengthOfStates; i++){
            
            if(i==lengthOfStates-1 && states.charAt(i)==' '){
                listOfStates.add(word);
            } else if(i==lengthOfStates-1){
                word = word + states.charAt(i);
                listOfStates.add(word);
            } else if(states.charAt(i) ==' '){
                listOfStates.add(word);
                System.out.println(word);                //A test to check whether the loop cycles through each word
                word = "";
            } else {
                word = word + states.charAt(i);
            }
        }
        
        //MainFrame.createMatrices(listOfStates);

        //Tests of the state of the fields after harvesting the information from JTextArea
        System.out.println(states);
        System.out.println(states.length());
        System.out.println(listOfStates);
        
        //Create the matrices using static method and ArrayList<String> containing the state names. This method also calls dispose() on the instance of StateSpacePanel.
        parentCreator.createMatrices(listOfStates);
        
        /**
        Window f = SwingUtilities.windowForComponent(this);
        f.removeAll();
        
        JFrame t = null;
        Container temp = this;
        while(temp.getParent() != null){
            temp = temp.getParent();
            if (temp.getParent() instanceof JFrame){
                temp.getParent().removeAll();
            }
        }  */      
    }//GEN-LAST:event_createJButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea stateSpace;
    // End of variables declaration//GEN-END:variables

    
}

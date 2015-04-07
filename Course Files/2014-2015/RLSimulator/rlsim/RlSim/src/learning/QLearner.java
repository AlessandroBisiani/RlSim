/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author alessandrobisiani
 */
public class QLearner implements Learner{
    private Agent agent;
    public String currentState;
    
    private JTable qMatrix;
    private JTable rMatrix;
    //These are default table models because DefaultTableModel includes more methods for operating on the model and extracting table data
    private TableModel qModel;
    private TableModel rModel;
    
    private double gamma;
    private double alpha;
    
    public String[] stateSpace = {"state1","state2","state3","state4",
                                                 "state5","state6","state7","state8","state9"};
    
    
    public QLearner(JTable q, JTable r,Agent a){
        agent = a;
        qMatrix = q;
        rMatrix = r;
        
        currentState = stateSpace[0];
        alpha = 0.5;
        gamma = 1.0;
        
        qModel = new DefaultTableModel(new String[][] {{"state1","0","0","0","0","0","0","0","0","0"}, 
                                                            {"state2","0","0","0","0","0","0","0","0","0"}, 
                                                            {"state3","0","0","0","0","0","0","0","0","0"},
                                                            {"state4","0","0","0","0","0","0","0","0","0"},
                                                            {"state5","0","0","0","0","0","0","0","0","0"},
                                                            {"state6","0","0","0","0","0","0","0","0","0"},
                                                            {"state7","0","0","0","0","0","0","0","0","0"},
                                                            {"state8","0","0","0","0","0","0","0","0","0"},
                                                            {"state9","0","0","0","0","0","0","0","0","0"},
                                                            {"state10","0","0","0","0","0","0","0","0","0"}}, 
                                             new String[] {"","state1","state2","state3","state4",
                                                 "state5","state6","state7","state8","state9"});
        rModel = new DefaultTableModel(new String[][] {{"state1","","","","","","","","",""}, 
                                                            {"state2","","","","","","","","",""}, 
                                                            {"state3","","","","","","","","",""},
                                                            {"state4","","","","","","","","",""},
                                                            {"state5","","","","","","","","",""},
                                                            {"state6","","","","","","","","",""},
                                                            {"state7","","","","","","","","",""},
                                                            {"state8","","","","","","","","",""},
                                                            {"state9","","","","","","","","",""},
                                                            {"state10","","","","","","","","",""}}, 
                                             new String[] {"","state1","state2","state3","state4",
                                                 "state5","state6","state7","state8","state9"});
        q.setModel(qModel);
        r.setModel(rModel);
    }
    
    public void episode(){
        resetStartingPosition();
        getAvailableActions();
    }
    //sets startingPosition to a random position taken from stateSpace
    public void resetStartingPosition(){
        int i = (int)(Math.random()*100);
        currentState = stateSpace[i%stateSpace.length];
        System.out.println("starting position reset -" +currentState);
    }
    //finds the current state String in the rMatirx and returns an String[] containing the available next state names.
    public ArrayList<String> getAvailableActions(){
        ArrayList<String> available = new ArrayList<>();
        for(int i=0;i<rMatrix.getRowCount();i++){
            if(currentState.equals(rMatrix.getValueAt(i,0))){
                System.out.println("currentStateFound");
                for(int c=1;c<=rMatrix.getRowCount();c++){
                    if(!rMatrix.getValueAt(i,c).equals("")){
                        available.add(rMatrix.getColumnName(c));
                        System.out.println(rMatrix.getColumnName(c));
                    }
                }
                return available;
            }
        }
        //Warning: no possible actions encountered for state: + currentState
        return available;
    }
    
    public void setGamma(double g){
        gamma = g;
    }
    public void setAlpha(double a){
        alpha = a;
    }
    //Sets the pointers to QLearner's model fields to the parameters taken. Point of doing it manually is to maintain them as DefaultTableModels.
    public void setModels(DefaultTableModel q, DefaultTableModel r){
        qModel = q;
        rModel = r;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author alessandrobisiani
 */
public class QLearner implements Learner{
    private Agent agent;
    public String currentState;
    private Policy policy;
    
    private JTable qMatrix;
    private JTable rMatrix;
    //These are default table models because DefaultTableModel includes more methods for operating on the model and extracting table data
    private Matrix qModel;
    private Matrix rModel;
    
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
        gamma = 0.7;
        
        qModel = new Matrix(new String[][] {{"state1","0","0","0","0","0","0","0","0","0"}, 
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
        rModel = new Matrix(new String[][] {{"state1","","","","","","","","",""}, 
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
        /*
        qModel = new Matrix(new String[][] {{}}, new String[] {});
        rModel = new Matrix(new String[][] {{}}, new String[] {});
        */
        q.setModel(qModel);
        r.setModel(rModel);
    }
    
    public void episode(){
        if(rMatrix.getRowCount()<2){
            return;
        }
        policy = new EpsilonGreedy(this);
        
        resetStartingPosition();
        
        HashMap m = getAvailableActions();
        System.out.println("This is the size - " + m.size() + " " + m.keySet() + " " + m.values());
        
        String nextState = policy.next(m);
        
        System.out.println("state selected by qlearner: " + nextState);
        String r = (String) m.get(nextState);
        double reward = Double.parseDouble(r);
        
        double maxQ = getMaxQ(nextState);
        System.out.println("Max Q value found: "+maxQ);
        
        int nextStateIndex = qModel.findColumn(nextState);
        double currentQ = getCurrentQ(currentState, nextStateIndex);
        System.out.println("Current Q value found: "+currentQ);
        
        //double td = (reward*(gamma*maxQ))-currentQ;
        double newQ = currentQ + (alpha*((reward+(gamma*maxQ))-currentQ));
        System.out.println("New Q value found for "+currentState +": "+newQ);
        
        setQ(currentState,nextStateIndex,newQ);
        
    }
    //sets startingPosition to a random position taken from stateSpace
    public void resetStartingPosition(){
        int i = (int)(Math.random()*100);
        currentState = stateSpace[i%stateSpace.length];
        System.out.println("starting position reset -" +currentState);
    }
    
    //finds the current state String in the first rMatrix column and returns a HashMap(reward,statename) containing the rewards associated with names of available next states.
    public HashMap getAvailableActions(){
        
        HashMap available = new HashMap((rMatrix.getRowCount()),1);
        
        for(int i=0;i<rMatrix.getRowCount();i++){
            if(currentState.equals(rMatrix.getValueAt(i,0))){
                System.out.println("currentStateFound");
                for(int c=1;c<=rMatrix.getRowCount();c++){
                    if(!rMatrix.getValueAt(i,c).equals("")){
                        available.put(rMatrix.getColumnName(c) , rMatrix.getValueAt(i,c));
                        System.out.println(rMatrix.getColumnName(c));
                    }
                }
                return available;
            }
        }
        //Warning: no possible actions encountered for state: + currentState
        return available;
    }
    
    public double getMaxQ(String state){
        double max = 0;
        String maxS = "";
        
        for(int i=0;i<qMatrix.getRowCount();i++){
            if(state.equals(qMatrix.getValueAt(i,0))){
                
                maxS = (String)qMatrix.getValueAt(i, 1);
                max = Double.parseDouble(maxS);
                
                for(int c=2;c<=qMatrix.getRowCount();c++){
                    String maxS2 = (String)qMatrix.getValueAt(i, c);
                    double max2 = Double.parseDouble(maxS2);
                    if(max<max2){
                        max = max2;
                    }
                }
                return max;
            }
        }            
        return max;
    }
    
    public double getCurrentQ(String state, int nextStateIndex){
        int c = nextStateIndex;
        double q = 0;
        for(int i=0;i<qMatrix.getRowCount();i++){
            if(state.equals(qMatrix.getValueAt(i,0))){
                String qS = (String) qMatrix.getValueAt(i, c);
                q = Double.parseDouble(qS);
            }
        }
        return q;
    }
    
    public void setQ(String state, int nextStateIndex, double q){
        int c = nextStateIndex;
        for(int i=0;i<qMatrix.getRowCount();i++){
            if(state.equals(qMatrix.getValueAt(i,0))){
                qMatrix.setValueAt(q, i, c);
                System.out.println(qMatrix.getValueAt(i, i+1));
                
                qMatrix.repaint();
                return;
            }
        }
    }
    
    public void setGamma(double g){
        gamma = g;
    }
    public void setAlpha(double a){
        alpha = a;
    }
    //Sets the pointers to QLearner's model fields to the parameters taken. Point of doing it manually is to maintain them as DefaultTableModels.
    public void setModels(Matrix q, Matrix r){
        qModel = q;
        rModel = r;
    }
    
}

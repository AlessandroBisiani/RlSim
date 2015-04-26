/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import gui.MainFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/** 
 *
 * @author alessandrobisiani
 */
public class QLearner extends Learner {
    
    private Policy policy;
    //private ExperimentData data;
    private String currentState;
    private String goalState;
    private String initialState;
    
    private JTable qMatrix;
    private JTable rMatrix;
    
    private Matrix qModel;
    private Matrix rModel;
    
    private double gamma;
    private double alpha;
    
    private int stepsPerEpisode;
    
    //private int numEpisodes;
    
    //private MainFrame mainFrame;
    
    //public String[] stateSpace = {"s1", "s2"};
    
    public QLearner(JTable q, JTable r, int numEpisodes, MainFrame mFrame){
        super(numEpisodes,mFrame);
        //mainFrame = mFrame;
        //data = new ExperimentData(r.getRowCount(), numEpisodes);
        //this.numEpisodes = numEpisodes;
        qMatrix = q;
        rMatrix = r;
        
        //policy = new EpsilonGreedy(this,0.3);
        
        alpha = 0.5;
        gamma = 0.7;
        
        qModel = (Matrix) q.getModel();
        rModel = (Matrix) r.getModel();
    }
    
    
    
    
    @Override
    public void episode(){
        stepsPerEpisode = 0;
        currentState = initialState;
        while(!currentState.equals(goalState)){
            HashMap m = getAvailableActions();
            //System.out.println("This is the size - " + m.size() + " " + m.keySet() + " " + m.values());

            String nextState = policy.next(m);

            //System.out.println("state selected by qlearner: " + nextState);
            String r = (String) m.get(nextState);
            double reward = Double.parseDouble(r);

            double maxQ = getMaxQ(nextState);
            //System.out.println("Max Q value found: "+maxQ);

            int nextStateIndex = qModel.findColumn(nextState);
            double currentQ = getCurrentQ(currentState, nextStateIndex);
            //System.out.println("Current Q value found: "+currentQ);

            double td = (reward+(gamma*maxQ))-currentQ;
            double newQ = currentQ + (alpha*(td));
            
            //System.out.println("New Q value found for "+currentState +": "+newQ);

            setQ(currentState,nextStateIndex,newQ);
            
            currentState = nextState;
            
            stepsPerEpisode = stepsPerEpisode+1;
            
        }
        if(currentState.equals(goalState)){
            HashMap m = getAvailableActions();
            //System.out.println("This is the size - " + m.size() + " " + m.keySet() + " " + m.values());

            Policy p = new EpsilonGreedy(this,0);
            String nextState = p.next(m);

            //System.out.println("state selected by qlearner: " + nextState);
            String r = (String) m.get(nextState);
            double reward = Double.parseDouble(r);

            double maxQ = getMaxQ(nextState);
            //System.out.println("Max Q value found: "+maxQ);

            int nextStateIndex = qModel.findColumn(nextState);
            double currentQ = getCurrentQ(currentState, nextStateIndex);
           // System.out.println("Current Q value found: "+currentQ);

            double td = (reward+(gamma*maxQ))-currentQ;
            double newQ = currentQ + (alpha*(td));
            
            //System.out.println("New Q value found for "+currentState +": "+newQ);

            setQ(currentState,nextStateIndex,newQ);
            
            currentState = nextState;
            
            stepsPerEpisode = stepsPerEpisode+1;
        }
        qMatrix.repaint();
        //data.addSteps(stepsPerEpisode);
        System.out.println(stepsPerEpisode);
    }
    
    @Override
    public int getStepsPerEpisode(){
        return stepsPerEpisode;
    }
    
    //returns cumulative Q value per TableModel
    //Takes all Q values, finds the largest, and divides each by that value and multiplies by 100. Adds them together and returns that value as double.
    @Override
    public double calculateCumulativeQ(){
        double normalizedQ = 0;
        double[] qS;
        //get all non 0 Q values into ArrayList<> qValues
        int size = qMatrix.getRowCount();
        ArrayList<String> qValues = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int c=1;c<=size;c++){
                if(!qMatrix.getValueAt(i,c).equals("0")){
                    qValues.add((String) qMatrix.getValueAt(i,c));
                }
            }
        }
        
        qS = new double[qValues.size()];
        
        //Find max value
        double maxQ = Double.parseDouble(qValues.get(0));
        qS[0] = maxQ;
        for(int x=1;x<qValues.size();x++){
            double nextQ = Double.parseDouble(qValues.get(x));
            qS[x] = nextQ;
            if(maxQ<nextQ){
                maxQ=nextQ;
            }
        }
        //normalize and add to normalizedQs array
        for(int q=0;q<qS.length;q++){
            double normalized = ((qS[q])/maxQ)*100;
            normalizedQ = normalizedQ + normalized;
            //normalizedQs[q] = normalized;
            System.out.println("Normalized Q value: "+normalized);
        }
        return normalizedQ;
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
                
                //qMatrix.repaint();
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
    @Override
    public void setPolicy(Policy p){
        policy = p;
    }
    @Override
    public void setGoalState(String gs){
        goalState = gs;
    }
    @Override
    public void setInitialState(String is){
        initialState = is;
    }
    //Sets the pointers to QLearner's model fields to the parameters taken
    @Override
    public void setModels(Matrix q, Matrix r){
        qModel = q;
        rModel = r;
    }
    
    public String getGoalState(){
        return goalState;
    }
    @Override
    public double[][] getEpisodeData(){
        double[][] epData = new double[rMatrix.getRowCount()][rMatrix.getRowCount()];
        for(int row=0;row<epData.length;row++){
            for(int column=1;column<=epData.length;column++){
                epData[row][column-1] = qModel.getDoubleAt(row, column);
            }
        }
        return epData;
    } 

    @Override
    public void run() {
        try {
            experiment();
        } catch (InterruptedException ex) {
            Logger.getLogger(QLearner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

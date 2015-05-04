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
 * @author AlessandroBisiani
 * @version v1.0 - 1 May 2015
 */
public class SARSA extends Learner{
    
    private Policy policy;
    private String currentState;
    private String goalState;
    private String initialState;
    private JTable qMatrix;
    private JTable rMatrix;
    private Matrix qModel;
    private Matrix rModel;
    private double alpha;
    private double gamma;
    private int stepsPerEpisode;
    
    public SARSA(JTable q, JTable r, int numEpisodes, MainFrame mFrame){
        super(numEpisodes, mFrame);
        qMatrix = q;
        rMatrix = r;
        qModel = (Matrix) qMatrix.getModel();
        rModel = (Matrix) rMatrix.getModel();
    }
    
    

    @Override
    public void episode(int episodeNumber) {
        stepsPerEpisode = 0;
        currentState = initialState;
        HashMap m = getAvailableActions();
        String nextState = policy.next(m, episodeNumber);
        
        while(!currentState.equals(goalState)){
            nextState = step(nextState, episodeNumber);
            stepsPerEpisode++;// = stepsPerEpisode+1;
        }
        if(currentState.equals(goalState)){
            step(nextState,episodeNumber);
            stepsPerEpisode++;// = stepsPerEpisode+1;
        }
        qMatrix.repaint();
        System.out.println(stepsPerEpisode);
    }
    public String step(String s, int episodeNumber){
        //HashMap m = getAvailableActions();
        //System.out.println("This is the size - " + m.size() + " " + m.keySet() + " " + m.values());
        
        String stateS = null;
    //Pick next state
        String nextState = s;
    //Move there and save previous state
        stateS = currentState;
        currentState = nextState;
    //pick the next action
        HashMap m = getAvailableActions();
        System.out.println(m+" Available Actions");
        nextState = policy.next(m, episodeNumber);
        //System.out.println("state selected by qlearner: " + nextState);
    //Get reward for the transition from stateS to currentState
        //GETTING THE VALUE FOR CURRENTSTATE FROM M IS EQUIVALENT TO GETTING THE TRANSITION FROM CURRENTSTATE TO ITSELF.
        String r = (String) m.get(currentState);
        double reward = Double.parseDouble(r);
    //Get the Q to update
        int currentStateIndex = qModel.findColumn(currentState);
        double qOfTransitionS = getCurrentQ(stateS, currentStateIndex);
        //System.out.println("Current Q value found: "+currentQ);
    //Get the Q of the next action
        int nextStateIndex = qModel.findColumn(nextState);
        double qCurrentTransition = getCurrentQ(currentState, nextStateIndex);

        double td = (reward+(gamma*qCurrentTransition))-qOfTransitionS;
        double newQ = qOfTransitionS + (alpha*(td));

        //System.out.println("New Q value found for "+currentState +": "+newQ);

        setQ(stateS,currentStateIndex,newQ);
        
        return nextState;

        //currentState = nextState;
        
        /*//Pick next state
        String nextState = policy.next(m);
        //System.out.println("state selected by qlearner: " + nextState);
        String r = (String) m.get(nextState);
        double reward = Double.parseDouble(r);

        //double maxQ = getMaxQ(nextState);
        //System.out.println("Max Q value found: "+maxQ);

        int nextStateIndex = qModel.findColumn(nextState);
        double currentQ = getCurrentQ(currentState, nextStateIndex);
        //System.out.println("Current Q value found: "+currentQ);

        double td = (reward+(gamma*maxQ))-currentQ;
        double newQ = currentQ + (alpha*(td));

        //System.out.println("New Q value found for "+currentState +": "+newQ);

        setQ(currentState,nextStateIndex,newQ);

        currentState = nextState;*/
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
    
    public double getCurrentQ(String state, int followingStateIndex){
        int c = followingStateIndex;
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
    
    @Override
    public int getStepsPerEpisode() {
        return stepsPerEpisode;
    }

    @Override
    public double calculateCumulativeQ() {
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
    
    @Override
    public void setPolicy(Policy p) {
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
    /*@Override
    private void setModels(Matrix q, Matrix r){
        qModel = q;
        rModel = r;
    }*/
    
    public void setAlpha(double alpha){
        this.alpha = alpha;
    }
    public void setGamma(double gamma){
        this.gamma = gamma;
    }
    
    @Override
    public double[][] getAllEpisodeData() {
        double[][] epData = new double[rMatrix.getRowCount()][rMatrix.getRowCount()];
        for(int row=0;row<epData.length;row++){
            for(int column=1;column<=epData.length;column++){
                epData[row][column-1] = qModel.getDoubleAt(row, column);
            }
        }
        return epData;
    }
    
    @Override
    public double[] getQValues() {
        ArrayList<Double> data = new ArrayList<>();
        for(int row=0;row<rMatrix.getRowCount();row++){
            for(int column=1;column<=rMatrix.getRowCount();column++){
                double q = qModel.getDoubleAt(row, column);
                if(q!=0){
                    data.add(q);
                }
            }
        }
        double[] epData = new double[data.size()];//data.toArray(new Double[data.size()]);
        for(int i=0;i<data.size();i++){
            epData[i] = data.get(i);
        }
        return epData;
    }

    @Override
    public void run() {
        super.run();
    }

    
    
}

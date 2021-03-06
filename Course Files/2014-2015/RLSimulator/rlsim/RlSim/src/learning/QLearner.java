/*
 */
package learning;

import gui.MainFrame;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;

/** 
 * An implementation of the Q-learning algorithm for use by gui.MainFrame.
 * See extended class Learner for details of algorithm execution by mainFrame.
 * Public method episode(int episodeNumber), and private method step(int episodeNumber)
 * implement all behavior unique to q-learning.
 * QLearner.run() simply calls Learner.run(). Use this implementation by calling its run() method.
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public class QLearner extends Learner {
    
    private Policy policy;
    private String currentState;
    private String goalState;
    private String initialState;
    private JTable qMatrix;
    private JTable rMatrix;
    private Matrix qModel;
    private double gamma;
    private double alpha;
    private int stepsPerEpisode;
    
    /**
     * Creates a QLearner passing numEpisodes and mFrame to superclass 
     * constructor Learner(int numEpisodes, MainFrame mFrame) and initializing 
     * fields.
     * @param q             JTable containing the Q matrix.
     * @param r             JTable containing the Reward matrix.
     * @param numEpisodes   The number of episodes the experiment should run for.
     * @param mFrame        The instance of MainFrame from which the experiment is run.
     */
    public QLearner(JTable q, JTable r, int numEpisodes, MainFrame mFrame){
        super(numEpisodes,mFrame);
        qMatrix = q;
        rMatrix = r;
        qModel = (Matrix) q.getModel();
    }
    
    @Override
    public void episode(int episodeNumber){
        stepsPerEpisode = 0;
        currentState = initialState;
        while(!currentState.equals(goalState)){
            step(episodeNumber);
            stepsPerEpisode = stepsPerEpisode+1;
        }
        if(currentState.equals(goalState)){
            step(episodeNumber);
            stepsPerEpisode = stepsPerEpisode+1;
        }
        qMatrix.repaint();
        System.out.println(stepsPerEpisode);
    }
    
    private void step(int episodeNumber){
        HashMap m = getAvailableActions();
        //TEST
        //System.out.println("This is the size - " + m.size() + " " + m.keySet() + " " + m.values());

        String nextState = policy.next(m, episodeNumber);

        //TEST
        //System.out.println("state selected by qlearner: " + nextState);
        String r = (String) m.get(nextState);
        double reward = Double.parseDouble(r);

        double maxQ = getMaxQ(nextState);
        //TEST
        //System.out.println("Max Q value found: "+maxQ);

        int nextStateIndex = qModel.findColumn(nextState);
        double currentQ = getCurrentQ(currentState, nextStateIndex);
        //TEST
        //System.out.println("Current Q value found: "+currentQ);

        double td = (reward+(gamma*maxQ))-currentQ;
        double newQ = currentQ + (alpha*(td));

        //TEST
        //System.out.println("New Q value found for "+currentState +": "+newQ);

        setQ(currentState,nextStateIndex,newQ);

        currentState = nextState;
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
        
        //Find max value and populate qS with Strings parsed to doubles. 
        //maxQ is not the actual max at first; it is only initialized with the first element of qValues.
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
            //TEST            
            //System.out.println("Normalized Q value: "+normalized);
        }
        return normalizedQ;
    }
    
    
    /**
     * Searches for available state transitions and returns a map of transitions mapped to their rewards.
     * Available transitions are represented by the states to which they lead - these are the Keys.
     * @return      A HashMap of statesAvailableToTransitionTo(Key) , rewardForThatTransition(Value)
     */
    public HashMap getAvailableActions(){
        
        HashMap available = new HashMap((rMatrix.getRowCount()),1);
        
        for(int i=0;i<rMatrix.getRowCount();i++){
            if(currentState.equals(rMatrix.getValueAt(i,0))){
                //TEST
                //System.out.println("currentStateFound");
                for(int c=1;c<=rMatrix.getRowCount();c++){
                    if(!rMatrix.getValueAt(i,c).equals("")){
                        available.put(rMatrix.getColumnName(c) , rMatrix.getValueAt(i,c));
                        //TEST
                        //System.out.println(rMatrix.getColumnName(c));
                    }
                }
                return available;
            }
        }
        //Warning: no possible actions encountered for state: + currentState
        return available;
    }
    
    /**
     * Finds the highest Q value that can be reached from a state.
     * @param state     The state from which to search for the highest Q.
     * @return          The highest Q value.
     */
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
    
    /**
     * Finds the Q value for the specified transition.
     * @param state             A state from which to transition.
     * @param nextStateIndex    Column index of the next state.
     * @return                  Q value for the specified transition.
     */
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
    
    /**
     * Sets a Q value.
     * @param state             State the transition is from. The y-axis value.
     * @param nextStateIndex    Column index of the next state.
     * @param q                 New Q value.
     */
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
    
    /**
     * Sets the value of gamma.
     * @param gamma     New value of gamma.
     */
    public void setGamma(double gamma){
        this.gamma = gamma;
    }
    
    /**
     * Sets the value of alpha.
     * @param alpha     New value of alpha.
     */
    public void setAlpha(double alpha){
        this.alpha = alpha;
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
    
    /**
     * Gets the current goal state.
     * @return  The goal state name.
     */
    public String getGoalState(){
        return goalState;
    }
    @Override
    public double[][] getAllEpisodeData(){
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
        double[] epData = new double[data.size()];;//data.toArray(new Double[data.size()]);
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

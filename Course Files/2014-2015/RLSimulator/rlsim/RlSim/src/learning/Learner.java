/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import javax.swing.JTable;

/**
 *
 * @author alessandrobisiani
 */
public interface Learner {
    
    
    
    public void experiment(int episodes);
    
    //public abstract void episode();
    
    public double calculateCumulativeQ();
    
    public void setPolicy(Policy p);
    
    public void setGoalState(String gs);
    
    public void setInitialState(String is);
    //Sets the pointers to QLearner's model fields to the parameters taken
    public void setModels(Matrix q, Matrix r);
    
    public ExperimentData getExperimentData();
}

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
public abstract class Learner implements Runnable{
    
    public Learner(){
        
    }
    
    public abstract void experiment() throws InterruptedException;
    
    //public abstract void episode();
    
    public abstract double calculateCumulativeQ();
    
    public abstract void setPolicy(Policy p);
    
    public abstract void setGoalState(String gs);
    
    public abstract void setInitialState(String is);
    //Sets the pointers to QLearner's model fields to the parameters taken
    public abstract void setModels(Matrix q, Matrix r);
    
    public abstract ExperimentData getExperimentData();
    
    @Override
    public void run(){
        
    };
}

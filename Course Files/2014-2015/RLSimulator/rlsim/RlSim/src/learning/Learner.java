/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import gui.MainFrame;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public abstract class Learner implements Runnable{
   
    
    private MainFrame mainFrame;
    private int numOfEpisodes;
    
    public Learner(int numEpisodes, MainFrame mFrame){
        mainFrame = mFrame;
        numOfEpisodes = numEpisodes;
    }
    
    /**
     * Performs the experiment and saves the data output. Relies on episode().
     * @throws InterruptedException 
     */
    public  void experiment() throws InterruptedException{
        for(int i=0;i<numOfEpisodes;i++){
            episode(mainFrame.data.getStepsXEpisode().size()+1);
            mainFrame.data.addSteps(getStepsPerEpisode());
            mainFrame.data.addEpisode(getAllEpisodeData());
            mainFrame.data.addQValues(getQValues());
            mainFrame.data.addReward(calculateCumulativeQ());
            Thread.sleep(1);
        }
        mainFrame.data.printSteps();
        mainFrame.data.printCumulativeRewards();
        System.out.println(mainFrame.data.getAllData().size());
        
        double t = 1 - (Math.max((double)mainFrame.data.getTemperatureRate()*numOfEpisodes,0.0));
        
        System.out.println(t +" FINAL TEMPERATURE");//1-(Math.max(mainFrame.data.getTemperatureRate()*numOfEpisodes,0.0)) + " THE TEMPERATURE");
        System.out.println(mainFrame.data.getTemperatureRate());
        System.out.println(numOfEpisodes);
        mainFrame.setRunningJLabel("Completed");
    };
    
    /**
     * Performs a single episode.
     * @param episodeNumber     The number of episodes completed in the experiment +1.
     */
    public abstract void episode(int episodeNumber);
    
    /**
     * Get the number of steps taken to complete the current episode.
     * @return  Number of steps taken to completion of the current episode.
     */
    public abstract int getStepsPerEpisode();
    
    /**
     * Calculates weighted cumulative Q value for the current episode.
     * All updated Q values are divided my the highest Q value and multiplied by 100.
     * @return  Weighted cumulative Q value for the current episode.
     */
    public abstract double calculateCumulativeQ();
    
    /**
     * Set the policy object with which the learner will function.
     * @param p     The new policy to associate with an instance of learner.
     */
    public abstract void setPolicy(Policy p);
    
    /**
     * Set the goal state for this experiment.
     * @param gs    The name of the goal state.
     */
    public abstract void setGoalState(String gs);
    
    /**
     * Set the initial state for this experiment.
     * @param is    The name of the initial state.
     */
    public abstract void setInitialState(String is);
    
    /**
     * Get all Q matrix data for the current episode.
     * @return  The Q matrix data.
     */
    public abstract double[][] getAllEpisodeData();
    
    /**
     * Get all updated Q values for the current episode.
     * @return  A collection of updated q values.
     */
    public abstract double[] getQValues();
    
   
    /**
     * Calls Learner.experiment().
     * Overriding this method should always at the very least either call this method with super.run(), or simply call experiment().
     */
    @Override
    public void run(){
        try {
            experiment();
        } catch (InterruptedException ex) {
            Logger.getLogger(QLearner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

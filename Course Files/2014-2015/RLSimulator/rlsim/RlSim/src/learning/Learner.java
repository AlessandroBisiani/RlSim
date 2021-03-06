/*
 */
package learning;

import gui.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Learner is intended for extension when implementing learning algorithms to be used with gui.MainFrame.
 * The Learner abstract class implements the Runnable interface so that Learning algorithms can be interrupted 
 * separately from the rest of the application. Implementing episode(int episodeNumber), setPolicy(Policy p),
 * setGoalState(String gs), setInitialState(String is), getQValues(), and run() is sufficient to satisfy a call to 
 * experiment() from MainFrame.
 * Note: Extending classes are required to override Run(). The implementation should always either call super() at 
 * the end or in case special behavior is necessary, execute experiment().
 * Learner guarantees that implementing classes will call Thread.sleep(1) after each episode, giving the thread 
 * a chance to throw an InterruptedException if the interrupted flag is set. Experiments can be interrupted this way.
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public abstract class Learner implements Runnable{
   
    
    private MainFrame mainFrame;
    private int numOfEpisodes;
    
    /**
     * Sets internal fields to the parameters numEpisodes and mFrame.
     * @param numEpisodes   The number of episodes the experiment should run for.
     * @param mFrame        The MainFrame from which the experiment is run.
     */
    public Learner(int numEpisodes, MainFrame mFrame){
        mainFrame = mFrame;
        numOfEpisodes = numEpisodes;
    }
    
    /**
     * Performs the experiment and saves the data output.
     * Relies on episode().
     * @throws InterruptedException After each episode execution Thread.sleep() provides the opportunity to throw an interrupted exception if the interrupted flag was set.
     */
    public  void experiment() throws InterruptedException{
        for(int i=0;i<numOfEpisodes;i++){
            episode(mainFrame.data.getStepsXEpisode().size()+1);
            mainFrame.data.addSteps(getStepsPerEpisode());
            mainFrame.data.addEpisode(getAllEpisodeData());
            mainFrame.data.addQValues(getQValues());
            mainFrame.data.addReward(calculateCumulativeQ());
            System.out.println(mainFrame.data.getStepsXEpisode().size()+1);
            Thread.sleep(1);
        }
        mainFrame.data.printSteps();
        mainFrame.data.printCumulativeRewards();
        
        //TEST
        //System.out.println(mainFrame.data.getAllData().size());
        //double t = 1 - (Math.max((double)mainFrame.data.getTemperatureRate()*numOfEpisodes,0.0));
        //System.out.println(t +" FINAL TEMPERATURE");//1-(Math.max(mainFrame.data.getTemperatureRate()*numOfEpisodes,0.0)) + " THE TEMPERATURE");
        //System.out.println(mainFrame.data.getTemperatureRate());
        System.out.println(numOfEpisodes);
        mainFrame.setRunningJLabel("Completed");
    };
    
    /**
     * Performs a single episode.
     * All algorithm Q updates and episode termination code should be included here.
     * @param episodeNumber     The number of episodes completed in the experiment +1.
     */
    public abstract void episode(int episodeNumber);
    
    /**
     * Gets the number of steps taken to complete the current episode.
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
     * Sets the policy object with which the learner will function.
     * @param p     The new policy to associate with an instance of learner.
     */
    public abstract void setPolicy(Policy p);
    
    /**
     * Sets the goal state for this experiment.
     * @param gs    The name of the goal state.
     */
    public abstract void setGoalState(String gs);
    
    /**
     * Sets the initial state for this experiment.
     * @param is    The name of the initial state.
     */
    public abstract void setInitialState(String is);
    
    /**
     * Gets all Q matrix data for the current episode.
     * @return  The Q matrix data.
     */
    public abstract double[][] getAllEpisodeData();
    
    /**
     * Gets all updated Q values for the current episode.
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

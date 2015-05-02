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
 * @author alessandrobisiani
 */

   
public abstract class Learner implements Runnable{
   
    
    private MainFrame mainFrame;
    private int numOfEpisodes;
    
    public Learner(int numEpisodes, MainFrame mFrame){
        mainFrame = mFrame;
        numOfEpisodes = numEpisodes;
    }
    
    
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
        //temperature = 1-(Math.max((double)temperatureDecreaseRate*episodeNum,0.0));
        
        double t = 1 - (Math.max((double)mainFrame.data.getTemperatureRate()*numOfEpisodes,0.0));
        
        System.out.println(t +" FINAL TEMPERATURE");//1-(Math.max(mainFrame.data.getTemperatureRate()*numOfEpisodes,0.0)) + " THE TEMPERATURE");
        System.out.println(mainFrame.data.getTemperatureRate());
        System.out.println(numOfEpisodes);
        mainFrame.setRunningJLabel("Completed");
    };
    
    public abstract void episode(int episodeNumber);
    
    public abstract int getStepsPerEpisode();
    
    public abstract double calculateCumulativeQ();
    
    public abstract void setPolicy(Policy p);
    
    public abstract void setGoalState(String gs);
    
    public abstract void setInitialState(String is);
    //Sets the pointers to QLearner's model fields to the parameters taken
    public abstract void setModels(Matrix q, Matrix r);
    
    
    public abstract double[][] getAllEpisodeData();
    
    public abstract double[] getQValues();
    
   
    @Override
    public void run(){
        try {
            experiment();
        } catch (InterruptedException ex) {
            Logger.getLogger(QLearner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

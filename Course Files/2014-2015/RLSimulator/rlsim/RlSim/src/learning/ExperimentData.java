/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alessandrobisiani
 */
public class ExperimentData implements Serializable{
    
    //size is the number of episodes, it relates the number of the episode with the number of steps it took.
    private ArrayList<Integer> stepsXEpisode;
    
    //size is number of ep. Relates the episode w the normalized cumulative reward.
    private ArrayList<Double> ratioOfCumulativeRewards;
    
    public ExperimentData(){
        stepsXEpisode = new ArrayList<>();
        ratioOfCumulativeRewards = new ArrayList<>();
    }
    
    public void addSteps(int steps){
        stepsXEpisode.add(steps);
    }
    public void addReward(double reward){
        ratioOfCumulativeRewards.add(reward);
    }
    
    
    public void resetData(){
        ratioOfCumulativeRewards.removeAll(ratioOfCumulativeRewards);
        stepsXEpisode.removeAll(stepsXEpisode);
    }
    
    
    public void printSteps(){
        int s = stepsXEpisode.size();
        Integer[] steps = new Integer[s];
        stepsXEpisode.toArray(steps);
        for(int i=0;i<s;i++){
            System.out.println(steps[i]);
        }
        System.out.println("# Steps: "+s);
    }
    public void printCumulativeRewards(){
        int s = ratioOfCumulativeRewards.size();
        Double[] rewards = new Double[s];
        ratioOfCumulativeRewards.toArray(rewards);
        for(int i=0;i<s;i++){
            System.out.println(rewards[i]);
        }
        System.out.println("# Cumulative rewards: "+s);
    }
    
}

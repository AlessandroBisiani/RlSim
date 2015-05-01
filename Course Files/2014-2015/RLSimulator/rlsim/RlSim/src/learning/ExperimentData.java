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
    
    //all q matrix data for every episode. Should probably not include the zeros.
    private ArrayList<double[][]> allData;
    
    private ArrayList<double[]> qValuesPerEpisode;
    
    private String policy;
    private double epsilon;
    private double gamma;
    private double alpha;
    private String goalState;
    private String initialState;
    private double temperatureRate;
    private String algorithm;
    
    public ExperimentData(int numStates, int numEpisodes){
        stepsXEpisode = new ArrayList<>();
        ratioOfCumulativeRewards = new ArrayList<>();
        allData = new ArrayList<>();
        qValuesPerEpisode = new ArrayList<>();
        gamma = 0.0;
        alpha = 0.0;
        temperatureRate = 0.0;
    }
    
    public void addSteps(int steps){
        stepsXEpisode.add(steps);
    }
    public void addReward(double reward){
        ratioOfCumulativeRewards.add(reward);
    }
    public void addEpisode(double[][] episode){
        allData.add(episode);
    }
    public void addQValues(double[] episodeQs){
        qValuesPerEpisode.add(episodeQs);
    }
    
    public ArrayList<Integer> getStepsXEpisode(){
        return stepsXEpisode;
    }
    public ArrayList<Double> getRatioOfCumulativeRewards(){
        return ratioOfCumulativeRewards;
    }
    public ArrayList<double[][]> getAllData(){
        return allData;
    }
    public int getAllDataArraySize(){
        return allData.size();
    }
    public ArrayList<double[]> getQValuesPerEpisode(){
        return qValuesPerEpisode;
    }
    
    
    public void resetData(){
        ratioOfCumulativeRewards.removeAll(ratioOfCumulativeRewards);
        stepsXEpisode.removeAll(stepsXEpisode);
        allData = new ArrayList<>();
        qValuesPerEpisode = new ArrayList<>();
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
    
    public void setPolicy(String policy){
        this.policy = policy;
    }
    public void setEpsilon(double epsilon){
        this.epsilon = epsilon;
    }
    public void setGamma(double gamma){
        this.gamma = gamma;
    }
    public void setAlpha(double alpha){
        this.alpha = alpha;
    }
    public void setGoalState(String gState){
        goalState = gState;
    }
    public void setInitialState(String iState){
        initialState = iState;
    }
    public void setTemperatureRate(double temp){
        temperatureRate = temp;
    }
    public void setAlgorithm(String algorithm){
        this.algorithm = algorithm;
    }
    
    public String getPolicy(){
        return policy;
    }
    public double getEpsilon(){
        return epsilon;
    }
    public double getGamma(){
        return gamma;
    }
    public double getAlpha(){
        return alpha;
    }
    public String getGoalState(){
        return goalState;
    }
    public String getInitialState(){
        return initialState;
    }
    public double getTemperatureRate(){
        return temperatureRate;
    }
    public String getAlgorithm(){
        return algorithm;
    }
}

/*
 */
package learning;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An structure for experiment data collection.
 * It collects experiment raw data, Q matrix states per episode, and experiment 
 * metrics steps per episode, and cumulative weighted non-zero Q values per episode.
 * It is intended to preserve collected data across experiment executions. resetData() is provided for
 * resetting the accumulated data. Note: this does not affect experiment parameters.
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public class ExperimentData implements Serializable{
    
    //size is the number of episodes, it relates the number of the episode with the number of steps it took.
    private ArrayList<Integer> stepsXEpisode;
    //size is number of ep. Relates the episode w the normalized cumulative reward.
    private ArrayList<Double> ratioOfCumulativeRewards;
    //all q matrix data for every episode.
    private ArrayList<double[][]> allData;
    //all q values updated during that experiment.
    private ArrayList<double[]> qValuesPerEpisode;
    
    private String policy;
    private double epsilon;
    private double gamma;
    private double alpha;
    private String goalState;
    private String initialState;
    private double initialTemp;
    private double temperatureRate;
    private String algorithm;
    
    /**
     * Constructs an ExperimentData object and initializes all experiment parameters
     * to 0 or empty String.
     */
    public ExperimentData(){
        stepsXEpisode = new ArrayList<>();
        ratioOfCumulativeRewards = new ArrayList<>();
        allData = new ArrayList<>();
        qValuesPerEpisode = new ArrayList<>();
        policy = "";
        epsilon = 0.0;
        gamma = 0.0;
        alpha = 0.0;
        goalState = "";
        initialState = "";
        initialTemp = 0.0;
        temperatureRate = 0.0;
        algorithm = "";
    }
    /**
     * Adds the number of steps executed in an episode to collected experiment data.
     * @param steps     Number of steps to reach the goal state that episode.
     */
    public void addSteps(int steps){
        stepsXEpisode.add(steps);
    }
    /**
     * Adds the cumulative weighted Q values in an episode to collected experiment data.
     * @param reward    The cumulative weighted Q values that episode.
     */
    public void addReward(double reward){
        ratioOfCumulativeRewards.add(reward);
    }
    /**
     * Adds the Q matrix at the end of an episode in its entirety to collected experiment data.
     * @param episode   All Q matrix entries that episode.
     */
    public void addEpisode(double[][] episode){
        allData.add(episode);
    }
    /**
     * Adds the updated Q values at the end of an episode to collected experiment data.
     * @param episodeQs     All updated Q values that episode.
     */
    public void addQValues(double[] episodeQs){
        qValuesPerEpisode.add(episodeQs);
    }
    /**
     * Resets the accumulated experiment data.
     */
    public void resetData(){
        ratioOfCumulativeRewards.clear();
        stepsXEpisode.clear();
        allData.clear();
        qValuesPerEpisode.clear();
    }
    
    /**
     * Prints the number of steps per episode and number of data points collected to the current output.
     */
    public void printSteps(){
        int s = stepsXEpisode.size();
        Integer[] steps = new Integer[s];
        stepsXEpisode.toArray(steps);
        for(int i=0;i<s;i++){
            System.out.println(steps[i]);
        }
        System.out.println("# Steps Data Points: "+s);
    }
    /**
     * Prints the collected cumulative rewards and number of cumulative rewards collected to the current output.
     */
    public void printCumulativeRewards(){
        int s = ratioOfCumulativeRewards.size();
        Double[] rewards = new Double[s];
        ratioOfCumulativeRewards.toArray(rewards);
        for(int i=0;i<s;i++){
            System.out.println(rewards[i]);
        }
        System.out.println("# Cumulative rewards: "+s);
    }
    
    /**
     * Gets the data collected on the number of steps taken per episode throughout the latest experiment.
     * @return      A list of steps taken per episode with index 0 corresponding to the first episode.
     */
    public ArrayList<Integer> getStepsXEpisode(){
        return stepsXEpisode;
    }
    /**
     * Gets the data collected on amount of expected return (cumulative Q) per episode throughout the latest experiment.
     * @return      A list of cumulative weighted Q values per episode with index 0 corresponding to the first episode.
     */
    public ArrayList<Double> getRatioOfCumulativeRewards(){
        return ratioOfCumulativeRewards;
    }
    /**
     * Gets the Q matrices collected at episode termination throughout the latest experiment.
     * @return      A list containing all Q matrices at the end of each episode, with index 0 corresponding to the first episode.
     */
    public ArrayList<double[][]> getAllData(){
        return allData;
    }
    /**
     * Gets the updated Q values per episode throughout the latest experiment.
     * @return      A list containing updated Q values at the end of each episode, with index 0 corresponding to the first episode.
     */
    public ArrayList<double[]> getQValuesPerEpisode(){
        return qValuesPerEpisode;
    }
    /**
     * Gets the policy name associated with the latest experiment.
     * @return  The name of the experiment policy.
     */
    public String getPolicy(){
        return policy;
    }
    /**
     * Gets the epsilon value associated with the experiment data.
     * @return  The latest epsilon value associated with the experiment data.
     */
    public double getEpsilon(){
        return epsilon;
    }
    /**
     * Gets the gamma value associated with the experiment data.
     * @return  The latest gamma value associated with the experiment data.
     */
    public double getGamma(){
        return gamma;
    }
    /**
     * Gets the alpha value associated with the experiment data.
     * @return  The latest alpha value associated with the experiment data.
     */
    public double getAlpha(){
        return alpha;
    }
    /**
     * Gets the coal state associated with the experiment data.
     * @return  The goal state associated with the experiment data.
     */
    public String getGoalState(){
        return goalState;
    }
    /**
     * Gets the initial state associated with the experiment data.
     * @return  The initialState associated with the experiment data.
     */
    public String getInitialState(){
        return initialState;
    }
    /**
     * Gets initial temperature associated with the experiment data.
     * @return  The initial temperature.
     */
    public double getInitialTemperature(){
        return initialTemp;
    }
    /**
     * Gets the temperature decrease per episode value associated with the experiment data.
     * @return  The latest temperature decrease per episode value associated with the experiment data.
     */
    public double getTemperatureRate(){
        return temperatureRate;
    }
    /**
     * Gets the learning algorithm name associated with the experiment data.
     * @return  The latest learning algorithm name associated with the experiment data.
     */
    public String getAlgorithm(){
        return algorithm;
    }
    
    /**
     * Sets the policy name associated with the experiment data.
     * @param policy    The policy name.
     */
    public void setPolicy(String policy){
        this.policy = policy;
    }
    /**
     * Sets the epsilon value associated with the experiment data.
     * @param epsilon   The new value for epsilon.
     */
    public void setEpsilon(double epsilon){
        this.epsilon = epsilon;
    }
    /**
     * Sets the gamma value associated with the experiment data.
     * @param gamma   The new value for gamma.
     */
    public void setGamma(double gamma){
        this.gamma = gamma;
    }
    /**
     * Sets the alpha value associated with the experiment data.
     * @param alpha   The new value for alpha.
     */
    public void setAlpha(double alpha){
        this.alpha = alpha;
    }
    /**
     * Sets the goal state associated with the experiment data.
     * @param gState   The new name for experiment goal state.
     */
    public void setGoalState(String gState){
        goalState = gState;
    }
    /**
     * Sets the initial state name associated with the experiment data.
     * @param iState   The new name for experiment initial state.
     */
    public void setInitialState(String iState){
        initialState = iState;
    }
    public void setInitialTemperature(double initialTemperature){
        initialTemp = initialTemperature;
    }
    /**
     * Sets the temperature decrease per episode value associated with the experiment data.
     * @param temp   The new value for temperature decrease per episode.
     */
    public void setTemperatureRate(double temp){
        temperatureRate = temp;
    }
    /**
     * Sets the learning algorithm name associated with the experiment data.
     * @param algorithm   The new algorithm name.
     */
    public void setAlgorithm(String algorithm){
        this.algorithm = algorithm;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public class Softmax implements Policy{

    private double temperature;
    private double temperatureDecreaseRate;
    
    
    public Softmax(double temperatureRate){
        temperature = 0;
       
        this.temperatureDecreaseRate = (temperatureRate);
    }
    
    /**
     * Selects the next action based on available action-reward pairs and how many episodes have already completed.
     * With HashMap of available next states and the rewards for those transitions in String form, decides whether to choose randomly or greedily and returns String representing next state.
     * @param stateRewardMap    A HashMap of available next states mapped to their rewards.
     * @param episodeNumber     The number of episodes for which the experiment has run +1.
     * @return                  The selected action in the form of a String representing the state it leads to.
     */
    @Override
    public String next(HashMap stateRewardMap, int episodeNumber) {
        String nextState = null;
        String[] keySet = new String[stateRewardMap.size()];
        stateRewardMap.keySet().toArray(keySet);
        if(keySet.length==0){
            return nextState; //if there are no available actions return null, throwing a NullPointerException.
        } else if(keySet.length==1){
            nextState = keySet[0];
        }
        //TEST
        //System.out.println(keySet.length + " NUMBER OF KEYS");
        double[] prob = new double[keySet.length];
        double denominator = 0.0;
        
        //for every element in the keySet, in the order of keySet, take the corresponding q value from the stateRewardMap, put it through
        //the exponent function, divide that by the temperature, and put it in the prob array which will contain the probabilities for each action. 
        //At every step the denominator is itself added to the next element of prob[].
        //The probabilities array is then iterated over once the denominator is found to divide each numerator (in prob[]) by the denominator.
        //prob[] now contains the probabilities for each action in the order of keySet[].
        for (int i=0;i<keySet.length;i++) {
            String v = (String) stateRewardMap.get(keySet[i]);
            double value = Double.parseDouble(v);
            
            //Vary temperature
            temperature = 1-((double)temperatureDecreaseRate*episodeNumber);
            
            if(temperature==0.0)temperature = 0.000000001;
            prob[i] = (Math.exp(value))/temperature;
            denominator += prob[i];
        } 
        //System.out.println(temperature + " Find Temp");
        //System.out.println(denominator + " DENOMINATOR");
        
        for(int i=0;i<keySet.length;i++) {
            prob[i] /= denominator;
            //System.out.println(prob[i] + " prob " + i);
        }
        //pick an action based on the prob just calculated.
        double rand = Math.random();
        double lowerBound = 0.0;
        for(int i=0;i<prob.length;i++){
            //if the random number is between the bounds of the previous prob max value and the current (i) max value, select that state.
            if(lowerBound<rand && rand<(lowerBound+prob[i])){
                nextState = keySet[i];
            }
            //The prob are added as the loop executes so they are distributed b/w 0 and 1.
            //e.g. if three actions carry prob 0.333, rand between 0-0.333 will select action 1, 
            //between 0.333-0.666 selects action 2, between 0.666-0.999 selects action 3
            lowerBound += prob[i];
        }
        return nextState;
    }
    public void setTemperature(double temperature){
        this.temperature = temperature;
    }
}

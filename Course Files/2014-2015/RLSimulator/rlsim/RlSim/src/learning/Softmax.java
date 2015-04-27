/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.HashMap;

/**
 *
 * @author alessandrobisiani
 */
public class Softmax implements Policy{

    private double temperature;
    
    public Softmax(double temperature){
        this.temperature = (temperature);
    }
    
    @Override
    public String next(HashMap stateRewardMap) {
        String nextState = null;
        String[] keySet = new String[stateRewardMap.size()];
        stateRewardMap.keySet().toArray(keySet);
        if(keySet.length==0){
            return nextState; //if there are no available actions return null, throwing a NullPointerException.
        } else if(keySet.length==1){
            nextState = keySet[0];
        }
        System.out.println(keySet.length + " NUMBER OF KEYS");
        //Adapted from RLearner.java (on Desktop)------------------------------
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
            
            prob[i] = Math.exp(value);
            //prob[i] = (Math.exp(value))/temperature;
            denominator += prob[i];
        }
        //System.out.println(denominator + " DENOMINATOR");
        
        for(int i=0;i<keySet.length;i++) {
            prob[i] /= denominator;
            //System.out.println(prob[i] + " prob " + i);
        }
        //pick an action based on the prob just calculated.
        double rand = Math.random();
        double tempMaxProb = 0.0;
        double tempProb = 0.0;
        for(int i=0;i<prob.length;i++){
            //if the random number is between the bounds of the previous prob max value and the current (i) max value, select that state.
            if(tempProb<rand && rand<(tempProb+prob[i])){
                nextState = keySet[i];
            }
            //The prob are added as the loop executes so they are distributed b/w 0 and 1.
            //e.g. if three actions carry prob 0.333, rand between 0-0.333 will select action 1, 
            //between 0.333-0.666 selects action 2, between 0.666-0.999 selects action 3
            tempProb += prob[i];
        }
        return nextState;
    }
    public void setTemperature(double temperature){
        this.temperature = temperature;
    }
}

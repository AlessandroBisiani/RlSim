/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public class EpsilonGreedy implements Policy, Serializable{

    private double epsilon;
    
    public EpsilonGreedy(double epsilon){
        this.epsilon = epsilon;
    }
    
    /**
     * Sets the value of epsilon.
     * @param epsilon   A constant in the range 0-1. A measure of randomness of choice where 1 = always random.
     */
    public void setEpsilon(double epsilon){
        this.epsilon = epsilon;
    }
           
    
    /**
     * Selects the next action based on available action-reward pairs and epsilon.
     * With HashMap of available next states and the rewards for those transitions in String form, decides whether to choose randomly or greedily and returns String representing next state.
     * @param stateRewardMap    A HashMap of available next states mapped to their rewards.
     * @param episodeNumber     The number of episodes for which the experiment has run +1. Not used here.
     * @return                  The selected action in the form of a String representing the state it leads to.
     */
    @Override
    public String next(HashMap stateRewardMap, int episodeNumber) {
        String nextState = null;
        String[] set = new String[stateRewardMap.size()];
        stateRewardMap.keySet().toArray(set);
        
        //If there is more than one element in set take random value to compare against.
        if(!stateRewardMap.isEmpty() && stateRewardMap.size()>1){
            int rand = (int)(Math.random()*100);
            //pick greedily or in the case that multiple keys have the same reward equal to the max reward, pick randomly between them.
            if(Math.random()>epsilon){
                
                String val = (String) stateRewardMap.get(set[0]);
                double maxReward = Double.parseDouble(val);
                
                ArrayList<String> equivalentKeys = new ArrayList<>();
                equivalentKeys.add(set[0]);
                
                for(int i=1;i<set.length;i++){
                    String v = (String) stateRewardMap.get(set[i]);
                    double tempR = Double.parseDouble(v);
                    
                    if(tempR > maxReward){
                        maxReward = tempR;
                        equivalentKeys = new ArrayList<>();
                        equivalentKeys.add(set[i]);
                    } else if(tempR==maxReward){
                        equivalentKeys.add(set[i]);
                    }
                }
                //if there are multiple keys with the reward maxReward, pick randomly between them.
                if(equivalentKeys.size() > 1){
                    int randIndex = rand%equivalentKeys.size();
                    nextState = equivalentKeys.get(randIndex);
                } else if(equivalentKeys.size()==1){
                    nextState = equivalentKeys.get(0);
                }
                //TEST
                //System.out.println("Greedy action selected" + maxReward);
                return nextState;
            //Else pick randomly
            } else{
                nextState = set[rand%set.length];
            }
            //returns null, throwing NullPointException down the line.
            return nextState;
        //if there is only one action    
        } else if(stateRewardMap.size()==1){
                nextState = set[0];
                return nextState;
        //returns null throwing NullPointerException down the line.
        } else{
            return nextState;
        }
    }
        
    
    
    
}

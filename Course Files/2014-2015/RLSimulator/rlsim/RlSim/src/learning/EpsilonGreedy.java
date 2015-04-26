/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alessandrobisiani
 */
public class EpsilonGreedy implements Policy{

    private double epsilon;
    private Learner learner;
    
    public EpsilonGreedy(QLearner learner){
        epsilon = 0.5;
        this.learner = learner;
    }
    public EpsilonGreedy(Learner learner, double epsilon){
        this.epsilon = epsilon;
        this.learner = learner;
    }
    
    public void setEpsilon(double epsilon){
        this.epsilon = epsilon;
    }
           
    //With HashMap of available actions and their rewards in String form, decides whether to choose randomly or greedily and returns name of next action.
    @Override
    public String next(HashMap m) {
        String nextState = null;
        String[] set = new String[m.size()];
        m.keySet().toArray(set);
        
        //If there is more than one element in set take random value to compare against.
        if(!m.isEmpty() && m.size()>1){
            int rand = (int)(Math.random()*100);
            //pick greedily or in the case that multiple keys have the same reward equal to the max reward, pick randomly between them.
            if(Math.random()>epsilon){
                
                String val = (String) m.get(set[0]);
                double maxReward = Double.parseDouble(val);
                
                ArrayList<String> equivalentKeys = new ArrayList<>();
                equivalentKeys.add(set[0]);
                
                for(int i=1;i<set.length;i++){
                    String v = (String) m.get(set[i]);
                    double tempR = Double.parseDouble(v);
                    
                    if(tempR > maxReward){
                        maxReward = tempR;
                        //nextState = set[i];
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
                System.out.println("Greedy action selected" + maxReward);
                return nextState;
            //Else pick randomly
            } else{
                nextState = set[rand%set.length];
            }
            //returns null, throwing NullPointException down the line.
            return nextState;
        //if there is only one action    
        } else if(m.size()==1){
                nextState = set[0];
                return nextState;
        //returns null throing NullPointerException down the line.
        } else{
            return nextState;
        }
    }
        
    
    
    
}

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
public class DiscountedEGreedy implements Policy{

    private Learner learner;
    private double epsilon;
    
    public DiscountedEGreedy(QLearner l, double epsilon){
        learner = l;
        this.epsilon = epsilon;
    }
    
    public String next(HashMap m, int episodeNum) {
        String nextState = null;
        String[] set = new String[m.size()];
        m.keySet().toArray(set);
        
        if(!m.isEmpty() && m.size()>1){
            
            if(Math.random()>epsilon){
                //If there is more than one element in set take the first value to compare against.
                String val = (String) m.get(set[0]);
                double reward = Double.parseDouble(val);;
                nextState = set[0];
                
                for(int i=1;i<set.length;i++){
                        String v = (String) m.get(set[i]);
                        if(Double.parseDouble(v) > reward){
                            reward = Double.parseDouble(v);
                            nextState = set[i];
                        }
                }
                System.out.println("Greedy action selected" + reward);
                return nextState;
            } else{
                int i = (int)(Math.random()*100);
                nextState = set[i%set.length];
                //nextState = qLearner.stateSpace[i%qLearner.stateSpace.length];
            }
            return nextState;
            
        } else if(m.size()==1){
                nextState = set[0];
                return nextState;
        } else{
            return nextState;
        }
    }

    
    
}

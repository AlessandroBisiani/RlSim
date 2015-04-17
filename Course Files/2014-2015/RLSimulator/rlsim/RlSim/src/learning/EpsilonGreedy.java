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
public class EpsilonGreedy implements Policy{

    private double epsilon;
    private QLearner qLearner;
    
    public EpsilonGreedy(QLearner l){
        epsilon = 0.5;
        qLearner = l;
    }
    public EpsilonGreedy(QLearner l, double epsilon){
        this.epsilon = epsilon;
        qLearner = l;
    }
    
    public void setEpsilon(double e){
        epsilon = e;
    }
           
    //With HashMap of available actions and their rewards in String form, decides whether to choose randomly or greedily and returns name of next action.
    @Override
    public String next(HashMap m) {
        String nextState = null;
        String[] set = new String[m.size()];
        m.keySet().toArray(set);
        
        if(!m.isEmpty() && m.size()>1){
            
            if(Math.random()>epsilon){
                //If there is more than one element in set take random value to compare against.
                int rand = (int)(Math.random()*100);
                String val = (String) m.get(set[rand%set.length]);
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
        
    
    @Override
    public void setStateSpace(String[] ss) {
        //stateSpace = ss;    
    }
    
}

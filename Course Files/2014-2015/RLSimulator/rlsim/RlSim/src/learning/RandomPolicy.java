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
public class RandomPolicy implements Policy{

    private double epsilon;
    private Learner learner;
    
    public RandomPolicy(QLearner l, double epsilon){
        this.epsilon = epsilon;
    }
    @Override
    public String next(HashMap m) {
        String nextState = null;
        String[] set = new String[m.size()];
        m.keySet().toArray(set);
        
        int i = (int)(Math.random()*100);
        nextState = set[i%set.length];
            
        return nextState;
    }
}

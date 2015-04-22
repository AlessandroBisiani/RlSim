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
            if(Math.random()>epsilon){
                
                int randIndex = rand%set.length;
                
                String val = (String) m.get(set[randIndex]);
                double reward = Double.parseDouble(val);;
                nextState = set[randIndex];
                
                //String[] equivalentVals = new String[set.length];
                
                for(int i=0;i<set.length;i++){
                    String v = (String) m.get(set[i]);
                    double tempV = Double.parseDouble(v);
                    if(tempV > reward){
                        reward = tempV;
                        nextState = set[i];
                       // for(int y=0;y<equivalentVals.length;y++){
                       //     equivalentVals[y]="";
                    }
                  //  } else if(tempV==reward){
                  //      equivalentVals[i] = set[i];
                  //  }
                }
                
                System.out.println("Greedy action selected" + reward);
                return nextState;
            } else{
                nextState = set[rand%set.length];
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

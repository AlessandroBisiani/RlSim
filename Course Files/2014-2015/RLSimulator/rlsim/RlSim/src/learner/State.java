/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learner;

/**
 *
 * @author alessandrobisiani
 */
public class State {
    
    private double r    = 0;
    private int[] name  = new int[2];
    
    public State(){
        
    }
    
    public double getReward(){
        return r;
    }
    public void setReward(double reward){
        r = reward;
    }
            
    public int[] getStateName(){
        return name;
    }
    
}

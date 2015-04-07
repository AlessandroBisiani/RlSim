/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

/**
 *
 * @author alessandrobisiani
 */
public class EpsilonGreedy implements Policy{

    private double epsilon;
    
    private String[] stateSpace;
    
    public EpsilonGreedy(){
        epsilon = 0.5;
    }
    
    public void setEpsilon(double e){
        epsilon = e;
    }
           
    @Override
    public String next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public void setStateSpace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

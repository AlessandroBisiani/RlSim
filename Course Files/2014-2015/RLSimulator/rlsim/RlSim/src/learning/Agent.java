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
public class Agent {
    
    private final learning.RMatrix rMatrix;
    private final learning.QMatrix qMatrix;
    //where the agent finds itself
    private String location;
    private double epsilon;
    //available actions mapped to binary value denoting their availability. 0 for unavailable, 1 for available.
    private HashMap actions;
    
    
    public Agent(){
        rMatrix = new RMatrix();
        qMatrix = new QMatrix();
        
        location = "";
        
        epsilon = 1;
        
        actions = new HashMap(4,1);
        
        
        System.out.println(actions);
        findActions();
        System.out.println(actions);
        eGreedy();
        System.out.println(actions);
    }
    
    public void move(){
        
    }
    
    private void eGreedy(){
        
    }
    
    private String selectRandomAction(){
        return null;
    }
    
    private void findActions(){
        
    }
    
    private void denyActions(){
        
    }
    
    
    /**
    //what method is going to pull the agent through an entire episode, and coordinate it's restart/termination, and experiment termination?
    //For now, move() takes the name of an action and exectues the relative location[] update method.
    
    
    //E-Greedy action selection
    private String eGreedy(){
        findActions();
        String action = null;
        
        if(Math.random()>epsilon){
            //find and select greedy action
            double reward = 0;
            //WHY DID I TELL IT TO DETERMINE THE REWARD FOR THE NEW ACTION HERE?--------------------------------------!
            if(actions.get("up").equals(1)){
                if(reward < qMatrix.getValueAt(location[0],location[1]-1)){
                    reward = qMatrix.getValueAt(location[0],location[1]-1);
                    action = "up";
                }}
            if(actions.get("right").equals(1)){
                if(reward < qMatrix.getValueAt(location[0]+1,location[1])){
                    reward = qMatrix.getValueAt(location[0]+1,location[1]);
                    action = "right";
                }}
            if(actions.get("down").equals(1)){
                if(reward < qMatrix.getValueAt(location[0],location[1]+1)){
                    reward = qMatrix.getValueAt(location[0],location[1]+1);
                    action = "down";
                }}
            if(actions.get("left").equals(1)){
                if(reward < qMatrix.getValueAt(location[0]-1,location[1])){
                    reward = qMatrix.getValueAt(location[0]-1,location[1]);
                    action = "left";
                }}
        } else {
            //select random action
            System.out.println("else test");
            action = selectRandomAction();
        }
        
        //if epsilon is fired but no action was selected by the if statements, select randomly.
        if(action == null){
            action = selectRandomAction();
        }
        System.out.println(action);
        return action;
    }
    
    //supports action selection method so that a random action can be selected outside the if statement in case no action == null at the end. 
    private String selectRandomAction(){
        HashMap options = new HashMap();
        options.put(0,"up");
        options.put(1,"right");
        options.put(2,"down");
        options.put(3,"left");
        
        //pick a random number between 0 and 3. check if it maps to an available action. if not, add one to it until an available action is found.
        //could use an exception since while(true) could run forever given a bug.
        int i = (int)(Math.random() * 100 % 4);
        System.out.println(i + " - THIS TEST HERE");
        while(true){
            if(actions.get(options.get(i)).equals(1)){
                return (String)options.get(i);
            } else {
                i = (i+1) % 4;
            }
        }
        
    }
    
    //check which actions can be performed and set the HashMap accordingly
    private void findActions(){
        if(location[0]<0 || location[0]>9 || location[1]<0 || location[0]>9){
            denyActions();
            System.out.println("Out of bounds.");
            return;
        }
        if(location[0]>0 && location[0]<10){
            actions.put("left", 1);
        } else {actions.put("left", 0);}
        
        if(location[0]>-1 && location[0]<9){
            actions.put("right", 1);
        } else {actions.put("right", 0);}
        
        if(location[1]>0 && location[1]<10){
            actions.put("up", 1);
        } else {actions.put("up", 0);}
        
        if(location[1]>-1 && location[1]<9){
            actions.put("down", 1);
        } else {actions.put("down", 0);}
    }
    
    private void denyActions(){
        actions.put("up", 0);
        actions.put("right", 0);
        actions.put("down", 0);
        actions.put("left", 0);
    }
    
 
    //4 methods to change the value of location in response to their being called.
    //They describe the available actions. They are unprotected.
    private void up(){
        location[1] = location[1] - 1;
    }
    private void right(){
        location[0] = location[0] + 1;
    }
    private void down(){
        location[1] = location[1] + 1;
    }
    private void left(){
        location[0] = location[0] - 1;
    }
*/
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura
 */
public class Gang extends Thread {
//implements Runnable {
String name;
public boolean flagUp;
public boolean ourTurn;
public boolean exitingCourt;
 
public Gang otherGang;
GUI gui;
public Gang(String n, GUI g){
    //Sets name for id purposes, flag down by default
    name = n;
    gui = g;
    flagUp = false;
    ourTurn = false;
    exitingCourt = false;
}
public void setOthers(Gang g){
    //Takes the other gang as a parameter, so they can check their actions
    otherGang = g;
}

private void lowerFlag(){
    flagUp = false;
    if(name.equals("Sharks")){
        //Sets the flag to  be down on the GUI
        gui.lowerSharks();
    }else{
        gui.lowerJets();
    }
}

public void setTurn(){
    //So the other gang can set the turn indicator
    ourTurn = true;
}

private void checkOthers()throws InterruptedException{
    if(otherGang.flagUp == true && otherGang.ourTurn == true){
        System.out.println("The "+name+" cannot play,it is the " +otherGang.name+"' turn.");
        lowerFlag();
        //Visibly lowers flag on GUI
        if(name.equals("Sharks")){
            gui.lowerSharks();
        }else{
            gui.lowerJets();
        }
        Thread.sleep(100);
        request();
    }else{
        enterCourt();
            System.out.println("Yay! The "+name+" can play");
    }
}


public void request() throws InterruptedException{
    Thread.sleep(100);
    //Sets turn indicator for other gang
    otherGang.setTurn();
    //Sets the turn indicator on the GUI
    gui.setTurn(otherGang.name);
    
    flagUp = true;
    if(name.equals("Sharks")){
        //Sets the flag to  be up on the GUI
        gui.raiseSharks();
    }else{
        gui.raiseJets();
    }
    Thread.sleep(800);
    checkOthers();
     Thread.sleep(100);
     
    
    }


public void enterCourt() throws InterruptedException{
    //Shows the gang on the GUI
    if("Sharks".equals(name)){
        gui.sharksPlay();
    }else{
        gui.jetsPlay();
    }
    //Longer sleep to allow more time to see the visualisation on GUI
    Thread.sleep(4000);
    try{
    exitCourt();
    }catch(Exception e){
        System.out.println("Exception: " + e );
    }
    
}

public void exitCourt() throws InterruptedException{
       flagUp = false;
       ourTurn = false;
       exitingCourt = true;
       otherGang.setTurn();
       
       //Resets to empty court on GUI
       gui.exitCourt();
       //Lowers the flag
       if(name.equals("Sharks")){
           gui.lowerSharks();
       }else{
           gui.lowerJets();
       }
       request();
}



 @Override   
    public void run(){
      System.out.println("The gang name is: " +name);
      //request is first thing they do
      try{
        request();
      }catch(Exception e){
          System.out.println("Exception running in Gang class: " + e);
      }
    }
    public static void main(String args[]) {

    }

   
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in3042;
/**
 *
 * @author Laura
 */
public class Gang extends Thread{
//implements Runnable {
String name;
public boolean flagUp;
public boolean ourTurn;
public boolean exitingCourt;
 
public Gang otherGang;

public Gang(String n){
    //Sets name for id purposes, flag down by default
    name = n;
    flagUp = false;
    ourTurn = false;
    exitingCourt = false;

}
public void setOthers(Gang g){
    //Takes the other gang as a parameter, so they can check their actions
    otherGang = g;
}

private void raiseFlag(){
    flagUp = true;
    
}
private void lowerFlag(){
    flagUp = false;
    //request();
   
    
}

public void setTurn(){
    //So the other gang can set the turn indicator
    ourTurn = true;
}

private void checkOthers()throws InterruptedException{
    if(otherGang.flagUp == true && otherGang.ourTurn == true){
        System.out.println("The "+name+" cannot play,it is the " +otherGang.name+"' turn.");
        lowerFlag();
        request();
    }else{
        enterCourt();
            System.out.println("Yay! The "+name+" can play");
    }
}


public void request() throws InterruptedException{
    raiseFlag();
    otherGang.setTurn();
    checkOthers();
    System.out.println("The "+name+" have requested to play.");
    Thread.sleep (1000);
    }


public void enterCourt(){
    System.out.println("The " + name+ " have played and are leaving the court.");
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
       Thread.sleep (1000); 
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

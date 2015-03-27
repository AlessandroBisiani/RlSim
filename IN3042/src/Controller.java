/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura
 */
public class Controller implements Runnable{
    
    @Override
    public void run(){
        
    }
    
    public static void main(String args[]) {
        //Court c = new Court();
           Gang sharks = new Gang("Sharks");
           Gang jets = new Gang("Jets");
           sharks.setOthers(jets);
           jets.setOthers(sharks);
           System.out.println("TEST");
           Thread s = new Thread(sharks);
           Thread j = new Thread(jets);
            s.start();
            j.start();
    }

}
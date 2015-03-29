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
           GUI gui = new GUI();
           gui.setVisible(true);
           
           Gang sharks = new Gang("Sharks", gui);
           Gang jets = new Gang("Jets", gui);
           sharks.setOthers(jets);
           jets.setOthers(sharks);
           System.out.println("TEST");
           Thread s = new Thread(sharks);
           Thread j = new Thread(jets);
            s.start();
            j.start();
    }

}
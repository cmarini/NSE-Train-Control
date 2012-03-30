/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import CTC.CTCView;
import java.lang.Math;

public class SimulatorRunner
{
    private static CTCView view;
    private static Simulator s;
    private static int clockRate = 60;
    private static boolean isOpen;
    
    public static void main(String[] args) 
    {
        s = new Simulator();
        view = new CTCView();
        s.setView(view);
        view.setSimulator(s);
        isOpen = true;
        
        while(isOpen)
        {
            int sleeptime = (int)(Math.ceil(clockRate/60.0));
            s.run();
            clockRate = view.getClockRate();
            isOpen = view.getIsOpen();
            sleep(sleeptime);
        }
    }
    
    
    
    public static void sleep(long milliseconds) 
    {
        Thread thread = new Thread();
        
        try 
        { 
            thread.sleep(milliseconds); 
        }
        catch (Exception e) 
        {
            
        }
    }
    
}

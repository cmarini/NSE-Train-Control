/*
*	Program Name:	SimulatorRunner.java
*	Lead Programmer:	Zachary Sweigart
*	Date Modified:	4/19/12
*/

package simulator;

import ctc.CTCView;
import java.lang.Math;

/**
 * Contains the main method for the NSE train simulator program as well as the
 * clock that is used
 * 
 * @author Zachary Sweigart
 */

public class SimulatorRunner
{
    public static final boolean DEBUG_MODE = true; // used to turn on/off debug print statements
    private static CTCView view;    // references the main GUI of the program
    private static Simulator s;     // used to initiate updates on clock ticks
    private static int clockRate = 60;  // holds the number of minutes equal to one hour in the simulation
    private static boolean isOpen;  // used to determine if the GUI has been closed

    public static void main(String[] args) 
    {
        s = new Simulator(DEBUG_MODE);
        view = new CTCView(DEBUG_MODE, s);
        
        s.setView(view);
        s.setModel(view.getModel());
        isOpen = true;
        
        while(isOpen)
        {
            int sleeptime = (int)(Math.ceil(clockRate/60.0));
            s.run();
            clockRate = view.getClockRate();
            //s.setTrainControllerClockRate(clockRate);
            isOpen = view.getIsOpen();
            sleep(sleeptime);
        }
    }
    
    /**
    * Pauses the program for the specified number of milliseconds between 
    * clock ticks
    * 
    * @param milliseconds The number of milliseconds between clock ticks
    */
    public static void sleep(long milliseconds) 
    {
        Thread thread = new Thread(); // Creates a thread that is used to pause for a clock tick
        
        try 
        { 
            thread.sleep(milliseconds); 
        }
        catch (Exception e) 
        {
            System.out.println("Thread pause error");
        }
    }
    
}

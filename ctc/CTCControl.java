/*
*	Program Name:	CTCControl.java
*	Lead Programmer:	Zachary Sweigart
*	Date Modified:	4/19/12
*/

package ctc;

import simulator.*;
import global.*;
import trackmodel.*;
import traincontroller.*;
import wayside.*;

/**
 * This file contains the specification for the CTCControl which passes messages
 *  to the track controllers from the view
 * 
 * @author Zachary Sweigart
 */
public class CTCControl 
{
    private static boolean debugMode;   // sets the debug mode flag
    private CTCModel model; // reference to the model
    private Simulator sim;  // reference to the simulator
    
    CTCControl(CTCModel m)
    {
        model = m;
    }
    
    /**
     * sets the debug mode flag
     * 
     * @param d boolean debug mode
     */
    public void setDebugMode(boolean d)
    {
        debugMode = d;
    }
    
    /**
     * sets the reference to the simulator
     * 
     * @param s Simulator reference
     */
    public void setSimulator(Simulator s)
    {
        sim = s;
    }
    
    /**
     * send the dispatcher speed to the specified track block
     * 
     * @param sp integer setpoint from the dispatcher
     * @param l Line of the block
     * @param wayside character wayside of the block
     * @param unit integer block unit
     */
    public void setDispatcherSpeed(int sp, Line l, char wayside, int unit)
    {
        Track t = model.getTrack(new ID(l, wayside, unit));
        t.setDispatchLimit(sp);
        if(debugMode)
        {
            System.out.println("CTC Control: Dispatcher speed set to: " + sp + " for block: " + t.getID().toString());
        }
    }
 
    /**
     * send the dispatcher authority to the specified track block
     * 
     * @param auth integer authority from the dispatcher
     * @param l Line of the block
     * @param wayside character wayside of the block
     * @param unit integer block unit
     */
    public void setDispatcherAuthority(int auth, Line l, char wayside, int unit)
    {     
        Wayside w = model.getWayside(l, wayside);
        Track t = model.getTrack(new ID(l, wayside, unit));
        w.setAuthority(t.getID(), auth);
        t.setAuthority(auth);
        if(debugMode)
        {
            System.out.println("CTC Control: Dispatcher authority set to: " + auth + " for block: " + t.getID().toString());
        }
    }
    
    /**
     * send the operator commands to the specified train
     * 
     * @param sp integer speed
     * @param b boolean brake signal
     * @param trainID String ID of the train
     */
    public void setOperatorCommands(int sp, boolean b, String trainID)
    {
        TrainController t = sim.getTrainController(trainID);
        t.setBraking(b);
        t.setOperatorSpeed(sp);
        if(debugMode)
        {
            System.out.println("CTC Control: Operator Speed : " + sp + " Brake: " + b + " to train: " + trainID);
        }
    }
}

/*
*	Program Name:	CTCControl.java
*	Lead Programmer:	Zachary Sweigart
*	Date Modified:	4/19/12
*/

package ctc;

import simulator.*;

/**
 * This file contains the specification for the CTCControl which passes messages
 *  to the track controllers from the view
 * 
 * @author Zachary Sweigart
 */
public class CTCControl 
{
    private static boolean debugMode;   // sets the debug mode flag
    private int authority;  // authority sent be the dispatcher
    private int setpoint;   // setpoint sent by the dispatcher
    private int operatorSpeed;  // speed sent by the operator
    private boolean operatorBrake;  // brake signal sent by the operator
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
     */
    public void setDispatcherSpeed(int sp)
    {
        setpoint = sp;
        if(debugMode)
        {
            System.out.println("CTC Control: Dispatcher speed set to: " + setpoint);
        }
    }
 
    /**
     * send the dispatcher authority to the specified track block
     * 
     * @param auth integer authority from the dispatcher
     */
    public void setDispatcherAuthority(int auth)
    {        
        authority = auth;
        if(debugMode)
        {
            System.out.println("CTC Control: Dispatcher authority set to: " + authority);
        }
    }
    
    /**
     * send the operator commands to the specified train
     * 
     * @param sp integer speed
     * @param b boolean brake signal
     */
    public void setOperatorCommands(int sp, boolean b)
    {
        operatorSpeed = sp;
        operatorBrake = b;
        if(debugMode)
        {
            System.out.println("CTC Control: Operator Speed : " + operatorSpeed + " Brake: " + operatorBrake);
        }
    }
}

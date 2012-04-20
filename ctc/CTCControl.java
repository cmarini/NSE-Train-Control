/*
*	Program Name:	CTCControl.java
*	Lead Programmer:	Zachary Sweigart
*	Description:	
*	Date Modified:	4/19/12
*/

package ctc;

/**
 * 
 * @author AM
 */
public class CTCControl 
{
    private static boolean debugMode;
    private int authority;
    private int setpoint;
    private int operatorSpeed;
    private boolean operatorBrake;
    private CTCModel model;
    
    CTCControl(CTCModel m)
    {
        model = m;
    }
    
    /**
     * 
     * @param d
     */
    public void setDebugMode(boolean d)
    {
        debugMode = d;
    }
    
    /**
     * 
     * @param sp
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
     * 
     * @param auth
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
     * 
     * @param sp
     * @param b
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

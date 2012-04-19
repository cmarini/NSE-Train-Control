/*
*	Program Name:	File.java
*	Lead Programmer:	First Last
*	Description:	This class/file/program will…
*	Date Modified:	1/20/12
*/

package ctc;

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
    
    public void setDebugMode(boolean d)
    {
        debugMode = d;
    }
    
    public void setDispatcherSpeed(int sp)
    {
        setpoint = sp;
        if(debugMode)
        {
            System.out.println("CTC Control: Dispatcher speed set to: " + setpoint);
        }
    }
 
    public void setDispatcherAuthority(int auth)
    {        
        authority = auth;
        if(debugMode)
        {
            System.out.println("CTC Control: Dispatcher authority set to: " + authority);
        }
    }
    
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

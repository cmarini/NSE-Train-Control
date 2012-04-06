/*
*	Program Name:	File.java
*	Lead Programmer:	First Last
*	Description:	This class/file/program will…
*	Date Modified:	1/20/12
*/

package ctc;

public class CTCControl 
{
    private int authority;
    private int setpoint;
    private int operatorSpeed;
    private boolean operatorBrake;
    private CTCModel model;
    
    CTCControl(CTCModel m)
    {
        model = m;
    }
    
    public void setDispatcherSpeed(int sp)
    {
        setpoint = sp;
        System.out.println(sp);
    }
 
    public void setDispatcherAuthority(int auth)
    {        
        authority = auth;
        System.out.println(auth);
    }
    
    public void setOperatorCommands(int sp, boolean b)
    {
        operatorSpeed = sp;
        operatorBrake = b;
        //System.out.println("Operator Speed : " + operatorSpeed + " Brake: " + operatorBrake);
    }
}

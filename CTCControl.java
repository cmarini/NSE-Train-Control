/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CTC;

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
    
    public void setCommands(int sp, int auth)
    {
        setpoint = sp;
        authority = auth;
        //System.out.println("Setpoint: " + setpoint + "   Authority:  " + authority);
    }
    
    public void setOperatorCommands(int sp, boolean b)
    {
        operatorSpeed = sp;
        operatorBrake = b;
        //System.out.println("Operator Speed : " + operatorSpeed + " Brake: " + operatorBrake);
    }
    
    public void createTrain()
    {
        
    }
}

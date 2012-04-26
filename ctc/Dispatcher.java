/*
*	Program Name:	Login.java
*	Lead Programmer:	Zachary Sweigart
*	Description:	
*	Date Modified:	4/19/12
*/

package ctc;

import global.*;

/**
 * This file contains the description of a dispatcher and its properties
 * 
 * @author Zachary Swigart
 */
public class Dispatcher 
{
    private String userName;    // the dispatchers login username/ID
    private String password;    // the dispatchers login password
    private Line line;      // the line the dispatcher works on
    private ID waysideID;   // the wayside section the dispatcher is responsible for
    
    Dispatcher(String u, String p, Line l, ID w)
    {
        userName = u;
        password = p;
        line = l;
        waysideID = w;
    }
    
    /**
     * get the username of the dispatcher
     * 
     * @return string username
     */
    public String getUserName()
    {
        return userName;
    }
    
    /**
     * get the password of the dispatcher
     * 
     * @return string password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * get the dispatcher line
     * 
     * @return Line dispatcher line
     */
    public Line getLine()
    {
        return line;
    }
    
    /**
     * get the dispatcher wayside
     * 
     * @return ID of the wayside
     */
    public ID getWayside()
    {
        return waysideID;
    }
}


package ctc;

import global.*;

/**
 *
 * @author AM
 */
public class Dispatcher 
{
    private String userName;
    private String password;
    private Line line;
    private ID waysideID;
    
    Dispatcher(String u, String p, Line l, ID w)
    {
        userName = u;
        password = p;
        line = l;
        waysideID = w;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public Line getLine()
    {
        return line;
    }
    
    public ID getWayside()
    {
        return waysideID;
    }
}

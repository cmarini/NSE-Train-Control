/*
*	Program Name:	Login.java
*	Lead Programmer:	Zachary Sweigart
*	Description:	
*	Date Modified:	4/19/12
*/

package ctc;

/**
 * This class contains methods for determining whether or not a dispatcher has 
 * logged in successfully
 * 
 * @author Zachary Sweigart
 */
public class Login 
{

    /**
     * Determine if the username and password provided are valid
     * 
     * @param userName String username entered by the user
     * @param password String password entered by the user
     * @param debugMode boolean debug mode flag
     * @param dispatchers valid dispatcher objects
     * @return integer location of successful login information in the dispatcher array
     */
    public static int authenticate(String userName, String password, boolean debugMode, Dispatcher [] dispatchers)
    {
        String temp = "";
        int i = 0;
        for(i = 0; i < dispatchers.length; i++)
        {
            if(userName.equals(dispatchers[i].getUserName()))
            {
                temp = dispatchers[i].getUserName();
                break;
            }   
        }
        if(debugMode)
        {
            System.out.println("Log In: Username: " + userName);
            System.out.println("Log In: Password: " + password);
        }
        if(temp.equals(""))
        {
            return -1;
        }
        else
        {
            if(password.equals(dispatchers[i].getPassword()))
            {
                return i;
            }
        }
        return -1;
    }
    
}

/*
*	Program Name:	Login.java
*	Lead Programmer:	Zachary Sweigart
*	Description:	
*	Date Modified:	4/19/12
*/

package ctc;

/**
 * 
 * @author AM
 */
public class Login 
{

    /**
     * 
     * @param userName
     * @param password
     * @param debugMode
     * @return
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

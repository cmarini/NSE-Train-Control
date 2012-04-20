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
    private static String userIDs[] = {"sweigartz", "washingtons", "marinic", "korenicj", "agiobeneboo"};
    private static String userPasswords[] = {"password", "1234", "secret", "admin", "default"};
    
    /**
     * 
     * @param userName
     * @param password
     * @param debugMode
     * @return
     */
    public static boolean authenticate(String userName, String password, boolean debugMode)
    {
        String temp = "";
        int i = 0;
        for(i = 0; i < userIDs.length; i++)
        {
            if(userName.equals(userIDs[i]))
            {
                temp = userIDs[i];
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
            return false;
        }
        else
        {
            if(password.equals(userPasswords[i]))
            {
                return true;
            }
        }
        return false;
    }
    
}

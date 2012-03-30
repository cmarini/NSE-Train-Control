/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CTC;

/**
 *
 * @author AM
 */
public class Login 
{
    private static String userIDs[] = {"sweigartz", "washingtons", "marinic", "korenicj", "agiobeneboo"};
    private static String userPasswords[] = {"password", "1234", "secret", "admin", "default"};
    
    public static boolean authenticate(String userName, String password)
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

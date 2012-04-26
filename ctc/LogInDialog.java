/*
*	Program Name:	LogInDialog.java
*	Lead Programmer:	Zachary Sweigart
*	Description:	
*	Date Modified:	4/19/12
*/

package ctc;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
        
/**
 * This file contains the specification for the dispatcher login dialog box
 * 
 * @author Zachary Sweigart
 */
public class LogInDialog extends JDialog
{
    private boolean debugMode;  // Sets the debug mode flag
    private JTextField tfUsername;  // allows the user to enter their username
    private JPasswordField pfPassword; // allows the user to enter their password
    private JLabel lbUsername;  // labels the username field
    private JLabel lbPassword;  // labels the password field
    private JButton btnLogin;   // logs the user in
    private JButton btnCancel;  // canels the login dialog
    private boolean succeeded;  // signals whether or not the user logged in successfully
    private String verifiedUsername; // holds the verified username
    private Dispatcher [] dispatchers;  // holds the valid dispatchers
    private Dispatcher verifiedDispatcher; // holds the verified dispatcher

    /**
     * create and display a new log in dialog
     * 
     * @param owner frame that displays the the dialog
     * @param d sets the debug mode flag
     * @param dispatch  holds the valid dispatcher information
     */
    public LogInDialog(JFrame owner, boolean d, Dispatcher [] dispatch) 
    {
        super(owner, "Login", true);
        
        debugMode = d;
        dispatchers = dispatch;
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);

        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {
                if(debugMode)
                {
                    System.out.println("Log In Dialog: Username entered: " + getUsername());
                    System.out.println("Log In Dialog: Password entered: " + getPassword());
                }
                int location = Login.authenticate(getUsername(), getPassword(), debugMode, dispatchers);
                if (!(location == -1)) 
                {
                    JOptionPane.showMessageDialog(LogInDialog.this,
                        "Hi " + getUsername() + "! You have successfully logged in.",
                        "Login",
                    JOptionPane.INFORMATION_MESSAGE);
                    verifiedUsername = getUsername();
                    verifiedDispatcher = dispatchers[location];
                    succeeded = true;
                    dispose();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(LogInDialog.this,
                        "Invalid username or password",
                        "Login",
                    JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
                    dispose();
                }
            }
        });
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                if(debugMode)
                {
                    System.out.println("Log In Dialog: Cancel button clicked");
                }
                dispose();
            }
        });
        
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        //setLocationRelativeTo(parent);
    }

    /**
     * return the successfully logged in username
     * 
     * @return string verified username
     */
    public String getVerifiedUsername()
    {
        return verifiedUsername;
    }
    
    /**
     * return the successfully logged in dispatcher
     * 
     * @return dispatcher successfully logged in
     */
    public Dispatcher getVerifiedDispatcher()
    {
        return verifiedDispatcher;
    }
    
    /**
     * get the username out of the text field
     * 
     * @return string entered username
     */
    public String getUsername() 
    {
        return tfUsername.getText().trim();
    }

    /**
     * get the password out of the text field
     * 
     * @return string entered password
     */
    public String getPassword() 
    {
        return new String(pfPassword.getPassword());
    }

    /**
     * return if the user logged in successfully
     * 
     * @return boolean successful login
     */
    public boolean isSucceeded() 
    {
        return succeeded;
    }
}

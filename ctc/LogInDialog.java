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
 * 
 * @author AM
 */
public class LogInDialog extends JDialog
{
    private boolean debugMode;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    private String verifiedUsername;

    /**
     * 
     * @param owner
     * @param d
     */
    public LogInDialog(JFrame owner, boolean d) 
    {
        super(owner, "Login", true);
        
        debugMode = d;
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
                if (Login.authenticate(getUsername(), getPassword(), debugMode)) 
                {
                    JOptionPane.showMessageDialog(LogInDialog.this,
                        "Hi " + getUsername() + "! You have successfully logged in.",
                        "Login",
                    JOptionPane.INFORMATION_MESSAGE);
                    verifiedUsername = getUsername();
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
     * 
     * @return
     */
    public String getVerifiedUsername()
    {
        return verifiedUsername;
    }
    
    /**
     * 
     * @return
     */
    public String getUsername() 
    {
        return tfUsername.getText().trim();
    }

    /**
     * 
     * @return
     */
    public String getPassword() 
    {
        return new String(pfPassword.getPassword());
    }

    /**
     * 
     * @return
     */
    public boolean isSucceeded() 
    {
        return succeeded;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CTC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
        
public class LogInDialog extends JDialog
{

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    private String verifiedUsername;

    public LogInDialog(JFrame owner) 
    {
        super(owner, "Login", true);
        //
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
                if (Login.authenticate(getUsername(), getPassword())) 
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

    public String getVerifiedUsername()
    {
        return verifiedUsername;
    }
    
    public String getUsername() 
    {
        return tfUsername.getText().trim();
    }

    public String getPassword() 
    {
        return new String(pfPassword.getPassword());
    }

    public boolean isSucceeded() 
    {
        return succeeded;
    }
}

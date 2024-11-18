package GuiScreen.ProjectFrames;

import Input_output.Read;
import User.Account;
import User.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LogInFrame extends JFrame implements ActionListener
{
    private String accountAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\TheAccountsOfUsers.txt";
    private String usersAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\Users.txt";
    private ArrayList<User> USERS = Read.readUsersFromFile(usersAdress);
    private ArrayList<Account> ACCOUNTS = Read.readAccountsFromFile(accountAdress);
    private JTextField passwordTextField = new JTextField("");
    private JTextField usernameTextField = new JTextField("");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel usernameLabel = new JLabel("Username");
    private JButton LogInButton = new JButton("Log In");
    private JButton SignUpButton = new JButton("Sign Up");

    
    public LogInFrame()
    {
        setTitle("Log In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,250);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        
        usernameLabel.setBounds(getWidth()/2 - 35, 20, 65, 20);
        usernameTextField.setBounds(getWidth()/2 - 65, 40, 120, 20);
        passwordLabel.setBounds(getWidth()/2 - 35, 80, 65, 20);
        passwordTextField.setBounds(getWidth()/2 - 65, 100, 120, 20);
        LogInButton.setBounds(getWidth()/2-90, 160, 80, 20);
        SignUpButton.setBounds(getWidth()/2+10, 160, 80, 20);

        
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordTextField);
        add(LogInButton);
        add(SignUpButton);
        
        LogInButton.addActionListener(this);
        SignUpButton.addActionListener(this);
        
        setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == LogInButton)
        {
            String password = passwordTextField.getText();
            String username = usernameTextField.getText();
            
            Account account = Account.getTheAccoutnFromTheUsername(ACCOUNTS, username);
            
            if(account == null)
            {
                JOptionPane.showMessageDialog(this, "The Account is not Exist, Sign In !");
                clearTheTextFields();
                return;
            }
            else
            {
                if(!account.getPassword().equals(password))
                {
                    JOptionPane.showMessageDialog(this, "The Password is not Correct !!");
                    passwordTextField.setText("");
                    return;
                }
                else
                {
                    USERS = Read.readUsersFromFile(usersAdress);
                    UserFrame f = new UserFrame(User.getUser(USERS, account));
                    dispose();
                }
            }
        }
        else if(e.getSource() == SignUpButton)
        {
            SignUpFrame singfram = new SignUpFrame();
            dispose();
        }
    }
    
    private void clearTheTextFields()
    {
        usernameTextField.setText("");
        passwordTextField.setText("");
    }
    
}

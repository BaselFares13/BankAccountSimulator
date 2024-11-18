package GuiScreen.ProjectFrames;

import Input_output.Read;
import Input_output.Write;
import User.Account;
import User.Date;
import User.FullName;
import User.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SignUpFrame extends JFrame implements ActionListener
{
    private String accountAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\TheAccountsOfUsers.txt";
    private String usersAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\Users.txt";
    private ArrayList<User> USERS = Read.readUsersFromFile(usersAdress);
    private ArrayList<Account> ACCOUNTS = Read.readAccountsFromFile(accountAdress);
    private int Id = ACCOUNTS.size();
    private String []cities = {"Hebron", "Nablus", "Ramallah", "Jerico", "Jerusalem", "BethLehem", "Jinen", "Haifa", "Yafa", "Acre", "Nazaret"};
    private JTextField passwordTextField = new JTextField("");
    private JTextField fullNameTextField = new JTextField("");
    private JTextField ageTextField = new JTextField("");
    private JComboBox<String> cityComboBox = new JComboBox<>(cities);
    private JTextField dayTextField = new JTextField("");
    private JTextField monthTextField = new JTextField("");
    private JTextField yearTextField = new JTextField("");
    private JTextField confirmingPasswordTextField = new JTextField("");
    private JTextField usernameTextField = new JTextField("");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel confirmingPasswordLabel = new JLabel("Confirm Your Password");
    private JLabel usernameLabel = new JLabel("Username");
    private JLabel fullNameLabel = new JLabel("Full Name");
    private JLabel ageLabel = new JLabel("Age");
    private JLabel cityLabel = new JLabel("City");
    private JLabel dayLabel = new JLabel("Day");
    private JLabel monthLabel = new JLabel("Month");
    private JLabel yearLabel = new JLabel("Year");
    private JButton SignUpButton = new JButton("Sign Up");
    private JButton LogInButton = new JButton("Log In");
    
    public SignUpFrame()
    {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,550);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        
        usernameLabel.setBounds(getWidth()/2 - 35, 20, 65, 20);
        usernameTextField.setBounds(getWidth()/2 - 65, 40, 120, 20);
        passwordLabel.setBounds(getWidth()/2 - 35, 80, 65, 20);
        passwordTextField.setBounds(getWidth()/2 - 65, 100, 120, 20);
        confirmingPasswordLabel.setBounds(getWidth()/2 - 72, 140, 140, 20);
        confirmingPasswordTextField.setBounds(getWidth()/2 - 65, 160, 120, 20);
        fullNameLabel.setBounds(getWidth()/2 - 35 ,200 ,65 ,20);
        fullNameTextField.setBounds(getWidth()/2 - 105, 220, 200, 20);
        ageLabel.setBounds(getWidth()/2 - 19 ,260 ,30 ,20);
        ageTextField.setBounds(getWidth()/2 - 25 ,280 ,40 ,20);
        cityLabel.setBounds(getWidth()/2 - 19 ,320 ,30 ,20);
        cityComboBox.setBounds(getWidth()/2 - 55 ,340 ,100 ,20);
        dayLabel.setBounds(getWidth()/2 - (19+60) ,380 ,30 ,20);
        monthLabel.setBounds(getWidth()/2 - 24 ,380 ,40 ,20);
        yearLabel.setBounds(getWidth()/2 - (19-60) ,380 ,30 ,20);
        dayTextField.setBounds(getWidth()/2 - (24+60) ,400 ,40 ,20);
        monthTextField.setBounds(getWidth()/2 - 24 ,400 ,40 ,20);
        yearTextField.setBounds(getWidth()/2 - (24-60) ,400 ,40 ,20);
        SignUpButton.setBounds(getWidth()/2 - 90 ,465 ,80 ,20);
        LogInButton.setBounds(getWidth()/2, 465, 80, 20);
        
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordTextField);
        add(confirmingPasswordLabel);
        add(confirmingPasswordTextField);
        add(fullNameLabel);
        add(fullNameTextField);
        add(ageLabel);
        add(ageTextField);
        add(cityLabel);
        add(cityComboBox);
        add(dayLabel);
        add(dayTextField);
        add(monthLabel);
        add(monthTextField);
        add(yearLabel);
        add(yearTextField);
        add(SignUpButton);
        add(LogInButton);
        
        LogInButton.addActionListener(this);
        SignUpButton.addActionListener(this);
        
        setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == LogInButton)
        {
            LogInFrame logFrm = new LogInFrame();
            dispose();
        }
        else if(e.getSource() == SignUpButton)
        {
            try
            {
                String password = passwordTextField.getText();
                String username = usernameTextField.getText();
                Date creationDate = new Date(Integer.valueOf(dayTextField.getText()),Integer.valueOf(monthTextField.getText()),Integer.valueOf(yearTextField.getText()));
            
                
                if(!password.equals(confirmingPasswordTextField.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Confirm if the password correct !!");
                    confirmingPasswordTextField.setText("");
                    return;
                }
                  
                Account account = new Account(username, password, creationDate);
            
                if(Account.isTheUsernameUsedBefore(ACCOUNTS, account))
                {
                    clearTheTextFields();
                    JOptionPane.showMessageDialog(this, "The Username is already used !!");
                }
                else
                {
                    FullName fullName = new FullName(fullNameTextField.getText());
                    int age = Integer.valueOf(ageTextField.getText());
                    String city = cities[cityComboBox.getSelectedIndex()];
                    Id++;
                    String ID = String.format("%08d", Id);
                
                    User user = new User(account, ID, fullName, age, city);
                    USERS.add(user);
                    Write.updateUsersInTheFile(usersAdress, USERS);
                    ACCOUNTS.add(account);
                    Write.updateAccountsInTheFile(accountAdress, ACCOUNTS);
                
                    JOptionPane.showMessageDialog(this, "You created an Account succfuly, to access to your Account, Log In !");
                    clearTheTextFields();

                }
            }
            catch(Exception E)
            {
                JOptionPane.showMessageDialog(this, E.toString());
            }
        }
       
    }
    
    private void clearTheTextFields()
    {
        usernameTextField.setText("");
        passwordTextField.setText("");
        confirmingPasswordTextField.setText("");
        fullNameTextField.setText("");
        ageTextField.setText("");
        dayTextField.setText("");
        monthTextField.setText("");
        yearTextField.setText("");
    }
    
    public static void main(String []agrs)
    {
        SignUpFrame s = new SignUpFrame();
    }
}

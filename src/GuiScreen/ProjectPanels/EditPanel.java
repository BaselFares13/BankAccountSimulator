package GuiScreen.ProjectPanels;

import Input_output.Read;
import Input_output.Write;
import User.Account;
import User.FullName;
import User.User;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel extends JPanel implements ActionListener
{
    private User user;
    private String accountAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\TheAccountsOfUsers.txt";
    private String usersAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\Users.txt";
    private ArrayList<User> USERS;
    private ArrayList<Account> ACCOUNTS;
    private String []cities = {"Hebron", "Nablus", "Ramallah", "Jerico", "Jerusalem", "BethLehem", "Jinen", "Haifa", "Yafa", "Acre", "Nazaret"};
    private JTextField passwordTextField = new JTextField("");
    private JTextField usernameTextField = new JTextField("");
    private JTextField fullNameTextField = new JTextField("");
    private JComboBox<String> cityComboBox = new JComboBox<>(cities);
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel usernameLabel = new JLabel("Username");
    private JLabel fullNameLabel = new JLabel("Full Name");
    private JLabel cityLabel = new JLabel("City");
    private JButton passwordButton = new JButton("Apply");
    private JButton usernameButton = new JButton("Apply");
    private JButton fullNameButton = new JButton("Apply");
    private JButton cityButton = new JButton("Apply");
    private boolean shouldDraw = true;

    
    public EditPanel(User user)
    {
        this.user = user;
        setLayout(null);
        setPreferredSize(new Dimension(600,500));

        
        usernameLabel.setBounds(10, 100, 65, 20);
        usernameTextField.setBounds(85, 100, 120, 20);
        usernameButton.setBounds(215, 100, 120, 20);
        passwordLabel.setBounds(10, 150, 65, 20);
        passwordTextField.setBounds(85, 150, 120, 20);
        passwordButton.setBounds(215, 150, 120, 20);
        cityLabel.setBounds(10, 200, 40, 20);
        cityComboBox.setBounds(60, 200, 120, 20);
        cityButton.setBounds(190, 200, 120, 20);
        fullNameLabel.setBounds(10,250 ,65 ,20);
        fullNameTextField.setBounds(85, 250, 200, 20);
        fullNameButton.setBounds(295, 250, 120, 20);

        usernameButton.addActionListener(this);
        passwordButton.addActionListener(this);
        cityButton.addActionListener(this);
        fullNameButton.addActionListener(this);
        
        add(usernameLabel);
        add(usernameTextField);
        add(usernameButton);
        add(passwordLabel);
        add(passwordTextField);
        add(passwordButton);
        add(cityLabel);
        add(cityComboBox);
        add(cityButton);
        add(fullNameLabel);
        add(fullNameTextField);
        add(fullNameButton);
        
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        String Title = "Edit Page";
        g.setFont(new Font("Serif",Font.BOLD, 50));
        g.drawString(Title, 10, 50);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        USERS = Read.readUsersFromFile(usersAdress);
        ACCOUNTS = Read.readAccountsFromFile(accountAdress);
        this.user = User.getUser(USERS, user.getID());
        ACCOUNTS.remove(user.getAccount());
        USERS.remove(user);
        User newUser = user;
        
        if(e.getSource().equals(usernameButton))
        {
            newUser.getAccount().setUsername(usernameTextField.getText());
            
            if(Account.isTheUsernameUsedBefore(ACCOUNTS, newUser.getAccount()))
            {
                ACCOUNTS.add(user.getAccount());
                USERS.add(user);
                JOptionPane.showMessageDialog(this, "Username you inserted is already used !!");
                usernameTextField.setText("");
                return;
            }
            
            usernameTextField.setText("");
        }
        else if(e.getSource().equals(passwordButton))
        {
            
            newUser.getAccount().setPassword(passwordTextField.getText());
            passwordTextField.setText("");
        }
        else if(e.getSource().equals(fullNameButton))
        {
            newUser.setFullName(new FullName(fullNameTextField.getText()));
            fullNameTextField.setText("");
        }
        else if(e.getSource().equals(cityButton))
        {
            newUser.setCity(cities[cityComboBox.getSelectedIndex()]);
            fullNameTextField.setText("");
        }
        
        USERS.add(newUser);
        ACCOUNTS.add(user.getAccount());
        Write.updateUsersInTheFile(usersAdress, USERS);
        Write.updateAccountsInTheFile(accountAdress, ACCOUNTS);
        user = newUser;
    }
}

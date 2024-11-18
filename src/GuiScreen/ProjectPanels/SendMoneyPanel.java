package GuiScreen.ProjectPanels;

import Input_output.Read;
import Input_output.Write;
import User.User;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SendMoneyPanel extends JPanel implements ActionListener
{
    private User user;
    private String usersAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\Users.txt";
    private ArrayList<User> USERS;
    private JButton sendButton = new JButton("Send");
    private JLabel theIdForThePersonWhoWillReseveTheMoneyLabel = new JLabel("Enter the Id of The Person Who you Will send to :");
    private JLabel TheAmountOfSentMoneyLabel = new JLabel("Enter the Amount if Money that you will send :");
    private JTextField theIdForThePersonWhoWillReseveTheMoneyTextField = new JTextField("");
    private JTextField TheAmountOfSentMoneyTextField = new JTextField("");
    
    public SendMoneyPanel(User user)
    {
        USERS = Read.readUsersFromFile(usersAdress);
        this.user = User.getUser(USERS, user.getID());
        
        setLayout(null);
        setPreferredSize(new Dimension(600,500));
        
        theIdForThePersonWhoWillReseveTheMoneyLabel.setBounds(10, 100, 300, 20);
        theIdForThePersonWhoWillReseveTheMoneyTextField.setBounds(10, 125, 120, 20);
        TheAmountOfSentMoneyLabel.setBounds(10, 180, 300, 20);
        TheAmountOfSentMoneyTextField.setBounds(10, 205, 120, 20);
        sendButton.setBounds(10, 260, 120, 100);
        
        sendButton.addActionListener(this);
        
        add(theIdForThePersonWhoWillReseveTheMoneyLabel);
        add(theIdForThePersonWhoWillReseveTheMoneyTextField);
        add(TheAmountOfSentMoneyLabel);
        add(TheAmountOfSentMoneyTextField);
        add(sendButton);
        
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        String Title = "Send Money Page";
        g.setFont(new Font("Serif",Font.BOLD, 50));
        g.drawString(Title, 10, 50);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            USERS = Read.readUsersFromFile(usersAdress);
            this.user = User.getUser(USERS, user.getID());
        
            String Id = theIdForThePersonWhoWillReseveTheMoneyTextField.getText();
            int MoneyAmount = Integer.valueOf(TheAmountOfSentMoneyTextField.getText());
            
            if(Id.equals(user.getID()))
            {
                JOptionPane.showMessageDialog(this, "You Can't Send to yourself !!");
                theIdForThePersonWhoWillReseveTheMoneyTextField.setText("");
                return;
            }
                     
            if(MoneyAmount > user.getBankBalance())
            {
                JOptionPane.showMessageDialog(this, "You Don't Have all The money that you will send !!");
                TheAmountOfSentMoneyTextField.setText("");

                return;
            }
            
            User theReceivingUser = User.getUser(USERS, Id);
        
            USERS.remove(theReceivingUser);
            USERS.remove(user);

            user.setBankBalance(user.getBankBalance() - MoneyAmount);
            theReceivingUser.setBankBalance(theReceivingUser.getBankBalance() + MoneyAmount);
        
            USERS.add(user);
            USERS.add(theReceivingUser);
            
            Write.updateUsersInTheFile(usersAdress, USERS);
            
            ClearThePanel();
        }
        catch(Exception p)
        {
            JOptionPane.showMessageDialog(this, p.toString());
        }
    }
    
    private void ClearThePanel()
    {
        TheAmountOfSentMoneyTextField.setText("");
        theIdForThePersonWhoWillReseveTheMoneyTextField.setText("");
    }
}

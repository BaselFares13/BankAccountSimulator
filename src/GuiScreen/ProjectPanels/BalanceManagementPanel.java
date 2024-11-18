package GuiScreen.ProjectPanels;

import Input_output.Read;
import Input_output.Write;
import User.Account;
import User.User;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BalanceManagementPanel extends JPanel implements ActionListener
{
    private User user;
    private String usersAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\Users.txt";
    private ArrayList<User> USERS;
    private JButton addMoney = new JButton("Add Money");
    private JButton withdrawMoney = new JButton("Withdraw Money");
    
    public BalanceManagementPanel(User user)
    {
        this.user = user;
        setLayout(null);
        setPreferredSize(new Dimension(600,500));

        
        addMoney.setBounds(30, 100, 150, 150);
        withdrawMoney.setBounds(30, 300, 150, 150);
        
        addMoney.addActionListener(this);
        withdrawMoney.addActionListener(this);

        
        add(addMoney);
        add(withdrawMoney);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        String Title = "Balance Management";
        g.setFont(new Font("Serif",Font.BOLD, 50));
        g.drawString(Title, 10, 50);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        USERS = Read.readUsersFromFile(usersAdress);
        this.user = User.getUser(USERS, user.getID());
        USERS.remove(user);
        User newUser = user;
        
        try
        {
            int amountOfMoney = Integer.valueOf(JOptionPane.showInputDialog(this,"Enter The amount of money"));
               
            if(e.getSource().equals(addMoney))
            {
            
                if(amountOfMoney < 0)
                {
                    JOptionPane.showMessageDialog(this, "You Entered Invlid Value");
                    return;
                }
            
                newUser.setBankBalance(newUser.getBankBalance() + amountOfMoney);

            }
            else if(e.getSource().equals(withdrawMoney))
            {
            
                if(amountOfMoney < 0 || newUser.getBankBalance() < amountOfMoney)
                {
                    JOptionPane.showMessageDialog(this, "You Entered Invlid Value");
                    return;
                }
            
                newUser.setBankBalance(newUser.getBankBalance() - amountOfMoney); 
            }
        }
        catch(Exception q)
        {
            JOptionPane.showMessageDialog(this, q.toString());
            return;
        }
            
            USERS.add(newUser);
            Write.updateUsersInTheFile(usersAdress, USERS);
            user = newUser;
    }
   
}

package GuiScreen.ProjectFrames;

import GuiScreen.ProjectPanels.BalanceManagementPanel;
import GuiScreen.ProjectPanels.EditPanel;
import GuiScreen.ProjectPanels.InformationPanel;
import GuiScreen.ProjectPanels.MassagePanel;
import GuiScreen.ProjectPanels.SendMoneyPanel;
import Input_output.Read;
import User.Account;
import User.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserFrame extends JFrame implements ActionListener
{
    private String usersAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\Users.txt";
    private ArrayList<User> USERS;
    private User user;
    private InformationPanel infoPanel;
    private EditPanel editPa;
    private SendMoneyPanel seMoPa;
    private BalanceManagementPanel BalManPa;
    private MassagePanel massPa;
    private JPanel ButtonPanel = new JPanel();
    private JButton viewInformation = new JButton("View Information");
    private JButton editInformation = new JButton("Edit Information");
    private JButton balanceManagement = new JButton("Balance Management");
    private JButton sendMoney = new JButton("Send Money");
    private JButton sendMassage = new JButton("Send Massage");
    private JButton logOut = new JButton("Log Out");
    private JButton Exist = new JButton("Exit");
    
    public UserFrame(User user)
    {
        setTitle("User Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,450);
        setVisible(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        USERS = Read.readUsersFromFile(usersAdress);
        this.user = User.getUser(USERS,user.getID());
        infoPanel = new InformationPanel(user);
        editPa = new EditPanel(user);
        BalManPa = new BalanceManagementPanel(user);
        seMoPa = new SendMoneyPanel(user);
        massPa = new MassagePanel(user);
        
        ButtonPanel.setLayout(new GridLayout(7,1));
        ButtonPanel.setPreferredSize(new Dimension(175,500));
        ButtonPanel.add(viewInformation);
        ButtonPanel.add(editInformation);
        ButtonPanel.add(balanceManagement);
        ButtonPanel.add(sendMoney);
        ButtonPanel.add(sendMassage);
        ButtonPanel.add(logOut);
        ButtonPanel.add(Exist);
        
        viewInformation.addActionListener(this);
        editInformation.addActionListener(this);
        balanceManagement.addActionListener(this);
        sendMoney.addActionListener(this);
        sendMassage.addActionListener(this);
        Exist.addActionListener(this);
        logOut.addActionListener(this);
        
        add(infoPanel,BorderLayout.CENTER);
        add(ButtonPanel, BorderLayout.WEST);
        
        pack();
        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        USERS = Read.readUsersFromFile(usersAdress);
        this.user = User.getUser(USERS,user.getID());

                    
        if(e.getSource().equals(viewInformation))
        {            
            editPa.removeAll();
            BalManPa.removeAll();
            seMoPa.removeAll();
            massPa.removeAll();
            
            infoPanel = new InformationPanel(user);
            add(infoPanel,BorderLayout.CENTER);

        }
        else if(e.getSource().equals(editInformation))
        {
            BalManPa.removeAll();
            seMoPa.removeAll();
            massPa.removeAll();
            
            editPa = new EditPanel(user);
            add(editPa,BorderLayout.CENTER); 
        }
        else if(e.getSource().equals(balanceManagement))
        {
            editPa.removeAll();
            seMoPa.removeAll();
            massPa.removeAll();
            
            BalManPa = new BalanceManagementPanel(user);
            add(BalManPa,BorderLayout.CENTER);
        }
        else if(e.getSource().equals(sendMoney))
        {
            BalManPa.removeAll();
            editPa.removeAll();
            massPa.removeAll();
            
            seMoPa = new SendMoneyPanel(user);
            add(seMoPa, BorderLayout.CENTER);
        }
        else if(e.getSource().equals(sendMassage))
        {
            seMoPa.removeAll();
            BalManPa.removeAll();
            editPa.removeAll();
            
            massPa = new MassagePanel(user);
            add(massPa,BorderLayout.CENTER);
        }
        else if(e.getSource().equals(Exist))
        {
            System.exit(0);
            return;
        }
        else if(e.getSource().equals(logOut))
        {
            LogInFrame loFr = new LogInFrame();
            dispose();
            return;
        }
        
        pack();
    }
}

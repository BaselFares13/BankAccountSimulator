package GuiScreen.ProjectPanels;

import Input_output.Read;
import Input_output.Write;
import User.Massage;
import User.User;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;


public class MassagePanel extends JPanel implements ActionListener
{
    private User user;
    private String usersAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\Users.txt";
    private String massagesAdress = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BankManagementSystem\\Massages.txt";
    private ArrayList<User> USERS;
    private ArrayList<Massage> MASSAGES;
    private JButton sendButton = new JButton("Send");
    private JButton ShowButton = new JButton("Show");
    private JButton cleanButton = new JButton("Clean");
    private JLabel massageToLabel = new JLabel("The Massage to ");
    private JTextField massageToTextField = new JTextField("");
    private JTextArea massageTextArea = new JTextArea("");
    private JList<String> receivedMassageList = new JList();
    
    public MassagePanel(User user)
    {
        USERS = Read.readUsersFromFile(usersAdress);
        this.user = User.getUser(USERS, user.getID());
        MASSAGES = Read.readMassagesFromFile(massagesAdress);
        
        setLayout(null);
        setPreferredSize(new Dimension(600,500));
        
        massageToLabel.setBounds(10, 100, 100, 20);
        massageToTextField.setBounds(110, 100, 150, 20);
        massageTextArea.setBounds(10, 150, 300, 280);
        receivedMassageList.setBounds(350, 150, 150, 280);
        sendButton.setBounds(10, 450, 100, 20);
        cleanButton.setBounds(130, 450, 100, 20);
        ShowButton.setBounds(375, 450, 100, 20);
        
        massageTextArea.setBorder(new TitledBorder("Massage Body"));
        receivedMassageList.setBorder(new TitledBorder("Received Massages list"));
        String[] listData = getTheSentMassageUsernames(this.user);
        receivedMassageList.setListData(listData);
 
        sendButton.addActionListener(this);
        ShowButton.addActionListener(this);
        cleanButton.addActionListener(this);
        
        add(massageToLabel);
        add(massageToTextField);
        add(massageTextArea);
        add(receivedMassageList);
        add(sendButton);
        add(cleanButton);
        add(ShowButton); 
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        String Title = "Send Massages Page";
        g.setFont(new Font("Serif",Font.BOLD, 50));
        g.drawString(Title, 10, 50);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            USERS = Read.readUsersFromFile(usersAdress);
            MASSAGES = Read.readMassagesFromFile(massagesAdress);
            this.user = User.getUser(USERS, user.getID());
            this.user.setMassage(MASSAGES);
           
            
            if(e.getSource().equals(ShowButton))
            {
                String bodyMassage = user.getMassage().get(receivedMassageList.getSelectedIndex()).getMassageBody();
                bodyMassage = bodyMassage.replaceAll("null", "");
                massageTextArea.setText(bodyMassage);
            }
            else if(e.getSource().equals(sendButton))
            {
                String to = massageToTextField.getText();
                String massage = massageTextArea.getText();
                
                Massage m = new Massage(user.getAccount().getUsername(), to, massage);
                
                MASSAGES.add(m);           
                
                massageTextArea.setText("");
                massageToTextField.setText("");
            }
            else if(e.getSource().equals(cleanButton))
            {
                massageTextArea.setText("");
                massageToTextField.setText("");
            }
                
            Write.updateMassagesInTheFile(massagesAdress, MASSAGES);
        }
        catch(Exception p)
        {
            JOptionPane.showMessageDialog(this, p.toString());
        }
    }
    
    public String[] getTheSentMassageUsernames(User user)
    {
        user.setMassage(MASSAGES);
        ArrayList<Massage> ownMass = user.getMassage();
        ArrayList<String> ownMas = new ArrayList<>();
        
        for(Massage m : ownMass)
        {
            if(m.getTo().equals(user.getAccount().getUsername()))
            {
                ownMas.add(m.getFrom());
            }
        }
        
        String[] arr = new String[ownMas.size()];
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = ownMas.get(i);
        }
        
        return arr;
    }
}
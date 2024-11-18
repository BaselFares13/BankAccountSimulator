package GuiScreen.ProjectPanels;

import User.User;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class InformationPanel extends JPanel
{
    private User user;
    private boolean shouldDraw = true;
    
    public InformationPanel(User user)
    {
        this.user = user;
        setPreferredSize(new Dimension(600,500));

    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if(shouldDraw)
        {
            drawTheInformation(g);
        }
    }
    
    public void clear() 
    {
        shouldDraw = false;
        repaint();
    }
    
    private void drawTheInformation(Graphics g)
    {
        String username = "Username: " + user.getAccount().getUsername();
        String password = "Password: " + user.getAccount().getPassword();
        String creationDate = "Account Creation Date: " + user.getAccount().getCreationDate().toString();
        String Id = "ID: " + user.getID();
        String city = "The City you live in: " + user.getCity();
        String bankBalance = "Your Balance: " + String.valueOf(user.getBankBalance()) + " $";
        String fullName = "Your Full Name: " + user.getFullName().toString();
        String salutation = "Hello " + user.getFullName().getFirstName();
        
        g.setFont(new Font("Serif",Font.BOLD, 50));
        g.drawString(salutation, 10, 50);
        g.setFont(new Font("Serif",Font.BOLD, 25));
        g.drawString(fullName, 10, 100);
        g.drawString(username, 10, 160);
        g.drawString(password, 10, 220);
        g.drawString(bankBalance, 10, 280);
        g.drawString(Id, 10, 340);
        g.drawString(creationDate, 10, 400);
        g.drawString(city, 10, 460);
    }
}

package Input_output;

import User.Account;
import User.Massage;
import User.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Write 
{
    private static PrintWriter Pr;
    private static File file;
    
    public static void updateUsersInTheFile(String fileAdress, ArrayList<User> users)
    {   
        try 
        {
            file = new File(fileAdress);
            Pr = new PrintWriter(file);
            
            for(User us : users)
            {
                String line = us.getAccount().getUsername() + "-" + us.getAccount().getPassword() + "-" + us.getAccount().getCreationDate() 
                        + "-" + us.getID() + "-" + us.getBankBalance() + "-" + us.getFullName() + "-" + us.getAge() + "-" + us.getCity();
                
                Pr.write(line);
                Pr.println();
            }
            
            Pr.close();
            
        }catch(FileNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        
    }
    
    public static void updateMassagesInTheFile(String fileAdress, ArrayList<Massage> massages)
    {   
        try 
        {
            file = new File(fileAdress);
            Pr = new PrintWriter(file);
            
            for(Massage m : massages)
            {
                String line = m.getFrom()+ ">" + m.getTo() + "-" + m.getMassageWithoutNewLines();
                line = line.replaceAll("null", "");
                Pr.write(line);
                Pr.println();
            }
            
            Pr.close();
            
        }catch(FileNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        
    }
    
    public static void updateAccountsInTheFile(String fileAdress, ArrayList<Account> accounts)
    {
        try
        {
            file = new File(fileAdress);
            Pr = new PrintWriter(file);
            
            for(Account ac : accounts)
            {
                String line = ac.getUsername() + "-" + ac.getPassword() + "-" + ac.getCreationDate();
                
                Pr.write(line);
                Pr.println();
            }
            Pr.close();
        }
        catch(FileNotFoundException ex)
        {
           ex.printStackTrace();
        }
    }
}

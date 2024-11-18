package Input_output;

import User.Account;
import User.Date;
import User.FullName;
import User.Massage;
import User.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Read 
{
    private static Scanner input;
    
    public static ArrayList<Account> readAccountsFromFile(String fileAdress)
    {
        ArrayList<Account> accounts = new ArrayList<>();
        
        try 
        {
            File accountsFile = new File(fileAdress);
            input = new Scanner(accountsFile);
            
            while(input.hasNext())
            {
                String str = input.nextLine();
                String []account = str.split("-");
                accounts.add(new Account(account[0], account[1], new Date(account[2])));
            }
            input.close();
        }
        catch(FileNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        
        return accounts;
    }
    
    public static Massage readTheMassageFromTheUsername(String username, ArrayList<Massage> massages)
    {
        for(Massage m : massages)
        {
            if(m.getFrom().equals(username))
            {
                return m;
            }
        }
        
        return null;
    }
    
    public static ArrayList<Massage> readMassagesFromFile(String fileAdress)
    {
        ArrayList<Massage> massages = new ArrayList<>();
        
        try 
        {
            File massagesFile = new File(fileAdress);
            input = new Scanner(massagesFile);
            
            while(input.hasNext())
            {
                String str = input.nextLine();
                String []massage = str.split("-");
                massages.add(new Massage(massage[0], massage[1]));
            }
            input.close();
        }
        catch(FileNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        
        return massages;
    }
    
    public static ArrayList<User> readUsersFromFile(String fileAdress)
    {
        ArrayList<User> users = new ArrayList<>();
        
        try
        {
            File usersFile = new File(fileAdress);
            input = new Scanner(usersFile);
            
            while(input.hasNext())
            {
                String str = input.nextLine();
                String []user = str.split("-");
                users.add(new User(new Account(user[0], user[1], new Date(user[2])), user[3], Integer.valueOf(user[4]), new FullName(user[5]), Integer.valueOf(user[6]), user[7]));
            }
            input.close();
        }
        catch(FileNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        
        return users;
    }
}

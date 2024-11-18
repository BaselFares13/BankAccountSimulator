package User;

import java.util.ArrayList;
import java.util.Objects;

public class User extends Person
{
    private Account account;
    private String ID;
    private ArrayList<Massage> massage;
    private int bankBalance;

    public User() 
    {
        super();
        account = null;
        ID = null;
        massage = new ArrayList<>();
        bankBalance = 0;
    }
    
    public User(Account account, String ID, FullName fullName, int age, String city) {
        super(fullName, age, city);
        this.account = account;
        this.ID = ID;
        bankBalance = 0;
        massage = new ArrayList<>();    
    }
    
    public User(Account account, String ID, FullName fullName, int age, String city, ArrayList<Massage> massage) {
        super(fullName, age, city);
        this.account = account;
        this.ID = ID;
        bankBalance = 0;
        this.massage = massage;
    }

    public User(Account account, String ID, int bankBalance, FullName fullName, int age, String city) {
        super(fullName, age, city);
        this.account = account;
        this.ID = ID;
        this.bankBalance = bankBalance;
        massage = new ArrayList<>();    
    }    
    
    public static User getUser(ArrayList<User> users, Account acc)
    {
        for(User us : users)
        {
            if(us.getAccount().equals(acc))
            {
                return us;
            }
        }
        
        return null;
    }
    
    public void setMassage(ArrayList<Massage> allMassages)
    {      
        for(Massage m : allMassages)
        {
            if(m.getTo().equals(account.getUsername()))
            {
                massage.add(m);
            }
        }
    }
    
    public static User getUser(ArrayList<User> users, String Id)
    {
        for(User us : users)
        {
            if(us.getID().equals(Id))
            {
                return us;
            }
        }
        
        return null;
    }

    public ArrayList<Massage> getMassage() {
        return massage;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Account getAccount() {
        return account;
    }

    public String getID() {
        return ID;
    }

    public int getBankBalance() {
        return bankBalance;
    }
    
    @Override
    public String toString() {
        return  super.toString() + "\n" + account + "\nID : " + ID + "\nBank Balance : " + bankBalance;
    }

    @Override
    public boolean equals(Object obj) 
    {
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if(!super.equals(obj))
        {
            return false;
        }
        if (this.ID != other.ID) {
            return false;
        }
        if (this.bankBalance != other.bankBalance) {
            return false;
        }
        return Objects.equals(this.account, other.account);
    } 
}
